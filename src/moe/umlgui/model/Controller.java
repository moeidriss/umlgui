/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

import java.util.ArrayList;


public class Controller extends CoreObject  implements java.io.Serializable{

    public Controller() {
        super();
        setName("New Controller");
    }

    
    
    @Override
    public UmlCoreElement clone() {
        Controller x = new Controller();
        x.setName(name);
        return x;
    }
}
