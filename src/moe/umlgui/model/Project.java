/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
    ArrayList<UmlCoreElement> coreElementList = new ArrayList();
    
    public HashMap<Long,UmlCoreElement> getCoreElementMap() {
        return coreElementMap;
    }
    
    public ArrayList<UmlElement> getElementList(){
        ArrayList elementList = new ArrayList();
        for(Iterator i = coreElementList.iterator() ; i.hasNext() ;){
            Object o = i.next();
            if(UmlElement.class.isInstance(o))  elementList.add(o);
        }
        return elementList;
    }
    
    public ArrayList<UmlCoreElement> getCoreElementList(){
        return coreElementList;
    }
    
    public ArrayList<Activity> getActivityList(){
        ArrayList activityList = new ArrayList();
        for(Iterator i = coreElementList.iterator() ; i.hasNext() ;){
            Object o = i.next();
            if(Activity.class.isInstance(o))  activityList.add(o);
        }
        return activityList;
    }
    
    
    public ArrayList<UmlRelationship> getRelationshipList(){
        ArrayList relationshipList = new ArrayList();
        for(Iterator i = coreElementList.iterator() ; i.hasNext() ;){
            Object o = i.next();
            if(UmlRelationship.class.isInstance(o))  relationshipList.add(o);
        }
        return relationshipList;
    }
    
    public ArrayList<CoreObject> getBusinessObjects(){
        ArrayList<CoreObject> ar = new ArrayList();
        for(Iterator i = coreElementList.iterator() ; i.hasNext() ;){
            Object o = i.next();
            if(BusinessObjectOwner.class.isInstance(o))
                ar.addAll(((BusinessObjectOwner)o).getBusinessObjects());
        }
        return ar;
    }
    
    public ArrayList<CoreObject> getControllers(){
        ArrayList<CoreObject> ar = new ArrayList();
        for(Iterator i = coreElementList.iterator() ; i.hasNext() ;){
            Object o = i.next();
            if(ControllerOwner.class.isInstance(o))
                ar.addAll(((ControllerOwner)o).getControllers());
        }
        return ar;
    }
    
    public ArrayList<CoreObject> getCoreObjects(){
        ArrayList<CoreObject> ar = new ArrayList();
        ar.addAll(getBusinessObjects());
        ar.addAll(getControllers());
        return ar;
    }
    
    
    //TODO update from UmlDiagram.add
    public void addCoreElement(UmlCoreElement umlCoreElement){
        coreElementMap.put(umlCoreElement.getId(), umlCoreElement);        
        coreElementList.add(umlCoreElement);
    }
    
    
    public Project(){
    }
    
    ArrayList<UmlModel> models =new ArrayList();

    public ArrayList<UmlModel> getModels() {
        return models;
    }
    
    
    public String toString(){
        return name;
    }
    
}
