package sample.JPA;

import org.hibernate.exception.JDBCConnectionException;
import org.hibernate.service.spi.ServiceException;
import sample.utils.Constants;

import javax.persistence.*;
import java.util.List;

import static sample.JPA.JPAUtil.getScene;

public class CategoriesDAO {


    public static void insert(Categories categories) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        entityManager.persist(categories);

        entityManager.getTransaction().commit();
        entityManager.close();
    }


    public static List<Categories> searchByName(String name) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Categories> query = entityManager.createQuery("Select e From Categories e WHERE e.name = ?1", Categories.class);
        List<Categories> categories = query.setParameter(1, name).getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return categories;
    }


    public static void deleteById(int id) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Categories categories = entityManager.find(Categories.class, id);
        entityManager.remove(categories);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void updateById(Categories categories, int id) {
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Categories categories1 = entityManager.find(Categories.class, id);
        categories1.setName(categories.getName());
        categories1.setlft(categories.getlft());
        categories1.setrght(categories.getrght());

        entityManager.getTransaction().commit();
        entityManager.close();
    }


    public static List<Categories> selectCategoryById(int id) {

        EntityManager entityManager;
        EntityTransaction entityTransaction;
        TypedQuery<Categories> query;
        List<Categories> categories = null;

        try {
            entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            query = entityManager.createQuery("SELECT e FROM Categories e" +
                    " JOIN Categories a  ON (e.lft >= a.lft AND e.rght <= a.rght) " +
                    "WHERE a.id = ?1 ORDER BY e.lft", Categories.class);
            categories = query.setParameter(1, id).getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (IllegalStateException e) {
            JPAUtil.showPopupWindow("Nepavyko užmegzti ryšio", "Nepavyko prisijungti prie duomenų bazės\n- Patikrinkite ar turite interneto ryšį. \n- Priešingu atveju kreipkitės: į ECOSprendimai\n- Klaidos kodas:" + e + "\n- Programos versija: " + Constants.PROGRAM_VERSION, "#b02a37", "#FFFFFF", getScene());
        } catch (JDBCConnectionException e) {
            JPAUtil.showPopupWindow("Nepavyko užmegzti ryšio", "Nepavyko prisijungti prie duomenų bazės\n- Patikrinkite ar turite interneto ryšį. \n- Priešingu atveju kreipkitės: į ECOSprendimai\n- Klaidos kodas: " + e + "\n- Programos versija: " + Constants.PROGRAM_VERSION, "#b02a37", "#FFFFFF", getScene());
        } catch (ServiceException e) {
            JPAUtil.showPopupWindow("Nepavyko užmegzti ryšio", "Nepavyko prisijungti prie duomenų bazės\n- Patikrinkite ar turite interneto ryšį. \n- Priešingu atveju kreipkitės: į ECOSprendimai\n- Klaidos kodas: " + e + "\n- Programos versija: " + Constants.PROGRAM_VERSION, "#b02a37", "#FFFFFF", getScene());
        } catch (PersistenceException e) {
            JPAUtil.showPopupWindow("Nepavyko užmegzti ryšio", "Nepavyko prisijungti prie duomenų bazės\n- Patikrinkite ar turite interneto ryšį. \n- Priešingu atveju kreipkitės: į ECOSprendimai\n- Klaidos kodas: " + e + "\n- Programos versija: " + Constants.PROGRAM_VERSION, "#b02a37", "#FFFFFF", getScene());
        }
        return categories;
    }


    public static List<Categories> displayAllCategories(){
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Categories> query = entityManager.createQuery("Select e From Categories e", Categories.class);
        List<Categories> categories = query.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return categories;
    }


    public static List<Categories> selectCategoriesForListView(){
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Categories> query = entityManager.createQuery("SELECT NEW Categories(node.id, CONCAT(REPEAT('    ', count(parent.name) - 3), CASE WHEN (count(parent.name) - 3 <= 0) THEN CONCAT('   ', node.name) ELSE CONCAT(' |- ', node.name) END), count(parent.name), node.category_parameter_id) FROM Categories as node INNER JOIN Categories as parent ON ( parent.lft <= node.lft AND parent.rght >= node.lft ) GROUP BY node.id ORDER BY node.lft", Categories.class);
        List<Categories> categories = query.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return categories;
    }

    public static List<Categories> selectCategoriesForComboBox(){
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Categories> query = entityManager.createQuery("SELECT NEW Categories(node.id, CONCAT(REPEAT('    ', count(parent.name) - 3), CASE WHEN (count(parent.name) - 3 <= 0) THEN CONCAT('   ', node.name) ELSE CONCAT(' |- ', node.name) END), node.lft, node.rght, count(parent.name), node.category_parameter_id) FROM Categories as node INNER JOIN Categories as parent ON ( parent.lft <= node.lft AND parent.rght >= node.lft ) GROUP BY node.id ORDER BY node.lft", Categories.class);
        List<Categories> categories = query.getResultList();

        entityManager.getTransaction().commit();
        entityManager.close();

        return categories;
    }

    public static void updateCategoryLefts(int lft){
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Query query = entityManager.createQuery("Update Categories a SET a.lft = a.lft + 2 WHERE a.lft > ?1");
        query.setParameter(1, lft);
        int rowsUpdated = query.executeUpdate();
        System.out.println("entities Updated: " + rowsUpdated);

        entityManager.getTransaction().commit();
        entityManager.close();
    }



    public static void updateCategoryParameterById(int categoryParameter, int id){
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Query query = entityManager.createQuery("Update Categories a SET a.category_parameter_id = ?1 WHERE a.id = ?2");
        query.setParameter(1, categoryParameter);
        query.setParameter(2, id);
        int rowsUpdated = query.executeUpdate();
        System.out.println("entities Updated: " + rowsUpdated);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static void updateCategoryRights(int lft){
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        Query query = entityManager.createQuery("Update Categories a SET a.rght = a.rght + 2 WHERE a.rght > ?1");
        query.setParameter(1, lft);
        int rowsUpdated = query.executeUpdate();
        System.out.println("entities Updated: " + rowsUpdated);

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public static Categories displayParentCategoryById(int id){
        EntityManager entityManager = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TypedQuery<Categories> query = entityManager.createQuery("Select e From Categories e WHERE e.id =?1", Categories.class);
        Categories category = query.setParameter(1, id).getSingleResult();

        entityManager.getTransaction().commit();
        entityManager.close();

        return category;
    }

}

