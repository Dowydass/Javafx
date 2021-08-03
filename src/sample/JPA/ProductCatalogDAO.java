package sample.JPA;


import org.hibernate.HibernateException;
import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.service.spi.ServiceException;
import sample.utils.Constants;

import javax.persistence.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static sample.JPA.JPAUtil.getScene;

public class ProductCatalogDAO {


    public static void insert(ProductCatalog productCatalog) {
        EntityManager entityManager;
        EntityTransaction entityTransaction;

        try {
            entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(productCatalog);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (JDBCConnectionException e) {
            System.out.println("ProductCatalogDAO.insert() JDBCConnectionException");
        } catch (HibernateException e) {
            System.out.println("ProductCatalogDAO.insert() HibernateException");
        }



    }

    public static List<ProductCatalog> displayAllItems()   {

        EntityManager entityManager;
        EntityTransaction entityTransaction;
        List<ProductCatalog> productCatalog = null;
        TypedQuery<ProductCatalog> query;

        try {
            entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            query = entityManager.createQuery("Select e From ProductCatalog e", ProductCatalog.class);
            productCatalog = query.getResultList();

            entityManager.getTransaction().commit();
            entityManager.close();

        } catch (NullPointerException e ) {
            System.out.println("ProductCatalogDAO.displayAllItems() NullPointerExecption");
        }
        catch (RuntimeException e) {
            System.out.println("ProductCatalogDAO.displayAllItems() IllegalStateException");
        }

        return productCatalog;
    }

    public static List<ProductCatalog> searchByName(String name) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<ProductCatalog> query = entityManager.createQuery("Select e From ProductCatalog e WHERE e.name = ?1", ProductCatalog.class);
        List<ProductCatalog> productCatalog = query.setParameter(1, name).getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return productCatalog;
    }


    public static void updatePrice(double price, int id) {

        EntityManager entityManager;
        EntityTransaction entityTransaction;
        ProductCatalog productCatalog1;

        try {
            entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            productCatalog1 = entityManager.find(ProductCatalog.class, id);
            productCatalog1.setPriceNet(price);
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

    public static void updateCatalog_no(String catalog_no, int id) {

        EntityManager entityManager;
        EntityTransaction entityTransaction;
        ProductCatalog productCatalog1;

        try {
            entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            productCatalog1 = entityManager.find(ProductCatalog.class, id);
            productCatalog1.setCatalogNo(catalog_no);
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

    public static void updateStock(int stock, int id) {

        EntityManager entityManager;
        EntityTransaction entityTransaction;
        ProductCatalog productCatalog1;

        try {
            entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            productCatalog1 = entityManager.find(ProductCatalog.class, id);
            productCatalog1.setStock(stock);
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

    public static void updateSymbol(String symbol, int id) {

        EntityManager entityManager;
        EntityTransaction entityTransaction;
        ProductCatalog productCatalog1;

        try {
            entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            productCatalog1 = entityManager.find(ProductCatalog.class, id);
            productCatalog1.setSymbol(symbol);
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



    public static List<ProductCatalog> searchByTreeItemName(String treeItemSearchName) {
        treeItemSearchName = "%" + treeItemSearchName + "%";
        System.out.println(treeItemSearchName);

        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        TypedQuery<Categories> query = entityManager.createQuery("SELECT e FROM Categories e WHERE e.name LIKE ?1", Categories.class);
        List<Categories> category = query.setParameter(1, treeItemSearchName).getResultList();

        List<ProductCatalog> downloadedProductCatalog = ProductCatalogDAO.displayAllItems();

        List<ProductCatalog> iteratedProductCatalog = new ArrayList<>();

        for (Categories category2 : category) {
            for (ProductCatalog product : downloadedProductCatalog) {
                if (product.getGroupId() == category2.getId()) {
                    iteratedProductCatalog.add(product);
                }
            }
        }

        entityManager.close();


        return iteratedProductCatalog;
    }

    public static void checkIfCatalogExistsIfNotCreateIt() {
        String checkQuery = "SHOW TABLES FROM ecosprendi_kitm LIKE 'product_catalog'";
        String createTableQuery = "CREATE TABLE IF NOT EXISTS `product_catalog` (`id` INT(11) PRIMARY KEY NOT NULL AUTO_INCREMENT,`catalog_no` INT(11) DEFAULT NULL,`date` DATE DEFAULT NULL,`group_id` INT(11) DEFAULT NULL,`price_net` VARCHAR(30) DEFAULT NULL,`stock` INT(11) DEFAULT NULL,`symbol` VARCHAR(255) DEFAULT NULL)";
        Statement stmt;
        System.out.println("checkIfCatalogExistsIfNotCreateIt method initiated...");
        try {
            Connection conn = DriverManager.getConnection(Constants.DB_URL, Constants.USER, Constants.PASS);
            stmt = conn.createStatement();
            int rsBool = stmt.executeUpdate(checkQuery);
            if (rsBool == 0) {
                System.out.println("product_catalog table has not been found, creating a new one...");
                stmt.execute(createTableQuery);
            }
            conn.close();
            stmt.close();
        } catch (SQLException throwables) {
            System.out.println("checkIfCatalogExistsIfNotCreateIt() SQLExecption");
        } catch (NullPointerException e) {
            System.out.println("checkIfCatalogExistsIfNotCreateIt NullPointerException");
        } catch (RuntimeException e) {
            System.out.println("checkIfCatalogExistsIfNotCreateIt RuntimeExeception");
        }
    }
}