/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package moe.umlgui.ui;

import java.awt.Component;
import java.util.Iterator;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import moe.umlgui.model.*;

/**
 *
 * @author Moe
 */
public class BusinessObjectMethodComponent extends javax.swing.JPanel {

    BusinessObjectMethod method;
    
    /**
     * Creates new form LogicalTestComponent
     */
    public BusinessObjectMethodComponent(BusinessObjectMethod method) {
        this.method = method;
        initComponents();
        load();
    }

    private void load(){
        if(method.getReturnType()!=null) typeComboBox.setSelectedItem(method.getReturnType());
        if(method.getName()!=null)    nameTextField.setText(method.getName());
        
        jTable1.getColumn("Data Type").setCellRenderer(new TableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JComboBox b = new JComboBox();
                b.setModel(new DefaultComboBoxModel(BusinessObjectProperty.DATA_TYPES));
                if(value!=null) b.setSelectedItem(value);
                //else b.setSelectedIndex(2);
                return b;
            }
            
        });
        
        for(Iterator<String> i = method.getParameters().keySet().iterator() ; i.hasNext();){
            String p = i.next();
            ((DefaultTableModel)jTable1.getModel()).addRow(new Object[]{p,method.getParameters().get(p)});
        }
        
        //blank row
        if(method.getParameters().size()==0){
            ((DefaultTableModel)jTable1.getModel()).addRow(new Object[]{null,null});
        }
        ((DefaultTableModel)jTable1.getModel()).addTableModelListener(new TableModelListener(){
            @Override
            public void tableChanged(TableModelEvent e) {
                if(((DefaultTableModel)jTable1.getModel()).getValueAt(((DefaultTableModel)jTable1.getModel()).getRowCount()-1,0)!=null){
                    ((DefaultTableModel)jTable1.getModel()).addRow(new Object[]{null,null});
                }
            }            
        });
    }
    
    //TODO VALIDATE
    public void save(){
        method.setReturnType((String)typeComboBox.getSelectedItem());
        method.setName(nameTextField.getText());
        
        
        for(int i=0 ; i<((DefaultTableModel)jTable1.getModel()).getRowCount() ; i++){
            Object pN = ((DefaultTableModel)jTable1.getModel()).getValueAt(i,0);
            Object pT = ((DefaultTableModel)jTable1.getModel()).getValueAt(i,1);
            java.lang.System.out.println(pN +  ":" + pT);
            if(pN!=null && pT!=null){
                method.getParameters().put((String)pN, (String)pT);
            }
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
        java.awt.GridBagConstraints gridBagConstraints;

        nameTextField = new javax.swing.JTextField();
        typeComboBox = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

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

        typeComboBox.setModel(new DefaultComboBoxModel(BusinessObjectMethod.RETURN_TYPES));
        typeComboBox.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder("Return Type"), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED)));
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

        jScrollPane1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder("Parameters"), new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED)));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Data Type"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(jScrollPane1, gridBagConstraints);
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JComboBox<String> typeComboBox;
    // End of variables declaration//GEN-END:variables
}
