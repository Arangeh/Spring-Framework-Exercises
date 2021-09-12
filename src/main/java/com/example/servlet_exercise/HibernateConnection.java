package com.example.servlet_exercise;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateConnection {

    private static HibernateConnection me;
    private Configuration cfg;
    private SessionFactory sessionFactory;

    private HibernateConnection() throws HibernateException {

// build the config
        cfg = new Configuration();

/**
 * Connection Information..
 */
        cfg.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        cfg.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/internship");
        cfg.setProperty("hibernate.connection.username", "root");
        cfg.setProperty("hibernate.connection.password", "");
        cfg.setProperty("hibernate.show_sql", "true");
        //The following line creates the corresponding table in relational database
        System.out.println("Create relational database automatically");
        cfg.setProperty("hibernate.hbm2ddl.auto", "update");

/**
 * Mapping Resources..
 */
        cfg.addResource("User.hbm.xml");

        sessionFactory = cfg.buildSessionFactory();
    }

    public static synchronized HibernateConnection getInstance() throws HibernateException {
        if (me == null) {
            me = new HibernateConnection();
        }

        return me;
    }

    public Session getSession() throws HibernateException {
        Session session = sessionFactory.openSession();
        if (!session.isConnected()) {
            this.reconnect();
        }
        return session;
    }

    private void reconnect() throws HibernateException {
        this.sessionFactory = cfg.buildSessionFactory();
    }
}
