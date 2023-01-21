/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

/**
 *
 * @author Moe
 */
public class FlowFinalNode extends ActivityNode implements java.io.Serializable{
    
    public FlowFinalNode() {
        super("Flow Final Node");
        setName("New Flow Final Node");
    }
    
    

    @Override
    public UmlCoreElement clone() {
        FlowFinalNode x = new FlowFinalNode();
        x.setName(name);
        return x;
    }
}
