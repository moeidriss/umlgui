/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

/**
 *
 * @author Moe
 */
public abstract class UmlRelationship extends UmlCoreElement implements java.io.Serializable{
    
    public UmlRelationship(String type) {
        super(type);
    }
    
    
    UmlElement partyA = null;
    UmlElement partyB = null;

    public UmlElement getPartyA() {
        return partyA;
    }

    public void setPartyA(UmlElement partyA) {
        this.partyA = partyA;
    }

    public UmlElement getPartyB() {
        return partyB;
    }

    public void setPartyB(UmlElement partyB) {
        this.partyB = partyB;
    }
    
}
