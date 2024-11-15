package es.cheste;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class App {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("HibernateJPA");
        EntityManager em = emf.createEntityManager();

        // Transacciones
        User u = new User();
        u.setId(1);
        u.setUserName("Admin");
        u.setUserMessage("Welcome to administrator session.");

        User u2 = new User();
        u.setId(2);
        u.setUserName("Jacinto");
        u.setUserMessage("Bienvenido, Jacinto (JPA)!");

        em.getTransaction().begin();
        em.persist(u);
        em.persist(u2);

        em.getTransaction().commit();

        em.close();
        emf.close();
    }
}
