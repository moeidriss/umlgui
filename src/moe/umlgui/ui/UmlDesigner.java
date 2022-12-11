/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package moe.umlgui.ui;

import moe.umlgui.model.*;
import moe.umlgui.model.UmlDiagram;
import moe.umlgui.model.UseCaseDiagram;
import moe.umlgui.model.Project;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


/**
 *
 * @author Moe
 */
public class UmlDesigner extends javax.swing.JFrame implements PropertyChangeListener{

    //TODO tab order, default comps
    //TODO control keys
    
    /**
     * Creates new form UmlEditor
     */
    public UmlDesigner() {
        initComponents();
        buildEditor();
    }

    
    
    
    private void buildEditor(){ 
        //controlsTabbedPane.addTab("Palette", palette);
        controlsTabbedPane.addTab("Library", libraryExplorer);
        //controlsTabbedPane.addTab("Diagram", diagramExplorer);
        //controlsTabbedPane.addTab("Project", projectExplorer);
        
        //jSplitPane2.setBottomComponent(new JScrollPane(propertyEditor));
        //propertyEditor.addPropertyChangeListener(this);
        
        pack();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    //Palette palette = new Palette();
    //PropertyEditor propertyEditor = new PropertyEditor();
    LibraryExplorer libraryExplorer = new LibraryExplorer();
    //DiagramExplorer diagramExplorer = new DiagramExplorer();    
    //ProjectExplorer projectExplorer = new ProjectExplorer(this);*/
    
    
    
    /*public void openDiagram(UmlDiagram umlDiagram){
        UmlDiagramPanel dp = new UmlDiagramPanel(umlDiagram);
        contentPanel.removeAll();
        contentPanel.add(dp , BorderLayout.CENTER);
        dp.addPropertyChangeListener(this);
        addPropertyChangeListener(dp);
        propertyEditor.edit(umlDiagram);
        diagramExplorer.exploreDiagram(umlDiagram);
        revalidate();
    }
    */
    
    public void display(JPanel p){
        contentPanel.removeAll();
        contentPanel.add(p , BorderLayout.CENTER);
        revalidate();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        newProjectButton = new javax.swing.JButton();
        openProjectButton = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        contentPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        controlsTabbedPane = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Waiting4Godot: UML Designer");

        jToolBar1.setRollover(true);

        newProjectButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/24x24/ProjectNew.PNG"))); // NOI18N
        newProjectButton.setText("New Project");
        newProjectButton.setToolTipText("New Project");
        newProjectButton.setFocusable(false);
        newProjectButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        newProjectButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        newProjectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newProjectButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(newProjectButton);

        openProjectButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/24x24/ProjectOpen.png"))); // NOI18N
        openProjectButton.setText("Open Project");
        openProjectButton.setToolTipText("Open Project");
        openProjectButton.setFocusable(false);
        openProjectButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        openProjectButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        openProjectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openProjectButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(openProjectButton);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.NORTH);

        jSplitPane1.setDividerLocation(100);
        jSplitPane1.setResizeWeight(0.3);
        jSplitPane1.setOneTouchExpandable(true);

        contentPanel.setLayout(new java.awt.BorderLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/w4g.png"))); // NOI18N
        contentPanel.add(jLabel1, java.awt.BorderLayout.CENTER);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("IN PROGRESS...");
        jLabel2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        contentPanel.add(jLabel2, java.awt.BorderLayout.SOUTH);

        jSplitPane1.setRightComponent(contentPanel);
        jSplitPane1.setTopComponent(controlsTabbedPane);

        getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newProjectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newProjectButtonActionPerformed
        
        JDialog d = new JDialog(this);
        Project project = new Project();
        PropertyEditor pe = new PropertyEditor();
        pe.setContext(project);
        pe.edit(project);
        pe.addPropertyChangeListener(new PropertyChangeListener(){
            public void propertyChange(PropertyChangeEvent evt) {
                if(evt.getPropertyName().equals("Project updated")){
                    ProjectExplorer projectExplorer = new ProjectExplorer();
                    projectExplorer.addPropertyChangeListener(UmlDesigner.this);
                    projectExplorer.explore(project);
                    controlsTabbedPane.addTab(project.getName() , projectExplorer);
                    controlsTabbedPane.setSelectedComponent(projectExplorer);
                    revalidate();
                    d.setVisible(false);
                }
            }
        });
        
        d.getContentPane().add(pe , BorderLayout.CENTER);
        d.pack();
        d.setLocationRelativeTo(null);
        d.setVisible(true);
    }//GEN-LAST:event_newProjectButtonActionPerformed

    private void openProjectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openProjectButtonActionPerformed
        // TODO add your handling code here:
        
        //setupProject();
    }//GEN-LAST:event_openProjectButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(UmlDesigner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(UmlDesigner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(UmlDesigner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(UmlDesigner.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UmlDesigner().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contentPanel;
    private javax.swing.JTabbedPane controlsTabbedPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton newProjectButton;
    private javax.swing.JButton openProjectButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String pn = evt.getPropertyName();
        if(pn.equals("Display")){
            display((JPanel)evt.getNewValue());
            revalidate();
        }
        /*else if(pn.equals("Project in focus")){
            propertyEditor.edit((Project)evt.getNewValue() , ((Project)evt.getSource()).project);
            revalidate();
        }
        else if(pn.equals("Element updated")){
            this.firePropertyChange("Element saved", evt.getOldValue(), evt.getNewValue());
        }*/
    }
}