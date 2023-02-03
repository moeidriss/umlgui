/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package moe.umlgui.ui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import moe.umlgui.model.*;
import moe.umlgui.ui.tree.Explorer;
import moe.umlgui.ui.tree.ExplorerTreeCellRenderer;
import moe.umlgui.ui.tree.ExplorerTreeModel;

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
    Explorer explorer;
    HashMap<UmlDiagram, DiagramExplorer> openDiagramExplorers = new HashMap();
    HashMap<UmlDiagram, UmlDiagramPanel> openDiagramPanels = new HashMap();
    
    public void explore(Project project){
        this.project = project;
        loadProject();
    }

    private void loadProject(){        
        //setBorder(new TitledBorder(project.getName()));
        
        propertyEditor.setContext(project);
        //palette.setProject(project);
        
        explorer = new Explorer(project);      
        explorer.addPropertyChangeListener(this);
        jSplitPane2.setTopComponent(explorer);
        propertyEditor.addPropertyChangeListener(this);
        
        jTabbedPane1.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                ArrayList q =new ArrayList();
                q.add(this);
                //TODO firing this will make explorer and diagramPanel tab selections tally each other (nice to have)
                //but will it make it impossible to drag an element from a diagram's explorer into another's diagramPanel
                //ProjectExplorer.this.firePropertyChange("Tab selection", q, jTabbedPane1.getTitleAt(jTabbedPane1.getSelectedIndex()));
            }        
        });
        
        revalidate();
    }
    
    private void closeDiagram(int i){
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        saveProjectButton = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        jLabel1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jSplitPane1 = new javax.swing.JSplitPane();
        jSplitPane2 = new javax.swing.JSplitPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        pHolderPanel2 = new javax.swing.JPanel();
        pHolderPanel1 = new javax.swing.JPanel();
        propertyEditorScrollPane = new javax.swing.JScrollPane();
        propertyEditor = new moe.umlgui.ui.PropertyEditor();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.GridLayout(2, 0));

        saveProjectButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/24x24/media-floppy.png"))); // NOI18N
        saveProjectButton.setText("Save Project");
        saveProjectButton.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        saveProjectButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        saveProjectButton.setIconTextGap(200);
        saveProjectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveProjectButtonActionPerformed(evt);
            }
        });
        jPanel1.add(saveProjectButton);

        jToolBar1.setFloatable(true);
        jToolBar1.setRollover(true);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/24x24/DiagramNew.PNG"))); // NOI18N
        jLabel1.setText("New Diagram: ");
        jToolBar1.add(jLabel1);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Use Case Diagram", "Activity Diagram", "Sequence Diagram", "Package Diagram" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jToolBar1.add(jComboBox1);

        jPanel1.add(jToolBar1);

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jSplitPane1.setDividerLocation(200);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setOneTouchExpandable(true);

        jSplitPane2.setDividerLocation(100);
        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane2.setOneTouchExpandable(true);

        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });
        jTabbedPane1.addTab("tab1", pHolderPanel2);

        jSplitPane2.setBottomComponent(jTabbedPane1);
        jSplitPane2.setLeftComponent(pHolderPanel1);

        jSplitPane1.setLeftComponent(jSplitPane2);

        propertyEditorScrollPane.setViewportView(propertyEditor);

        jSplitPane1.setRightComponent(propertyEditorScrollPane);

        add(jSplitPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        UmlModel model = null;
        ArrayList a = new ArrayList();
        a.addAll(project.getModels());
        a.add("-- NEW MODEL --");
        Object o = JOptionPane.showInputDialog(this, "Select Model", "New Diagram", JOptionPane.INFORMATION_MESSAGE, null, a.toArray(), a.get(0));
        if(o!=null && o.equals("-- NEW MODEL --")){
            String n = JOptionPane.showInputDialog(this,"Name it", "New Business Model");
            if(n!=null && !n.isEmpty()){
                model = new BusinessModel(project);
                model.setName(n);
            }
        }
        else if(o!=null && UmlModel.class.isInstance(o))    
            model = (UmlModel)o;

        if(model==null){
            model = new BusinessModel(project);
            model.setName("New Business Model");
        }

        
        UmlDiagram ud = null;
        
        if(jComboBox1.getSelectedItem().equals("Use Case Diagram")){
            ud = new UseCaseDiagram(model);            
        }
        else if(jComboBox1.getSelectedItem().equals("Activity Diagram")){
            ud = new ActivityDiagram(model);
        }
        else if(jComboBox1.getSelectedItem().equals("Sequence Diagram")){
            ud = new SequenceDiagram(model);
        }
        else if(jComboBox1.getSelectedItem().equals("Package Diagram")){
            ud = new PackageDiagram(model);
        }
        
        if(ud !=  null){
            project.getDiagrams().add(ud);
            model.getDiagrams().add(ud);
            propertyEditor.edit(ud);
            
            DiagramExplorer diagramExplorer = new DiagramExplorer();
            diagramExplorer.setProject(project);
            diagramExplorer.exploreDiagram(ud);
            diagramExplorer.addPropertyChangeListener(this);
            addPropertyChangeListener(diagramExplorer);
            openDiagramExplorers.put(ud, diagramExplorer);
            
            jTabbedPane1.addTab(ud.getName(), new ImageIcon(getClass().getResource("/moe/umlgui/img/16x16/Cancel.png")), diagramExplorer );
            jTabbedPane1.setSelectedComponent(diagramExplorer);
            
            UmlDiagramPanel dp = new UmlDiagramPanel(ud);
            dp.addPropertyChangeListener(this);
            dp.addPropertyChangeListener(diagramExplorer);
            addPropertyChangeListener(dp);
            diagramExplorer.addPropertyChangeListener(dp);
            openDiagramPanels.put(ud, dp);
            
            ArrayList q =new ArrayList();
            q.add(this);
            this.firePropertyChange("Display", q, dp);
            
            explorer.reload();
            revalidate();
            this.getParent().revalidate();
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void saveProjectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveProjectButtonActionPerformed
        try{
            JFileChooser fc = new JFileChooser((project.getFolder()));
            fc.setFileFilter(new FileNameExtensionFilter("Waiting4Godot files","w4g"));
            if(fc.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
                File f = new File(fc.getSelectedFile().getAbsolutePath()+".w4g");//TODO fix
                FileOutputStream out = new FileOutputStream(f);
                ObjectOutputStream s = new ObjectOutputStream(out);
                s.writeObject(project);
                s.flush();
            }
        } catch (java.io.IOException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error saving", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_saveProjectButtonActionPerformed

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        int i = jTabbedPane1.getUI().tabForCoordinate(jTabbedPane1, evt.getX(), evt.getY());
        if(i!=-1)   closeDiagram(i);
    }//GEN-LAST:event_jTabbedPane1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JPanel pHolderPanel1;
    private javax.swing.JPanel pHolderPanel2;
    private moe.umlgui.ui.PropertyEditor propertyEditor;
    private javax.swing.JScrollPane propertyEditorScrollPane;
    private javax.swing.JButton saveProjectButton;
    // End of variables declaration//GEN-END:variables

    
    
    public boolean equals(Object o){
        return (hashCode()==o.hashCode());
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(!evt.getPropertyName().equals("Element updated") &&
            !evt.getPropertyName().equals("Diagram updated")  &&
            !evt.getPropertyName().equals("Project updated") &&
            !evt.getPropertyName().equals("Element inserted")  &&
            !evt.getPropertyName().equals("Element removed")  &&
            !evt.getPropertyName().equals("Diagram attached")  &&
            !evt.getPropertyName().equals("Explorer selection")   &&
            !evt.getPropertyName().equals("Tab selection") 
        ){
            return;
        }
                
        //PropertyEditor events
        if(evt.getPropertyName().equals("Element updated") && !((ArrayList)evt.getOldValue()).contains(this)){
            ((ArrayList)evt.getOldValue()).add(this);
            explorer.reload();
            explorer.setSelection(evt.getNewValue());
            this.firePropertyChange("Element updated", evt.getOldValue(), evt.getNewValue());
        }
        else if(evt.getPropertyName().equals("Diagram updated") && !((ArrayList)evt.getOldValue()).contains(this)){
            ((ArrayList)evt.getOldValue()).add(this);
            explorer.reload();
            explorer.setSelection(evt.getNewValue());
        }
        else if(evt.getPropertyName().equals("Project updated") &&!((ArrayList)evt.getOldValue()).contains(this)){
            ((ArrayList)evt.getOldValue()).add(this);
            explorer.reload();
            explorer.setSelection(evt.getNewValue());
        }
        
        //DiagramPanel / diagramExplorer events
        else if(evt.getPropertyName().equals("Element inserted") && !((ArrayList)evt.getOldValue()).contains(this)){
            ((ArrayList)evt.getOldValue()).add(this);
            UmlCoreElement el = (UmlCoreElement)evt.getNewValue();
            //project.addCoreElement(el);
            propertyEditor.edit(el);        
            explorer.reload();
            
            //TODO SELECTION
            //only if element is displayable: attOwner with parent diagram inserted
            explorer.setSelection(el);    
        }
        
        else if(evt.getPropertyName().equals("Element removed")){
            explorer.reload();
        }
        
        else if(evt.getPropertyName().equals("Diagram attached") && !((ArrayList)evt.getOldValue()).contains(this)){
            ((ArrayList)evt.getOldValue()).add(this);
            
            UmlDiagram ud = (UmlDiagram)evt.getNewValue();
            //project.getDiagrams().add(ud);
            //project.getModels().get(0).getDiagrams().add(ud);
            //TODO assoc attached diagrams to project (!) only for an inventory of diagrams in the project
            //
            propertyEditor.edit(ud);
                
            DiagramExplorer diagramExplorer = openDiagramExplorers.get(ud);
            if(diagramExplorer==null){
                diagramExplorer = new DiagramExplorer();                
                diagramExplorer.setProject(project);
                diagramExplorer.exploreDiagram(ud);
                diagramExplorer.addPropertyChangeListener(this);
                addPropertyChangeListener(diagramExplorer);
                openDiagramExplorers.put(ud, diagramExplorer);
                jTabbedPane1.addTab(ud.getName(), diagramExplorer);
            }
            jTabbedPane1.setSelectedComponent(diagramExplorer);

            UmlDiagramPanel dp = openDiagramPanels.get(ud);
            if(dp==null){
                dp = new UmlDiagramPanel(ud);
                dp.addPropertyChangeListener(this);
                dp.addPropertyChangeListener(diagramExplorer);
                addPropertyChangeListener(dp);
                diagramExplorer.addPropertyChangeListener(dp);
                openDiagramPanels.put(ud, dp);
            }
            

            ArrayList q =new ArrayList();
            q.add(this);
            this.firePropertyChange("Display", q, dp);

            explorer.reload();
            revalidate();
        }
        
        
        else if(evt.getPropertyName().equals("Explorer selection") && !((ArrayList)evt.getOldValue()).contains(this)){
            ((ArrayList)evt.getOldValue()).add(this);
            
            if(Project.class.isInstance(evt.getNewValue())){
                propertyEditor.edit((Project)evt.getNewValue());
            }
            else if(UmlModel.class.isInstance(evt.getNewValue())){
                propertyEditor.edit((UmlModel)evt.getNewValue());
            }
            else if(UmlDiagram.class.isInstance(evt.getNewValue())){
                UmlDiagram ud = (UmlDiagram)evt.getNewValue();
                propertyEditor.edit(ud);
                
                DiagramExplorer diagramExplorer = openDiagramExplorers.get(ud);
                if(diagramExplorer==null){
                    diagramExplorer = new DiagramExplorer();                
                    diagramExplorer.setProject(project);
                    diagramExplorer.exploreDiagram(ud);
                    diagramExplorer.addPropertyChangeListener(this);
                    addPropertyChangeListener(diagramExplorer);
                    openDiagramExplorers.put(ud, diagramExplorer);
                    jTabbedPane1.addTab(ud.getName(), diagramExplorer);
                }
                jTabbedPane1.setSelectedComponent(diagramExplorer);
                
                UmlDiagramPanel dp = openDiagramPanels.get(ud);
                if(dp==null){
                    dp = new UmlDiagramPanel(ud);
                    dp.addPropertyChangeListener(this);
                    dp.addPropertyChangeListener(diagramExplorer);
                    addPropertyChangeListener(dp);
                    diagramExplorer.addPropertyChangeListener(dp);
                    openDiagramPanels.put(ud, dp);
                }
                
                ArrayList q =new ArrayList();
                q.add(this);
                this.firePropertyChange("Display", q, dp);

                explorer.reload();
                explorer.setSelection(evt.getNewValue());
                revalidate();
            }
            else if(UmlCoreElement.class.isInstance(evt.getNewValue())){
                propertyEditor.edit((UmlCoreElement)evt.getNewValue());
            }
        }
        else if(evt.getPropertyName().equals("Tab selection") && !((ArrayList)evt.getOldValue()).contains(this)){
            ((ArrayList)evt.getOldValue()).add(this);
            for(int i=0 ; i < jTabbedPane1.getTabCount() ; i++){
                if(jTabbedPane1.getTitleAt(i).equals(evt.getNewValue())){
                    jTabbedPane1.setSelectedIndex(i);
                }
            }
        }
    }
}
