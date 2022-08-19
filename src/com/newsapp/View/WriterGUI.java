package com.newsapp.View;

import com.newsapp.Helper.Helper;
import com.newsapp.Model.Category;
import com.newsapp.Model.User;
import com.newsapp.Model.Writer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WriterGUI extends JFrame {
    private JPanel wrapper;
    private JButton btnExit;
    private JLabel lblWelcome;
    private JPanel pnlBottom;
    private JTabbedPane tabWriter;
    private JPanel pnlAddNews;
    private JPanel pnlPostedNews;
    private JTextField fldNewsHeadline;
    private JTextArea areaNewsText;
    private JComboBox cmbCategoryList;
    private JButton btnPostNewNews;
    private JScrollPane scrlText;

    private final User writer;

    public WriterGUI(User writer){
        this.writer = writer;
        add(wrapper);
        setSize(1000,500);
        int x = Helper.screenCenterPoint("x",getSize());
        int y = Helper.screenCenterPoint("y",getSize());
        setLocation(x,y);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("NEWS APP");
        setVisible(true);
        lblWelcome.setText("Welcome, "+writer.getName());

        cmbCategoryList.addItem("");
        Category.updateCategoryCombo(cmbCategoryList);

        // news post button
        btnPostNewNews.addActionListener(e -> {
            String headline = fldNewsHeadline.getText();
            String text = areaNewsText.getText();
            int categoryId = Category.getCategoryId(cmbCategoryList.getSelectedItem().toString());

            if(Helper.isFieldEmpty(fldNewsHeadline) || Helper.isFieldEmpty(areaNewsText) || cmbCategoryList.getSelectedItem().toString() == ""){
                Helper.showMessage("fill");
            }
            else{
                if(Writer.postNews(writer.getId(), headline,text,categoryId)){
                    Helper.showMessage("done");
                    Helper.clearFields(fldNewsHeadline,areaNewsText,cmbCategoryList);

                }
            }
        });

        // exit button
        btnExit.addActionListener(e -> {
            dispose();
            LoginGUI loginGUI = new LoginGUI();
        });
    }

    public static void main(String[] args) {
        User writer = new User("Writer Name","writerUname","Password","writer");
        WriterGUI writerGUI = new WriterGUI(writer);
    }


}
