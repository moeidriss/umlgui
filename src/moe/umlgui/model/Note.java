/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

import java.util.ArrayList;


public class Note extends UmlElement  implements java.io.Serializable{

    public Note() {
        super("Note");
    }

    ArrayList<UmlCoreElement> linkedCoreElements = new ArrayList();

    public ArrayList<UmlCoreElement> getLinkedCoreElements() {
        return linkedCoreElements;
    }
    
    @Override
    public UmlCoreElement clone() {
        Note x = new Note();
        x.setName(name);
        return x;
    }
}
