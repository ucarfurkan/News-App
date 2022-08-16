package com.newsapp.View;

import com.newsapp.Helper.Helper;
import com.newsapp.Model.Admin;
import com.newsapp.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {
    private JPanel wrapper;
    private JPanel pnlLogin;
    private JTextField fldLogInUserName;
    private JTextField fldLogInPassword;
    private JButton btnLogIn;
    private JButton btnSignUp;

    private User user;

    public LoginGUI(){
        add(wrapper);
        setSize(300,400);
        int x = Helper.screenCenterPoint("x",getSize());
        int y = Helper.screenCenterPoint("y",getSize());
        setLocation(x,y);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("NEWS APP");
        setVisible(true);

        // login button
        btnLogIn.addActionListener(e -> {
            if(Helper.isFieldEmpty(fldLogInPassword) || Helper.isFieldEmpty(fldLogInUserName)){
                Helper.showMessage("fill");
            }
            else{
                User user = User.logIn(fldLogInUserName.getText(),fldLogInPassword.getText());
                if(user.getId() == -1){
                    Helper.showMessage("Username or password is incorrect.");
                    Helper.clearFields(fldLogInPassword);
                    Helper.clearFields(fldLogInUserName);
                }else{
                    dispose();
                    String userType = user.getUserType();
                    switch (userType){
                        case "admin":
                            AdminGUI adminGUI = new AdminGUI(user);
                            break;
                        case "writer":
                            Helper.showMessage("Welcome Writer");
                            break;
                        case "member":
                            Helper.showMessage("Welcome Member");
                            break;
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        LoginGUI loginGUI = new LoginGUI();
    }
}
