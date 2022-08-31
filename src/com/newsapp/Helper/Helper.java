package com.newsapp.Helper;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
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

    public static boolean isFieldEmpty(JTextArea area){
        if(area.getText().length()==0){
            return true;
        }
        else{
            return false;
        }
    }

    public static void clearFields(JComponent... args){
        for(Object obj : args){
            if(obj instanceof JTextField){
                ((JTextField) obj).setText("");
            }
            else if(obj instanceof JTextArea){
                ((JTextArea) obj).setText("");
            }
            else if(obj instanceof JComboBox){
                ((JComboBox<?>) obj).setSelectedItem("");
            }
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

    public static boolean confirm(String str){
        String message;

        switch(str) {
            case "sure":
                message = "Are you sure you want to delete it?";
                break;
            default:
                message=str;
        }
        return JOptionPane.showConfirmDialog(null,message,"Confirm",JOptionPane.YES_NO_OPTION) == 0;
    }

    public static void centerCells(JTable table){
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for(int i=0; i< table.getColumnCount(); i++){
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }


}
