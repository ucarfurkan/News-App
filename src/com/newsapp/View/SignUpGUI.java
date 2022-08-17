package com.newsapp.View;

import com.newsapp.Helper.Helper;
import com.newsapp.Model.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpGUI extends JFrame{
    private JPanel wrapper;
    private JPanel pnlSignUp;
    private JTextField fldSignUpName;
    private JTextField fldSignUpUName;
    private JLabel Password;
    private JTextField fldSignUpPassword;
    private JButton btnSignUp;

    public SignUpGUI(){
        add(wrapper);
        add(wrapper);
        setSize(250,300);
        int x = Helper.screenCenterPoint("x",getSize());
        int y = Helper.screenCenterPoint("y",getSize());
        setLocation(x,y);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("NEWS APP");
        setVisible(true);

        btnSignUp.addActionListener(e -> {
            String name = fldSignUpName.getText();
            String uName = fldSignUpUName.getText();
            String password = fldSignUpPassword.getText();
            User user = new User(name,uName,password,"member");

            if(Helper.isFieldEmpty(fldSignUpName) || Helper.isFieldEmpty(fldSignUpUName) || Helper.isFieldEmpty(fldSignUpPassword)){
                Helper.showMessage("fill");
            }
            else{
                if(User.add(user)){
                    Helper.showMessage("done");
                    dispose();
                }
                else{
                    Helper.showMessage("usedUname");
                }
            }

        });
    }
}
