package com.codegym.dao;


import com.codegym.model.User;
import com.codegym.utils.MySQLConnUtils;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private int noOfRecords;
    private static final String SP_INSERT_USER = "{CALL sp_insert_user(?, ?, ?, ?, ?, ?, ?,?)}";
    private static final String EXIST_EMAIL_USER = "SELECT COUNT(*) AS count FROM users AS u WHERE u.email = ?;";
    private static final String EXIST_MOBILE_USER = "SELECT COUNT(*) AS count FROM users AS u WHERE u.mobile = ?;";
    private static final String SELECT_ALL_USER_PAGE = "SELECT SQL_CALC_FOUND_ROWS * FROM users AS u WHERE u.id LIMIT ?,?";
    private static final String SP_SEARCH_USER = "{CALL sp_search_user(?)}";
    private static final String SEARCH_USER = "SELECT * FROM users WHERE full_name LIKE ? OR email LIKE ? OR mobile LIKE ?;";
    private static final String FIND_USER_ID = "SELECT u.id, u.full_name, u.email,u.mobile, u.address FROM users AS u WHERE u.id = ? AND u.is_active = 1;";
    private static final String EXIST_USER_ID = "SELECT COUNT(*) AS count FROM users AS u WHERE u.id = ?;";
    private static final String FIND_STATUS_USER_ID = "SELECT * FROM users WHERE id = ?;";
    private static final String UPDATE_STATUS_USER = "UPDATE users SET is_active = ? WHERE id = ?;";
    private static final String SP_UPDATE_USER = "{CALL sp_update_user(?, ?, ?, ?, ?, ?, ?)}";

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Map<String, String> update(User user) {
        Map<String, String> result = new HashMap<>();
        try {
            Connection connection = MySQLConnUtils.getConnection();

            CallableStatement statement = connection.prepareCall(SP_UPDATE_USER);
            statement.setInt(1, user.getId());
            statement.setString(2, user.getFullName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getMobile());
            statement.setString(5, user.getAddress());
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
    public boolean remove(int id) {
        return false;
    }

    @Override
    public Map<String, String> doCreate(User user) {
        Map<String, String> result = new HashMap<>();
        try {
            Connection connection = MySQLConnUtils.getConnection();

            CallableStatement statement = connection.prepareCall(SP_INSERT_USER);
            statement.setString(1, user.getFullName());
            statement.setString(2, user.getMobile());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPasswordUser());
            statement.setInt(5, user.getAdmin());
            statement.setString(6, user.getAddress());
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
    public boolean existsByEmail(String email) {
        boolean exists = false;

        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement statement = connection.prepareCall(EXIST_EMAIL_USER);
            statement.setString(1, email);
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
    public boolean existsByMobile(String mobile) {
        boolean exists = false;

        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement statement = connection.prepareCall(EXIST_MOBILE_USER);
            statement.setString(1, mobile);
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
    public List<User> findUserId(int id) {
        List<User> userList = new ArrayList<>();
        try (
                Connection connection = MySQLConnUtils.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_ID);
        ) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idUser = rs.getInt("id");
                String fullName = rs.getString("full_name");
                String mobile = rs.getString("mobile");
                String email = rs.getString("email");
                String address = rs.getString("address");
                userList.add(new User(idUser, fullName, mobile, email, address));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public boolean existsById(int userId) {
        boolean exists = false;

        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement statement = connection.prepareCall(EXIST_USER_ID);
            statement.setInt(1, userId);
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

    public List<User> findAll(int offset, int noOfRecords) {
        List<User> userList = new ArrayList<>();
        try {
            Connection connection = MySQLConnUtils.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USER_PAGE);
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, noOfRecords);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String fullName = rs.getString("full_name");
                String mobile = rs.getString("mobile");
                String email = rs.getString("email");
                String registeredAt = rs.getString("registered_at");
                String updatedAt = rs.getString("updated_at");
                int admin = rs.getInt("is_admin");
                int status = rs.getInt("is_active");
                String urlImage = rs.getString("url_image");
                String address = rs.getString("address");
                userList.add(new User(id, fullName, mobile, email, registeredAt, updatedAt, admin, status, address, urlImage));
            }
            rs = preparedStatement.executeQuery("SELECT FOUND_ROWS()");
            if (rs.next())
                this.noOfRecords = rs.getInt(1);
        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return userList;
    }

    public List<User> searchByKey(String key) {
        List<User> userList = new ArrayList<>();
        try {
            Connection connection = MySQLConnUtils.getConnection();

            CallableStatement statement = connection.prepareCall(SEARCH_USER);
            statement.setString(1, '%' + key + '%');
            statement.setString(2, '%' + key + '%');
            statement.setString(3, '%' + key + '%');
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String fullName = rs.getString("full_name");
                String mobile = rs.getString("mobile");
                String email = rs.getString("email");
                int role = rs.getInt("is_admin");
                int status = rs.getInt("is_active");
                String urlImage = rs.getString("url_image");
                userList.add(new User(id, fullName, mobile, email, role, status, urlImage));
            }

        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return userList;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }

    public List<User> findStatusUserId(int idSearch) {
        List<User> userList = new ArrayList<>();
        try (
                Connection connection = MySQLConnUtils.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(FIND_STATUS_USER_ID);
        ) {
            preparedStatement.setInt(1, idSearch);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int idUser = rs.getInt("id");
                String fullName = rs.getString("full_name");
                String mobile = rs.getString("mobile");
                String email = rs.getString("email");
                int role = rs.getInt("is_admin");
                int status = rs.getInt("is_active");
                String urlImage = rs.getString("url_image");
                userList.add(new User(idUser, fullName, mobile, email, role, status, urlImage));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public boolean updateStatus(int id, int status) {
        boolean rowUpdate = false;
        try {
            Connection connection = MySQLConnUtils.getConnection();

            PreparedStatement statement = connection.prepareStatement(UPDATE_STATUS_USER);
            statement.setInt(1, status);
            statement.setInt(2, id);
            rowUpdate = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            MySQLConnUtils.printSQLException(e);
        }
        return rowUpdate;
    }
}
