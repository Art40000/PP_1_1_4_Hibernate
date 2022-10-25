package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private static final String TABLE_NAME = "User";

    @Override
    public void createUsersTable() {

        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" + "id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT, "
                + "name VARCHAR(50), " + "lastname VARCHAR(50), " + "age  TINYINT)";

        try(Session session = Util.getSessionFactory().openSession()) {

            session.getTransaction().begin();
            session.createNativeQuery(sql).executeUpdate();

        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("The table hasn't been created");
        }

    }

    @Override
    public void dropUsersTable() {

        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;

        try(Session session = Util.getSessionFactory().openSession()) {

            session.getTransaction().begin();
            session.createNativeQuery(sql).executeUpdate();

        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("The table hasn't been dropped");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try(Session session = Util.getSessionFactory().openSession()) {

            session.getTransaction().begin();
            User user = new User(name, lastName, age);
            session.save(user);
            session.getTransaction().commit();
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("table doesn't save user");
        }
    }

    @Override
    public void removeUserById(long id) {
        try(Session session = Util.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            User user = new User();
            user.setId(id);
            session.delete(user);
            session.getTransaction().commit();
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("table doesn't remove user");
        }
    }
    @Override
    public List<User> getAllUsers() {

        List<User> res = new LinkedList<>();

        try(Session session = Util.getSessionFactory().openSession()) {
            session.getTransaction().begin();
            Query query = session.createQuery("from " + TABLE_NAME);
            res = query.list();
            session.getTransaction().commit();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public void cleanUsersTable() {
        String sql = "TRUNCATE TABLE " + TABLE_NAME;

        try(Session session = Util.getSessionFactory().openSession()) {

            session.getTransaction().begin();
            session.createNativeQuery(sql).executeUpdate();

        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("The table hasn't been dropped");
        }
    }
}
