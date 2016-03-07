/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kcl.keepitclean.main.GUI;


import javafx.scene.control.TextField;

/**
 *
 * @author rosiengo
 */
public class  IntegerTextField extends TextField{
   
    public IntegerTextField(){
      super();
    }
  
    @Override
    public void replaceText(int start, int end, String text) {
        String oldValue = getText();
        if ((validate(text))) {
            super.replaceText(start, end, text);
            String newText = super.getText();
            if (!validate(newText)) {
                super.setText(oldValue);
            }
        }
    }

    @Override
    public void replaceSelection(String text) {
        String oldValue = getText();
        if (validate(text)) {
            super.replaceSelection(text);
            String newText = super.getText();
            if (!validate(newText)) {
                super.setText(oldValue);
            }
        }
    }
    
    private Boolean validate(String text){
        if (text.matches("[0-9]{1,4}$"))
            return true;
        else
            return false;
    }
}
   
