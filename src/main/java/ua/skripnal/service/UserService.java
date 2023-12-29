package ua.skripnal.service;

import ua.skripnal.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> readById(int id);
    Optional<User> readByEmail(String email);
    void readAll();
    void insert(String firstName, String lastName, String email, String password, String role);
}
