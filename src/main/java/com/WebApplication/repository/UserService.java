package com.WebApplication.repository;

import com.WebApplication.models.User;
import org.springframework.web.context.request.WebRequest;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserService {
    User user;

    public User create(String username, String password) {
        user = new User(username, password);
        return user;
    }


    public User convertRsToUser(ResultSet rs) {

        try {

            if (rs.next()) {
                user = new User(
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return user;
    }
}
