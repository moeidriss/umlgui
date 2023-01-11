/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

/**
 *
 * @author Moe
 */
public class SystemComponent extends ItSystem {

    
    public SystemComponent() {
        super();
        setName("New System Component");
    }

    @Override
    public UmlCoreElement clone() {
        SystemComponent x = new SystemComponent();
        x.setName(name);
        return x;
    }
}
