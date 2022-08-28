package com.newsapp.View;

import com.newsapp.Helper.Helper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReadGUI extends JFrame {
    private JPanel wrapper;
    private JButton btnExit;
    private JLabel lblHeadline;
    private JTextArea areaText;
    private JLabel lblWriter;

    public ReadGUI(String headline, String text, String name){
        add(wrapper);
        setSize(500,600);
        int x = Helper.screenCenterPoint("x",getSize());
        int y = Helper.screenCenterPoint("y",getSize());
        setLocation(x,y);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("NEWS APP");
        setVisible(true);

        String[] names = name.split("[||]");
        lblWriter.setText(names[0]);
        lblHeadline.setText(headline);
        areaText.setText(text);
    }
}
