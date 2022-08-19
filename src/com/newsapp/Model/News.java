package com.newsapp.Model;

import com.newsapp.Helper.DatabaseConnector;
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
    private int likeCount;

    public News() {
        this.likeCount = 0;
    }

    public News(int writerId, int categoryId, String headline, String text, int likeCount) {
        this.writerId = writerId;
        this.categoryId = categoryId;
        this.headline = headline;
        this.text = text;
        this.likeCount = 0;
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

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public static ArrayList<News> getList(){
        String query = "SELECT * FROM news";
        ArrayList<News> list = new ArrayList<>();
        News obj;
        try {
            Statement st = DatabaseConnector.getInstance().createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){
                obj = new News();
                obj.setId(rs.getInt(1));
                obj.setWriterId(rs.getInt(2));
                obj.setCategoryId(rs.getInt(6));
                obj.setHeadline(rs.getString(3));
                obj.setText(rs.getString(4));
                obj.setLikeCount(rs.getInt(5));
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
        query = query.replace("{{writerId}}",writerId);
        query = query.replace("{{headline}}",headline);
        query = query.replace("{{text}}",text);
        if(!category.isEmpty()){
            int categoryId = Category.getCategoryId(category);
            query += " AND category_id="+categoryId;
        }
        if(!writerId.isEmpty()){
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
                obj.setLikeCount(rs.getInt("like_count"));
                newsList.add(obj);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newsList;
    }

}
