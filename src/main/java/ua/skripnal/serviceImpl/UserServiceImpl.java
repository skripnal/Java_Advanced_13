package ua.skripnal.serviceImpl;

import org.apache.log4j.Logger;
import ua.skripnal.dao.UserDao;
import ua.skripnal.model.User;
import ua.skripnal.service.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private static Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
    public UserServiceImpl(UserDao userDao) {
        LOGGER.info("UserServiceImpl -> constructor");
        this.userDao = userDao;
    }

    @Override
    public Optional<User> readById(int id) {
        LOGGER.info("UserServiceImpl -> readById");
        return userDao.readById(id);
    }

    @Override
    public Optional<User> readByEmail(String email) {
        LOGGER.info("UserServiceImpl -> readByEmail");
        return userDao.readByEmail(email);
    }

    @Override
    public void readAll() {
        LOGGER.info("UserServiceImpl -> readAll");
        for (User user : userDao.readAll()){
            System.out.println(user);
        }
    }

    @Override
    public void insert(String firstName, String lastName, String email, String password, String role) {
        LOGGER.info("UserServiceImpl -> insert");
        userDao.insert(firstName,lastName,email,password,role);
    }
}
