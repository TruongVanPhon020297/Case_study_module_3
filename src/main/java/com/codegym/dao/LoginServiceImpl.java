package com.codegym.dao;

import com.codegym.model.Product;
import com.codegym.model.User;
import com.codegym.utils.MySQLConnUtils;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoginServiceImpl implements LoginService{
    private static final String FIND_EMAIL = "SELECT * FROM _user AS u WHERE u.email = ?;";
    private static final String EXISTS_EMAIL = "SELECT COUNT(*) AS count FROM _user AS u WHERE u.email = ?;";
    @Override
    public boolean existsEmail(String email) {
        boolean exists = false;

        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement statement = connection.prepareCall(EXISTS_EMAIL);
            statement.setString(1,email);
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
    public List<User> findUserByEmail(String email) {
        List<User> userList = new ArrayList<>();
        try(
                Connection connection = MySQLConnUtils.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(FIND_EMAIL);
        ){
            preparedStatement.setString(1,email);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
                int id = rs.getInt("id");
                String fullName = rs.getString("full_name");
                String mobile = rs.getString("mobile");
                String emailFind = rs.getString("email");
                String password = rs.getString("password_user");
                int admin = rs.getInt("_admin");
                int status = rs.getInt("_status");
                userList.add(new User(id,fullName,mobile, emailFind, password,admin,status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

}
