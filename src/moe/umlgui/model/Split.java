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
public class Split extends ControlNode implements java.io.Serializable{
    
    public Split() {
        super("Split");
        setName("New Split");
    }

    ArrayList<ActivityFlow> activityFlows =  new ArrayList();
    

    public ArrayList<ActivityFlow> getActivityFlows() {
        return activityFlows;
    }

    
    
    
    
    
    @Override
    public UmlCoreElement clone() {
        Split x = new Split();
        x.setName(name);
        return x;
    }
}
