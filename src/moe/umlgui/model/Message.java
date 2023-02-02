/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

import java.util.ArrayList;
import java.util.HashSet;


public class Message extends UmlCoreElement  implements java.io.Serializable , BusinessObjectOwner{

    public Message() {
        super("Message");
        setName("New Message");
    }

    Actor from;
    Actor to;
    
    
    
    HashSet<CoreObject> businessObjects = new HashSet(){
        public String toString(){
            return "Business Objects";
        }
    };

    public HashSet<CoreObject> getBusinessObjects() {
        return businessObjects;
    }


    public Actor getFrom() {
        return from;
    }

    public void setFrom(Actor from) {
        this.from = from;
    }

    public Actor getTo() {
        return to;
    }

    public void setTo(Actor to) {
        this.to = to;
    }
    
    
    
    
    @Override
    public UmlCoreElement clone() {
        Message x = new Message();
        x.setName(name);
        return x;
    }
}
