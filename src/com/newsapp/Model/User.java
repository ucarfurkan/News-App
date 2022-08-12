package com.newsapp.Model;

import com.newsapp.Helper.DatabaseConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class User {
    private int id;
    private String name;
    private String userName;
    private String password;
    private String userType;

    public User(){

    }
    public User(int id, String name, String userName, String password, String userType) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.password = password;
        this.userType = userType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public static ArrayList<User> getList(){
        String query = "SELECT * FROM public.user";
        ArrayList<User> list = new ArrayList<>();
        User obj;
        try {
            Statement st = DatabaseConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUserName(rs.getString("user_name"));
                obj.setPassword(rs.getString("pass"));
                obj.setUserType(rs.getString("user_type"));
                list.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
