package com.newsapp.View;

import com.newsapp.Helper.Helper;
import com.newsapp.Model.Category;
import com.newsapp.Model.News;
import com.newsapp.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Member;

public class MemberGUI extends JFrame{
    private JPanel wrapper;
    private JLabel lblWelcome;
    private JButton btnExit;
    private JPanel pnlBottom;
    private JPanel pnlSearch;
    private JButton btnSearch;
    private JTextField fldSearchHeadline;
    private JTextField fldSearchText;
    private JTextField fldSearchWriter;
    private JComboBox cmbSearchCategory;
    private JTable tblNews;
    private DefaultTableModel mdlNews;
    private Object[] rowNews;

    private final User member;

    public MemberGUI(User member){
        this.member = member;
        add(wrapper);
        setSize(1024,512);
        int x = Helper.screenCenterPoint("x",getSize());
        int y = Helper.screenCenterPoint("y",getSize());
        setLocation(x,y);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("NEWS APP");
        setVisible(true);

        cmbSearchCategory.addItem(" ");
        Category.updateCategoryCombo(cmbSearchCategory);


        // model news
        mdlNews = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        Object[] colNewsList = {"Headline","Text","Writer","Category","Read","Like"};
        mdlNews.setColumnIdentifiers(colNewsList);
        rowNews = new Object[colNewsList.length];
        loadNewsModel();
        tblNews.setModel(mdlNews);
        tblNews.getTableHeader().setReorderingAllowed(false);
        Helper.centerCells(tblNews);
        tblNews.getColumnModel().getColumn(3).setMaxWidth(200);
        tblNews.getColumnModel().getColumn(3).setMaxWidth(75);
        tblNews.getColumnModel().getColumn(4).setMaxWidth(75);
        tblNews.getColumnModel().getColumn(5).setMaxWidth(75);
    }

    public void loadNewsModel(){
        DefaultTableModel clearModel = (DefaultTableModel) tblNews.getModel();
        clearModel.setRowCount(0);

        for(News news : News.getList()){
            rowNews[0] = news.getHeadline();
            rowNews[1] = news.getText();
            rowNews[2] = news.getWriterId();
            rowNews[3] = Category.getCategoryName(String.valueOf(news.getCategoryId()));
            mdlNews.addRow(rowNews);
        }
    }

    public static void main(String[] args) {
        User user = new User();
        MemberGUI memberGUI = new MemberGUI(user);
    }
}
