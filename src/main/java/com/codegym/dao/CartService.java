package com.codegym.dao;

import com.codegym.model.Cart;

import java.util.Map;

public interface CartService extends IGeneralService<Cart> {

    Map<String, String> addToCart(Cart cart);
}
