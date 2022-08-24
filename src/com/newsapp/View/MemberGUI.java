package com.newsapp.View;

import com.newsapp.Helper.Helper;
import com.newsapp.Model.Category;
import com.newsapp.Model.News;
import com.newsapp.Model.User;
import com.newsapp.Model.Writer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Member;
import java.util.ArrayList;

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
    private JPanel pnlTop;
    private DefaultTableModel mdlNews;
    private Object[] rowNews;
    JButton button = new JButton();

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
        Object[] colNewsList = {"Headline","Text","Writer & ID","Category",""};
        mdlNews.setColumnIdentifiers(colNewsList);
        rowNews = new Object[colNewsList.length];
        loadNewsModel();
        tblNews.setModel(mdlNews);
        tblNews.getTableHeader().setReorderingAllowed(false);
        Helper.centerCells(tblNews);
        tblNews.setRowHeight(20);
        tblNews.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());
        tblNews.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JCheckBox()));
        tblNews.getColumnModel().getColumn(4).setMaxWidth(75);

        button.addActionListener(
                event -> System.out.println("read")
        );

        // news search button
        btnSearch.addActionListener(e -> {

            String headline = fldSearchHeadline.getText();
            String text = fldSearchText.getText();
            String writer = fldSearchWriter.getText();
            String category = cmbSearchCategory.getSelectedItem().toString();
            String query = News.searchQuery(writer,category,headline,text);
            ArrayList<News> searchingNews = News.searchNewsList(query);
            loadNewsModel(searchingNews);
        });
    }


    class ButtonRenderer extends JButton implements TableCellRenderer
    {
        public ButtonRenderer() {
            setOpaque(true);
        }
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "Read" : value.toString());
            return this;
        }
    }
    class ButtonEditor extends DefaultCellEditor
    {
        private String label;

        public ButtonEditor(JCheckBox checkBox)
        {
            super(checkBox);
        }
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column)
        {
            label = (value == null) ? "Read" : value.toString();
            button.setText(label);
            return button;
        }
        public Object getCellEditorValue()
        {
            return new String(label);
        }
    }

    public void loadNewsModel(){
        DefaultTableModel clearModel = (DefaultTableModel) tblNews.getModel();
        clearModel.setRowCount(0);

        for(News news : News.getList()){
            rowNews[0] = news.getHeadline();
            rowNews[1] = news.getText();
            rowNews[2] = Writer.getWritersName(news.getWriterId()) + " | " + news.getWriterId();
            rowNews[3] = Category.getCategoryName(String.valueOf(news.getCategoryId()));
            mdlNews.addRow(rowNews);
        }
    }

    public void loadNewsModel(ArrayList<News> list){
        DefaultTableModel clearModel = (DefaultTableModel) tblNews.getModel();
        clearModel.setRowCount(0);

        for(News news: list){
            rowNews[0] = news.getHeadline();
            rowNews[1] = news.getText();
            rowNews[2] = Writer.getWritersName(news.getWriterId());
            rowNews[3] = Category.getCategoryName(String.valueOf(news.getCategoryId()));
            mdlNews.addRow(rowNews);
        }
    }

    public static void main(String[] args) {
        User user = new User();
        MemberGUI memberGUI = new MemberGUI(user);
    }
}
