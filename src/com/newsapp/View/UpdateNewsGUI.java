package com.newsapp.View;

import com.newsapp.Helper.Helper;
import com.newsapp.Model.Category;
import com.newsapp.Model.News;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateNewsGUI extends JFrame {
    private JPanel wrapper;
    private JTextField fldNewsId;
    private JTextField fldNewsHeadline;
    private JTextArea areaNewsText;
    private JComboBox cmbNewsCategory;
    private JButton btnNewsUpdate;

    public UpdateNewsGUI(String id){
        add(wrapper);
        setSize(500,600);
        int x = Helper.screenCenterPoint("x",getSize());
        int y = Helper.screenCenterPoint("y",getSize());
        setLocation(x,y);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setTitle("NEWS APP");
        setVisible(true);
        fldNewsId.setText(id);

        News selectedNews = News.getSelectedNews(id);

        if(selectedNews != null){
            fldNewsId.setText(String.valueOf(selectedNews.getId()));
            fldNewsHeadline.setText(selectedNews.getHeadline());
            areaNewsText.setText(selectedNews.getText());
            Category.updateCategoryCombo(cmbNewsCategory);
            cmbNewsCategory.setSelectedItem(Category.getCategoryName(String.valueOf(selectedNews.getCategoryId())));
        }

        // update news
        btnNewsUpdate.addActionListener(e -> {
            String newsId = fldNewsId.getText();
            String headline = fldNewsHeadline.getText();
            String text = areaNewsText.getText();
            int categoryId = Category.getCategoryId(cmbNewsCategory.getSelectedItem().toString());
            System.out.println(categoryId);
            if(Helper.isFieldEmpty(fldNewsId) || Helper.isFieldEmpty(fldNewsHeadline) || Helper.isFieldEmpty(areaNewsText)){
                Helper.showMessage("fill");
            }
            else{
                if(News.update(newsId,headline,text, String.valueOf(categoryId))){
                    Helper.showMessage("done");
                }
                else{
                    Helper.showMessage("error");
                }
                dispose();
            }
        });
    }

}
