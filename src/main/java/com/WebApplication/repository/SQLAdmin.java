package com.WebApplication.repository;

import com.WebApplication.repository.dbmanager.DBManager;
import com.WebApplication.models.User;

import java.sql.*;
import java.util.ArrayList;



public class SQLAdmin {
    Connection connection;
    PreparedStatement ps;
    boolean bol;
    ResultSet rs;
    ArrayList<User> userList = new ArrayList<>();
    ArrayList<User> users = getResults(scriptRecieve("select * from accounts"));

    public ArrayList<User> getUsers() {
        return users;
    }

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


    public String createAccount(User user) {
        getUsers().add(user);
        scriptCommand("insert into accounts(username, password, email)values" +
                "(" + "\"" + user.getUsername() + "\",\"" + user.getPassword() + "\",\"" + user.getEmail() + "\")");
        return "index";
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

    public ResultSet validateLogin(User user) {
        return scriptRecieve("SELECT * FROM accounts where username = "
                + "\"" + user.getUsername() + "\" and password = \"" + user.getPassword()+"\"");
    }
}

/*
    public String arrayToString(ArrayList<Movie> movielist){
        movie = "";
        for (int i = 0; i < movielist.size(); i++) {
            movie += movieList.get(i);
        }
        return movie;*/
