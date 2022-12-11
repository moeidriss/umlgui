/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;


public class Actor extends UmlElement  implements java.io.Serializable{

    public Actor() {
        super("Actor");
        setName("New Actor");
    }

    @Override
    public UmlCoreElement clone() {
        Actor x = new Actor();
        x.setName(name);
        return x;
    }
}
