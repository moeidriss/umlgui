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
public class LogicalTestPanel extends javax.swing.JPanel {

    ConditionalBlock entity;
    TestTableModel testTableModel;
    
    Project context;
    
    /**
     * Creates new form LogicalTestPanel
     */
    public LogicalTestPanel(ConditionalBlock entity, Project context) {
        this.entity = entity;
        this.context = context;
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
                JComboBox b = new JComboBox();
                b.setModel(new DefaultComboBoxModel(context.getActivityList().toArray()));
                if(value!=null) b.setSelectedItem(value);
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
                if(columnIndex == 4)    return ArrayList.class;//TODO ...
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
                    t.setOperandA((String)aValue);
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
        jToolBar1.add(deleteButton);

        upButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/16x16/go-up.png"))); // NOI18N
        upButton.setFocusable(false);
        upButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        upButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(upButton);

        downButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/16x16/go-down.png"))); // NOI18N
        downButton.setFocusable(false);
        downButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        downButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(downButton);

        add(jToolBar1, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    //TODO implement logic control (a single 'if', single 'else') and testList order
    
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        LogicalTest t = entity.newTest();
        if(entity.getTestList().isEmpty())  t.setCondition("IF");
        
        Activity ac = new Action();
        
        LogicalTestComponent tComp = new LogicalTestComponent(t);
        
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(0,1));
        p.add(tComp);
        
        //NewActivityButton'
        
        
        JComboBox cb = new JComboBox();
        cb.setModel(new DefaultComboBoxModel(context.getActivityList().toArray()));
        
        JButton newActivityButton = new JButton("New ...");
        newActivityButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Object sel = JOptionPane.showInputDialog(null, "Select Actvity Type" , 
                        "Actvity Type", JOptionPane.INFORMATION_MESSAGE, null, 
                        new String[] {"Action","Flow End","Stop"}, "Action");
                Activity ac = null;
                if(sel.equals("Action")){
                    ac = new Action();
                }
                else if(sel.equals("Flow End")){
                    ac = new FlowFinalNode();
                }
                else if(sel.equals("Stop")){
                    ac = new ActivityFinalNode();
                }                
                
                if(ac!=null){
                    editNewActivity(ac,cb);
                }
            }            
        });
        
        JPanel pp = new JPanel();
        pp.add(new JLabel("Action: "));
        pp.add(cb);
        pp.add(newActivityButton);
        p.add(pp);
        
        JDialog d = new JDialog();
        d.getContentPane().add(p , BorderLayout.CENTER);
        
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                tComp.save();
                entity.getTestMap().get(t).add((Activity)cb.getSelectedItem());
                //entity.newTest();//new 'else'
                jTable1.revalidate();
                d.setVisible(false);
            }            
        });
        d.getContentPane().add(okButton , BorderLayout.SOUTH);
        
        d.pack();
        d.setSize(600 , d.getSize().height);
        d.setLocationRelativeTo(null);
        d.setVisible(true);
        
    }//GEN-LAST:event_addButtonActionPerformed

    private void editNewActivity(Activity ac ,JComboBox cb ){
        PropertyEditor pe = new PropertyEditor();
        pe.setContext(context);
        pe.edit(ac , null);
        pe.showToolbar(false);
        
        JDialog acDialog = new JDialog();
        acDialog.getContentPane().add(pe , BorderLayout.CENTER);
        
        JPanel buttonsP = new JPanel();
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                pe.save();
                context.addCoreElement(ac);
                cb.setModel(new DefaultComboBoxModel(context.getActivityList().toArray()));
                cb.setSelectedItem(ac);
                cb.revalidate();
                acDialog.setVisible(false);
            }            
        });
        buttonsP.add(okButton);
        
        acDialog.getContentPane().add(buttonsP , BorderLayout.SOUTH);
        acDialog.pack();
        acDialog.setSize(600 , acDialog.getSize().height);
        acDialog.setLocationRelativeTo(null);
        
        acDialog.setVisible(true);
    }

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
