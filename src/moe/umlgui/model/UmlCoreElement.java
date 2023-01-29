/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

import java.awt.Desktop;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 *
 * @author Moe
 */
public abstract class UmlCoreElement implements java.io.Serializable{
    
String type;

    public String getType() {
        return type;
    }
    String name = "New element";
    Image image;

    public Image getImage() throws IOException {
        if(image==null){
            return javax.imageio.ImageIO.read(getClass().getResource("/moe/umlgui/img/" + getClass().getSimpleName() + ".png"));
        }
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    int noteNumColumns = 1;

    public int getNoteNumColumns() {
        return noteNumColumns;
    }

    public void setNoteNumColumns(int noteNumColumns) {
        this.noteNumColumns = noteNumColumns;
    }
    
    public static int LEFT = 0;
    public static int RIGHT = 1;
    public static int UP = 2;
    public static int DOWN = 3;
    
   int position = LEFT;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    
    Long id;

    public Long getId() {
        if(id==null) id = new Date().getTime();
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    
    String umlCode;

    public String getUmlCode() {
        return umlCode;
    }

    public void setUmlCode(String umlCode) {
        this.umlCode = umlCode;
    }
    
     
    //null if template
    UmlDiagram umlDiagram;

    public UmlDiagram getUmlDiagram() {
        return umlDiagram;
    }

    public void setUmlDiagram(UmlDiagram umlDiagram) {
        this.umlDiagram = umlDiagram;
    }
    
    Package pckage;

    public Package getPckage() {
        return pckage;
    }

    public void setPckage(Package pckage) {
        this.pckage = pckage;
        pckage.getCoreElements().add(this);
    }
    
    
    
    public UmlCoreElement(String type){
        this.type = type;
    }
    
    public String toString(){
        return name;
    }
    
    public boolean equals(Object o){
        if(o==null) return false;
        try{
        if(((UmlCoreElement)o).getId() == getId())  return true;
        return false;
        }catch(ClassCastException cce){
            return false;
        }
    }
    
    //TODO validate implementations 
    public abstract UmlCoreElement clone();
    
    
    public String dump(){
        StringBuffer sb = new StringBuffer();
        sb.append("!!! Dumping " + name + " !!!\n");
        if(BusinessObjectOwner.class.isInstance(this)){
            sb.append("\t Business Objects\n");
            for(CoreObject o : ((BusinessObjectOwner)this).getBusinessObjects()){
                sb.append(o.dump());
            }
        }
        
        if(ControllerOwner.class.isInstance(this)){
            sb.append("\t Controllers\n");
            for(CoreObject o : ((ControllerOwner)this).getControllers()){
                sb.append(o.dump());
            }
        }
        
        sb.append("==========================================");
        
        return sb.toString();
    }

}