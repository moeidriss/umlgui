/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

import java.util.ArrayList;


public class Package extends UmlElement  implements java.io.Serializable{

    public Package() {
        super("Package");
    }

    //TODO parent
    

    ArrayList<UmlCoreElement> linkedCoreElements = new ArrayList();

    public ArrayList<UmlCoreElement> getLinkedCoreElements() {
        return linkedCoreElements;
    }
    
    
    @Override
    public UmlCoreElement clone() {
        Package x = new Package();
        x.setName(name);
        return x;
    }
}
