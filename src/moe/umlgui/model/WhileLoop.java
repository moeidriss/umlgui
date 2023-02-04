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
        activityFlow =  new ActivityFlow(umlDiagram);
    }

    LogicalTest logicalTest = new LogicalTest();
    
    ActivityFlow activityFlow;
    


    public LogicalTest getLogicalTest() {
        return logicalTest;
    }

    public ActivityFlow getActivityFlow() {
        return activityFlow;
    }

    
    
    
    
    
    @Override
    public UmlCoreElement clone() {
        WhileLoop x = new WhileLoop();
        x.setName(name);
        return x;
    }
}
