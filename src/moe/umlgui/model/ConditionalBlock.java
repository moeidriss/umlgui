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
 * TODO <!pragma useVerticalIf>
 * 
 * @author Moe
 */
public class ConditionalBlock extends ControlNode implements java.io.Serializable{
    
    public ConditionalBlock() {
        super("Conditional Block");
        setName("New Conditional Block");
    }

    HashMap<LogicalTest,ActivityFlow> testMap = new HashMap();
    ArrayList<LogicalTest> testList = new ArrayList();
    
    
    
    
    
    //TODO implement logic control/validation (a single 'if', single 'else') and testList order

    public HashMap<LogicalTest, ActivityFlow> getTestMap() {
        return testMap;
    }

    public ArrayList<LogicalTest> getTestList(){
        return testList;
    }
    
    public LogicalTest newTest(){
        LogicalTest t = new LogicalTest();
        testMap.put(t, new ActivityFlow());
        testList.add(t);
        return t;
    }
    
    
    @Override
    public UmlCoreElement clone() {
        ConditionalBlock x = new ConditionalBlock();
        x.setName(name);
        return x;
    }
}
