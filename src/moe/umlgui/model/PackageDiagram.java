/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;


public class PackageDiagram extends UmlDiagram implements java.io.Serializable{
    public PackageDiagram(UmlModel model){
        super("Package Diagram",model);
        setName("New Package Diagram");
    }
    
    //TODO override addCoreElement...
}
