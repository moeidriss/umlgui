/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Moe
 */
public class CoreObjectMethod implements java.io.Serializable{
    
    
    public static String VOID_RETURN_TYPE = "Void";
    public static String INTEGER_RETURN_TYPE = "Integer";
    public static String NUMBER_RETURN_TYPE = "Number";
    public static String STRING_RETURN_TYPE = "String";
    public static String DATE_RETURN_TYPE = "Date";
    public static String BOOLEAN_RETURN_TYPE = "Boolean";
    
    public static String[] RETURN_TYPES = {VOID_RETURN_TYPE ,
                                        INTEGER_RETURN_TYPE ,
                                        NUMBER_RETURN_TYPE ,
                                        STRING_RETURN_TYPE ,
                                        DATE_RETURN_TYPE ,
                                        BOOLEAN_RETURN_TYPE};
    
    String name;
    String returnType;
    HashMap<String,String> parameters = new HashMap(){
        public String toString(){
            return "<params>";
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public HashMap<String,String> getParameters() {
        return parameters;
    }
    
    CoreObject businessObject;

    public CoreObject getBusinessObject() {
        return businessObject;
    }

    public CoreObjectMethod(CoreObject businessObject) {
        this.businessObject = businessObject;
    }
    
    public CoreObjectMethod(String name, String returnType, CoreObject businessObject) {
        this.name = name;
        this.returnType = returnType;
        this.businessObject = businessObject;
    }
    
    
    
    public String toString(){
        return name + "()";
    }
    
}
