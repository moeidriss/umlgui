/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

import java.util.ArrayList;


public class CoreObject extends UmlElement  implements java.io.Serializable{

    public CoreObject() {
        super("Business Object");
        setName("New Business Object");
    }

    ArrayList<CoreObjectProperty> properties = new ArrayList();//TODO Property class
    ArrayList<CoreObjectMethod> methods = new ArrayList();//TODO Method class

    public ArrayList<CoreObjectProperty> getProperties() {
        return properties;
    }

    public ArrayList<CoreObjectMethod> getMethods() {
        return methods;
    }
    
    
    
    @Override
    public UmlCoreElement clone() {
        CoreObject x = new CoreObject();
        x.setName(name);
        return x;
    }
}
