/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * TODO replace project as context...
 * @author Moe
 */
public abstract class UmlModel implements java.io.Serializable{
    
    String name = "model";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    Project project;

    public Project getProject() {
        return project;
    }
    
    public UmlModel (Project project){
        this.project = project;
    }
    
    
    ArrayList<UmlDiagram> diagrams =new ArrayList();

    public ArrayList<UmlDiagram> getDiagrams() {
        /*ArrayList<UmlDiagram> l = new ArrayList();
        for(Iterator<UmlDiagram> i = diagrams.iterator() ; i.hasNext() ;){
            UmlDiagram u = i.next();
            if(!u.isAttached()) l.add(u);
        }
        return l;*/return getAllDiagrams();
    }

    /* incluing attached diagrama */
    public ArrayList<UmlDiagram> getAllDiagrams() {
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
            if(BusinessObject.class.isInstance(o))  ar.add((CoreObject) o);
        }
        return ar;
    }
    
    public ArrayList<CoreObject> getControllers(){
        ArrayList<CoreObject> ar = new ArrayList();
        for(Iterator i = coreElementList.iterator() ; i.hasNext() ;){
            Object o = i.next();
            if(Controller.class.isInstance(o)) ar.add((CoreObject) o);
        }
        return ar;
    }
    
    public ArrayList<CoreObject> getCoreObjects(){
        ArrayList<CoreObject> ar = new ArrayList();
        for(Iterator i = coreElementList.iterator() ; i.hasNext() ;){
            Object o = i.next();
            if(CoreObject.class.isInstance(o)) ar.add((CoreObject) o);
        }
        return ar;
    }
    
    

    public void addCoreElement(UmlCoreElement umlCoreElement){
        if(!coreElementList.contains(umlCoreElement)){
            coreElementMap.put(umlCoreElement.getId(), umlCoreElement);        
            coreElementList.add(umlCoreElement);
        }
        if(project!=null)
            project.addCoreElement(umlCoreElement);
    }
    
    public void removeCoreElement(UmlCoreElement el){
        coreElementList.remove(el);
        coreElementMap.remove(el.getId());
        if(project!=null) project.removeCoreElement(el);
    }
    
    public ArrayList<Actor> getActors(){        
        ArrayList aList = new ArrayList();
        for(Iterator i = coreElementList.iterator() ; i.hasNext() ;){
            Object o = i.next();
            if(Actor.class.isInstance(o))  aList.add(o);
        }
        return aList;
    }
    
    
    
    public String toString(){
        return name;
    }
    
    public String dump(){
        StringBuffer sb = new StringBuffer();
        
        sb.append("Model.dump():").append(name).append("\n");
        sb.append("\tElements\n");
        for(UmlCoreElement e : this.coreElementList){
            sb.append(e.dump());
        }
        sb.append("\tDiagrams\n");
        for(UmlDiagram e : this.getDiagrams()){
            sb.append(e.dump());
        }
        return sb.toString();
    }
    
    
    /*populated  by checkConsistency */
    String consistencyCheck;

    public String getConsistencyCheck() {
        return consistencyCheck;
    }
    
    
    public boolean checkConsistency(){
        //TODO all CoreObject methods referenced in SeqD or ActD
        return false;
    }
}
