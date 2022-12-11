/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Moe
 */
public class ConditionalNode extends ActivityNode implements java.io.Serializable{
    
    public ConditionalNode() {
        super("Conditional Node");
        setName("New Conditional Node");
    }

    HashMap<LogicalTest,Activity> testMap = new HashMap();
    ArrayList<LogicalTest> testList = new ArrayList();
    
    //TODO convert Activity to Activity set

    public HashMap<LogicalTest, Activity> getTestMap() {
        return testMap;
    }
    //TODO Ordered/linked set/may
    public ArrayList<LogicalTest> getTestList(){
        return testList;
    }
    
    public LogicalTest newTest(){
        LogicalTest t = new LogicalTest();
        testMap.put(t, null);
        testList.add(t);
        return t;
    }
    
    
    @Override
    public UmlCoreElement clone() {
        ConditionalNode x = new ConditionalNode();
        x.setName(name);
        return x;
    }
}
