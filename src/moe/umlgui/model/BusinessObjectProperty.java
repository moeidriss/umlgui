/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

/**
 *
 * @author Moe
 */
public class BusinessObjectProperty implements java.io.Serializable{
 
    public static String INTEGER_DATA_TYPE = "Integer";
    public static String NUMBER_DATA_TYPE = "Number";
    public static String STRING_DATA_TYPE = "String";
    public static String DATE_DATA_TYPE = "Date";
    public static String BOOLEAN_DATA_TYPE = "Boolean";
    
    public static String[] DATA_TYPES = {INTEGER_DATA_TYPE ,
                                        NUMBER_DATA_TYPE ,
                                        STRING_DATA_TYPE ,
                                        DATE_DATA_TYPE ,
                                        BOOLEAN_DATA_TYPE};
    
    
    String name;
    String dataType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
    
    
    BusinessObject businessObject;

    public BusinessObject getBusinessObject() {
        return businessObject;
    }

    public BusinessObjectProperty(BusinessObject businessObject) {
        this.businessObject = businessObject;
    }

    
    public BusinessObjectProperty(String name, String dataType, BusinessObject businessObject) {
        this.name = name;
        this.dataType = dataType;
        this.businessObject = businessObject;
    }
    
    public String toString(){
        return name;
    }
}
