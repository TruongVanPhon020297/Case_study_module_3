package com.codegym.dao;

import com.codegym.model.Order;

import java.util.Map;

public interface OrderService extends IGeneralService<Order> {

    Map<String, String> payOrder(Order order);
}
