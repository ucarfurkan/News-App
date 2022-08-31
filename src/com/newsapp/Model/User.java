package com.newsapp.Model;

import com.newsapp.Helper.DatabaseConnector;
import java.sql.PreparedStatement;
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

    public User(String name, String userName, String password, String userType) {
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
            st.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean add(User user){
        String query = "INSERT INTO public.user (name, user_name, pass, user_type) VALUES (?,?,?,CAST(? AS user_types))";
        if(!User.getFetch(user)){
            return false;
        }
        else{
            try {
                PreparedStatement pr = DatabaseConnector.getInstance().prepareStatement(query);
                pr.setString(1,user.getName());
                pr.setString(2,user.getUserName());
                pr.setString(3,user.getPassword());
                pr.setString(4,user.getUserType());
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

    public static boolean update(User u) {
        String query = "UPDATE public.user SET name=?, user_name=?, pass=?, user_type=CAST(? AS user_types) WHERE id=?";

        if(!User.updateUserFetch(u) && !User.getFetch(u)){
                return false;
        }
        else{
            try {
                PreparedStatement pr = DatabaseConnector.getInstance().prepareStatement(query);
                pr.setString(1,u.getName());
                pr.setString(2,u.getUserName());
                pr.setString(3,u.getPassword());
                pr.setString(4,u.getUserType());
                pr.setInt(5,u.getId());
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

    public static boolean delete(User u) {
        String query = "DELETE FROM public.user WHERE id=? AND user_name=?";
        if(User.updateUserFetch(u)){
            try {
                PreparedStatement pr = DatabaseConnector.getInstance().prepareStatement(query);
                pr.setInt(1,u.getId());
                pr.setString(2,u.getUserName());
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

    public static String searchQuery(String name, String uname, String user_type){
        String query = "SELECT * FROM public.user WHERE user_name LIKE '%{{uname}}%' AND name LIKE '%{{name}}%'";
        query = query.replace("{{uname}}",uname);
        query = query.replace("{{name}}",name);
        if(!user_type.isEmpty()){
            query += " AND user_type='{{user_type}}'";
            query = query.replace("{{user_type}}",user_type);
        }
        return query;
    }

    public static ArrayList<User> searchUserList(String query){
        ArrayList<User> userList = new ArrayList<>();
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
                userList.add(obj);
            }
            st.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }


    public static boolean getFetch(User user){
        String query = "SELECT * FROM public.user WHERE user_name=?";
        try {
            PreparedStatement pr = DatabaseConnector.getInstance().prepareStatement(query);
            pr.setString(1,user.getUserName());
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                return false;
            }
            rs.close();
            pr.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean updateUserFetch(User user){
        String query = "SELECT * FROM public.user WHERE EXISTS (SELECT * FROM public.user WHERE user_name=? AND id=?)";

        try {
            PreparedStatement pr = DatabaseConnector.getInstance().prepareStatement(query);
            pr.setString(1,user.getUserName());
            pr.setInt(2,user.getId());
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                return true;
            }
            pr.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static User logIn(String userName, String password){
        String query = "SELECT * FROM public.user WHERE user_name='"+userName+"' AND pass='"+password+"'";
        User obj = new User(-1,"F","F","admin","admin");
        try {
            PreparedStatement pr = DatabaseConnector.getInstance().prepareStatement(query);
            ResultSet rs = pr.executeQuery();
            if(rs.next()){
                obj = new User();
                obj.setId(rs.getInt("id"));
                obj.setName(rs.getString("name"));
                obj.setUserName(rs.getString("user_name"));
                obj.setPassword(rs.getString("pass"));
                obj.setUserType(rs.getString("user_type"));
            }
            pr.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
