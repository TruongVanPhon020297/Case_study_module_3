package com.codegym.dao;

import com.codegym.model.User;

import java.util.List;

public interface LoginService {
    boolean existsEmail(String email);
    List<User>  findUserByEmail(String email);
}
