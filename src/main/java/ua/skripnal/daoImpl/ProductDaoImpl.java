package ua.skripnal.daoImpl;

import org.apache.log4j.Logger;
import ua.skripnal.dao.ProductDao;
import ua.skripnal.model.CustomProductBucket;
import ua.skripnal.model.Product;
import ua.skripnal.model.User;
import ua.skripnal.utils.FactoryManager;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDaoImpl implements ProductDao {
    private EntityManager entityManager = FactoryManager.getEntityManager();
    public ProductDaoImpl() {
    }

    @Override
    public List<CustomProductBucket> readALLProductBucket(int userId) {
        String jpql = "SELECT NEW ua.skripnal.model.CustomProductBucket(\n" +
                "    b.id, p.id, p.name, p.description, p.price, p.category, p.subCategory, p.createDate, p.imagePath\n" +
                ") FROM Bucket b JOIN Product p ON b.productId = p.id WHERE b.userId = :id";

        TypedQuery<CustomProductBucket> query = entityManager.createQuery(jpql, CustomProductBucket.class);
        query.setParameter("id", userId);

        return query.getResultList();
    }

    @Override
    public Optional<Product> readById(int id) {
        Product product = entityManager.find(Product.class, id);
        if (product != null){
            return Optional.of(product);
        }else return Optional.empty();
    }

    @Override
    public List<Product> readByCategory(String category, String subCategory) {
            String jpql = "SELECT e FROM Product e WHERE e.category = :category AND e.subCategory = :subCategory";
            TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);
            query.setParameter("category", category);
            query.setParameter("subCategory", subCategory);

            return query.getResultList();
    }

    @Override
    public List<Product> readAll() {
        String jpql = "SELECT e FROM Product e";
        TypedQuery<Product> query = entityManager.createQuery(jpql, Product.class);

        return query.getResultList();
    }

    @Override
    public void insert(String name, String description, double price, String category, String subCategory, String imagePath) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(new Product(name,description,price,category,subCategory,imagePath));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

}
