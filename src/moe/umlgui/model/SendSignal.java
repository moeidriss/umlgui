/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

/**
 *
 * @author Moe
 */
public class SendSignal extends Activity implements java.io.Serializable{
    
    public SendSignal() {
        super("Send Signal");
        setName("New Send Signal");
    }

    @Override
    public UmlCoreElement clone() {
        SendSignal x = new SendSignal();
        x.setName(name);
        return x;
    }
}
