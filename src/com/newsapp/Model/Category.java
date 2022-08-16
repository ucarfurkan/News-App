package com.newsapp.Model;

import com.newsapp.Helper.DatabaseConnector;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Category {
    private int id;
    private String name;

    public Category(){

    }

    public Category(String name) {
        this.name = name;
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

    public static ArrayList<Category> getList(){
        String query = "SELECT * FROM category";
        ArrayList<Category> list = new ArrayList<>();
        Category obj;
        try {
            Statement st = DatabaseConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                obj = new Category();
                obj.setId(rs.getInt(1));
                obj.setName(rs.getString(2));
                list.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
