/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

/**
 *
 * @author Moe
 */
public class ItSystem extends System{
    
    public ItSystem() {
        super();
        setName("New IT System");
    }

    @Override
    public UmlCoreElement clone() {
        ItSystem x = new ItSystem();
        x.setName(name);
        return x;
    }
}
