package ua.skripnal.daoImpl;

import org.apache.log4j.Logger;
import ua.skripnal.dao.UserDao;
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

public class UserDaoImpl implements UserDao {
    private EntityManager entityManager = FactoryManager.getEntityManager();

    public UserDaoImpl(){

    }
    @Override
    public Optional<User> readById(int id) {
        User user = entityManager.find(User.class, id);
        if (user != null){
            return Optional.of(user);
        }else return Optional.empty();
    }

    @Override
    public Optional<User> readByEmail(String email) {
        try {
            String jpql = "SELECT u FROM User u WHERE u.email = :email";
            TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
            query.setParameter("email", email);

            User user = query.getSingleResult();
            return Optional.of(user);
        }catch (NoResultException e){
            return Optional.empty();
        }
    }

    @Override
    public List<User> readAll() {
        String jpql = "SELECT u FROM User u";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);

        return query.getResultList();
    }

    @Override
    public void insert(String firstName, String lastName, String email, String password, String role) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(new User(firstName, lastName, email, password, role));
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

}
