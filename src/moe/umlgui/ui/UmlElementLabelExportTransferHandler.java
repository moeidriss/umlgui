/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.ui;

import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.TransferHandler;
import moe.umlgui.model.*;

/**
 *
 * @author Moe
 */
public class UmlElementLabelExportTransferHandler extends TransferHandler {
    UmlCoreElement umlCoreElement;
    Image img;
    
    public UmlElementLabelExportTransferHandler(UmlCoreElement umlCoreElement, Image img){
        super();
        this.umlCoreElement = umlCoreElement;
        this.img = img;
        //super.setDragImage(img);
    }
    
    @Override
    protected Transferable createTransferable(JComponent c){
        if(UmlElementLabel.class.isInstance(c)) {
            
            return new TransferrableImpl(umlCoreElement.clone());
        }
        return new TransferrableImpl(umlCoreElement);
    }
    
    
    @Override
    protected void exportDone(JComponent source,Transferable data, int action){
        super.exportDone(source, data, action);
    }
    
    /**
     *
     * @param c
     * @return
     */
    public int getSourceActions(JComponent c){
        return COPY_OR_MOVE;
    }
}
