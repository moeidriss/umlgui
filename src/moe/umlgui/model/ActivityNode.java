/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

//TODO extend UmlElement
public abstract class ActivityNode extends Activity  implements java.io.Serializable{

    public ActivityNode() {
        super("Activity Node");
        setName("New Activity Node");
    }

    
    public ActivityNode(String type) {
        super(type);
        setName("New Activity Node");
    }
}
