package com.newsapp.View;

import com.newsapp.Helper.Helper;
import com.newsapp.Model.User;

import javax.swing.*;

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
    private JComboBox comboBox1;
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




    }

    public static void main(String[] args) {
        User writer = new User("Writer Name","writerUname","Password","writer");
        WriterGUI writerGUI = new WriterGUI(writer);
    }


}
