/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

/**
 *
 * @author Moe
 */
public class System extends Actor{
    
    public System() {
        super();
        setName("New System");
    }

    @Override
    public UmlCoreElement clone() {
        System x = new System();
        x.setName(name);
        return x;
    }
}
