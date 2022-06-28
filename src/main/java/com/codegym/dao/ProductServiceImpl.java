package com.codegym.dao;

import com.codegym.model.Product;
import com.codegym.utils.MySQLConnUtils;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductServiceImpl implements ProductService {
    private int noOfRecords;
    private static final String SELECT_ALL_PRODUCT = "SELECT * FROM products;";
    private static final String SP_INSERT_PRODUCT = "{CALL sp_insert_product(?, ?, ?, ?, ?, ?, ?)}";
    private static final String SP_UPDATE_PRODUCT = "{CALL sp_update_product(?, ?, ?, ?, ?, ?, ?,?)}";
    private static final String EXIST_PRODUCT_ID = "SELECT COUNT(*) AS count FROM products AS p WHERE p.id = ?;";
    private static final String FIND_PRODUCT_ID = "SELECT p.id, p.title, p.price,p.quantity, p.category_id FROM products AS p WHERE id = ? AND p.stop_selling = 0;";
    private static final String FIND_STATUS_PRODUCT_ID = "SELECT * FROM products WHERE id = ?;";
    private static final String SELECT_ALL_PRODUCT_PAGE = "SELECT SQL_CALC_FOUND_ROWS * FROM products WHERE id LIMIT ?,?";
    private static final String SP_SEARCH_PRODUCT = "{CALL sp_search_product(?)}";
    private static final String UPDATE_STATUS_PRODUCT = "UPDATE products SET stop_selling = ? WHERE id = ?;";
    private static final String SP_UPDATE_PRODUCT_NO = "{CALL sp_update_product_not_image(?, ?, ?, ?, ?, ?, ?)}";



    @Override
    public List<Product> findAll() {
        List<Product> productList = new ArrayList<>();
        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement statement = connection.prepareCall(SELECT_ALL_PRODUCT);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String urlImage = rs.getString("url_image");
                BigDecimal price = rs.getBigDecimal("price");
                int quantity = rs.getInt("quantity");
                String createdAt = rs.getString("created_at");
                String updatedAt = rs.getString("updated_at");
                int stopSelling = rs.getInt("stop_selling");
                int idCategory = rs.getInt("category_id");
                productList.add(new Product(id, title, urlImage, price, quantity, createdAt, updatedAt, stopSelling, idCategory));
            }
        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return productList;
    }

    @Override
    public Map<String, String> update(Product product) {
        Map<String, String> result = new HashMap<>();
        try {
            Connection connection = MySQLConnUtils.getConnection();

            CallableStatement statement = connection.prepareCall(SP_UPDATE_PRODUCT);
            statement.setInt(1, product.getId());
            statement.setString(2, product.getTitle());
            statement.setBigDecimal(3, product.getPrice());
            statement.setString(4, product.getUrlImage());
            statement.setInt(5, product.getQuantity());
            statement.setInt(6, product.getCategoryId());
            statement.registerOutParameter(7, Types.BOOLEAN);
            statement.registerOutParameter(8, Types.VARCHAR);
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

    @Override
    public boolean remove(int id) {
        return false;
    }

    @Override
    public Map<String, String> doCreate(Product product) {
        Map<String, String> result = new HashMap<>();
        try {
            Connection connection = MySQLConnUtils.getConnection();

            CallableStatement statement = connection.prepareCall(SP_INSERT_PRODUCT);
            statement.setString(1, product.getTitle());
            statement.setBigDecimal(2, product.getPrice());
            statement.setString(3, product.getUrlImage());
            statement.setInt(4, product.getQuantity());
            statement.setInt(5, product.getCategoryId());
            statement.registerOutParameter(6, Types.BOOLEAN);
            statement.registerOutParameter(7, Types.VARCHAR);
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

    @Override
    public boolean existsById(int productId) {
        boolean exists = false;

        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement statement = connection.prepareCall(EXIST_PRODUCT_ID);
            statement.setInt(1, productId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int count = rs.getInt("count");

                if (count > 0) {
                    exists = true;
                }
            }

        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return exists;
    }

    @Override
    public Product findProductId(int id) {
//        List<Product> productList = new ArrayList<>();

        Product product = null;

        try (
                Connection connection = MySQLConnUtils.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(FIND_PRODUCT_ID);
        ) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idProduct = rs.getInt("id");
                String title = rs.getString("title");
                BigDecimal price = rs.getBigDecimal("price");
                int quantity = rs.getInt("quantity");
                int idCategory = rs.getInt("category_id");
//                productList.add(new Product(idProduct, title, price, quantity, idCategory));
                product = new Product(idProduct, title, price, quantity, idCategory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public Map<String, String> updateNo(Product product) {
        Map<String, String> result = new HashMap<>();
        try {
            Connection connection = MySQLConnUtils.getConnection();

            CallableStatement statement = connection.prepareCall(SP_UPDATE_PRODUCT_NO);
            statement.setInt(1, product.getId());
            statement.setString(2, product.getTitle());
            statement.setBigDecimal(3, product.getPrice());
            statement.setInt(4, product.getQuantity());
            statement.setInt(5, product.getCategoryId());
            statement.registerOutParameter(6, Types.BOOLEAN);
            statement.registerOutParameter(7, Types.VARCHAR);
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

    @Override
    public List<Product> findStatusProductId(int id) {
        List<Product> productList = new ArrayList<>();
        try (
                Connection connection = MySQLConnUtils.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(FIND_STATUS_PRODUCT_ID);
        ) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idProduct = rs.getInt("id");
                String title = rs.getString("title");
                String urlImage = rs.getString("url_image");
                BigDecimal price = rs.getBigDecimal("price");
                int quantity = rs.getInt("quantity");
                String createdAt = rs.getString("created_at");
                String updatedAt = rs.getString("updated_at");
                int stopSelling = rs.getInt("stop_selling");
                int idCategory = rs.getInt("category_id");
                productList.add(new Product(idProduct, title, urlImage, price, quantity, createdAt, updatedAt, stopSelling, idCategory));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public List<Product> findAll(int offset, int noOfRecords) {
        List<Product> productList = new ArrayList<>();
        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCT_PAGE);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String urlImage = rs.getString("url_image");
                BigDecimal price = rs.getBigDecimal("price");
                int quantity = rs.getInt("quantity");
                String createdAt = rs.getString("created_at");
                String updatedAt = rs.getString("updated_at");
                int stopSelling = rs.getInt("stop_selling");
                int idCategory = rs.getInt("category_id");
                productList.add(new Product(id, title, urlImage, price, quantity, createdAt, updatedAt, stopSelling, idCategory));
            }
            rs = preparedStatement.executeQuery("SELECT FOUND_ROWS()");
            if (rs.next())
                this.noOfRecords = rs.getInt(1);
        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return productList;
    }

    @Override
    public List<Product> searchByKey(String key) {
        List<Product> productList = new ArrayList<>();
        try {
            Connection connection = MySQLConnUtils.getConnection();

            CallableStatement statement = connection.prepareCall(SP_SEARCH_PRODUCT);
            statement.setString(1, '%' + key + '%');
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String urlImage = rs.getString("url_image");
                BigDecimal price = rs.getBigDecimal("price");
                int quantity = rs.getInt("quantity");
                String createdAt = rs.getString("created_at");
                String updatedAt = rs.getString("updated_at");
                int stopSelling = rs.getInt("stop_selling");
                int idCategory = rs.getInt("category_id");
                productList.add(new Product(id, title, urlImage, price, quantity, createdAt, updatedAt, stopSelling, idCategory));
            }

        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return productList;
    }

    @Override
    public int getNoOfRecords() {
        return noOfRecords;
    }

    @Override
    public boolean updateStatus(int id, int status) {
        boolean rowUpdate = false;
        try {
            Connection connection = MySQLConnUtils.getConnection();

            PreparedStatement statement = connection.prepareStatement(UPDATE_STATUS_PRODUCT);
            statement.setInt(1, status);
            statement.setInt(2, id);
            rowUpdate = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return rowUpdate;
    }

    @Override
    public List<Product> getAllProducts(long categoryId) {
        final String GET_ALL_PRODUCTS_BY_CATEGORY = "SELECT p.id, p.title, p.price FROM products AS p WHERE p.category_id = ? AND p.stop_selling = 0;";

        List<Product> productList = new ArrayList<>();
        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement statement = connection.prepareCall(GET_ALL_PRODUCTS_BY_CATEGORY);
            statement.setLong(1, categoryId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                BigDecimal price = rs.getBigDecimal("price");
                productList.add(new Product(id, title, price));
            }
        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return productList;
    }
}
