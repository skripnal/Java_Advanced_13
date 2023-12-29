package ua.skripnal.daoImpl;

import org.apache.log4j.Logger;
import ua.skripnal.Main;
import ua.skripnal.dao.BucketDao;
import ua.skripnal.model.Bucket;
import ua.skripnal.model.Product;
import ua.skripnal.utils.FactoryManager;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BucketDaoImpl implements BucketDao {

    private EntityManager entityManager = FactoryManager.getEntityManager();

    public BucketDaoImpl() {
    }

    @Override
    public Optional<Bucket> readById(int id) {
        Bucket bucket = entityManager.find(Bucket.class, id);
        if (bucket != null){
            return Optional.of(bucket);
        }else return Optional.empty();
    }

    @Override
    public void deleteProduct(int id) {
        entityManager.getTransaction().begin();
        entityManager.remove(readById(id).get());
        entityManager.getTransaction().commit();
    }

    @Override
    public void insertProductByUserId(int userId, int productId) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(new Bucket(userId, productId));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

}
