/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

import java.util.ArrayList;

/**
 *
 * @author Moe
 */
public abstract class UmlModel {
    
    String name = "model";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    Project project;

    public Project getProject() {
        return project;
    }
    
    public UmlModel (Project project){
        this.project = project;
    }
    
    
    ArrayList<UmlDiagram> diagrams =new ArrayList();

    public ArrayList<UmlDiagram> getDiagrams() {
        return diagrams;
    }
    
    public String toString(){
        return name;
    }
}
