package es.cheste;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class App {

    public static void main(String[] args) {

        // Código legacy
        // SessionFactory sf = new Configuration().configure().buildSessionFactory();

        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sf = new MetadataSources(ssr).buildMetadata().buildSessionFactory();

        // Apertura de la sesión
        Session s = sf.openSession();
        s.clear();

        // Transacciones
        User u = new User();
        u.setId(1);
        u.setUserName("Admin");
        u.setUserMessage("Welcome to administrator session.");

        s.getTransaction().begin();
        s.merge(u);

        s.getTransaction().commit();

        s.close();
        sf.close();
    }
}
