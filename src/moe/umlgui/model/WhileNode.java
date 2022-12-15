/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

import java.util.HashMap;

/**
 *
 * @author Moe
 */
public class WhileNode extends ControlNode implements java.io.Serializable{
    
    public WhileNode() {
        super("While Node");
        setName("New While Node");
    }

    LogicalTest logicalTest;
    Activity activity;
    //TODO convert Activity to Activity Ordered/Linked set/map

    public LogicalTest getLogicalTest() {
        return logicalTest;
    }

    public void setLogicalTest(LogicalTest logicalTest) {
        this.logicalTest = logicalTest;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    
    
    
    
    
    @Override
    public UmlCoreElement clone() {
        WhileNode x = new WhileNode();
        x.setName(name);
        return x;
    }
}
