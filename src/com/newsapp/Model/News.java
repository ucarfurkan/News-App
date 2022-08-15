package com.newsapp.Model;

import com.newsapp.Helper.DatabaseConnector;

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
                obj.setWriterId(rs.getInt(1));
                obj.setCategoryId(rs.getInt(5));
                obj.setHeadline(rs.getString(2));
                obj.setText(rs.getString(4));
                list.add(obj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
