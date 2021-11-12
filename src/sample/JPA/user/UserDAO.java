package sample.JPA.user;

import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.service.spi.ServiceException;
import sample.JPA.JPAUtil;
import sample.JPA.ProductCatalog;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

public class UserDAO {
    public static void insert(User user) {
        try {
            EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();

            entityManager.persist(user);

            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (IllegalStateException e) {
            System.out.println("UserDAO.insert IllegalStateException");
        } catch (JDBCConnectionException e) {
            System.out.println("UserDAO.insert JDBCConnectionException");
        } catch (ServiceException e) {
            System.out.println("UserDAO.insert ServiceException");
        } catch (PersistenceException e) {
            System.out.println("UserDAO.insert PersistenceException");
        }
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

        } catch (JDBCConnectionException e) {
            System.out.println("UserDAO.insert() JDBCConnectionException");
            return false;
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

    public static Timestamp getLastLogin(User holderUser){
        EntityManager entityManager;
        EntityTransaction entityTransaction;
        Timestamp userLastLogin = null;
        try {
            entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
            entityTransaction = entityManager.getTransaction();

            entityTransaction.begin();
            Query query = entityManager.createQuery(
                            "SELECT a FROM User a WHERE a.email = :email2")
                    .setParameter("email2", holderUser.getEmail());
            User downloadedUser = (User) query.getSingleResult();
            userLastLogin = downloadedUser.getLastLogin();

            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (IllegalStateException e) {
            System.out.println("ProductCatalogDAO.updateUserTimeSpent IllegalStateException");
        } catch (JDBCConnectionException e) {
            System.out.println("ProductCatalogDAO.updateUserTimeSpent JDBCConnectionException");
        } catch (ServiceException e) {
            System.out.println("ProductCatalogDAO.updateUserTimeSpent ServiceException");
        } catch (PersistenceException e) {
            System.out.println("ProductCatalogDAO.updateUserTimeSpent PersistenceException");
        }

        return userLastLogin;
    }

    public static void updateUserTimeSpent(User holdedUser, int timeSpent) {

        EntityManager entityManager;
        EntityTransaction entityTransaction;

        try {
            entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
            entityTransaction = entityManager.getTransaction();

            entityTransaction.begin();
            Query query = entityManager.createQuery(
                    "SELECT a FROM User a WHERE a.email = :email2")
                    .setParameter("email2", holdedUser.getEmail());
            User downloadedUser = (User) query.getSingleResult();
            entityManager.getTransaction().commit();

            entityTransaction.begin();
            holdedUser = entityManager.find(User.class, downloadedUser.getId());
            holdedUser.setTimeSpend(timeSpent + downloadedUser.getTimeSpend());
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (IllegalStateException e) {
            System.out.println("ProductCatalogDAO.updateUserTimeSpent IllegalStateException");
        } catch (JDBCConnectionException e) {
            System.out.println("ProductCatalogDAO.updateUserTimeSpent JDBCConnectionException");
        } catch (ServiceException e) {
            System.out.println("ProductCatalogDAO.updateUserTimeSpent ServiceException");
        } catch (PersistenceException e) {
            System.out.println("ProductCatalogDAO.updateUserTimeSpent PersistenceException");
        }
    }

    public static void setLastLoginTime(User holdedUser) {

        EntityManager entityManager;
        EntityTransaction entityTransaction;

        try {
            entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
            entityTransaction = entityManager.getTransaction();

            entityTransaction.begin();
            Query query = entityManager.createQuery(
                    "SELECT a FROM User a WHERE a.email = :email2")
                    .setParameter("email2", holdedUser.getEmail());
            User downloadedUser = (User) query.getSingleResult();
            entityManager.getTransaction().commit();

            entityTransaction.begin();
            holdedUser = entityManager.find(User.class, downloadedUser.getId());
            holdedUser.setLastLogin();
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (IllegalStateException e) {
            System.out.println("ProductCatalogDAO.setLastLoginTime IllegalStateException");
        } catch (JDBCConnectionException e) {
            System.out.println("ProductCatalogDAO.setLastLoginTime JDBCConnectionException");
        } catch (ServiceException e) {
            System.out.println("ProductCatalogDAO.setLastLoginTime ServiceException");
        } catch (PersistenceException e) {
            System.out.println("ProductCatalogDAO.setLastLoginTime PersistenceException");
        }
    }

    public static void updateBlockedStatus(Boolean isBlocked, int id) {

        EntityManager entityManager;
        EntityTransaction entityTransaction;
        User user;

        try {
            entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            user = entityManager.find(User.class, id);
            user.setBlocked(isBlocked);
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (IllegalStateException e) {
            System.out.println("User.updateBlockedStatus IllegalStateException");
        } catch (JDBCConnectionException e) {
            System.out.println("User.updateBlockedStatus JDBCConnectionException");
        } catch (ServiceException e) {
            System.out.println("User.updateBlockedStatus ServiceException");
        } catch (PersistenceException e) {
            System.out.println("User.updateBlockedStatus PersistenceException");
        }
    }

}
