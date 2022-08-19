package com.newsapp.Model;

import com.newsapp.Helper.DatabaseConnector;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Writer extends User {
    private int id;
    private String name;
    private String userName;
    private String password;
    private String userType = "writer";

    public Writer(int id, String name, String userName, String password) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUserType() {
        return userType;
    }

    @Override
    public void setUserType(String userType) {
        this.userType = userType;
    }

    public static boolean postNews(int writerId, String headline,String text, int categoryId){
        String query = "INSERT INTO news (writer_id, headline, text, category_id) VALUES (?,?,?,?);";
        try {
            PreparedStatement pr = DatabaseConnector.getInstance().prepareStatement(query);
            pr.setInt(1, writerId);
            pr.setString(2,headline);
            pr.setString(3,text);
            pr.setInt(4, categoryId);
            int result = pr.executeUpdate();
            pr.close();
            if(result == -1){
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}