/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

import java.util.ArrayList;


public class Package extends UmlCoreElement  implements java.io.Serializable{

    public Package() {
        super("Package");
        setName("New Package");
    }

    public Package(Package parent) {
        super("Package");
        this.parent = parent;
        if(parent!=null && !parent.getChildren().contains(this))
            parent.getChildren().add(this);
        setName("New Package");
    }

    //TODO parent
    Package parent;
    ArrayList<Package> children = new ArrayList();

    public ArrayList<Package> getChildren() {
        return children;
    }
    
    
    ArrayList<UmlCoreElement> coreElements = new ArrayList();

    public Package getParent() {
        return parent;
    }

    public ArrayList<UmlCoreElement> getCoreElements() {
        return coreElements;
    }

    public String toString(){
        if(parent!=null)    return parent.toString() + "." + name;
        else return name;
    }
    
    @Override
    public UmlCoreElement clone() {
        Package x = new Package();
        x.setName(name);
        return x;
    }
}
