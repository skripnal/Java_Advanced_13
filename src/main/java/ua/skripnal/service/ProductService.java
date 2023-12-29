package ua.skripnal.service;

import ua.skripnal.model.CustomProductBucket;
import ua.skripnal.model.Product;

import java.util.List;

public interface ProductService {

    Product readById(int id);
    List<Product> readAll();
    List<Product> readByCategory(String category, String subCategory);
    void insert(String name, String description, double price, String category, String subCategory, String imagePath);

    List<CustomProductBucket> readALLProductBucket(int userId);

}
