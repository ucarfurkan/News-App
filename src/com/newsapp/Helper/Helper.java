package com.newsapp.Helper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Helper {

    public static int screenCenterPoint(String axis, Dimension size){
        int point;
        switch(axis){
            case "x":
                point = (Toolkit.getDefaultToolkit().getScreenSize().width - size.width) / 2;
                break;
            case "y":
                point = (Toolkit.getDefaultToolkit().getScreenSize().height - size.height) / 2;
                break;
            default:
                point = 0;
        }
        return point;
    }

    public static boolean isFieldEmpty(JTextField field){
        if(field.getText().length()==0){
            return true;
        }
        else{
            return false;
        }
    }

    public static void clearFields(JTextField... args){
        for(JTextField field: args){
            field.setText("");
        }
    }

    public static void showMessage(String message){
        String msg = "";
        switch(message){
            case "fill":
                msg = "Please fill all the fields.";
                break;
            case "done":
                msg = "Operation successfully completed!";
                break;
            case "error":
                msg = "Something wrong happened.";
                break;
            case "usedUname":
                msg = "This username is used.";
                break;
            default:
                msg = message;
        }
        JOptionPane.showMessageDialog(null,msg,"Information",JOptionPane.INFORMATION_MESSAGE);
    }

    public static void fillField(JTextField field, String fill){
        field.setText(fill);
    }

    public static void fillField(JComboBox box, String fill){
        box.setSelectedItem(fill);
    }


}
