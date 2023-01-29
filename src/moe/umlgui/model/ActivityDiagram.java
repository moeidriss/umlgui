/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

import java.util.HashSet;


public class ActivityDiagram extends UmlDiagram implements java.io.Serializable{
    public ActivityDiagram(UmlModel model){
        super("Activity Diagram",model);
        setName("New Activity Diagram");
    }
    
    HashSet<String> swimlanes = new HashSet();

    public HashSet<String> getSwimlanes() {
        return swimlanes;
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
