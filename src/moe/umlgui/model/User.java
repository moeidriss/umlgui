/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

/**
 *
 * @author Moe
 */
public class User extends Actor {
    
    public User() {
        super();
        setName("New User");
    }

    @Override
    public UmlCoreElement clone() {
        User x = new User();
        x.setName(name);
        return x;
    }
}
