package com.codegym.dao;

import com.codegym.model.Category;
import com.codegym.utils.MySQLConnUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CategoryServiceImpl implements CategoryService {
    private static final String SELECT_ALL_CATEGORY = "SELECT c.id, c.title FROM categories AS c;";
    private static String EXIST_CATEGORY_ID = "SELECT COUNT(*) AS count FROM categories AS c WHERE c.id = ?;";

    @Override
    public List<Category> findAll() {
        List<Category> categoryList = new ArrayList<>();
        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement statement = connection.prepareCall(SELECT_ALL_CATEGORY);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                categoryList.add(new Category(id, title));
            }
        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return categoryList;
    }

    @Override
    public Map<String, String> update(Category category) {
        return null;
    }

    @Override
    public boolean remove(int id) {
        return false;
    }

    @Override
    public Map<String, String> doCreate(Category category) {
        return null;
    }

    @Override
    public boolean existById(int categoryId) {
        boolean exist = false;

        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement statement = connection.prepareCall(EXIST_CATEGORY_ID);
            statement.setInt(1, categoryId);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int count = rs.getInt("count");

                if (count > 0) {
                    exist = true;
                }
            }

        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }

        return exist;
    }
}
