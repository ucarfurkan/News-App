package com.newsapp.View;

import com.newsapp.Helper.Helper;
import com.newsapp.Model.User;

import javax.swing.*;

public class MemberGUI extends JFrame{
    private JPanel wrapper;

    private final User member;

    public MemberGUI(User member){
        this.member = member;
        add(wrapper);
        setSize(300,400);
        int x = Helper.screenCenterPoint("x",getSize());
        int y = Helper.screenCenterPoint("y",getSize());
        setLocation(x,y);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("NEWS APP");
        setVisible(true);
    }
}