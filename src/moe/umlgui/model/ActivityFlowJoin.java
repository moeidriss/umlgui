/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *TODO backward repeat, break
 * @author Moe
 */
public class ActivityFlowJoin extends ControlNode implements java.io.Serializable{
    
    public ActivityFlowJoin() {
        super("Join");
        setName("New Join");
    }

    ArrayList<ActivityFlow> activityFlows =  new ArrayList();
    

    public ArrayList<ActivityFlow> getActivityFlows() {
        return activityFlows;
    }

    
    
    
    
    
    @Override
    public UmlCoreElement clone() {
        ActivityFlowJoin x = new ActivityFlowJoin();
        x.setName(name);
        return x;
    }
}
