package com.newsapp.Model;

public class Admin extends User{
    public Admin() {
    }

    public Admin(int id, String name, String userName, String password, String userType) {
        super(id, name, userName, password, userType);
    }
}
