/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    UmlModel umlModel;

    public UmlModel getUmlModel() {
        return umlModel;
    }
    
    String name = "New diagram";
    
    public UmlDiagram(String type, UmlModel umlModel){
        this.type = type;
        this.umlModel = umlModel;
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
    
    String umlCode;

    public String getUmlCode() {
        return umlCode;
    }

    public void setUmlCode(String umlCode) {
        this.umlCode = umlCode;
    }
    
    
    HashMap<Long,UmlCoreElement> coreElementMap = new HashMap();
    //ArrayList<UmlElement> elementList = new ArrayList();
    ArrayList<UmlCoreElement> coreElementList = new ArrayList();
    /*ArrayList<Activity> activityList = new ArrayList();
    ArrayList<UmlRelationship> relationshipList = new ArrayList();
    ArrayList<ControlNode> controlNodeList = new ArrayList();*/
    
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

    public ArrayList<ControlNode> getControlNodeList() {
        ArrayList controlNodeList = new ArrayList();
        for(Iterator i = coreElementList.iterator() ; i.hasNext() ;){
            Object o = i.next();
            if(ControlNode.class.isInstance(o))  controlNodeList.add(o);
        }
        return controlNodeList;
    }
    
    
    //TODO throw ModelException instead
    /**
    override to enforce biz rules (element allowed, biz vs it model)
    */
    public void addCoreElement(UmlCoreElement umlCoreElement) throws ModelException{
        umlCoreElement.setUmlDiagram(this);
        coreElementMap.put(umlCoreElement.getId(), umlCoreElement);
        coreElementList.add(umlCoreElement);
        
        try {
            PUMLDriver.update(this);
        } catch (IOException ex) {
            throw new ModelException("Error updating PUML definition",ex);
        }
    }
    
    
    //TODO throw ModelException instead
    /**
    override to enforce biz rules (element allowed, biz vs it model)
    */
    public void insertCoreElement(int index , UmlCoreElement umlCoreElement) throws ModelException{
        umlCoreElement.setUmlDiagram(this);
        coreElementMap.put(umlCoreElement.getId(), umlCoreElement);
        coreElementList.add(index , umlCoreElement);
        
        try {
            PUMLDriver.update(this);
        } catch (IOException ex) {
            throw new ModelException("Error updating PUML definition",ex);
        }
    }
    
    public void moveUp(UmlElement el)throws ModelException{
        if(coreElementList.indexOf(el)==0)
            throw new ModelException("Can't move up");
        
        //find next element upward
        int newIndex = -1;
        for(int i=coreElementList.indexOf(el)-1 ; i > -1 ; i--){
            if(UmlElement.class.isInstance(coreElementList.get(i))){
                newIndex = i;
                break;
            }
        }
        
        if(newIndex != -1)
            Collections.swap(coreElementList, coreElementList.indexOf(el), newIndex);
        
        try {
            PUMLDriver.update(this);
        } catch (IOException ex) {
            throw new ModelException("Error updating PUML definition",ex);
        }
    }
    
    
    public void moveDown(UmlElement el)throws ModelException{
        if(coreElementList.indexOf(el)==(coreElementList.size()-1))
            throw new ModelException("Can't move down");
        
        //find next element upward
        int newIndex = -1;
        for(int i=coreElementList.indexOf(el)+1 ; i < coreElementList.size() ; i++){
            if(UmlElement.class.isInstance(coreElementList.get(i))){
                newIndex = i;
                break;
            }
        }
         
        if(newIndex != -1)
            Collections.swap(coreElementList, coreElementList.indexOf(el), newIndex);
        
        try {
            PUMLDriver.update(this);
        } catch (IOException ex) {
            throw new ModelException("Error updating PUML definition",ex);
        }
    }
    
    public String toString(){
        return name;
    }
    
}
