package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jdk.swing.interop.SwingInterOpUtils;

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
            member.setTeam(team);
            em.persist(member);

            em.flush();
            em.clear();

            System.out.println("=========================");
            final Member findMember = em.find(Member.class, member.getId());
            System.out.println("=========================");
            final List<Member> members = findMember.getTeam().getMembers();
            System.out.println("=========================");

            for (Member m : members) {
                System.out.println("m.getName() = " + m.getName());
            }
            System.out.println("=========================");

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
