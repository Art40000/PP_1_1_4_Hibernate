package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static final String TABLE_NAME = "myTable";
    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + "id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT, "
                + "name VARCHAR(50), " + "lastname VARCHAR(50), " + "age  TINYINT)";

        Session session =  Util.getSessionFactory().openSession();
        session.beginTransaction();

        session.createSQLQuery(sql);

        session.getTransaction().commit();
        Util.shutdown();
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;

        Session session =  Util.getSessionFactory().openSession();
        session.beginTransaction();

        session.createSQLQuery(sql);

        session.getTransaction().commit();
        Util.shutdown();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

    }

    @Override
    public void removeUserById(long id) {

    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void cleanUsersTable() {

    }
}
