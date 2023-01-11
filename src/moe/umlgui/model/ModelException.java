/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

/**
 *
 * @author Moe
 */
public class ModelException extends Exception{
    public ModelException(Exception e){
        super(e);
    }
    public ModelException(String s , Exception e){
        super(s , e);
    }
    public ModelException(String s){
        super(s);
    }
}
