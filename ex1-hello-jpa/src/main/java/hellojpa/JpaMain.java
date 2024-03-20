package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            final Member member1 = new Member();
            member1.setUsername("A");

            final Member member2 = new Member();
            member1.setUsername("B");

            final Member member3 = new Member();
            member1.setUsername("C");

            final Member member4 = new Member();
            member1.setUsername("D");

            final Member member5 = new Member();
            member1.setUsername("E");

            System.out.println("==================");

            em.persist(member1);
            em.persist(member2);
            em.persist(member3);
            em.persist(member4);
            em.persist(member5);

            System.out.println("member1.id = " + member1.getId());
            System.out.println("member2.id = " + member2.getId());
            System.out.println("member3.id = " + member3.getId());
            System.out.println("member4.id = " + member4.getId());
            System.out.println("member5.id = " + member5.getId());

            System.out.println("==================");

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
