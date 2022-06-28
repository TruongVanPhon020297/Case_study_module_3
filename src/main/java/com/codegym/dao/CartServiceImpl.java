package com.codegym.dao;

import com.codegym.model.Cart;
import com.codegym.model.Product;
import com.codegym.utils.MySQLConnUtils;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartServiceImpl implements CartService {
    @Override
    public List<Cart> findAll() {
        final String SELECT_ALL_CART = "SELECT c.product_id, c.title, c.price, c.quantity, c.total_price FROM carts AS c;";

        List<Cart> carts = new ArrayList<>();

        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement statement = connection.prepareCall(SELECT_ALL_CART);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int product_id = rs.getInt("product_id");
                String title = rs.getString("title");
                int price = rs.getInt("price");
                int quantity = rs.getInt("quantity");
                double total_price = rs.getDouble("total_price");
                carts.add(new Cart(product_id, title, price, quantity, total_price));
            }
        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return carts;
    }

    @Override
    public Map<String, String> update(Cart cart) {
        return null;
    }

    @Override
    public boolean remove(int id) {
        return false;
    }

    @Override
    public Map<String, String> doCreate(Cart cart) {
        return null;
    }

    @Override
    public Map<String, String> addToCart(Cart cart) {
        final String SP_ADD_CART = "{CALL sp_add_cart(?, ?, ?, ?)}";

        Map<String, String> result = new HashMap<>();
        try {
            Connection connection = MySQLConnUtils.getConnection();

            CallableStatement statement = connection.prepareCall(SP_ADD_CART);
            statement.setInt(1, cart.getProductId());
            statement.setInt(2, cart.getQuantity());
            statement.registerOutParameter(3, Types.BOOLEAN);
            statement.registerOutParameter(4, Types.VARCHAR);
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
