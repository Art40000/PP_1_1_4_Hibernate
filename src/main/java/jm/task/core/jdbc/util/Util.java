package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    //Block for JDBC
    private static final String DB_URL = "jdbc:mysql://localhost/db";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "Asdf1234";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("Connection OK");
        } catch ( SQLException e) {
            e.printStackTrace();
            System.out.println("Connection ERROR");
        }
        return connection;
    }

    //Block for Hibernate

    private static final SessionFactory sessionFactory = BuildSessionFactory();
    private static SessionFactory BuildSessionFactory() {
        try {
            Configuration cfg = new Configuration().addAnnotatedClass(User.class).
                    setProperty("hibernate.connection.url", DB_URL).
                    setProperty("hibernate.connection.username", DB_USERNAME).
                    setProperty("hibernate.connection.password", DB_PASSWORD).
                    setProperty("hibernate.connection.driver_class","com.mysql.cj.jdbc.Driver").
                    setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect").
                    setProperty("hibernate.format_sql", "true").
                    setProperty("show_sql", "true");

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().
                   applySettings(cfg.getProperties()).build();

            return cfg.buildSessionFactory(serviceRegistry);
        }
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
