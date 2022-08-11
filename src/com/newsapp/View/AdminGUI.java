package com.newsapp.View;

import com.newsapp.Helper.Helper;
import com.newsapp.Model.Admin;

import javax.swing.*;

public class AdminGUI extends JFrame {

    private JPanel wrapper;
    private JPanel pnlTop;
    private JLabel lblWelcome;
    private JButton btnExit;
    private JTabbedPane tabAdmin;
    private JTable tblUsers;
    private JLabel lblUserFullName;
    private JTextField fldUserFullName;
    private JLabel lblUserUName;
    private JTextField fldUserUName;
    private JTextField fldUserPassword;
    private JPanel pnlUserAdd;
    private JLabel lblUserUType;
    private JComboBox comboBox1;
    private JButton btnUserAdd;
    private final Admin admin;

    public AdminGUI(Admin admin){
        // screen sets
        this.admin = admin;
        add(wrapper);
        setSize(1000,500);
        int x = Helper.screenCenterPoint("x",getSize());
        int y = Helper.screenCenterPoint("y",getSize());
        setLocation(x,y);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("NEWS");
        setVisible(true);

        lblWelcome.setText("Welcome, " + admin.getName());
    }




    public static void main(String[] args) {
        Admin admin = new Admin(1,"Furkan Uçar","Furkan","qwdqwd","admin");
        AdminGUI adminGUI = new AdminGUI(admin);
    }
}
