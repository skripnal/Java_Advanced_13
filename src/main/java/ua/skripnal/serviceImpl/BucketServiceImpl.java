package ua.skripnal.serviceImpl;

import org.apache.log4j.Logger;
import ua.skripnal.dao.BucketDao;
import ua.skripnal.daoImpl.UserDaoImpl;
import ua.skripnal.model.Bucket;
import ua.skripnal.service.BucketService;

import java.util.List;

public class BucketServiceImpl implements BucketService {
    BucketDao bucketDao;
    private static Logger LOGGER = Logger.getLogger(BucketServiceImpl.class);

    public BucketServiceImpl(BucketDao bucketDao) {
        LOGGER.info("BucketServiceImpl -> constructor");
        this.bucketDao = bucketDao;
    }

    @Override
    public void insertProductByUserId(int userId, int productId) {
        LOGGER.info("BucketServiceImpl -> insertProductByUserId");
        bucketDao.insertProductByUserId(userId, productId);
    }

    @Override
    public void deleteProduct(int id) {
        bucketDao.deleteProduct(id);
    }
}
