/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

import java.util.ArrayList;


public class BusinessObject extends UmlElement  implements java.io.Serializable{

    public BusinessObject() {
        super("Actor");
        setName("New Actor");
    }

    ArrayList properties = new ArrayList();
    ArrayList methods = new ArrayList();
    
    @Override
    public UmlCoreElement clone() {
        BusinessObject x = new BusinessObject();
        x.setName(name);
        return x;
    }
}
