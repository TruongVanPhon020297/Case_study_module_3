package com.codegym.dao;

import com.codegym.model.Product;

import java.util.List;
import java.util.Map;

public interface ProductService extends IGeneralService<Product>{
    boolean existsById(int productId);
    Product findProductId(int id);
    Map<String, String> updateNo(Product product);

    List<Product> findAll();
    List<Product> findAll(int offset, int noOfRecords);

    List<Product> findStatusProductId(int id);
    boolean updateStatus(int id, int status);
    List<Product> searchByKey(String key);
    int getNoOfRecords();


    List<Product> getAllProducts(long categoryId);
}
