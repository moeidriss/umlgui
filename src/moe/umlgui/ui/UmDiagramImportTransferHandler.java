/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.ui;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.TransferHandler;
import moe.umlgui.controller.PUMLDriver;
import moe.umlgui.model.*;

/**
 *
 * @author Moe
 */
public class UmDiagramImportTransferHandler extends TransferHandler {
    
    UmlDiagram umlDiagram;
    
    public UmDiagramImportTransferHandler(UmlDiagram umlDiagram){
        super();
        this.umlDiagram = umlDiagram;
    }
    
    
    @Override
    public boolean canImport(JComponent comp, DataFlavor[] transferFlavors){
        
       /*java.lang.System.out.println(transferFlavors[0].getMimeType());
       java.lang.System.out.println(transferFlavors[0].getHumanPresentableName());
       java.lang.System.out.println(transferFlavors[0].getRepresentationClass());
       java.lang.System.out.println(comp);*/
       
       String elClass = transferFlavors[0].getMimeType().substring(transferFlavors[0].getMimeType().lastIndexOf(".")+1);
       
       //java.lang.System.out.println(elClass);
       
       if(elClass.equals("UseCase")){
            if(UseCaseDiagram.class.isInstance(umlDiagram)) return true;
        }
        else if(elClass.equals("Association")){
            if(UseCaseDiagram.class.isInstance(umlDiagram)) return true;
        }
        else if(elClass.equals("Include")){            
            if(UseCaseDiagram.class.isInstance(umlDiagram)) return true;
        }    
        else if(elClass.equals("Action")){
            if(ActivityDiagram.class.isInstance(umlDiagram)) return true;
        }
        else if(elClass.equals("CallActivity")){
            if(ActivityDiagram.class.isInstance(umlDiagram)) return true;
        }
        else if(elClass.equals("AcceptEvent")){
            if(ActivityDiagram.class.isInstance(umlDiagram)) return true;
        }
        else if(elClass.equals("AcceptTimeEvent")){
            if(ActivityDiagram.class.isInstance(umlDiagram)) return true;
        }
        else if(elClass.equals("SendSignal")){
            if(ActivityDiagram.class.isInstance(umlDiagram)) return true;
        }
        else if(elClass.equals("ActivityInitialNode")){
            if(ActivityDiagram.class.isInstance(umlDiagram)) return true;
        }
        else if(elClass.equals("ActivityFinalNode")){
            if(ActivityDiagram.class.isInstance(umlDiagram)) return true;
        }
        else if(elClass.equals("FlowFinalNode")){
            if(ActivityDiagram.class.isInstance(umlDiagram)) return true;
        }
        else if(elClass.equals("ConditionalBlock")){
            if(ActivityDiagram.class.isInstance(umlDiagram)) return true;
        }
        else if(elClass.equals("WhileLoop")){
            if(ActivityDiagram.class.isInstance(umlDiagram)) return true;
        }
        else if(elClass.equals("RepeatLoop")){
            if(ActivityDiagram.class.isInstance(umlDiagram)) return true;
        }
        else if(elClass.equals("Message")){
            if(SequenceDiagram.class.isInstance(umlDiagram)) return true;
        }
        
       
        else if(elClass.equals("Actor")
        ){
            if(UseCaseDiagram.class.isInstance(umlDiagram)) return true;
            else if(SequenceDiagram.class.isInstance(umlDiagram)) return true;
        }
        else if(elClass.equals("System") ||
                elClass.equals("BusinessSystem") ||
                elClass.equals("ItSystem") ||
                elClass.equals("User")
        ){
            if(SequenceDiagram.class.isInstance(umlDiagram)) return true;
        }
       
        else if(elClass.equals("BusinessObject") ||
                elClass.equals("Controller") 
        ){
            if(PackageDiagram.class.isInstance(umlDiagram)) return true;
        }
       
       JOptionPane.showMessageDialog(null, elClass + " not allowed in " + umlDiagram.getType());
        return false;
    }
    
    @Override
    public boolean importData(TransferHandler.TransferSupport support){
        try {
            
            super.importData(support);
            UmlCoreElement el = (UmlCoreElement)support.getTransferable().getTransferData(support.getDataFlavors()[0]);
            ((UmlDiagramPanel)support.getComponent()).insertElement(el);
            
            return true;
        } catch (UnsupportedFlavorException ex) {
            Logger.getLogger(UmDiagramImportTransferHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ModelException ex) {
            Logger.getLogger(UmDiagramImportTransferHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UmDiagramImportTransferHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    protected void exportDone(JComponent source, Transferable data, int action) {
        super.exportDone(source, data, action);
        
    }
    
    
            
}
