/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

import java.util.ArrayList;

/**
 *
 * @author Moe
 */
public class Action extends Activity implements java.io.Serializable , BusinessObjectOwner{
    
    public Action() {
        super("Action");
        setName("New Action");
    }
    
    
    ArrayList<BusinessObject> businessObjects = new ArrayList();

    public ArrayList<BusinessObject> getBusinessObjects() {
        return businessObjects;
    }

    @Override
    public UmlCoreElement clone() {
        Action x = new Action();
        x.setName(name);
        return x;
    }
}
