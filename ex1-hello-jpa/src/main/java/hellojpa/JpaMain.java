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
            final Member findMember1 = em.find(Member.class, 100L); // SELECT
            findMember1.setName("100L Member");

            em.clear();

            final Member findMember2 = em.find(Member.class, 100L); // SELECT => 다시 엔티티 등록

            System.out.println("===============");
            tx.commit(); // UPDATE 쿼리가 나감
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
