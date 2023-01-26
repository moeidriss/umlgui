/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package moe.umlgui.ui;

import moe.umlgui.model.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import moe.umlgui.controller.PUMLDriver;

/**
 *
 * @author Moe
 */
public class UmlDiagramPanel extends javax.swing.JPanel  implements PropertyChangeListener{

    UmlDiagram umlDiagram;

    public UmlDiagram getUmlDiagram() {
        return umlDiagram;
    }
    
    
    /**
     * Creates new form UmlDiagramPanel
     */
    public UmlDiagramPanel(UmlDiagram umlDiagram){
        super();
        this.umlDiagram = umlDiagram;
        
        initComponents();
        loadDiagramPanel();
        this.setTransferHandler(new UmDiagramImportTransferHandler(umlDiagram));
    }
    
    
    //TODO ImageRaster to replace jlabel
    
    private void loadDiagramPanel(){
        setName(umlDiagram.getName());        
        //this.setBorder(new TitledBorder(umlDiagram.getName()));   
        
        JSplitPane sp = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        sp.setOneTouchExpandable(true);
        sp.setDividerLocation(0.4);
        sp.setResizeWeight(0.4);
        add(sp, BorderLayout.CENTER);
        
        sp.setLeftComponent(new JScrollPane(label));
        sp.setRightComponent(textArea);
        
        JButton updateImgButton = new JButton("Update");
        updateImgButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                umlDiagram.setUmlCode(textArea.getText());
                try {
                    PUMLDriver.updateImage(umlDiagram);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, ex, "PUMLDriver Error", JOptionPane.ERROR_MESSAGE);
                    Logger.getLogger(UmlDiagramPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                updateImage();
            }
            
        });
        
        if(umlDiagram.getImage() != null)   
                    label.setIcon(new ImageIcon(umlDiagram.getImage()));
        
        add(updateImgButton, BorderLayout.SOUTH);
        
        this.revalidate();
    }
    
    JTextArea textArea = new JTextArea();//TODO REMOVE
    JLabel label = new JLabel();
    
    public void updateImage(){
        label.setIcon(new ImageIcon(umlDiagram.getImage()));
        textArea.setText(umlDiagram.getUmlCode());
        revalidate();
       
    }
    
    
    public void insertElement(UmlCoreElement el) throws ModelException{
        umlDiagram.addCoreElement(el);
        textArea.setText(umlDiagram.getUmlCode());
        
        //exclude 'complex' elements from img update at insertion
        if(!ControlNode.class.isInstance(el)
        ){
            updateImage();
        }
        
        ArrayList q =new ArrayList();
        q.add(this);
        this.firePropertyChange("Element inserted", q, el);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents

    
    public boolean equals(Object o){
        return (hashCode()==o.hashCode());
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(!evt.getPropertyName().equals("Element updated") &&
            !evt.getPropertyName().equals("Element inserted")  &&
            !evt.getPropertyName().equals("Diagram updated") 
        ){
            return;
        }
        java.lang.System.out.println("UDP:" +
                                    evt.getPropertyName() + "(" +
                                    evt.getNewValue() + ") -- " +
                                    ((ArrayList)evt.getOldValue()).size() +
                                    " consumers so far"
                
                );
        
        if( (evt.getPropertyName().equals("Element updated") || evt.getPropertyName().equals("Element inserted") ) 
                &&
                !((ArrayList)evt.getOldValue()).contains(this)
                &&
                umlDiagram.getCoreElementMap().containsKey(((UmlCoreElement)evt.getNewValue()).getId())
        ){
            ((ArrayList)evt.getOldValue()).add(this);
            try {
                PUMLDriver.update(umlDiagram);
                updateImage();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, ".");
            }
        }
        
        else if(evt.getPropertyName().equals("Diagram updated") && !((ArrayList)evt.getOldValue()).contains(this)){
            ((ArrayList)evt.getOldValue()).add(this);
            loadDiagramPanel();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
