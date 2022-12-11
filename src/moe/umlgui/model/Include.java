/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;


public class Include extends UmlRelationship implements java.io.Serializable {
    public Include(){
        super("Include");
        setName("New Include Relationship");
    }
    
    
    @Override
    public UmlCoreElement clone() {
        Include x = new Include();
        x.setName(name);
        x.setPartyA(partyA);
        x.setPartyB(partyB);
        return x;
    }
}
