/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

/*
TODO decide design of "activity flow" or "composite activity" for nesting 
in conditional blocks, loops, 
- ArrayList.
- ActivityBlock, ActivityFlow extends Activity
*/

public abstract class Activity extends UmlElement  implements java.io.Serializable{

    public Activity() {
        super("Activity");
        setName("New Activity");
    }

    
    public Activity(String type) {
        super(type);
        setName("New Activity");
    }
}
