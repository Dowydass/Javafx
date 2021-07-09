package sample.JPA;

import org.hibernate.HibernateException;
import org.hibernate.exception.JDBCConnectionException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class CategoryParametersDAO {

    //Paduodamas Categories objektas iš jo ištraukiamas category_parameter_id ir grąžinamas CategoryParameters objektas su kategorijų boolean'ais
    //Pastaba: Grąžinamas objektas gali būti null, jeigu nebus rastas atitikmuo DB
    public static CategoryParameters getParametersByCategoryParameterId(int category_parameter_id) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        CategoryParameters categoryParameters = null;

        try {
            TypedQuery<CategoryParameters> query = entityManager.createQuery("Select e From CategoryParameters e WHERE e.id = ?1", CategoryParameters.class);
            categoryParameters = query.setParameter(1, category_parameter_id).getSingleResult();

            entityManager.getTransaction().commit();
            entityManager.close();

        } catch (JDBCConnectionException e) {
            System.out.println("CategoryParametersDAO.getParametersByCategoryId() JDBCConnectionException");
        } catch (HibernateException e) {
            System.out.println("CategoryParametersDAO.getParametersByCategoryId() HibernateException");
        }
        return categoryParameters;
    }

    public static void createNewCategoryParametersField(CategoryParameters categoryParameters) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        try {
            entityManager.persist(categoryParameters);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (JDBCConnectionException e) {
            System.out.println("CategoryParametersDAO.createNewCategoryParametersField() JDBCConnectionException");
        } catch (HibernateException e) {
            System.out.println("CategoryParametersDAO.createNewCategoryParametersField() HibernateException");
        }
    }

}
