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

    HashMap<LogicalTest,ArrayList<Activity>> testMap = new HashMap();
    ArrayList<LogicalTest> testList = new ArrayList();
    
    
    
    
    //TODO convert Activity to Activity set
    
    //TODO implement logic control (a single 'if', single 'else') and testList order

    public HashMap<LogicalTest, ArrayList<Activity>> getTestMap() {
        return testMap;
    }
    //TODO Ordered/linked set/may
    public ArrayList<LogicalTest> getTestList(){
        return testList;
    }
    
    public LogicalTest newTest(){
        LogicalTest t = new LogicalTest();
        
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
        testMap.put(t, activityFlow);
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
