package ua.skripnal.dao;


import ua.skripnal.model.Bucket;

import java.util.List;
import java.util.Optional;

public interface BucketDao {

    Optional<Bucket> readById(int id);
    void insertProductByUserId(int userId, int productId);
    void deleteProduct(int id);

}
