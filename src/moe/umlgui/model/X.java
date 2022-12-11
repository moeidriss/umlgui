/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

/**
 * TODO RENAME BACK (!) TO AcceptEvent 
 * @author Moe
 */
public class X extends Activity implements java.io.Serializable{
    
    public X() {
        super("Accept Event");
        setName("New Accept Event");
    }

    @Override
    public UmlCoreElement clone() {
        X x = new X();
        x.setName(name);
        return x;
    }
}
