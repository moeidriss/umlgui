/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;


public class UseCase extends UmlElement  implements java.io.Serializable{
    public UseCase(){
        super("Use Case");
        setName("New Use Case");
    }
    
    String description = null;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;System.out.println("uc desc:"+description);
    }
    
    int descriptionNumColumns = 1;

    public Integer getDescriptionNumColumns() {
        return descriptionNumColumns;
    }

    public void setDescriptionNumColumns(Integer descriptionNumColumns) {
        this.descriptionNumColumns = descriptionNumColumns;
    }
    
    @Override
    public UmlElement clone() {
        UseCase x = new UseCase();
        x.setName(name);
        return x;
    }
}
