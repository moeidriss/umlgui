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
public class UmlElementImportTransferHandler extends TransferHandler {
    
    UmlDiagram umlDiagram;
    
    public UmlElementImportTransferHandler(UmlDiagram umlDiagram){
        super();
        this.umlDiagram = umlDiagram;
    }
    
    
    @Override
    public boolean canImport(JComponent comp, DataFlavor[] transferFlavors){
        //TODO restrict:
        //use case: actor, use case, association, include
        //activity: activity, 
       /* if(transferFlavors[0].getMimeType().equals(DataFlavor.javaJVMLocalObjectMimeType)
                &&
            transferFlavors[0].getHumanPresentableName().equals("moebullshitting.model.UmlElement")   
        ){
            return true;
        }
        
        return false;*/
        return true;
    }
    
    @Override
    public boolean importData(TransferHandler.TransferSupport support){
        try {
            
            super.importData(support);
            UmlCoreElement el = (UmlCoreElement)support.getTransferable().getTransferData(support.getDataFlavors()[0]);
            ((UmlDiagramPanel)support.getComponent()).insertElement(el);
            
            return true;
        } catch (UnsupportedFlavorException ex) {
            Logger.getLogger(UmlElementImportTransferHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UmlElementImportTransferHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    protected void exportDone(JComponent source, Transferable data, int action) {
        super.exportDone(source, data, action);
        
    }
    
    
            
}
