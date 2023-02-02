/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

import java.util.ArrayList;


public class SequenceDiagram extends UmlDiagram implements java.io.Serializable{
    public SequenceDiagram(UmlModel model){
        super("Sequence Diagram",model);
        setName("New Sequence Diagram");
    }
    
    //TODO HashMap userDelegates:: cntrl:Actor 

    public ArrayList<Actor> getActors() {
        ArrayList l = new ArrayList();
        for(UmlCoreElement e : coreElementList){
            if(Actor.class.isInstance(e))   l.add(e);
        }
        return l;
    }

    //actors created for CoreObjects & are keys in constraints
    public ArrayList getObjectActors(){
        ArrayList l = new ArrayList();
        for(Actor a : getActors()){
            if(constraints.containsKey(a)){
                l.add(a);
            }
        }
        return l;
    }
    
    
    
    

}
