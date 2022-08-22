package com.newsapp.View;

import com.newsapp.Helper.Helper;
import com.newsapp.Model.Category;
import com.newsapp.Model.News;
import com.newsapp.Model.User;
import com.newsapp.Model.Writer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.*;

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
    private JTable tblWritersNews;
    private JButton btnPostedNewsUpdate;
    private JTextField fldSelectedId;
    private DefaultTableModel mdlWritersNews;
    Object[] rowWritersNews;

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
                    loadNewsModel();
                    Helper.clearFields(fldNewsHeadline,areaNewsText,cmbCategoryList);

                }
            }
        });


        // exit button
        btnExit.addActionListener(e -> {
            dispose();
            LoginGUI loginGUI = new LoginGUI();
        });

        // model writers news
        mdlWritersNews = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        Object[] colWritersList = {"ID","Category ID","Headline","Text","Like Count"};
        mdlWritersNews.setColumnIdentifiers(colWritersList);
        rowWritersNews = new Object[colWritersList.length];
        loadNewsModel();
        tblWritersNews.setModel(mdlWritersNews);
        tblWritersNews.getTableHeader().setReorderingAllowed(false);
        Helper.centerCells(tblWritersNews);;
        tblWritersNews.getColumnModel().getColumn(0).setMaxWidth(75);
        tblWritersNews.getColumnModel().getColumn(1).setMaxWidth(75);
        tblWritersNews.getColumnModel().getColumn(4).setMaxWidth(75);


        // click news table
        tblWritersNews.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = tblWritersNews.rowAtPoint(e.getPoint());

                if(row >= 0 && row < tblWritersNews.getRowCount()){
                    tblWritersNews.setRowSelectionInterval(row,row);
                }
                else{
                    tblWritersNews.clearSelection();
                }
                int rowIndex = tblWritersNews.getSelectedRow();
                if(rowIndex != -1){
                    String newsId = tblWritersNews.getModel().getValueAt(rowIndex,0).toString();

                    Helper.fillField(fldSelectedId,newsId);
                    btnPostedNewsUpdate.setEnabled(true);
                }
                else{
                    Helper.clearFields(fldSelectedId);
                    btnPostedNewsUpdate.setEnabled(false);
                }
            }
        });

        // news update
        btnPostedNewsUpdate.addActionListener(e -> {
            String id = fldSelectedId.getText();
            UpdateNewsGUI updateNewsGUI = new UpdateNewsGUI(id);
            updateNewsGUI.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadNewsModel();
                }
            });
        });
    }

    public void loadNewsModel(){
        DefaultTableModel clearModel = (DefaultTableModel) tblWritersNews.getModel();
        clearModel.setRowCount(0);

        for(News writerNews : Writer.getList(writer.getId())){
            rowWritersNews[0] = writerNews.getId();
            rowWritersNews[1] = writerNews.getCategoryId();
            rowWritersNews[2] = writerNews.getHeadline();
            rowWritersNews[3] = writerNews.getText();
            rowWritersNews[4] = writerNews.getLikeCount();
            mdlWritersNews.addRow(rowWritersNews);
        }

    }


    public static void main(String[] args) {
        User writer = new User("Writer Name","writerUname","Password","writer");
        WriterGUI writerGUI = new WriterGUI(writer);
    }


}
