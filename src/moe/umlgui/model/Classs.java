/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;


public class Classs extends UmlElement  implements java.io.Serializable{
    public Classs(){
        super("Class");
    }
    
    @Override
    public UmlElement clone() {
        Classs x = new Classs();
        x.setName(name);
        return x;
    }
}
