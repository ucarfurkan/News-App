package com.newsapp.View;

import com.newsapp.Helper.Helper;

import javax.swing.*;

public class WriterGUI extends JFrame {
    private JPanel wrapper;

    public WriterGUI(){
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
