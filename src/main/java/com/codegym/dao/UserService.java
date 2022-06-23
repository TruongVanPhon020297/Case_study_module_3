package com.codegym.dao;

import com.codegym.model.Product;
import com.codegym.model.User;

import java.util.List;

public interface UserService extends IGeneralService<User>{
    public boolean existsByEmail(String email);
    public boolean existsByMobile(String mobile);
    List<User> findUserId(int id);
    boolean existsById(int productId);
}
