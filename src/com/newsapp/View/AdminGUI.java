package com.newsapp.View;

import com.newsapp.Helper.Helper;
import com.newsapp.Model.Admin;
import com.newsapp.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
    private JComboBox cmbUserType;
    private JButton btnUserAdd;
    private JButton btnUserUpdate;
    private JButton btnUserDelete;
    private JScrollPane scrl;
    private JPanel fldUsers;
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
        tblUsers.getColumnModel().getColumn(0).setMaxWidth(75);
        tblUsers.getColumnModel().getColumn(4).setMaxWidth(100);

        // add user button
        btnUserAdd.addActionListener(e -> {
            if(Helper.isFieldEmpty(fldUserFullName) || Helper.isFieldEmpty(fldUserPassword) || Helper.isFieldEmpty(fldUserUName)
            || cmbUserType.getSelectedItem().equals("")){
                Helper.showMessage("fill");
            }
            else {
                User u = new User(fldUserFullName.getText(), fldUserUName.getText(), fldUserPassword.getText(),
                        cmbUserType.getSelectedItem().toString());
                if (User.add(u)) {
                    Helper.showMessage("done");
                    loadUserModel();
                    Helper.clearFields(fldUserFullName, fldUserUName, fldUserPassword);
                    cmbUserType.setSelectedItem("");
                } else {
                    Helper.showMessage("This username is used.");
                }
            }
        });

        // right click on table
        tblUsers.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = tblUsers.rowAtPoint(e.getPoint());

                if(row >= 0 && row < tblUsers.getRowCount()){
                    tblUsers.setRowSelectionInterval(row,row);
                }
                else{
                    tblUsers.clearSelection();
                }
                int rowIndex = tblUsers.getSelectedRow();
                if(rowIndex != -1){
                    String name = tblUsers.getModel().getValueAt(rowIndex,1).toString();
                    String userName = tblUsers.getModel().getValueAt(rowIndex,2).toString();
                    String password = tblUsers.getModel().getValueAt(rowIndex,3).toString();
                    String userType = tblUsers.getModel().getValueAt(rowIndex,4).toString();

                    Helper.fillField(fldUserFullName,name);
                    Helper.fillField(fldUserUName,userName);
                    Helper.fillField(fldUserPassword,password);
                    Helper.fillField(cmbUserType,userType);
                    btnUserAdd.setEnabled(false);
                    btnUserUpdate.setEnabled(true);
                    btnUserDelete.setEnabled(true);
                }
                else{
                    Helper.clearFields(fldUserFullName,fldUserUName,fldUserPassword);
                    cmbUserType.setSelectedItem("");
                    btnUserAdd.setEnabled(true);
                    btnUserUpdate.setEnabled(false);
                    btnUserDelete.setEnabled(false);
                }
            }
        });
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
