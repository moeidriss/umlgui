/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

/**
 * TODO RENAME BACK (!) TO AcceptEvent 
 * @author Moe
 */
public class AcceptEvent extends Activity implements java.io.Serializable{
    
    public AcceptEvent() {
        super("Accept Event");
        setName("New Accept Event");
    }

    @Override
    public UmlCoreElement clone() {
        AcceptEvent x = new AcceptEvent();
        x.setName(name);
        return x;
    }
}
