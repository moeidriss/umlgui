/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package moe.umlgui.ui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.border.TitledBorder;
import moe.umlgui.model.*;

/**
 *
 * @author Moe
 */
public class ProjectExplorer extends javax.swing.JPanel implements PropertyChangeListener{

    
    /**
     * Creates new form ProjectExplorer
     */
    public ProjectExplorer() {
        initComponents();
    }
    
    Project project;
    
    public void explore(Project project){
        this.project = project;
        loadProject();
        
        propertyEditor.setContext(project);
        palette.setProject(project);
        diagramExplorer.setProject(project);
        
        propertyEditor.addPropertyChangeListener(this);
        diagramExplorer.addPropertyChangeListener(this);
    }

    private void loadProject(){        
        setBorder(new TitledBorder(project.getName()));
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

        diagramExplorer = new moe.umlgui.ui.DiagramExplorer();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jSplitPane1 = new javax.swing.JSplitPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        projectExplorerScrollPane = new javax.swing.JScrollPane();
        projectExplorerTree = new javax.swing.JTree();
        paletteScrollPane = new javax.swing.JScrollPane();
        palette = new moe.umlgui.ui.Palette();
        propertyEditorScrollPane = new javax.swing.JScrollPane();
        propertyEditor = new moe.umlgui.ui.PropertyEditor();

        setLayout(new java.awt.BorderLayout());

        jToolBar1.setRollover(true);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/24x24/DiagramNew.PNG"))); // NOI18N
        jLabel1.setText("New Diagram: ");
        jToolBar1.add(jLabel1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Use Case Diagram", "Activity Diagram" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jComboBox1);

        add(jToolBar1, java.awt.BorderLayout.PAGE_START);

        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        projectExplorerTree.setModel(null);
        projectExplorerScrollPane.setViewportView(projectExplorerTree);

        jTabbedPane1.addTab("Explorer", projectExplorerScrollPane);

        paletteScrollPane.setViewportView(palette);

        jTabbedPane1.addTab("Palette", paletteScrollPane);

        jSplitPane1.setLeftComponent(jTabbedPane1);

        propertyEditorScrollPane.setViewportView(propertyEditor);

        jSplitPane1.setRightComponent(propertyEditorScrollPane);

        add(jSplitPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        UmlDiagram ud = null;
        
        if(jComboBox1.getSelectedItem().equals("Use Case Diagram")){
            ud = new UseCaseDiagram();            
        }
        else if(jComboBox1.getSelectedItem().equals("Activity Diagram")){
            ud = new ActivityDiagram();
        }
        
        if(ud !=  null){
            project.getDiagrams().add(ud);
                        
            propertyEditor.edit(ud);
            
            diagramExplorer.exploreDiagram(ud);
            
            jTabbedPane1.setSelectedComponent(paletteScrollPane);
            jTabbedPane1.addTab(ud.getName(), diagramExplorer);
            
            UmlDiagramPanel dp = new UmlDiagramPanel(ud);
            dp.addPropertyChangeListener(this);
            addPropertyChangeListener(dp);
            this.firePropertyChange("Display", null, dp);
            
            revalidate();
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private moe.umlgui.ui.DiagramExplorer diagramExplorer;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private moe.umlgui.ui.Palette palette;
    private javax.swing.JScrollPane paletteScrollPane;
    private javax.swing.JScrollPane projectExplorerScrollPane;
    private javax.swing.JTree projectExplorerTree;
    private moe.umlgui.ui.PropertyEditor propertyEditor;
    private javax.swing.JScrollPane propertyEditorScrollPane;
    // End of variables declaration//GEN-END:variables

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //PropertyEditor events
        if(evt.getPropertyName().equals("Element updated")){
            this.firePropertyChange("Element updated", evt.getOldValue(), evt.getNewValue());
        }
        else if(evt.getPropertyName().equals("Diagram updated")){
            
        }
        else if(evt.getPropertyName().equals("Project updated")){
            
        }
        
        //DiagramPanel events
        else if(evt.getPropertyName().equals("Element inserted")){
            propertyEditor.edit((UmlCoreElement)evt.getNewValue() , ((UmlDiagramPanel)evt.getSource()).umlDiagram);
            project.addCoreElement((UmlCoreElement)evt.getNewValue() );
        }
        
    }
}