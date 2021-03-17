package com.arabie.daos;

import com.arabie.entities.UserEntity;
import java.sql.*;
import java.util.*;

public class UserDao {
    private Connection conn = null;

    public UserDao(Connection connection) {
        conn = connection;
    }

    public List<UserEntity> searchUsers(String name) {
        List<UserEntity> retrievedUsers = new ArrayList<>();
        final String SQL_SELECT = "Select * from user where first_name like ? or last_name like ?;";
        PreparedStatement pStmt = null;
        try {
            pStmt = conn.prepareStatement(SQL_SELECT);
            pStmt.setString(1, "%" + name + "%");
            pStmt.setString(2, "%" + name + "%");
            ResultSet resultSet = pStmt.executeQuery();
            retrievedUsers.clear();
            while (resultSet.next()) {
                UserEntity user = new UserEntity();
                user.setUserName(resultSet.getString("user_name"));
                user.setPassword(resultSet.getString("password"));
                user.setFirstName(resultSet.getString("first_name"));
                user.setLastName(resultSet.getString("last_name"));
                retrievedUsers.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return retrievedUsers;
    }
}
