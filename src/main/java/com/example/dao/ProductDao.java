package com.example.dao;

import com.example.bean.Product;
import org.apache.ibatis.annotations.Param;

public interface ProductDao {

    Product getProductById(long id);

    int decreaseProductById(@Param("id") long id, @Param("quantity")int quantity, @Param("version")int version);
}
