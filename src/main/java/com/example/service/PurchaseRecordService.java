package com.example.service;

import com.example.bean.Product;
import com.example.bean.PurchaseRecord;
import com.example.dao.ProductDao;
import com.example.dao.PurchaseRecordDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class PurchaseRecordService {

    @Resource
    private ProductDao productDao;
    @Resource
    private PurchaseRecordDao purchaseRecordDao;

    @Transactional
    public boolean purchase(long userId, long productId, int quantity) {
        Product product = productDao.getProductById(productId);
        if (product.getStock() < quantity) {
            return false;
        }
        productDao.decreaseProductById(productId, quantity);
        PurchaseRecord record = PurchaseRecord.init(userId, product, quantity);
        purchaseRecordDao.addRecord(record);
        return true;
    }
}
