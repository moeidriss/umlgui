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
import moe.umlgui.model.*;

/**
 *
 * @author Moe
 */
public class TransferrableImpl implements Transferable {

    UmlCoreElement umlCoreElement;
    
    public TransferrableImpl(UmlCoreElement umlCoreElement){
        this.umlCoreElement=umlCoreElement;
    }
    
    @Override
    public DataFlavor[] getTransferDataFlavors() {
        try {
            return new DataFlavor[]{new DataFlavor(DataFlavor.javaJVMLocalObjectMimeType + ";class=" + umlCoreElement.getClass().getName())};
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TransferrableImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.isMimeTypeEqual(DataFlavor.javaJVMLocalObjectMimeType + ";class=" + umlCoreElement.getClass().getName());
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        return umlCoreElement;
    }
    
}
