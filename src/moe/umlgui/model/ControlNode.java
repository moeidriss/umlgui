/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;


/*TODO 
    Extending Activity fitter but plan to use this in seq diag too.
    impact: ActivityFlow changed from ActivityFlow<Activity> to ActivityFlow<UmlCoreElement>
    to accomodate ControlNode in ActivityFlow
*/
public abstract class ControlNode extends UmlCoreElement  implements java.io.Serializable{

    public ControlNode() {
        super("Control Node");
        setName("New Control Node");
    }

    
    public ControlNode(String type) {
        super(type);
        setName("New Control Node");
    }
}
