package es.cheste;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class App {

    public static void main(String[] args) {

        StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sf = new MetadataSources(ssr).buildMetadata().buildSessionFactory();

        // Apertura de la sesión
        Session s = sf.openSession();

        // Transacciones

        // Cierre de la sesión
        s.close();
        sf.close();
    }
}
