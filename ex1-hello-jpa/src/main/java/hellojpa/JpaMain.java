package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            final Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            final Member member = new Member();
            member.setName("member1");
            member.changeTeam(team);
            em.persist(member);

            team.getMembers().add(member); // 양방향 연관관계 등록 - 이게 없다고 DB에 등록이 안되진 않음.

//            em.flush();
//            em.clear();

            final Team findTeam = em.find(Team.class, team.getId());
            final List<Member> members = findTeam.getMembers();

            System.out.println("================");
            for (Member m : members) {
                System.out.println("m.getName() = " + m.getName());
            }
            System.out.println("================");

            tx.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
