/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

import java.util.ArrayList;


public class BusinessObject extends UmlElement  implements java.io.Serializable{

    public BusinessObject() {
        super("Business Object");
        setName("New Business Object");
    }

    ArrayList<String> properties = new ArrayList();//TODO Property class
    ArrayList<String> methods = new ArrayList();//TODO Method class

    public ArrayList getProperties() {
        return properties;
    }

    public ArrayList getMethods() {
        return methods;
    }
    
    
    
    @Override
    public UmlCoreElement clone() {
        BusinessObject x = new BusinessObject();
        x.setName(name);
        return x;
    }
}
