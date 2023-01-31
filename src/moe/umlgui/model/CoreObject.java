/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

import java.util.ArrayList;

/*
Can only be created in a UseCase (BusinessObject), Actor (Controller),
Activity, Messsge
*/
public class CoreObject extends UmlElement  implements java.io.Serializable{

    public CoreObject() {
        super("Business Object");
        setName("New Business Object");
    }

    ArrayList<CoreObjectProperty> properties = new ArrayList();
    ArrayList<CoreObjectMethod> methods = new ArrayList();

    public ArrayList<CoreObjectProperty> getProperties() {
        return properties;
    }

    public ArrayList<CoreObjectMethod> getMethods() {
        return methods;
    }
    
    
    
    @Override
    public UmlCoreElement clone() {
        CoreObject x = new CoreObject();
        x.setName(name);
        return x;
    }
     public String dump(){
        StringBuffer sb = new StringBuffer();
        sb.append(name ).append("::\n");
        sb.append("\tprops\n");
                
        for(CoreObjectProperty p : getProperties()){
            sb.append("\t\t").append(p.dump()).append("\n");
        }
        
        sb.append("\tmethods\n");
        for(CoreObjectMethod m : getMethods()){
            sb.append("\t\t").append(m.dump()).append("\n");
        }
        
        return sb.toString();
    }
}
