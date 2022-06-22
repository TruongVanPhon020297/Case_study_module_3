package com.codegym.dao;

import com.codegym.model.Product;

import java.util.List;
import java.util.Map;

public interface IGeneralService<T>{
    List<T> findAll();

    Map<String, String> update(T t);

    boolean remove(int id);
    Map<String, String> doCreate(T t);
}
