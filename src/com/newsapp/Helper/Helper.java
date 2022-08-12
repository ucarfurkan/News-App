package com.newsapp.Helper;

import javax.swing.*;
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
}
