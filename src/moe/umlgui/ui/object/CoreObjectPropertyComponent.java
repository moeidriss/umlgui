/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package moe.umlgui.ui.object;

import javax.swing.DefaultComboBoxModel;
import moe.umlgui.model.*;

/**
 *
 * @author Moe
 */
public class CoreObjectPropertyComponent extends javax.swing.JPanel {

    CoreObjectProperty property;
    
    /**
     * Creates new form LogicalTestComponent
     */
    public CoreObjectPropertyComponent(CoreObjectProperty property) {
        this.property = property;
        initComponents();
        load();
    }

    private void load(){
        if(property.getDataType()!=null) typeComboBox.setSelectedItem(property.getDataType());
        if(property.getName()!=null)    nameTextField.setText(property.getName());
    }
    
    //TODO VALIDATE
    public boolean save(){
        property.setDataType((String)typeComboBox.getSelectedItem());
        property.setName(nameTextField.getText());
        return true;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        nameTextField = new javax.swing.JTextField();
        typeComboBox = new javax.swing.JComboBox<>();

        setLayout(new java.awt.GridBagLayout());

        nameTextField.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder("Name"), new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED)));
        nameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTextFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.SOUTHWEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(nameTextField, gridBagConstraints);

        typeComboBox.setModel(new DefaultComboBoxModel(moe.umlgui.model.CoreObjectProperty.DATA_TYPES));
        typeComboBox.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder("Data Type"), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED)));
        typeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typeComboBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(typeComboBox, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void nameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameTextFieldActionPerformed

    private void typeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typeComboBoxActionPerformed
        /*if(jComboBox1.getSelectedItem().equals("IS") ||
            jComboBox1.getSelectedItem().equals("=")
        ){
            labelTextField.setEnabled(false);
            labelTextField.setText(null);
        }
        else{
            labelTextField.setEnabled(true);
            if(test.getLabel() != null) labelTextField.setText(test.getLabel());
        }*/
    }//GEN-LAST:event_typeComboBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField nameTextField;
    private javax.swing.JComboBox<String> typeComboBox;
    // End of variables declaration//GEN-END:variables
}
