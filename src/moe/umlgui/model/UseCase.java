/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

import java.util.ArrayList;
import java.util.HashSet;


public class UseCase extends UmlElement  implements java.io.Serializable , BusinessObjectOwner , AttachmentOwner{
    public UseCase(){
        super("Use Case");
        setName("New Use Case");
    }
    
    String description = null;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    int descriptionNumColumns = 1;

    public Integer getDescriptionNumColumns() {
        return descriptionNumColumns;
    }

    public void setDescriptionNumColumns(Integer descriptionNumColumns) {
        this.descriptionNumColumns = descriptionNumColumns;
    }
    
    HashSet<CoreObject> businessObjects = new HashSet(){
        public String toString(){
            return "Business Objects";
        }
    };

    public HashSet<CoreObject> getBusinessObjects() {
        return businessObjects;
    }
    
    @Override
    public UmlElement clone() {
        UseCase x = new UseCase();
        x.setName(name);
        return x;
    }
    
    ArrayList<UmlDiagram> attachedDiagrams = new ArrayList(){
        public String toString(){
            return "Attached Diagrams";
        }
    };
    
    public ArrayList<UmlDiagram> getAttachedDiagrams(){
        return attachedDiagrams;
    }
}
