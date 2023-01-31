/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;


public class ActivityDiagram extends UmlDiagram implements java.io.Serializable{
    public ActivityDiagram(UmlModel model){
        super("Activity Diagram",model);
        setName("New Activity Diagram");
    }
    
    HashMap<String,ArrayList<UmlCoreElement>> swimlanes = new HashMap();

    public HashMap<String,ArrayList<UmlCoreElement>> getSwimlanes() {
        if(autoActorsForSwimlanes){
            for(Iterator<Actor> i = umlModel.getActors().iterator() ; i.hasNext() ;){
                Actor a = i.next();
                java.lang.System.out.println(a);
                if(!swimlanes.containsKey(a.getName()))
                    swimlanes.put(a.getName(), new ArrayList());
            }
            //TODO actors with same name??
            //TODO cleanup removed actors
            
        }
        
        return swimlanes;
    }
    
    public void addToSwimlane(UmlCoreElement el , String swimlane){
        //add to list and remove from all others //TODO
        swimlanes.get(swimlane).add(el);
    }
    
    public String getSwimlane(UmlCoreElement el){
        for(String ss : swimlanes.keySet()){
            ArrayList l = swimlanes.get(ss);
            if(l.contains(el))  return ss;
        }
        return null;
    }
    
    //------------------------------ OPTIONS ---------------------------------
    boolean autoActorsForSwimlanes = true;
    boolean useSwimlanes = true;

    public boolean isUseSwimlanes() {
        return useSwimlanes;
    }

    public void setUseSwimlanes(boolean useSwimlanes) {
        this.useSwimlanes = useSwimlanes;
    }
    


    public boolean isAutoActorsForSwimlanes() {
        return autoActorsForSwimlanes;
    }

    public void setAutoActorsForSwimlanes(boolean autoActorsForSwimlanes) {
        this.autoActorsForSwimlanes = autoActorsForSwimlanes;
    }
    
    
    
    
    //------------------------------------------------------------------------
    
}
