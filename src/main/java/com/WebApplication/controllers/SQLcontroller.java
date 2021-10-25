package com.WebApplication.controllers;

import com.WebApplication.dbmanager.DBManager;
import com.WebApplication.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;


@Controller
public class SQLcontroller {
    Connection connection;
    PreparedStatement ps;
    boolean bol;
    ResultSet rs;
    ArrayList<User> userList = new ArrayList<>();
    ArrayList <User> users = getResults(scriptRecieve("select * from users.accounts"));


    public void scriptCommand(String sqlCommand) {
        try {
            connection = DBManager.getConnection();
            ps = connection.prepareStatement(sqlCommand);
            bol = ps.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ResultSet scriptRecieve(String sqlCommand) {
        try {
            connection = DBManager.getConnection();
            ps = connection.prepareStatement(sqlCommand);
            rs = ps.executeQuery();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return rs;
    }



    /*public void batch(ArrayList<String> commands) {
        try {
            connection = DBManager.getConnection();
            s = connection.createStatement();
            for (int i = 0; i < commands.size(); i++) {
                s.addBatch(commands.get(i));
            }
            s.executeBatch();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }*/


    public ArrayList<User> getResults(ResultSet rs) {
        try {
            userList.clear();
            while (rs.next()) {
                userList.add(new User(
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return userList;
    }

    public String validateLogin(User user, WebRequest request, HttpSession session) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(user.getUsername())
                    && users.get(i).getPassword().equals(user.getPassword())) {
               if(session.getAttribute("username")== null){
                   request.setAttribute("username",user,WebRequest.SCOPE_SESSION);
               }
                return "loginSuccess";
            }
        }
        return "redirect:/";
    }
/*
    public String arrayToString(ArrayList<Movie> movielist){
        movie = "";
        for (int i = 0; i < movielist.size(); i++) {
            movie += movieList.get(i);
        }
        return movie;*/
    }