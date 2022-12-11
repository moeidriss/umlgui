/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;


public class Association extends UmlRelationship implements java.io.Serializable {
    public Association(){
        super("Association");
        setName("New Association");
    }

    @Override
    public UmlCoreElement clone() {
        Association x = new Association();
        x.setName(name);
        x.setPartyA(partyA);
        x.setPartyB(partyB);
        return x;
    }
    
}
