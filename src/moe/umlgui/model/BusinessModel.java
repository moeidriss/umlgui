/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

/**
 *
 * @author Moe
 */
public class BusinessModel extends UmlModel  implements java.io.Serializable{
    
    public BusinessModel(Project project) {
        super(project);
        setName("Business Model");
        project.getModels().add(this);
    }
    
}
