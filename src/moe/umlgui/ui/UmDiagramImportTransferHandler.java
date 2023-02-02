/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.ui;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.TransferHandler;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import moe.umlgui.controller.PUMLDriver;
import moe.umlgui.model.*;
import moe.umlgui.ui.object.CoreObjectMethodSelector;
import moe.umlgui.ui.tree.Explorer;

/**
 *
 * @author Moe
 */
public class UmDiagramImportTransferHandler extends TransferHandler {
    
    UmlDiagram diagram;
    
    public UmDiagramImportTransferHandler(UmlDiagram umlDiagram){
        super();
        this.diagram = umlDiagram;
    }
    
    
    @Override
    public boolean canImport(JComponent comp, DataFlavor[] transferFlavors){
        
       /*java.lang.System.out.println(transferFlavors[0].getMimeType());
       java.lang.System.out.println(transferFlavors[0].getHumanPresentableName());
       java.lang.System.out.println(transferFlavors[0].getRepresentationClass());
       java.lang.System.out.println(comp);*/
       
       String elClass = transferFlavors[0].getMimeType().substring(transferFlavors[0].getMimeType().lastIndexOf(".")+1);
       
       //java.lang.System.out.println(elClass);
       
       //TODO levelOfDetail rules
       if(elClass.equals("UseCase")){
            if(UseCaseDiagram.class.isInstance(diagram)) return true;
        }
        else if(elClass.equals("Association")){
            if(UseCaseDiagram.class.isInstance(diagram)) return true;
        }
        else if(elClass.equals("Include")){            
            if(UseCaseDiagram.class.isInstance(diagram)) return true;
        }    
        else if(elClass.equals("Action")){
            if(ActivityDiagram.class.isInstance(diagram)) return true;
        }
        else if(elClass.equals("CallActivity")){
            if(ActivityDiagram.class.isInstance(diagram)) return true;
        }
        else if(elClass.equals("AcceptEvent")){
            if(ActivityDiagram.class.isInstance(diagram)) return true;
        }
        else if(elClass.equals("AcceptTimeEvent")){
            if(ActivityDiagram.class.isInstance(diagram)) return true;
        }
        else if(elClass.equals("SendSignal")){
            if(ActivityDiagram.class.isInstance(diagram)) return true;
        }
        else if(elClass.equals("ActivityInitialNode")){
            if(ActivityDiagram.class.isInstance(diagram)) return true;
        }
        else if(elClass.equals("ActivityFinalNode")){
            if(ActivityDiagram.class.isInstance(diagram)) return true;
        }
        else if(elClass.equals("FlowFinalNode")){
            if(ActivityDiagram.class.isInstance(diagram)) return true;
        }
        else if(elClass.equals("ConditionalBlock")){
            if(ActivityDiagram.class.isInstance(diagram)) return true;
        }
        else if(elClass.equals("WhileLoop")){
            if(ActivityDiagram.class.isInstance(diagram)) return true;
        }
        else if(elClass.equals("RepeatLoop")){
            if(ActivityDiagram.class.isInstance(diagram)) return true;
        }
        else if(elClass.equals("Split")){
            if(ActivityDiagram.class.isInstance(diagram)) return true;
        }
        else if(elClass.equals("Join")){
            if(ActivityDiagram.class.isInstance(diagram)) return true;
        }
        else if(elClass.equals("Fork")){
            if(ActivityDiagram.class.isInstance(diagram)) return true;
        }
        
        else if(elClass.equals("Message")){
            if(SequenceDiagram.class.isInstance(diagram)) return true;
        }
        
       
        else if(elClass.equals("Actor")
        ){
            if(UseCaseDiagram.class.isInstance(diagram)) return true;
            else if(SequenceDiagram.class.isInstance(diagram)) return true;
        }
        else if(elClass.equals("System") ||
                elClass.equals("BusinessSystem") ||
                elClass.equals("ItSystem") ||
                elClass.equals("User")
        ){
            if(SequenceDiagram.class.isInstance(diagram)) return true;
        }
       
        else if(elClass.equals("BusinessObject") ||
                elClass.equals("Controller") 
        ){
            if(PackageDiagram.class.isInstance(diagram)) return true;
            
            else if(diagram.getControlLevel()==UmlDiagram.CONSTRAINED){
                if(SequenceDiagram.class.isInstance(diagram)) return true;
            }
        }
       
       JOptionPane.showMessageDialog(null, elClass + " not allowed in " + diagram.getType());
        return false;
    }
    
    
    
    
    @Override
    public boolean importData(TransferHandler.TransferSupport support){
        try {
            
            super.importData(support);
            UmlCoreElement el = (UmlCoreElement)support.getTransferable().getTransferData(support.getDataFlavors()[0]);
            
            
            if(diagram.getControlLevel()==UmlDiagram.FREE){
                ((UmlDiagramPanel)support.getComponent()).insertElement(el);
                return true;
            }
            else if( (ActivityDiagram.class.isInstance(diagram) && 
                    Activity.class.isInstance(el))
                    || 
                    (SequenceDiagram.class.isInstance(diagram) && 
                    CoreObject.class.isInstance(el))
            ){
                addConstrainedElement(el , ((UmlDiagramPanel)support.getComponent()));
                return true;
            }
            else {
                ((UmlDiagramPanel)support.getComponent()).insertElement(el);
                return true;
            }
        } catch (UnsupportedFlavorException ex) {
            Logger.getLogger(UmDiagramImportTransferHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ModelException ex) {
            Logger.getLogger(UmDiagramImportTransferHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UmDiagramImportTransferHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
    private void addConstrainedElement(UmlCoreElement newElement , UmlDiagramPanel udp){
        
        if(ActivityDiagram.class.isInstance(diagram)  && 
                    Activity.class.isInstance(newElement)
        ){
            JDialog d = new JDialog();
            
            //if activity: method
            CoreObjectMethodSelector methSelector = new CoreObjectMethodSelector(diagram.getUmlModel().getProject());

            JButton okButton = new JButton("OK");
            okButton.addActionListener(new ActionListener(){                
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(methSelector.getSelectedIndex()!=-1){
                        newElement.setName(methSelector.getSelectedMethod().toFullString());
                        try {
                            udp.insertElement(newElement);
                        } catch (ModelException ex) {
                            JOptionPane.showMessageDialog(null, ex);
                            ex.printStackTrace();
                        }
                        diagram.getConstraints().put(newElement, methSelector.getSelectedMethod());
                        
                        d.setVisible(false);
                    }
                }
            });
            
            d.getContentPane().add(methSelector , BorderLayout.CENTER);
            d.add(okButton, BorderLayout.SOUTH);
            
            d.pack();
            d.setLocationRelativeTo(null);
            d.setVisible(true);
        }
        else if(SequenceDiagram.class.isInstance(diagram)  && 
                    CoreObject.class.isInstance(newElement)
        ){
            if(!diagram.getConstraints().containsKey(newElement)){
                Actor a = new Actor();
                a.setName(newElement.getName());                                
                try {
                    udp.insertElement(a);
                } catch (ModelException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                    ex.printStackTrace();
                }
                diagram.getConstraints().put(newElement, a);
            }
        }
        else if(SequenceDiagram.class.isInstance(diagram)  && 
                    Message.class.isInstance(newElement)
        ){                              
            try {
                udp.insertElement(newElement);
            } catch (ModelException ex) {
                JOptionPane.showMessageDialog(null, ex);
                ex.printStackTrace();
            }
        }
    }


    @Override
    protected void exportDone(JComponent source, Transferable data, int action) {
        super.exportDone(source, data, action);
        
    }
    
    
            
}
