/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.ui;

import moe.umlgui.model.*;

/**
 *
 * @author Moe
 */
public class ViewException extends Exception{
    public ViewException(Exception e){
        super(e);
    }
    public ViewException(String s , Exception e){
        super(s , e);
    }
    public ViewException(String s){
        super(s);
    }
}
