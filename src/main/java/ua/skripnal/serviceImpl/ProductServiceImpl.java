package ua.skripnal.serviceImpl;

import org.apache.log4j.Logger;
import ua.skripnal.dao.ProductDao;
import ua.skripnal.model.CustomProductBucket;
import ua.skripnal.model.Product;
import ua.skripnal.service.ProductService;

import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    ProductDao productDao;
    private static Logger LOGGER = Logger.getLogger(ProductServiceImpl.class);

    public ProductServiceImpl(ProductDao productDao) {
        LOGGER.info("ProductServiceImpl -> constructor");
        this.productDao = productDao;
    }

    @Override
    public List<CustomProductBucket> readALLProductBucket(int userId) {
        return productDao.readALLProductBucket(userId);
    }

    @Override
    public Product readById(int id) {
        LOGGER.info("ProductServiceImpl -> readById");
        Optional<Product> optional = productDao.readById(id);
        if (optional.isPresent()) {
            return optional.get();
        } else throw new NullPointerException();
    }

    @Override
    public List<Product> readByCategory(String category, String subCategory) {
        return productDao.readByCategory(category, subCategory);
    }

    @Override
    public List<Product> readAll() {
        LOGGER.info("ProductServiceImpl -> readAll");
        return productDao.readAll();
    }

    @Override
    public void insert(String name, String description, double price, String category, String subCategory, String imagePath) {
        LOGGER.info("ProductServiceImpl -> insert");
        productDao.insert(name, description, price, category, subCategory, imagePath);
    }

}
