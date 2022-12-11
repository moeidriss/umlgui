/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

/**
 *
 * @author Moe
 */
public class CallActivity extends Activity implements java.io.Serializable{
    
    public CallActivity() {
        super("Call Activity");
        setName("New Call Activity");
    }

    @Override
    public UmlCoreElement clone() {
        CallActivity x = new CallActivity();
        x.setName(name);
        return x;
    }
}
