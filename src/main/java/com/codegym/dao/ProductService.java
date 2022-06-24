package com.codegym.dao;

import com.codegym.model.Product;

import java.util.List;
import java.util.Map;

public interface ProductService extends IGeneralService<Product>{
    boolean existsById(int productId);
    List<Product> findProductId(int id);
    Map<String, String> updateNo(Product product);
}
