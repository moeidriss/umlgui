/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import moe.umlgui.controller.PUMLDriver;

/**
 *
 * @author Moe
 */
public abstract class UmlDiagram implements Serializable{
    String type;

    public String getType() {
        return type;
    }
    
    String name = "New diagram";
    
    public UmlDiagram(String type){
        this.type = type;
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    byte[] image;

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    
    HashMap<Long,UmlCoreElement> coreElementMap = new HashMap();
    ArrayList<UmlElement> elementList = new ArrayList();
    ArrayList<Activity> activityList = new ArrayList();
    ArrayList<UmlRelationship> relationshipList = new ArrayList();
    
    public HashMap<Long,UmlCoreElement> getCoreElementMap() {
        return coreElementMap;
    }
    
    public ArrayList<UmlElement> getElementList(){
        return elementList;
    }
    
    public ArrayList<Activity> getActivityList(){
        return activityList;
    }
    
    
    public ArrayList<UmlRelationship> getRelationshipList(){
        return relationshipList;
    }
    
    public void addCoreElement(UmlCoreElement umlCoreElement) throws IOException{
        coreElementMap.put(umlCoreElement.getId(), umlCoreElement);
        
        if(UmlElement.class.isInstance(umlCoreElement)){
            elementList.add((UmlElement)umlCoreElement);
            if(Activity.class.isInstance(umlCoreElement)){
                activityList.add((Activity)umlCoreElement);
            }
        }
        else if(UmlRelationship.class.isInstance(umlCoreElement)) 
            relationshipList.add((UmlRelationship)umlCoreElement);
        
        PUMLDriver.update(this);
    }
    
}
