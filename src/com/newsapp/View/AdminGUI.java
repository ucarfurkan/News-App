package com.newsapp.View;

import com.newsapp.Helper.Helper;
import com.newsapp.Model.Admin;
import com.newsapp.Model.Category;
import com.newsapp.Model.News;
import com.newsapp.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AdminGUI extends JFrame {

    private JPanel wrapper;
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
    private JPanel pnlUsers;
    private JTextField fldUserId;
    private JPanel pnlUserSearch;
    private JPanel pnlUserNameSrch;
    private JPanel pnlUserUnameSrch;
    private JPanel pnlUserTypeSrch;
    private JTextField fldUserNameSrch;
    private JTextField fldUserUnameSrch;
    private JComboBox cmbUserTypeSrch;
    private JButton btnUserSrch;
    private JTable tblNews;
    private JPanel pnlNews;
    private JScrollPane scrlNews;
    private JPanel pnlBottom;
    private JScrollPane scrlUsers;
    private JButton btnExit;
    private JLabel lblWelcomed;
    private JTextField fldNewsIdDelete;
    private JButton btnNewsDelete;
    private JPanel pnlCategories;
    private JTable tblCategories;
    private JScrollPane scrlCategories;
    private JTextField fldCategoryId;
    private JTextField fldCategoryName;
    private JButton btnCategoryAdd;
    private JButton btnCategoryDelete;
    private JPanel pnlNewsDelete;
    private JPanel pnlNewsSearch;
    private JButton btnNewsSrch;
    private JTextField fldWriterNameSrch;
    private JComboBox cmbCategorySrch;
    private JTextField fldHeadlineSrch;
    private JTextField fldTextSrch;
    private DefaultTableModel mdlUserList;
    private Object[] rowUserList;
    private DefaultTableModel mdlNewsList;
    private Object[] rowNewsList;
    private DefaultTableModel mdlCategoryList;
    private Object[] rowCategoryList;


    private final User admin;

    public AdminGUI(User admin){
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

        lblWelcomed.setText("Welcome, "+ admin.getName());


        // ModelUserList
        mdlUserList = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
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
        Helper.centerCells(tblUsers);

        // ModelNewsList
        mdlNewsList = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Object[] colNewsList = {"ID","Writer ID","Category ID","Headline","Text"};
        mdlNewsList.setColumnIdentifiers(colNewsList);
        rowNewsList = new Object[colNewsList.length];
        loadNewsModel();
        tblNews.setModel(mdlNewsList);
        tblNews.getTableHeader().setReorderingAllowed(false);
        tblNews.getColumnModel().getColumn(0).setMaxWidth(75);
        tblNews.getColumnModel().getColumn(1).setMaxWidth(75);
        tblNews.getColumnModel().getColumn(2).setMaxWidth(75);
        Helper.centerCells(tblNews);

        // ModelCategoryList
        mdlCategoryList = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Object[] colCategoryList = {"ID","Category Name","News Count"};
        mdlCategoryList.setColumnIdentifiers(colCategoryList);
        rowCategoryList = new Object[colCategoryList.length];
        loadCategoryModel();
        tblCategories.setModel(mdlCategoryList);
        tblCategories.getTableHeader().setReorderingAllowed(false);
        Helper.centerCells(tblCategories);


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
                    loadNewsModel();
                    loadCategoryModel();
                    Helper.clearFields(fldUserFullName, fldUserUName, fldUserPassword);
                    cmbUserType.setSelectedItem("");
                } else {
                    Helper.showMessage("usedUname");
                }
            }
        });

        // click on table
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
                    String userID = tblUsers.getModel().getValueAt(rowIndex,0).toString();

                    Helper.fillField(fldUserId,userID);
                    Helper.fillField(fldUserFullName,name);
                    Helper.fillField(fldUserUName,userName);
                    Helper.fillField(fldUserPassword,password);
                    Helper.fillField(cmbUserType,userType);
                    btnUserUpdate.setEnabled(true);
                    btnUserDelete.setEnabled(true);
                }
                else{
                    Helper.clearFields(fldUserFullName,fldUserUName,fldUserPassword,fldUserId);
                    cmbUserType.setSelectedItem("");
                    btnUserUpdate.setEnabled(false);
                    btnUserDelete.setEnabled(false);
                }
            }
        });

        // update user button
        btnUserUpdate.addActionListener(e -> {
            if(Helper.isFieldEmpty(fldUserFullName) || Helper.isFieldEmpty(fldUserPassword) || Helper.isFieldEmpty(fldUserUName)
                    || cmbUserType.getSelectedItem().equals("")){
                Helper.showMessage("fill");
            }
            else {
                User u = new User(Integer.valueOf(fldUserId.getText()),fldUserFullName.getText(), fldUserUName.getText(), fldUserPassword.getText(),
                        cmbUserType.getSelectedItem().toString());

                if (User.update(u)) {
                    Helper.showMessage("done");
                    Helper.clearFields(fldUserFullName, fldUserUName, fldUserPassword,fldUserId);
                    cmbUserType.setSelectedItem("");
                }
                else{
                    Helper.showMessage("usedUname");
                }
                loadUserModel();
                loadNewsModel();
                loadCategoryModel();
            }
        });

        // delete user button
        btnUserDelete.addActionListener(e -> {
            if(Helper.isFieldEmpty(fldUserFullName) || Helper.isFieldEmpty(fldUserPassword) || Helper.isFieldEmpty(fldUserUName)
                    || cmbUserType.getSelectedItem().equals("")){
                Helper.showMessage("fill");
            }
            else{
                User u = new User(Integer.valueOf(fldUserId.getText()),fldUserFullName.getText(), fldUserUName.getText(), fldUserPassword.getText(),
                        cmbUserType.getSelectedItem().toString());
                if(Helper.confirm("Are you sure? Every news this user has added will be deleted.")){
                    if(News.deleteUsersNews(u.getId())){
                        if(User.delete(u)){
                            Helper.showMessage("done");
                            Helper.clearFields(fldUserFullName, fldUserUName, fldUserPassword,fldUserId);
                            cmbUserType.setSelectedItem("");
                        }
                        else{
                            Helper.showMessage("Username and id do not match. Update first or undo the changes.");
                        }
                    }

                }
                loadUserModel();
                loadNewsModel();
                loadCategoryModel();
            }
        });

        // user search button
        btnUserSrch.addActionListener(e -> {
                String name = fldUserNameSrch.getText();
                String uName = fldUserUnameSrch.getText();
                String userType = cmbUserTypeSrch.getSelectedItem().toString();
                String query = User.searchQuery(name,uName,userType);
                ArrayList<User> searchingUser = User.searchUserList(query);
                loadUserModel(searchingUser);
        });

        // click news table
        tblNews.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = tblNews.rowAtPoint(e.getPoint());

                if(row >= 0 && row < tblNews.getRowCount()){
                    tblNews.setRowSelectionInterval(row,row);
                }
                else{
                    tblNews.clearSelection();
                }
                int rowIndex = tblNews.getSelectedRow();
                if(rowIndex != -1){
                    String newsId = tblNews.getModel().getValueAt(rowIndex,0).toString();

                    Helper.fillField(fldNewsIdDelete,newsId);
                    btnNewsDelete.setEnabled(true);
                }
                else{
                    Helper.clearFields(fldNewsIdDelete);
                    btnNewsDelete.setEnabled(false);
                }
            }
        });

        // delete news button
        btnNewsDelete.addActionListener(e -> {
            if(Helper.isFieldEmpty(fldNewsIdDelete)){
                Helper.showMessage("fill");
            }
            else{
                if(Helper.confirm("sure")){
                    String selectedId = fldNewsIdDelete.getText();
                    if(News.delete(selectedId)){
                        Helper.showMessage("done");
                        Helper.clearFields(fldNewsIdDelete);
                    }
                    else{
                        Helper.showMessage("error");
                    }
                    loadNewsModel();
                    loadCategoryModel();
                }
            }
        });

        // click category table
        tblCategories.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = tblCategories.rowAtPoint(e.getPoint());

                if(row >= 0 && row < tblCategories.getRowCount()){
                    tblCategories.setRowSelectionInterval(row,row);
                }
                else{
                    tblCategories.clearSelection();
                }
                int rowIndex = tblCategories.getSelectedRow();
                if(rowIndex != -1){
                    String categoryId = tblCategories.getModel().getValueAt(rowIndex,0).toString();

                    Helper.fillField(fldCategoryId,categoryId);
                    btnCategoryDelete.setEnabled(true);
                }
                else{
                    Helper.clearFields(fldCategoryId);
                    btnCategoryDelete.setEnabled(false);
                }
            }
        });

        // category delete button
        btnCategoryDelete.addActionListener(e -> {
            if(Helper.isFieldEmpty(fldCategoryId)){
                Helper.showMessage("fill");
            }
            else{
                if(Helper.confirm("Are you sure? Every news with this category will lost. Please update them if you don't want to lose.")){
                    if(Category.deleteNewsInCategory(fldCategoryId.getText()))
                    if(Category.delete(fldCategoryId.getText())){
                        Helper.showMessage("done");
                        Helper.clearFields(fldCategoryId);
                    }
                    else{
                        Helper.showMessage("error");
                    }
                    loadNewsModel();
                    loadCategoryModel();
                }
            }
        });

        // category add button
        btnCategoryAdd.addActionListener(e -> {
            if(Helper.isFieldEmpty(fldCategoryName)){
                Helper.showMessage("fill");
            }
            else{
                if(Category.add(fldCategoryName.getText())){
                    Helper.showMessage("done");
                    Helper.clearFields(fldCategoryName);
                }
                else{
                    Helper.showMessage("error");
                }
                loadCategoryModel();
            }
        });

        // news search button
        btnNewsSrch.addActionListener(e -> {
            String name = fldWriterNameSrch.getText();
            String headline = fldHeadlineSrch.getText();
            String text = fldTextSrch.getText();
            String category = cmbCategorySrch.getSelectedItem().toString();
            String query = News.searchQuery(name,category,headline,text);
            ArrayList<News> searchingNews = News.searchNewsList(query);
            loadNewsModel(searchingNews);
        });

        // exit button
        btnExit.addActionListener(e -> {
            dispose();
            LoginGUI loginGUI = new LoginGUI();
        });
    }

    // loadUserModel
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

    public void loadUserModel(ArrayList<User> list){
        DefaultTableModel clearModel = (DefaultTableModel) tblUsers.getModel();
        clearModel.setRowCount(0);

        for(User user: list){
            rowUserList[0] = user.getId();
            rowUserList[1] = user.getName();
            rowUserList[2] = user.getUserName();
            rowUserList[3] = user.getPassword();
            rowUserList[4] = user.getUserType();
            mdlUserList.addRow(rowUserList);
        }
    }

    // load news model
    public void loadNewsModel(){
        DefaultTableModel clearModel = (DefaultTableModel) tblNews.getModel();
        clearModel.setRowCount(0);

        for(News news : News.getList()){
            rowNewsList[0] = news.getId();
            rowNewsList[1] = news.getWriterId();
            rowNewsList[2] = news.getCategoryId();
            rowNewsList[3] = news.getHeadline();
            rowNewsList[4] = news.getText();
            mdlNewsList.addRow(rowNewsList);
        }
    }

    public void loadNewsModel(ArrayList<News> list){
        DefaultTableModel clearModel = (DefaultTableModel) tblNews.getModel();
        clearModel.setRowCount(0);

        for(News news: list){
            rowNewsList[0] = news.getId();
            rowNewsList[1] = news.getWriterId();
            rowNewsList[2] = news.getCategoryId();
            rowNewsList[3] = news.getHeadline();
            rowNewsList[4] = news.getText();
            mdlNewsList.addRow(rowNewsList);
        }
    }

    // load category model
    public void loadCategoryModel(){
        DefaultTableModel clearModel = (DefaultTableModel) tblCategories.getModel();
        clearModel.setRowCount(0);

        for(Category category : Category.getList()){
            rowCategoryList[0] = category.getId();
            rowCategoryList[1] = category.getName();
            rowCategoryList[2] = Category.getNewsCount(category.getId());
            mdlCategoryList.addRow(rowCategoryList);
        }
        cmbCategorySrch.removeAllItems();
        cmbCategorySrch.addItem("");


        Category.updateCategoryCombo(cmbCategorySrch);
    }

    public static void main(String[] args) {
        Admin admin = new Admin();
        AdminGUI adminGUI = new AdminGUI(admin);
    }

}
