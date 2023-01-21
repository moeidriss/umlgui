/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.ui;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;
import javax.swing.border.LineBorder;
import moe.umlgui.model.*;

/**
 *
 * @author Moe
 */
public class UmlElementLabel extends JLabel {
    
    UmlCoreElement umlCoreElement;
    
    public UmlElementLabel( UmlCoreElement umlCoreElement){
        super();
        this.umlCoreElement = umlCoreElement;
        
        setName(umlCoreElement.getName());
        this.setToolTipText(umlCoreElement.getType());
        try{
            super.setIcon(new ImageIcon(umlCoreElement.getImage()));
        }catch(IOException ex){
            ex.printStackTrace();
        }
        
        setBorder( new LineBorder(Color.BLACK));
        this.setBackground(Color.white);
        this.setHorizontalAlignment(SwingConstants.CENTER);
        
        UmlElementLabelExportTransferHandler newHandler = new UmlElementLabelExportTransferHandler(umlCoreElement , ((ImageIcon)getIcon()).getImage());
        this.setTransferHandler(newHandler);
        this.addMouseMotionListener(new MouseAdapter(){
            public void mouseDragged(MouseEvent ev){
                UmlElementLabel l = (UmlElementLabel)ev.getSource();
                l.getTransferHandler().exportAsDrag(l, ev, TransferHandler.MOVE);
            }
        });
    }
}
