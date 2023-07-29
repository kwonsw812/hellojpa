package hellojpa;

import org.hibernate.Hibernate;
import org.hibernate.jpa.internal.PersistenceUnitUtilImpl;

import javax.persistence.*;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member1 = new Member();
            member1.setUsername("member1");
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            member1.setTeam(team);

            em.persist(member1);

            em.flush();
            em.clear();

            Member m = em.getReference(Member.class, member1.getId());

            System.out.println("m = " + m.getTeam().getClass());

            m.getTeam().getName();

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }


        emf.close();
    }
}
