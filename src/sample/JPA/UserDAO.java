package sample.JPA;

import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.service.spi.ServiceException;

import javax.persistence.*;
import java.util.List;

public class UserDAO {
    public static void insert(User user) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        entityManager.persist(user);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static User searchUserByEmail(String email) {
        try {


            EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();

            Query query = entityManager.createQuery(
                    "SELECT a FROM User a WHERE a.email = :email2")
                    .setParameter("email2", email);


            User user = (User) query.getSingleResult();
            entityManager.getTransaction().commit();
            entityManager.close();

            return user;

        } catch (NoResultException nre) {

            return null;
        }

    }


    public static boolean compareEmailForValidation(String email) {
        try {
            EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();


            Query query = entityManager.createQuery(
                    "SELECT a FROM User a WHERE a.email = :email2")
                    .setParameter("email2", email);

            query.setMaxResults(1);
            User user = (User) query.getSingleResult();

            user.getEmail();


            entityManager.getTransaction().commit();
            entityManager.close();
            return true;

        } catch (NoResultException nre) {
            return false;
        }
    }

    public static List<User> getAllUsers() {

        EntityManager entityManager;
        EntityTransaction entityTransaction;
        List<User> userList = null;
        TypedQuery<User> query;

        try {
            entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            query = entityManager.createQuery("Select e From User e", User.class);
            userList = query.getResultList();

            entityManager.getTransaction().commit();
            entityManager.close();

        } catch (NullPointerException e ) {
            System.out.println("ProductCatalogDAO.displayAllItems() NullPointerExecption");
        }
        catch (RuntimeException e) {
            System.out.println("ProductCatalogDAO.displayAllItems() IllegalStateException");
        }

        return userList;
    }

    public static void updateUserTimeSpent(int timeSpent) {

        EntityManager entityManager;
        EntityTransaction entityTransaction;
        User user1;

        try {
            entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            user1 = entityManager.find(User.class, timeSpent);
            user1.setTimeSpend(timeSpent);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (IllegalStateException e) {
            System.out.println("ProductCaalogDAO.updateByCatalog_no IllegalStateException");
        } catch (JDBCConnectionException e) {
            System.out.println("ProductCaalogDAO.updateByCatalog_no JDBCConnectionException");
        } catch (ServiceException e) {
            System.out.println("ProductCaalogDAO.updateByCatalog_no ServiceException");
        } catch (PersistenceException e) {
            System.out.println("ProductCaalogDAO.updateByCatalog_no PersistenceException");
        }
    }

}
