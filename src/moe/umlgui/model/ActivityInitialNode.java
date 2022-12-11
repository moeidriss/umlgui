/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

/**
 *
 * @author Moe
 */
public class ActivityInitialNode extends ActivityNode implements java.io.Serializable{
    
    public ActivityInitialNode() {
        super("Activity Initial Node");
        setName("New Activity Initial Node");
    }

    @Override
    public UmlCoreElement clone() {
        ActivityInitialNode x = new ActivityInitialNode();
        x.setName(name);
        return x;
    }
}
