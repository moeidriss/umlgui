/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

import java.util.ArrayList;


public class Actor extends UmlElement  implements java.io.Serializable , BusinessObjectOwner{

    public Actor() {
        super("Actor");
        setName("New Actor");
    }

    
    ArrayList<BusinessObject> businessObjects = new ArrayList();

    public ArrayList<BusinessObject> getBusinessObjects() {
        return businessObjects;
    }
    
    @Override
    public UmlCoreElement clone() {
        Actor x = new Actor();
        x.setName(name);
        return x;
    }
}
