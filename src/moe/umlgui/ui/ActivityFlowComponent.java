/*
 * Copyright 2023 Moe.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package moe.umlgui.ui;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import moe.umlgui.model.AcceptEvent;
import moe.umlgui.model.AcceptTimeEvent;
import moe.umlgui.model.Action;
import moe.umlgui.model.CallActivity;
import moe.umlgui.model.ActivityFinalNode;
import moe.umlgui.model.ActivityFlow;
import moe.umlgui.model.ConditionalBlock;
import moe.umlgui.model.FlowFinalNode;
import moe.umlgui.model.ModelException;
import moe.umlgui.model.RepeatLoop;
import moe.umlgui.model.SendSignal;
import moe.umlgui.model.UmlCoreElement;
import moe.umlgui.model.WhileLoop;

/**
 *
 * @author Moe
 */
public class ActivityFlowComponent extends javax.swing.JPanel {

    ActivityFlow activityFlow;
    
    
    //TODO if
    /**
     * Creates new form ActivityFlowComponent
     */
    public ActivityFlowComponent(ActivityFlow flow) {
        this.activityFlow = flow;
        initComponents();
        reloadList();
    }

    public ActivityFlow getActivityFlow() {
        return activityFlow;
    }

    
    private void reloadList(){
        ((DefaultListModel)jList1.getModel()).removeAllElements();
        for(Iterator i = activityFlow.iterator() ; i.hasNext() ; ){
          ((DefaultListModel)jList1.getModel()).addElement(i.next());
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
        jList1 = new javax.swing.JList<>();
        jToolBar1 = new javax.swing.JToolBar();
        addButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        upButton = new javax.swing.JButton();
        downButton = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jList1.setModel(new DefaultListModel());
        jList1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(jList1);

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

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        
        Object sel = JOptionPane.showInputDialog(null, "Select Actvity Type" ,
            "Actvity Type", JOptionPane.INFORMATION_MESSAGE, null,
            new String[] {"Action","Flow End","Stop" , "Call Activity"
             , "Accept Time Event" , "Send Signal" , "Conditional Block" ,
             "While Loop" , "Repeat Loop"}, "Action");
        
        final PropertyEditor pe = new PropertyEditor();
        UmlCoreElement ac = null;
        if(sel.equals("Action")){
            ac = new Action();
        }
        else if(sel.equals("Flow End")){
            ac = new FlowFinalNode();
        }
        else if(sel.equals("Stop")){
            ac = new ActivityFinalNode();
        }
        else if(sel.equals("Stop")){
            ac = new ActivityFinalNode();
        }
        else if(sel.equals("Call Activity")){
            ac = new CallActivity();
        }
        else if(sel.equals("Accept Event")){
            ac = new AcceptEvent();
        }
        else if(sel.equals("Accept Time Event")){
            ac = new AcceptTimeEvent();
        }
        else if(sel.equals("Send Signal")){
            ac = new SendSignal();
        }
        else if(sel.equals("Conditional Block")){
            ac = new ConditionalBlock();
        }
        else if(sel.equals("While Loop")){
            ac = new WhileLoop();
        }
        else if(sel.equals("Repeat Loop")){
            ac = new RepeatLoop();
        }
        else if(sel.equals("Split")){
            ac = new WhileLoop();
        }
        else if(sel.equals("Fork")){
            ac = new RepeatLoop();
            
        }
        
        if(ac!=null){
            try {
                activityFlow.getDiagram().addCoreElement(ac);
                pe.showToolbar(false);
                pe.edit(ac);

                JDialog acDialog = new JDialog();
                acDialog.getContentPane().add(pe , BorderLayout.CENTER);

                JPanel buttonsP = new JPanel();
                JButton okButton = new JButton("OK");
                okButton.addActionListener(new ActionListener(){
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        pe.save();                    
                        activityFlow.add(pe.umlCoreElement);//position   
                        reloadList();
                        acDialog.setVisible(false);
                    }            
                });
                buttonsP.add(okButton);

                acDialog.getContentPane().add(buttonsP , BorderLayout.SOUTH);
                acDialog.pack();
                acDialog.setSize(acDialog.getSize().width , (int)(Toolkit.getDefaultToolkit().getScreenSize().height*0.75));
                acDialog.setLocationRelativeTo(null);

                acDialog.setVisible(true);
            } catch (ModelException ex) {
                Logger.getLogger(ActivityFlowComponent.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }

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
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton upButton;
    // End of variables declaration//GEN-END:variables
}
