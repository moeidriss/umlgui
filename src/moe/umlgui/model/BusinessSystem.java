/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

/**
 *
 * @author Moe
 */
public class BusinessSystem extends System{
    
    public BusinessSystem() {
        super();
        setName("New Business System");
    }

    @Override
    public UmlCoreElement clone() {
        BusinessSystem x = new BusinessSystem();
        x.setName(name);
        return x;
    }
}
