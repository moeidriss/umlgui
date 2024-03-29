/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package moe.umlgui.ui;

import moe.umlgui.ui.object.CoreObjectPropertySelector;
import java.awt.BorderLayout;
import javax.swing.DefaultComboBoxModel;
import moe.umlgui.model.*;

/**
 *
 * @author Moe
 */
public class LogicalTestComponent extends javax.swing.JPanel {

    LogicalTest test;
    UmlDiagram context;
    
    /**
     * Creates new form LogicalTestComponent
     */
    public LogicalTestComponent(LogicalTest test , UmlDiagram context) {
        this.test = test;
        this.context = context;
        initComponents();
        load();
    }
    
    
    
    
    
    CoreObjectPropertySelector propSelector;

    private void load(){
        propSelector = new CoreObjectPropertySelector(context.getUmlModel().getProject());
        objPropertyPanel.add(propSelector , BorderLayout.CENTER);
        
        if(test.getCondition()!=null) conditionComboBox.setSelectedItem(test.getCondition());
        if(test.getOperandA()!=null)    propSelector.setSelectedProperty(test.getOperandA());
        if(test.getOperator()!=null) operatorComboBox.setSelectedItem(test.getOperator());
        if(test.getOperandB()!=null)    valueTextField.setText(test.getOperandB());
        if(test.getLabel() != null) labelTextField.setText(test.getLabel());
    }
    
    //TODO VALIDATE
    public void save(){
        test.setCondition((String)conditionComboBox.getSelectedItem());
        test.setOperandA(propSelector.getSelectedProperty());
        test.setOperator((String)operatorComboBox.getSelectedItem());
        test.setOperandB(valueTextField.getText());
        test.setLabel(labelTextField.getText());
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

        conditionComboBox = new javax.swing.JComboBox<>();
        objPropertyPanel = new javax.swing.JPanel();
        operatorComboBox = new javax.swing.JComboBox<>();
        valueTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        labelTextField = new javax.swing.JTextField();

        setLayout(new java.awt.GridBagLayout());

        conditionComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "IF", "ELSE IF", "ELSE" }));
        conditionComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                conditionComboBoxActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        add(conditionComboBox, gridBagConstraints);

        objPropertyPanel.setLayout(new java.awt.BorderLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        add(objPropertyPanel, gridBagConstraints);

        operatorComboBox.setModel(new DefaultComboBoxModel(LogicalTest.OPERATORS));
        operatorComboBox.setSelectedItem(test.getOperator());
        operatorComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                operatorComboBoxActionPerformed(evt);
            }
        });
        add(operatorComboBox, new java.awt.GridBagConstraints());

        valueTextField.setText(test.getOperandB());
        valueTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                valueTextFieldActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(valueTextField, gridBagConstraints);

        jLabel1.setText("Connector Label: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.LINE_START;
        add(jLabel1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        add(labelTextField, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void operatorComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_operatorComboBoxActionPerformed
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
    }//GEN-LAST:event_operatorComboBoxActionPerformed

    private void valueTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_valueTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_valueTextFieldActionPerformed

    private void conditionComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_conditionComboBoxActionPerformed
        if(conditionComboBox.getSelectedItem().equals("ELSE")){
            propSelector.setEnabled(false);
            propSelector.setSelectedProperty(null);
            operatorComboBox.setEnabled(false);
            operatorComboBox.setSelectedItem(null);
            valueTextField.setEnabled(false);
            valueTextField.setText(null);
        }
        else{
            propSelector.setEnabled(true);
            operatorComboBox.setEnabled(true);
            valueTextField.setEnabled(true);
            if(test.getOperandA()!=null)    propSelector.setSelectedProperty(test.getOperandA());
            if(test.getOperator()!=null) operatorComboBox.setSelectedItem(test.getOperator());
            if(test.getOperandB()!=null)    valueTextField.setText(test.getOperandB());
        }
    }//GEN-LAST:event_conditionComboBoxActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> conditionComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField labelTextField;
    private javax.swing.JPanel objPropertyPanel;
    private javax.swing.JComboBox<String> operatorComboBox;
    private javax.swing.JTextField valueTextField;
    // End of variables declaration//GEN-END:variables
}
