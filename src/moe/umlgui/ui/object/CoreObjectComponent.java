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
package moe.umlgui.ui.object;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import moe.umlgui.model.CoreObject;
import moe.umlgui.model.CoreObjectMethod;
import moe.umlgui.model.CoreObjectProperty;

/**
 *
 * @author Moe
 */
public class CoreObjectComponent extends javax.swing.JPanel {

    int type;
    
    CoreObject obj;
    
    /**
     * Creates new form CoreObjectComponent
     */
    public CoreObjectComponent(CoreObject obj , int type) {
        this.obj = obj;
        this.type = type;
        initComponents();
        loadComp();
    }
 
    
    private void loadComp(){
        if(obj.getName()!=null) this.nameTextField.setText(obj.getName());
        
        for(CoreObjectProperty prop : obj.getProperties()){
            ((DefaultListModel)propertyList.getModel()).addElement(prop);
        }
        for(CoreObjectMethod meth : obj.getMethods()){
            ((DefaultListModel)methodList.getModel()).addElement(meth);
        }
    }
    
    public boolean save(){        
        obj.setName(nameTextField.getText());   
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
        detailsPropPanel = new javax.swing.JPanel();
        detailsPropScrollPane = new javax.swing.JScrollPane();
        propertyList = new javax.swing.JList<>();
        propToolBar = new javax.swing.JToolBar();
        addPropertyButton = new javax.swing.JButton();
        deletePropertyButton = new javax.swing.JButton();
        detailsMethPanel = new javax.swing.JPanel();
        detailsMethScrollPane = new javax.swing.JScrollPane();
        methodList = new javax.swing.JList<>();
        methToolBar = new javax.swing.JToolBar();
        addMethodButton = new javax.swing.JButton();
        deleteMethodButton = new javax.swing.JButton();

        setLayout(new java.awt.GridBagLayout());

        nameTextField.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder("Name"), new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        add(nameTextField, gridBagConstraints);

        detailsPropPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Properties"));
        detailsPropPanel.setLayout(new java.awt.BorderLayout());

        propertyList.setModel(new DefaultListModel());
        propertyList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                propertyListKeyPressed(evt);
            }
        });
        detailsPropScrollPane.setViewportView(propertyList);

        detailsPropPanel.add(detailsPropScrollPane, java.awt.BorderLayout.CENTER);

        propToolBar.setBackground(new java.awt.Color(255, 255, 255));
        propToolBar.setRollover(true);

        addPropertyButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/16x16/Add.png"))); // NOI18N
        addPropertyButton.setFocusable(false);
        addPropertyButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addPropertyButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addPropertyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPropertyButtonActionPerformed(evt);
            }
        });
        propToolBar.add(addPropertyButton);

        deletePropertyButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/16x16/Remove.png"))); // NOI18N
        deletePropertyButton.setFocusable(false);
        deletePropertyButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deletePropertyButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        propToolBar.add(deletePropertyButton);

        detailsPropPanel.add(propToolBar, java.awt.BorderLayout.PAGE_START);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(detailsPropPanel, gridBagConstraints);

        detailsMethPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Methods"));
        detailsMethPanel.setLayout(new java.awt.BorderLayout());

        methodList.setModel(new DefaultListModel());
        methodList.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                methodListKeyPressed(evt);
            }
        });
        detailsMethScrollPane.setViewportView(methodList);

        detailsMethPanel.add(detailsMethScrollPane, java.awt.BorderLayout.CENTER);

        methToolBar.setBackground(new java.awt.Color(255, 255, 255));
        methToolBar.setRollover(true);

        addMethodButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/16x16/Add.png"))); // NOI18N
        addMethodButton.setFocusable(false);
        addMethodButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addMethodButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addMethodButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMethodButtonActionPerformed(evt);
            }
        });
        methToolBar.add(addMethodButton);

        deleteMethodButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/16x16/Remove.png"))); // NOI18N
        deleteMethodButton.setFocusable(false);
        deleteMethodButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        deleteMethodButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        methToolBar.add(deleteMethodButton);

        detailsMethPanel.add(methToolBar, java.awt.BorderLayout.PAGE_START);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        add(detailsMethPanel, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void propertyListKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_propertyListKeyPressed
        if(propertyList.getSelectedIndex()==-1) return;

        if(evt.getKeyCode()==java.awt.event.KeyEvent.VK_ENTER){
            CoreObjectProperty prop = (CoreObjectProperty)((DefaultListModel)propertyList.getModel()).getElementAt(propertyList.getSelectedIndex());
            CoreObjectPropertyComponent pComp = new CoreObjectPropertyComponent(prop);

            JDialog pD = new JDialog();
            JButton pB = new JButton("OK");
            pB.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(pComp.save()){
                        propertyList.revalidate();
                    }
                    pD.setVisible(false);
                }
            });

            pD.getContentPane().add(pComp , BorderLayout.CENTER);
            pD.getContentPane().add(pB , BorderLayout.SOUTH);
            pD.pack();
            pD.setLocationRelativeTo(null);
            pD.setVisible(true);
        }
    }//GEN-LAST:event_propertyListKeyPressed

    private void addPropertyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addPropertyButtonActionPerformed
        CoreObjectProperty prop = new CoreObjectProperty(obj); 
        CoreObjectPropertyComponent pComp = new CoreObjectPropertyComponent(prop);

        JDialog pD = new JDialog();
        JButton pB = new JButton("OK");
        pB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if( pComp.save()){
                    obj.getProperties().add(prop);
                    ((DefaultListModel)propertyList.getModel()).addElement(prop);
                }
                pD.setVisible(false);
            }                    
        });

        pD.getContentPane().add(pComp , BorderLayout.CENTER);
        pD.getContentPane().add(pB , BorderLayout.SOUTH);
        pD.pack();
        pD.setLocationRelativeTo(null);
        pD.setVisible(true);
    }//GEN-LAST:event_addPropertyButtonActionPerformed

    private void methodListKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_methodListKeyPressed
        if(methodList.getSelectedIndex()==-1) return;

        if(evt.getKeyCode()==java.awt.event.KeyEvent.VK_ENTER){
            CoreObjectMethod meth = (CoreObjectMethod)((DefaultListModel)methodList.getModel()).getElementAt(methodList.getSelectedIndex());
            CoreObjectMethodComponent pComp = new CoreObjectMethodComponent(meth);

            JDialog pD = new JDialog();
            JButton pB = new JButton("OK");
            pB.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(pComp.save()){
                        methodList.revalidate();
                    }
                    pD.setVisible(false);
                }
            });

            pD.getContentPane().add(pComp , BorderLayout.CENTER);
            pD.getContentPane().add(pB , BorderLayout.SOUTH);
            pD.pack();
            pD.setLocationRelativeTo(null);
            pD.setVisible(true);
        }
    }//GEN-LAST:event_methodListKeyPressed

    private void addMethodButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMethodButtonActionPerformed
        CoreObjectMethod meth = new CoreObjectMethod(obj);
        CoreObjectMethodComponent mComp = new CoreObjectMethodComponent(meth);

        JDialog pD = new JDialog();
        JButton pB = new JButton("OK");
        pB.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mComp.save()){
                    obj.getMethods().add(meth);
                    ((DefaultListModel)methodList.getModel()).addElement(meth);
                }
                pD.setVisible(false);
            }                    
        });

        pD.getContentPane().add(mComp , BorderLayout.CENTER);
        pD.getContentPane().add(pB , BorderLayout.SOUTH);
        pD.pack();
        pD.setLocationRelativeTo(null);
        pD.setVisible(true);
    }//GEN-LAST:event_addMethodButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addMethodButton;
    private javax.swing.JButton addPropertyButton;
    private javax.swing.JButton deleteMethodButton;
    private javax.swing.JButton deletePropertyButton;
    private javax.swing.JPanel detailsMethPanel;
    private javax.swing.JScrollPane detailsMethScrollPane;
    private javax.swing.JPanel detailsPropPanel;
    private javax.swing.JScrollPane detailsPropScrollPane;
    private javax.swing.JToolBar methToolBar;
    private javax.swing.JList<String> methodList;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JToolBar propToolBar;
    private javax.swing.JList<String> propertyList;
    // End of variables declaration//GEN-END:variables
}
