package ua.skripnal.service;

import ua.skripnal.model.Bucket;

import java.util.List;

public interface BucketService {

    void insertProductByUserId(int userId, int productId);
    void deleteProduct(int id);
}
