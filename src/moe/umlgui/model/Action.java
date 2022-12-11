/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

/**
 *
 * @author Moe
 */
public class Action extends Activity implements java.io.Serializable{
    
    public Action() {
        super("Action");
        setName("New Action");
    }

    @Override
    public UmlCoreElement clone() {
        Action x = new Action();
        x.setName(name);
        return x;
    }
}
