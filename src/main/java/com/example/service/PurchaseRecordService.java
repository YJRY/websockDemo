package com.example.service;

import com.example.bean.Product;
import com.example.bean.PurchaseRecord;
import com.example.dao.ProductDao;
import com.example.dao.PurchaseRecordDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PurchaseRecordService {

    @Resource
    private ProductDao productDao;
    @Resource
    private PurchaseRecordDao purchaseRecordDao;
    @Resource
    private RedisService redisService;

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public boolean purchase(long userId, long productId, int quantity) {
        //可根据时间戳来限制重复请求的次数
//        long start = System.currentTimeMillis();
//        while (true) {
//            long end = System.currentTimeMillis();
//            if (end - start > 100) {
//                return false;
//            }
//            Product product = productDao.getProductById(productId);
//            if (product.getStock() < quantity) {
//                return false;
//            }
//            int version = product.getVersion();
//            if (productDao.decreaseProductById(productId, quantity, version) == 0) {
//                continue;
//            }
//            PurchaseRecord record = PurchaseRecord.init(userId, product, quantity);
//            purchaseRecordDao.addRecord(record);
//            return true;
//        }
        //也可根据重试次数限制请求
        for (int i = 0; i < 3; i++) {
            Product product = productDao.getProductById(productId);
            if (product.getStock() < quantity) {
                return false;
            }
            int version = product.getVersion();
            if (productDao.decreaseProductById(productId, quantity, version) == 0) {
                continue;
            }
            PurchaseRecord record = PurchaseRecord.init(userId, product, quantity);
//            purchaseRecordDao.addRecord(record);
            redisService.lPush("purchaseRecord", record);
            return true;
        }
        return false;
    }

    public void batchAddRecords(List<PurchaseRecord> records){
        purchaseRecordDao.batchAddRecords(records);
    }
}
