/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

import java.awt.Image;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Moe
 */
public abstract class UmlElement extends UmlCoreElement implements Serializable{
    
    public UmlElement(String type) {
        super(type);
    }
    
    
}
