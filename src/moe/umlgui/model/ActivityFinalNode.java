/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

/**
 *
 * @author Moe
 */
public class ActivityFinalNode extends ActivityNode implements java.io.Serializable{
    
    public ActivityFinalNode() {
        super("Activity Final Node");
        setName("New Activity Final Node");
    }

    @Override
    public UmlCoreElement clone() {
        ActivityFinalNode x = new ActivityFinalNode();
        x.setName(name);
        return x;
    }
}
