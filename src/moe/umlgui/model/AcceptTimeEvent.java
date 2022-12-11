/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

/**
 *
 * @author Moe
 */
public class AcceptTimeEvent extends Activity implements java.io.Serializable{
    
    public AcceptTimeEvent() {
        super("Accept Time Event");
        setName("New Accept Time Event");
    }

    @Override
    public UmlCoreElement clone() {
        AcceptTimeEvent x = new AcceptTimeEvent();
        x.setName(name);
        return x;
    }
}
