package sample.JPA.copper_price;

import org.hibernate.HibernateException;
import sample.JPA.JPAUtil;
import sample.JPA.user.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class CopperStockDAO {

    public static void insert(CopperStock stock) {
        EntityManager em;
        EntityTransaction entityTransaction;

        try {
            em = JPAUtil.getEntityManagerFactory().createEntityManager();
            entityTransaction = em.getTransaction();
            entityTransaction.begin();
            em.persist(stock);
            em.getTransaction().commit();
            em.close();
        } catch (HibernateException e) {
            System.out.println(e);
        }
    }


    public static List<CopperStock> getLatestPrice() {
        EntityManager em;
        EntityTransaction entityTransaction;
        List<CopperStock> latestPrice = null;
        try {
            em = JPAUtil.getEntityManagerFactory().createEntityManager();
            entityTransaction = em.getTransaction();
            entityTransaction.begin();

            TypedQuery<CopperStock> query = em.createQuery("SELECT e FROM CopperStock e ORDER BY e.id DESC", CopperStock.class);
            query.setMaxResults(1);
            latestPrice = query.getResultList();

            em.close();
        } catch (HibernateException e) {
            System.out.println(e);
        }

        return latestPrice;
    }

    public static List<CopperStock> getAllPrices() {
        EntityManager em;
        EntityTransaction entityTransaction;
        List<CopperStock> latestPrice = null;
        try {
            em = JPAUtil.getEntityManagerFactory().createEntityManager();
            entityTransaction = em.getTransaction();
            entityTransaction.begin();

            TypedQuery<CopperStock> query = em.createQuery("SELECT e FROM CopperStock e ORDER BY e.id DESC", CopperStock.class);
            latestPrice = query.getResultList();

            em.close();
        } catch (HibernateException e) {
            System.out.println(e);
        }

        return latestPrice;
    }
}
