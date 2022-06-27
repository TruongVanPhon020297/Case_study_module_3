package com.codegym.dao;

import com.codegym.model.Order;
import com.codegym.utils.MySQLConnUtils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public Map<String, String> update(Order order) {
        return null;
    }

    @Override
    public boolean remove(int id) {
        return false;
    }

    @Override
    public Map<String, String> doCreate(Order order) {
        return null;
    }

    @Override
    public Map<String, String> payOrder(Order order) {
        final String SP_CREATE_ORDER = "{CALL sp_create_order(?, ?, ?, ?, ?, ?)}";

        Map<String, String> result = new HashMap<>();
        try {
            Connection connection = MySQLConnUtils.getConnection();

            CallableStatement statement = connection.prepareCall(SP_CREATE_ORDER);
            statement.setInt(1, order.getUserId());
            statement.setString(2, order.getFullName());
            statement.setString(3, order.getEmail());
            statement.setString(4, order.getMobile());
            statement.registerOutParameter(5, Types.BOOLEAN);
            statement.registerOutParameter(6, Types.VARCHAR);
            statement.execute();

            Boolean success = statement.getBoolean("success");
            String message = statement.getString("message");

            result.put("success", success.toString());
            result.put("message", message);

        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return result;
    }
}
