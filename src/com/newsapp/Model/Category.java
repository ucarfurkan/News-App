package com.newsapp.Model;

import com.newsapp.Helper.DatabaseConnector;

import javax.swing.*;
import java.sql.PreparedStatement;
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
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean delete(String categoryId){
        String query = "DELETE FROM category WHERE id="+categoryId+";";
        try {
            PreparedStatement pr = DatabaseConnector.getInstance().prepareStatement(query);
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

    public static boolean add(String categoryName) {

        if(Category.addToEnumCategoryType(categoryName)){
            String query = "INSERT INTO category (name) VALUES ('"+categoryName+"')";

            try {
                PreparedStatement pr = DatabaseConnector.getInstance().prepareStatement(query);
                int result = pr.executeUpdate();
                pr.close();
                if(result == -1){
                    return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public static boolean addToEnumCategoryType(String categoryName){
        String query = "ALTER TYPE categoryTypes ADD VALUE IF NOT EXISTS '"+categoryName+"'";
        try {
            PreparedStatement pr = DatabaseConnector.getInstance().prepareStatement(query);
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

    public static int getNewsCount(int categoryId){
        String query = "SELECT * FROM news WHERE category_id="+categoryId;
        int num = 0;
        try {
            Statement st = DatabaseConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                num++;
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return num;
    }

    public static boolean deleteNewsInCategory(String categoryId){
        String query = "DELETE FROM news WHERE category_id="+categoryId;

        try {
            PreparedStatement pr = DatabaseConnector.getInstance().prepareStatement(query);
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

    public static int getCategoryId(String categoryName){
        String query = "SELECT * FROM category WHERE name='"+categoryName+"'";
        int id=0;
        try {
            PreparedStatement pr = DatabaseConnector.getInstance().prepareStatement(query);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                id = rs.getInt("id");
            }
            rs.close();
            pr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public static void updateCategoryCombo(JComboBox comboBox){
        String query = "SELECT * FROM category";

        try {
            PreparedStatement pr = DatabaseConnector.getInstance().prepareStatement(query);
            ResultSet rs = pr.executeQuery();
            while(rs.next()){
                comboBox.addItem(rs.getString("name"));
            }
            rs.close();
            pr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
