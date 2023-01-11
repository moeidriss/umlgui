/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Moe
 */
public class WhileLoop extends ControlNode implements java.io.Serializable{
    
    public WhileLoop() {
        super("While Loop");
        setName("New While Loop");
    }

    LogicalTest logicalTest = new LogicalTest();
    
    ArrayList<Activity> activityFlow =  new ArrayList(){
        public String toString(){
            StringBuffer sb = new StringBuffer();
            for(Iterator<Activity> i = iterator() ; i.hasNext() ; ){
                sb.append(i.next());
                if(i.hasNext()) sb.append("->");
            }
            return sb.toString();
        }
    };
    
    //TODO convert Activity to Activity Ordered/Linked set/map

    public LogicalTest getLogicalTest() {
        return logicalTest;
    }

    public ArrayList<Activity> getActivityFlow() {
        return activityFlow;
    }

    
    
    
    
    
    @Override
    public UmlCoreElement clone() {
        WhileLoop x = new WhileLoop();
        x.setName(name);
        return x;
    }
}
