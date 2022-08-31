package com.newsapp.Model;

import com.newsapp.Helper.DatabaseConnector;

import javax.xml.crypto.Data;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class News {
    private int id;
    private int writerId;
    private int categoryId;
    private String headline;
    private String text;


    public News(){

    }
    public News(int writerId, int categoryId, String headline, String text) {
        this.writerId = writerId;
        this.categoryId = categoryId;
        this.headline = headline;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWriterId() {
        return writerId;
    }

    public void setWriterId(int writerId) {
        this.writerId = writerId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public static ArrayList<News> getList(){
        String query = "SELECT * FROM news ORDER BY id ASC";
        ArrayList<News> list = new ArrayList<>();
        News obj;
        try {
            Statement st = DatabaseConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                obj = new News();
                obj.setId(rs.getInt(1));
                obj.setWriterId(rs.getInt(2));
                obj.setCategoryId(rs.getInt(5));
                obj.setHeadline(rs.getString(3));
                obj.setText(rs.getString(4));
                list.add(obj);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean delete(String id){
        String query = "DELETE FROM news WHERE id="+id+";";
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

    public static boolean deleteUsersNews(int writerId){
        String query = "DELETE FROM news WHERE writer_id="+writerId;

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


    public static String searchQuery(String writerId,String category, String headline, String text){
        String query = "SELECT * FROM news WHERE headline LIKE '%{{headline}}%' AND text LIKE '%{{text}}%'";
        query = query.replace("{{headline}}",headline);
        query = query.replace("{{text}}",text);
        if(!category.isEmpty()){
            if(!category.equals(" ")){
                int categoryId = Category.getCategoryId(category);
                if(categoryId != -1){
                    query += " AND category_id="+categoryId;
                }
            }
        }
        if(!writerId.isEmpty()){
            query += " AND writer_id="+writerId;
        }
        return query;
    }

    public static String searchQueryWithName(String writerId,String category, String headline, String text){
        String query = "SELECT * FROM news WHERE headline LIKE '%{{headline}}%' AND text LIKE '%{{text}}%'";
        query = query.replace("{{headline}}",headline);
        query = query.replace("{{text}}",text);
        System.out.println(writerId);
        if(!category.isEmpty()){
            int categoryId = Category.getCategoryId(category);
            query += " AND category_id="+categoryId;
        }
        if(!writerId.isEmpty() || writerId != "0"){
            query += " AND writer_id="+writerId;
        }
        return query;
    }

    public static ArrayList<News> searchNewsList(String query){
        ArrayList<News> newsList = new ArrayList<>();
        News obj;
        try {
            Statement st = DatabaseConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                obj = new News();
                obj.setId(rs.getInt("id"));
                obj.setWriterId(rs.getInt("writer_id"));
                obj.setCategoryId(rs.getInt("category_id"));
                obj.setHeadline(rs.getString("headline"));
                obj.setText(rs.getString("text"));
                newsList.add(obj);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newsList;
    }

    public static News getSelectedNews(String id){
        News obj=null;
        String query = "SELECT * FROM news WHERE id="+id;
        try {
            PreparedStatement pr = DatabaseConnector.getInstance().prepareStatement(query);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                obj = new News();
                obj.setId(rs.getInt(1));
                obj.setWriterId(rs.getInt(2));
                obj.setHeadline(rs.getString(3));
                obj.setText(rs.getString(4));
                obj.setCategoryId(rs.getInt(5));
            }
            rs.close();
            pr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static boolean update(String id, String headline, String text, String categoryId){
        String query = "UPDATE news SET headline='"+headline+"', text='"+text+"', category_id="+categoryId+" WHERE id="+id;
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


}
