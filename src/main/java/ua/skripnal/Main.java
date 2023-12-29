package ua.skripnal;


import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import ua.skripnal.dao.*;
import ua.skripnal.daoImpl.*;
import ua.skripnal.model.User;
import ua.skripnal.service.*;
import ua.skripnal.serviceImpl.*;
import ua.skripnal.utils.ConnectionUtils;
import ua.skripnal.utils.FactoryManager;

import javax.persistence.EntityManager;
import java.sql.Connection;

public class Main {

    public static void main(String[] args) {


        UserDao userDao = new UserDaoImpl();
        UserService userService = new UserServiceImpl(userDao);

//        userService.readAll();
//        System.out.println(userService.readById(1));
        ProductDao productDao = new ProductDaoImpl();
        ProductService productService = new ProductServiceImpl(productDao);
        System.out.println(productService.readALLProductBucket(2));


    }
}
