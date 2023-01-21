/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;


public class Actor extends UmlElement  implements java.io.Serializable , ControllerOwner{

    public Actor() {
        super("Actor");
        setName("New Actor");
    }

    
    /*
    public static HashMap<String,Class> ACTORS = new HashMap();
    static{
        ACTORS.put("Actor" , Actor.class);
        ACTORS.put("System" , System.class);
        ACTORS.put("Business System" , BusinessSystem.class);
        ACTORS.put("IT System" , ItSystem.class);
        ACTORS.put("User" , User.class);
    }
    */
    
    
    
    HashSet<CoreObject> controllers = new HashSet(){
        public String toString(){
            return "Controllers";
        }
    };

    public HashSet<CoreObject> getControllers() {
        return controllers;
    }
    
    @Override
    public UmlCoreElement clone() {
        Actor x = new Actor();
        x.setName(name);
        return x;
    }
}
