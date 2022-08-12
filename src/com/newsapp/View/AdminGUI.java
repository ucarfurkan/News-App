package com.newsapp.View;

import com.newsapp.Helper.Helper;
import com.newsapp.Model.Admin;
import com.newsapp.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

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
    private DefaultTableModel mdlUserList;
    private Object[] rowUserList;


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

        // ModelUserList
        mdlUserList = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column == 0){
                    return false;
                }
                return super.isCellEditable(row, column);
            }
        };

        Object[] colUserList = {"ID","Name","Username","Password","User Type"};
        mdlUserList.setColumnIdentifiers(colUserList);
        rowUserList = new Object[colUserList.length];
        loadUserModel();
        tblUsers.setModel(mdlUserList);
        tblUsers.getTableHeader().setReorderingAllowed(false);
    }

    public void loadUserModel(){
        DefaultTableModel clearModel = (DefaultTableModel) tblUsers.getModel();
        clearModel.setRowCount(0);

        for(User user: User.getList()){
            rowUserList[0] = user.getId();
            rowUserList[1] = user.getName();
            rowUserList[2] = user.getUserName();
            rowUserList[3] = user.getPassword();
            rowUserList[4] = user.getUserType();
            mdlUserList.addRow(rowUserList);
        }
    }

    public static void main(String[] args) {
        Admin ad = new Admin();
        AdminGUI adminGUI = new AdminGUI(ad);
    }


}
