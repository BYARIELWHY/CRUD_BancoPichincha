package com.banco.crud_bancopichincha;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
public class HibernateUtil {
    private static final SessionFactory SESSION_FACTORY =
            new Configuration().configure().buildSessionFactory();
    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
    public static void shutdown() {
        getSessionFactory().close();
    }
}

