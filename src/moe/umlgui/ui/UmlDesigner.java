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
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.OptionalDataException;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.HashSet;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;


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
        //controlsTabbedPane.addTab("Library", libraryExplorer);
        //controlsTabbedPane.addTab("Diagram", diagramExplorer);
        //controlsTabbedPane.addTab("Project", projectExplorer);
        
        //jSplitPane2.setBottomComponent(new JScrollPane(propertyEditor));
        //propertyEditor.addPropertyChangeListener(this);
        
        pack();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    
    //Palette palette = new Palette();
    //PropertyEditor propertyEditor = new PropertyEditor();
    //LibraryExplorer libraryExplorer = new LibraryExplorer();
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
    
    JTabbedPane contentTabbedPane = null;
    HashSet openDiagrams = new HashSet();
    
    public void display(JPanel p){
        contentPanel.removeAll();
        
        if(UmlDiagramPanel.class.isInstance(p)){
            if(contentTabbedPane==null){
                contentTabbedPane = new JTabbedPane();
                contentTabbedPane.addChangeListener(new ChangeListener(){
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        ArrayList q =new ArrayList();
                        q.add(this);
                        //TODO firing this will make explorer and diagramPanel tab selections tally each other (nice to have)
                        //but will it make it impossible to drag an element from a diagram's explorer into another's diagramPanel
                        //UmlDesigner.this.firePropertyChange("Tab selection", q, contentTabbedPane.getTitleAt(contentTabbedPane.getSelectedIndex()));
                    }        
                });
            }
            if(!openDiagrams.contains(p)){
                contentTabbedPane.addTab(((UmlDiagramPanel)p).getUmlDiagram().getName(), p);
            }
            openDiagrams.add(p);
            
            contentPanel.add(contentTabbedPane , BorderLayout.CENTER);
            revalidate();
            contentTabbedPane.setSelectedComponent(p);
            revalidate();
        }
        else{
            contentPanel.add(p , BorderLayout.CENTER);
            revalidate();
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

        jToolBar1 = new javax.swing.JToolBar();
        newProjectButton = new javax.swing.JButton();
        openProjectButton = new javax.swing.JButton();
        helpButton = new javax.swing.JButton();
        jSplitPane1 = new javax.swing.JSplitPane();
        contentPanel = new javax.swing.JPanel();
        manScrollPane = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        controlsSplitPane = new javax.swing.JSplitPane();
        paletteScrollPane = new javax.swing.JScrollPane();
        palette = new moe.umlgui.ui.Palette();
        controlsTabbedPane = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Waiting4Godot: UML Designer");

        jToolBar1.setRollover(true);

        newProjectButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/24x24/ProjectNew.PNG"))); // NOI18N
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

        openProjectButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/24x24/ProjectOpen.png"))); // NOI18N
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

        helpButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/24x24/preferences-desktop-notification.png"))); // NOI18N
        helpButton.setText("Manual");
        helpButton.setFocusable(false);
        helpButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        helpButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(helpButton);

        getContentPane().add(jToolBar1, java.awt.BorderLayout.NORTH);

        jSplitPane1.setOneTouchExpandable(true);

        contentPanel.setLayout(new java.awt.BorderLayout());

        manScrollPane.setBackground(new java.awt.Color(204, 0, 0));

        jEditorPane1.setEditable(false);
        jEditorPane1.setContentType("text/html"); // NOI18N
        jEditorPane1.setText("<!DOCTYPE html>\n<!--\nCopyright 2023 Moe.\n\nLicensed under the Apache License, Version 2.0 (the \"License\");\nyou may not use this file except in compliance with the License.\nYou may obtain a copy of the License at\n\n     http://www.apache.org/licenses/LICENSE-2.0\n\nUnless required by applicable law or agreed to in writing, software\ndistributed under the License is distributed on an \"AS IS\" BASIS,\nWITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.\nSee the License for the specific language governing permissions and\nlimitations under the License.\n-->\n<html>\n    <head>\n        <title>Waiting4Godot </title>\n        <meta charset=\"UTF-8\">\n        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n    </head>\n    <body>\n        <div><h1>Waiting4Godot User Manual</h1></div>\n        \n        <div><h2>The model</h2></div>\n        \n        \n        <div><h2>Using the tool</h2></div>\n        <div><h3>Diagrams</h3></div>\n        \n        \n        <div><h4>Use Case Diagram</h4></div>\n        <div><p>Supports the following elements: Actor, Use Case and Association.</p></div>\n        \n        <div><h5>Control Level</h5></div>\n        <div>\n            <p>\n                Use Case Diagrams are not constrained.\n            </p>\n        </div>\n        \n        <div><h5>Grouping Elements</h5></div>\n        <div>\n            <p>\n                Use Packages to group Use Cases & Actors.\n            </p>\n        </div>\n        \n        <div><h5>Objects</h5></div>\n        <div>\n            <p>\n            Objects identified while constructing the diagram may be captured as follows:\n            </p>\n            <ul>\n                <li>Business Objects can be captured in Use Case elements.</li>\n                <li>Controllers can be captured in Actor elements.</li>\n            </ul>            \n            <p>\n                Its also possible to link Objects captured in other elements.\n            </p>\n        </div>\n                \n        <div><h5>Attached Diagrams</h5></div>\n        <div>\n            <p>\n                Its possible to catalog further analysis of certain Elements by \n                creating Attached Diagrams. Elements created in these Attached Diagrams\n                are available for use elsewhere in the model. The following Elements support\n                Attached Diagrams.                    \n            </p>                \n            <ul>\n                <li>Use Case</li>\n            </ul>   \n        </div>\n        \n        \n        \n        <div><h4>Activity Diagram</h4></div>\n        <div><p>Supports the following elements: Activity (Action, Call Activity, \n                Accept Event, Accept Time Event, Send Signal) , Activity Node (Start, Stop,Flow End) \n                & Control Node (Conditional Block, While Loop & Repeat Loop).</p></div>\n        \n        <div><h5>Control Level</h5></div>\n        <div>\n            <p>\n                Use Case Diagrams may be free or constrained.\n            </p>\n            <ul>                \n                <li>Free: Suitable mode for high level analysis to help identify and capture Objects, properties & methods.\n                </li>\n                <li>Constrained. Only methods of Objects captured in other diagrams can be used as Activities.</li>\n            </ul>\n        </div>\n        \n        <div><h5>Grouping Elements</h5></div>\n        <div>\n            <p>\n                Use swimlanes to group Use Cases & Actors.\n            </p>\n        </div>\n        \n        <div><h5>Objects</h5></div>\n        <div>\n            <p>\n            Objects identified while constructing the diagram may be captured as follows:\n            </p>\n            <ul>\n                <li></li>\n                <li>C</li>\n            </ul>            \n            <p>\n                \n            </p>\n        </div>\n                \n        <div><h5>Attached Diagrams</h5></div>\n        <div>\n            <p>\n                                  \n            </p>                \n            <ul>\n                <li></li>\n            </ul>   \n        </div>\n        \n        \n        \n        <div><h4>Sequence Diagram</h4></div>\n        <div><p>Supports the following elements: Actor, Message.</p></div>\n        \n        <div><h5>Control Level</h5></div>\n        <div>\n            <p>\n                Sequence Diagrams may be free or constrained.\n            </p>\n            <ul>                \n                <li>Free: Suitable mode for high level analysis to help identify and capture Objects, properties & methods.\n                </li>\n                <li>Constrained. Objects captured in other diagrams can be used as Actors. Only Object Messages can be used as Messages.</li>\n            </ul>\n        </div>\n        \n        <div><h5>Grouping Elements</h5></div>\n        <div>\n            <p>\n                Use Packages to group Messages.\n            </p>\n        </div>\n        \n        <div><h5>Objects</h5></div>\n        <div>\n            <p>\n            Objects identified while constructing the diagram may be captured as follows:\n            </p>\n            \n            <p>\n                Its also possible to link Objects captured in other elements.\n            </p>\n        </div>\n                \n        <div><h5>Attached Diagrams</h5></div>\n        <div>\n            <p>\n                Its possible to catalog further analysis of certain Elements by \n                creating Attached Diagrams. Elements created in these Attached Diagrams\n                are available for use elsewhere in the model. The following Elements support\n                Attached Diagrams.                    \n            </p>                \n        </div>\n        \n        \n        <div><h4>Package Diagram</h4></div>\n        \n        \n        <div><h4>Class Diagram</h4></div>\n        \n    </body>\n</html>\n");
        try {
            jEditorPane1.setPage(getClass().getResource("/man.html"));
        } catch (java.io.IOException e1) {
            e1.printStackTrace();
        }
        manScrollPane.setViewportView(jEditorPane1);

        contentPanel.add(manScrollPane, java.awt.BorderLayout.CENTER);

        jSplitPane1.setRightComponent(contentPanel);

        controlsSplitPane.setDividerLocation(200);
        controlsSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        controlsSplitPane.setOneTouchExpandable(true);

        paletteScrollPane.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createTitledBorder("Palette"), new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED)));
        paletteScrollPane.setViewportView(palette);

        controlsSplitPane.setTopComponent(paletteScrollPane);
        controlsSplitPane.setBottomComponent(controlsTabbedPane);

        jSplitPane1.setLeftComponent(controlsSplitPane);

        getContentPane().add(jSplitPane1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void newProjectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newProjectButtonActionPerformed
        
        JDialog d = new JDialog(this);
        Project project = new Project();
        PropertyEditor pe = new PropertyEditor();
        pe.edit(project);
        pe.addPropertyChangeListener(new PropertyChangeListener(){
            public void propertyChange(PropertyChangeEvent evt) {
                if(evt.getPropertyName().equals("Project updated")){
                    ProjectExplorer projectExplorer = new ProjectExplorer();
                    projectExplorer.addPropertyChangeListener(UmlDesigner.this);
                    UmlDesigner.this.addPropertyChangeListener(projectExplorer);
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
        d.setSize(400, d.getSize().height);
        d.setLocationRelativeTo(null);
        d.setVisible(true);
    }//GEN-LAST:event_newProjectButtonActionPerformed

    private void openProjectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openProjectButtonActionPerformed
        try{
            JFileChooser fc = new JFileChooser();
            fc.setFileFilter(new FileNameExtensionFilter("Waiting4Godot files","w4g"));
            int i = fc.showOpenDialog(null);
            if(i==JFileChooser.APPROVE_OPTION){
                FileInputStream in = new FileInputStream(fc.getSelectedFile());
                ObjectInputStream s = new ObjectInputStream(in);
                Project project = (Project)s.readObject();
                
                ProjectExplorer projectExplorer = new ProjectExplorer();
                projectExplorer.addPropertyChangeListener(UmlDesigner.this);
                UmlDesigner.this.addPropertyChangeListener(projectExplorer);
                projectExplorer.explore(project);
                controlsTabbedPane.addTab(project.getName() , projectExplorer);
                controlsTabbedPane.setSelectedComponent(projectExplorer);
                revalidate();
            }
        } catch(ClassNotFoundException | IOException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error saving", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }//StreamCorruptedException | OptionalDataException  | InvalidClassException  | 
    }//GEN-LAST:event_openProjectButtonActionPerformed

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed
        contentPanel.removeAll();
        contentPanel.add(manScrollPane , BorderLayout.CENTER);
    }//GEN-LAST:event_helpButtonActionPerformed

    //TODO file command line args
    
    /**
     * @param args the command line arguments
     * 
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

        /*JDialog d = new JDialog();
        JLabel l =new JLabel();
        l.setIcon(new javax.swing.ImageIcon(d.getClass().getResource("w4g.png")));
        d.getContentPane().add(l , BorderLayout.CENTER);
        //getClass().getResource("man.html")
        d.pack();
        d.setLocationRelativeTo(null);
        d.setAlwaysOnTop(true);
        d.setVisible(true);
        */
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UmlDesigner().setVisible(true);
                //d.setVisible(false);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contentPanel;
    private javax.swing.JSplitPane controlsSplitPane;
    private javax.swing.JTabbedPane controlsTabbedPane;
    private javax.swing.JButton helpButton;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JScrollPane manScrollPane;
    private javax.swing.JButton newProjectButton;
    private javax.swing.JButton openProjectButton;
    private moe.umlgui.ui.Palette palette;
    private javax.swing.JScrollPane paletteScrollPane;
    // End of variables declaration//GEN-END:variables

    
    public boolean equals(Object o){
        return (hashCode()==o.hashCode());
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {     
        if(!evt.getPropertyName().equals("Display") &&
            !evt.getPropertyName().equals("Tab selection")){
            return;
        }
        
        String pn = evt.getPropertyName();
        if(pn.equals("Display") && !((ArrayList)evt.getOldValue()).contains(this)){
            ((ArrayList)evt.getOldValue()).add(this);
            display((JPanel)evt.getNewValue());
            revalidate();
        }
        else if(pn.equals("Tab selection") && !((ArrayList)evt.getOldValue()).contains(this)){
            ((ArrayList)evt.getOldValue()).add(this);
            if(contentTabbedPane==null) return;
            for(int i=0 ; i < contentTabbedPane.getTabCount() ; i++){
                if(contentTabbedPane.getTitleAt(i).equals(evt.getNewValue())){
                    contentTabbedPane.setSelectedIndex(i);
                }
            }
        }
    }
}
