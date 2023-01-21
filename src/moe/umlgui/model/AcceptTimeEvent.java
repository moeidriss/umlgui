/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * @author Moe
 */
public class AcceptTimeEvent extends Activity implements java.io.Serializable, BusinessObjectOwner, ControllerOwner , AttachmentOwner{
    
    public AcceptTimeEvent() {
        super("Accept Time Event");
        setName("New Accept Time Event");
    }

    
    
    HashSet<CoreObject> businessObjects = new HashSet(){
        public String toString(){
            return "Business Objects";
        }
    };

    public HashSet<CoreObject> getBusinessObjects() {
        return businessObjects;
    }
    
    
    
    HashSet<CoreObject> controllers = new HashSet(){
        public String toString(){
            return "Controllers";
        }
    };

    public HashSet<CoreObject> getControllers() {
        return controllers;
    }
    
    
    
    ArrayList<UmlDiagram> attachedDiagrams = new ArrayList(){
        public String toString(){
            return "Attached Diagrams";
        }
    };
    
    public ArrayList<UmlDiagram> getAttachedDiagrams(){
        return attachedDiagrams;
    }
    
    
    @Override
    public UmlCoreElement clone() {
        AcceptTimeEvent x = new AcceptTimeEvent();
        x.setName(name);
        return x;
    }
}
