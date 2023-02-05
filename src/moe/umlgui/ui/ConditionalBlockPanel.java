/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package moe.umlgui.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import moe.umlgui.model.*;
/**
 *
 * @author Moe
 */
public class ConditionalBlockPanel extends javax.swing.JPanel {

    //TODO CANCELATION
    
    //TODO JXTreeTable, nesting conditions
    
    //TODO Action list, join, split, fork
    
    ConditionalBlock entity;
    TestTableModel testTableModel;
    
    /**
     * Creates new form LogicalTestPanel
     */
    public ConditionalBlockPanel(ConditionalBlock entity) {
        this.entity = entity;
        testTableModel = new TestTableModel();
        initComponents();
        
        jTable1.getColumn("Operator").setCellRenderer(new TableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JComboBox b = new JComboBox();
                b.setModel(new DefaultComboBoxModel(LogicalTest.OPERATORS));
                if(value!=null) b.setSelectedItem(value);
                return b;
            }
            
        });
        
        jTable1.getColumn("Activity").setCellRenderer(new TableCellRenderer(){
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JButton b = new JButton("Activity Flow");
                b.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ActivityFlowComponent fComp = new ActivityFlowComponent((ActivityFlow)value);
                        JDialog d = new JDialog();
                        d.getContentPane().add(fComp , BorderLayout.CENTER);

                        JButton okButton = new JButton("OK");
                        okButton.addActionListener(new ActionListener(){
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                d.setVisible(false);
                            }            
                        });
                        d.getContentPane().add(okButton , BorderLayout.SOUTH);

                        d.pack();
                        //d.setSize(600 , d.getSize().height);
                        d.setLocationRelativeTo(null);
                        d.setVisible(true);
                    }                    
                });
                if(value!=null) b.setText(value.toString());
                return b;
            }
            
        });
    }

    class  TestTableModel implements TableModel{
            @Override
            public int getRowCount() {
                return entity.getTestList().size();
            }
            
            String[] colNames = {"","Operand" , "Operator" , "Operand" , "Activity"};

            @Override
            public int getColumnCount() {
                return colNames.length;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                LogicalTest t = (entity.getTestList().get(rowIndex));
                
                if(columnIndex == 0){
                    return t.getCondition();
                }
                else if(columnIndex == 1){
                    return t.getOperandA();
                }
                else if(columnIndex == 2){
                    return t.getOperator();
                }
                else if(columnIndex == 3){
                    return t.getOperandB();
                    
                }
                else if(columnIndex == 4){
                    return entity.getTestMap().get(t);
                }
                
                return null;
            }

            @Override
            public String getColumnName(int columnIndex) {
                return colNames[columnIndex];
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if(columnIndex == 1)    return CoreObjectProperty.class;
                else if(columnIndex == 4)    return ArrayList.class;
                return String.class;
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                if(columnIndex==0 || columnIndex==4)  return false;
                return true;
            }

            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
                 LogicalTest t = (entity.getTestList().get(rowIndex));
                
                
                if(columnIndex == 0){
                    t.setCondition((String)aValue);
                }
                else if(columnIndex == 1){
                    t.setOperandA((CoreObjectProperty)aValue);
                }                
                else if(columnIndex == 2){
                    t.setOperator((String)aValue);
                }                
                else if(columnIndex == 3){
                    t.setOperandB((String)aValue);
                }                
                
                
                //if anything but activity is updated in last row, insert new 'else'
                /*if(rowIndex == (entity.getTestList().size()-1) && columnIndex != 4){
                    entity.newTest();
                }*/
            }
            
            ArrayList<TableModelListener> tableModelListeners = new ArrayList();

            @Override
            public void addTableModelListener(TableModelListener l) {
                tableModelListeners.add(l);
            }

            @Override
            public void removeTableModelListener(TableModelListener l) {
                tableModelListeners.remove(l);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        addButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        upButton = new javax.swing.JButton();
        downButton = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jTable1.setModel(testTableModel);
        jScrollPane1.setViewportView(jTable1);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jToolBar1.setBackground(new java.awt.Color(255, 255, 255));
        jToolBar1.setRollover(true);

        addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/16x16/Add.png"))); // NOI18N
        addButton.setFocusable(false);
        addButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(addButton);

        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/16x16/Remove.png"))); // NOI18N
        deleteButton.setFocusable(false);
        deleteButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(deleteButton);

        upButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/16x16/go-up.png"))); // NOI18N
        upButton.setFocusable(false);
        upButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        upButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        upButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(upButton);

        downButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/16x16/go-down.png"))); // NOI18N
        downButton.setFocusable(false);
        downButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        downButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        downButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(downButton);

        add(jToolBar1, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    //TODO implement logic control (a single 'if', single 'else') and testList order
    
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        LogicalTest t = new LogicalTest();
        if(entity.getTestList().isEmpty())  t.setCondition("IF");
        
        ActivityFlow af = new ActivityFlow(entity.getUmlDiagram());
        ActivityFlowComponent fComp = new ActivityFlowComponent(af);
        
        LogicalTestComponent tComp = new LogicalTestComponent(t,entity.getUmlDiagram());
        
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(0,1));
        p.add(tComp);
        p.add(fComp);
        
        JDialog d = new JDialog();
        d.getContentPane().add(p , BorderLayout.CENTER);
        
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                tComp.save();
                entity.addTest(t,af);
                jTable1.revalidate();
                d.setVisible(false);
            }            
        });
        
        JButton clButton = new JButton("Cancel");
        clButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                d.setVisible(false);
            }            
        });
        
        JPanel bPanel = new JPanel(new GridLayout(1,0));
        bPanel.add(okButton);
        bPanel.add(clButton);
        
        d.getContentPane().add(bPanel , BorderLayout.SOUTH);
        
        d.pack();
        d.setSize(600 , d.getSize().height);
        d.setLocationRelativeTo(null);
        d.setVisible(true);
        
    }//GEN-LAST:event_addButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void upButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_upButtonActionPerformed

    private void downButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_downButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton downButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton upButton;
    // End of variables declaration//GEN-END:variables
}
