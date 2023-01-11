/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import moe.umlgui.controller.PUMLDriver;

/**
 *
 * @author Moe
 */
public class Project implements java.io.Serializable{
    String name;
    File folder;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public File getFolder() {
        return folder;
    }

    public void setFolder(File folder) {
        this.folder = folder;
    }
    
    ArrayList<UmlDiagram> diagrams = new ArrayList();
    public ArrayList<UmlDiagram> getDiagrams() {
        return diagrams;
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
    
    //TODO update from UmlDiagram.add
    public void addCoreElement(UmlCoreElement umlCoreElement){
        coreElementMap.put(umlCoreElement.getId(), umlCoreElement);
        
        if(UmlElement.class.isInstance(umlCoreElement)){
            elementList.add((UmlElement)umlCoreElement);
            if(Activity.class.isInstance(umlCoreElement)){
                activityList.add((Activity)umlCoreElement);
            }
        }
        else if(UmlRelationship.class.isInstance(umlCoreElement)) 
            relationshipList.add((UmlRelationship)umlCoreElement);
    }
    
    
    public Project(){
        models.add(new BusinessModel(this));
    }
    
    ArrayList<UmlModel> models =new ArrayList();

    public ArrayList<UmlModel> getModels() {
        return models;
    }
    
    
    public String toString(){
        return name;
    }
    
}
