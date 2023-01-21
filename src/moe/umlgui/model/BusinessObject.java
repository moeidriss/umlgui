/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

import java.util.ArrayList;


public class BusinessObject extends CoreObject  implements java.io.Serializable{

    public BusinessObject() {
        super();
        setName("New Business Object");
    }

    
    
    @Override
    public UmlCoreElement clone() {
        BusinessObject x = new BusinessObject();
        x.setName(name);
        return x;
    }
}
