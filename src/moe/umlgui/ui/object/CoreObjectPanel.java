/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package moe.umlgui.ui.object;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import moe.umlgui.model.*;
import moe.umlgui.ui.tree.Explorer;
/**
 *
 * @author Moe
 */
public class CoreObjectPanel extends javax.swing.JPanel {

    UmlCoreElement entity;
    CoreObjectTableModel tableModel;
    
    
    static int BUSINESS_OBJECT = 0;
    static int CONTROLLER = 1;
    
    int type;
    
    /**
     * Creates new form LogicalTestPanel
     */
    public CoreObjectPanel(BusinessObjectOwner entity){
        this.entity = (UmlCoreElement)entity;
        type = BUSINESS_OBJECT;
        tableModel = new CoreObjectTableModel();
        initComponents();
        
        this.setBorder(BorderFactory.createTitledBorder("Business Objects"));       
    }

    
    
    /**
     * Creates new form LogicalTestPanel
     */
    public CoreObjectPanel(ControllerOwner entity){
        this.entity = (UmlCoreElement)entity;
        type = CONTROLLER;
        tableModel = new CoreObjectTableModel();
        initComponents();
        
        if(type==BUSINESS_OBJECT){
            setBorder(BorderFactory.createTitledBorder("Business Objects"));       
        }
        else if(type==CONTROLLER){
            setBorder(BorderFactory.createTitledBorder("Controllers"));       
        }
        
    }

    class  CoreObjectTableModel extends DefaultTableModel{
            @Override
            public int getRowCount() {
                if(type==BUSINESS_OBJECT)   
                    return ((BusinessObjectOwner)entity).getBusinessObjects().size();
                else if(type==CONTROLLER)   
                    return ((ControllerOwner)entity).getControllers().size();
                return 0;
            }
            
            String[] colNames = {"Name"};

            @Override
            public int getColumnCount() {
                return colNames.length;
            }

            @Override
            public Object getValueAt(int rowIndex, int columnIndex) {
                
                if(columnIndex == 0){
                    if(type==BUSINESS_OBJECT)   
                        return ((BusinessObjectOwner)entity).getBusinessObjects().toArray()[rowIndex];
                    else if(type==CONTROLLER)   
                        return ((ControllerOwner)entity).getControllers().toArray()[rowIndex];
                    
                }
                
                return null;
            }

            @Override
            public String getColumnName(int columnIndex) {
                return colNames[columnIndex];
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return String.class;
            }

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return false;
            }

            
            @Override
            public void setValueAt(Object aValue, int rowIndex, int columnIndex) {}
            
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
        linkButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        upButton = new javax.swing.JButton();
        downButton = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jTable1.setModel(tableModel);
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
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

        linkButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/16x16/insert-link.png"))); // NOI18N
        linkButton.setFocusable(false);
        linkButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        linkButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        linkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                linkButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(linkButton);

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

    
    class CoreObjectProxy{
        CoreObject obj = null;
        public CoreObjectProxy(CoreObject obj){
            this.obj = obj;
        }        
        
        boolean saveObj() throws ModelException{
            //boooean
            if(type==BUSINESS_OBJECT){
                if(!((BusinessObjectOwner)entity).getBusinessObjects().contains(obj))     
                    ((BusinessObjectOwner)entity).getBusinessObjects().add(((BusinessObject)obj));
            }
            else if(type==CONTROLLER){
                if(!((ControllerOwner)entity).getControllers().contains(obj))     
                    ((ControllerOwner)entity).getControllers().add(((Controller)obj));
            }
            else    return false;
            
            if(!entity.getUmlDiagram().getUmlModel().getElementList().contains(obj)){
                    entity.getUmlDiagram().getUmlModel().addCoreElement(obj);
            }
            return true;
        }
    }
            
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        CoreObject obj = null;
        
        if(type==BUSINESS_OBJECT)   
            obj = new BusinessObject();
        else if(type==CONTROLLER)   
            obj = new Controller();
        else    return;
        
        CoreObjectComponent comp = new CoreObjectComponent(obj , type);
        CoreObjectProxy proxy = new CoreObjectProxy(obj);
        
        JDialog d = new JDialog();
        d.getContentPane().add(comp , BorderLayout.CENTER );
        
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(comp.save()){         
                    try {
                        proxy.saveObj();
                    } catch (ModelException ex) {
                        JOptionPane.showMessageDialog(CoreObjectPanel.this, ex);
                        ex.printStackTrace();
                    }
                    jTable1.revalidate();
                    d.setVisible(false);                    
                }
                else{}//TODO
            }            
        });
        
        d.getContentPane().add(okButton , BorderLayout.SOUTH);
        d.pack();
        d.setLocationRelativeTo(null);
        d.setVisible(true);
        
    }//GEN-LAST:event_addButtonActionPerformed

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed
        if(jTable1.getSelectedRow()==-1)    return;
        int index= jTable1.getSelectedRow();
        
        if(evt.getKeyCode()==java.awt.event.KeyEvent.VK_ENTER){
            CoreObject obj = null;
            
            if(type==BUSINESS_OBJECT)   
                obj = (CoreObject) ((BusinessObjectOwner)entity).getBusinessObjects().toArray()[index];
            else if(type==CONTROLLER)   
                obj = (CoreObject) ((ControllerOwner)entity).getControllers().toArray()[index];
            else    return;
        
            CoreObjectComponent comp = new CoreObjectComponent(obj , type);
            CoreObjectProxy proxy = new CoreObjectProxy(obj);

            JDialog d = new JDialog();
            d.getContentPane().add(comp , BorderLayout.CENTER );

            JButton okButton = new JButton("OK");
            okButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(comp.save()){         
                        try {
                            proxy.saveObj();
                        } catch (ModelException ex) {
                            JOptionPane.showMessageDialog(CoreObjectPanel.this, ex);
                            ex.printStackTrace();
                        }
                        jTable1.revalidate();
                        d.setVisible(false);                    
                    }
                    else{}//TODO
                }            
            });

            d.getContentPane().add(okButton , BorderLayout.SOUTH);
            d.pack();
            d.setLocationRelativeTo(null);
            d.setVisible(true);
        }
    }//GEN-LAST:event_jTable1KeyPressed

    private void linkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_linkButtonActionPerformed
        ArrayList l = new ArrayList();
        for(CoreObject obj : entity.getUmlDiagram().getUmlModel().getProject().getCoreObjects()){
            if((type==BUSINESS_OBJECT && !((BusinessObjectOwner)entity).getBusinessObjects().contains(obj))
                ||
            (type==CONTROLLER && !((ControllerOwner)entity).getControllers().contains(obj)))
            {
                l.add(obj);
            }
        }
        
        Object o = JOptionPane.showInputDialog(this.getParent(), "Select Object to link", "Link Object", JOptionPane.INFORMATION_MESSAGE, null, l.toArray(), null);
        if(type==BUSINESS_OBJECT){
            ((BusinessObjectOwner)entity).getBusinessObjects().add((CoreObject)o);
        }
        else if(type==CONTROLLER){
            ((ControllerOwner)entity).getControllers().add((CoreObject)o);
        }
        //((DefaultTableModel)jTable1.getModel()).addRow(new Object[]{o});
        jTable1.revalidate();
    }//GEN-LAST:event_linkButtonActionPerformed

   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton downButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton linkButton;
    private javax.swing.JButton upButton;
    // End of variables declaration//GEN-END:variables
}