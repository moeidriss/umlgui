/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package moe.umlgui.ui;

import java.util.HashMap;
import java.util.Iterator;
import javax.swing.DefaultComboBoxModel;
import moe.umlgui.model.CoreObject;
import moe.umlgui.model.CoreObjectProperty;
import moe.umlgui.model.Project;

/**
 *
 * @author Moe
 */
public class CoreObjectPropertySelector extends javax.swing.JPanel {

    Project project;
    CoreObjectProperty selectedProperty;
    
    
    /**
     * Creates new form BusinessObjectPropertySelector
     */
    public CoreObjectPropertySelector(Project project) {
        this.project = project;
        initComponents();
        loadSelector();
    }
    
    
    /**
     * Creates new form BusinessObjectPropertySelector
     */
    public CoreObjectPropertySelector(Project project , CoreObjectProperty property) {
        this.project = project;
        this.selectedProperty = property;
        initComponents();
        loadSelector();
    }
    
    HashMap<String,CoreObjectProperty> propMap = new HashMap();
    
    private void loadSelector(){
        for(Iterator<CoreObject> i = project.getCoreObjects().iterator() ; i.hasNext();){
            CoreObject bo = i.next();
            for(Iterator<CoreObjectProperty> j = bo.getProperties().iterator() ; j.hasNext() ; ){
                CoreObjectProperty prop = j.next();
                String fN = bo.getName() + "." + prop.getName() + ": " + prop.getDataType();
                propMap.put(fN, prop);
                ((DefaultComboBoxModel)jComboBox1.getModel()).addElement(fN);
                jComboBox1.setSelectedItem(fN);
            }
        }
    }

    public CoreObjectProperty getSelectedProperty() {
        return selectedProperty;
    }

    public void setSelectedProperty(CoreObjectProperty selectedProperty) {
        this.selectedProperty = selectedProperty;
        if(selectedProperty!=null){
            String fN = selectedProperty.getBusinessObject().getName() + "." + 
                    selectedProperty.getName() + ": " + selectedProperty.getDataType();
            jComboBox1.setSelectedItem(fN);
        }
        else{
            jComboBox1.setSelectedItem(null);
        }
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();

        setLayout(new java.awt.BorderLayout());

        jComboBox1.setModel(new DefaultComboBoxModel());
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        add(jComboBox1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        if(jComboBox1.getSelectedItem()!=null){
            selectedProperty = propMap.get(jComboBox1.getSelectedItem());
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    // End of variables declaration//GEN-END:variables
}