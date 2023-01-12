/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package moe.umlgui.ui.tree;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import moe.umlgui.model.Actor;
import moe.umlgui.model.Association;
import moe.umlgui.model.Include;
import moe.umlgui.model.ModelException;
import moe.umlgui.model.Project;
import moe.umlgui.model.UmlCoreElement;
import moe.umlgui.model.UmlDiagram;
import moe.umlgui.model.UmlElement;
import moe.umlgui.model.UmlModel;
import moe.umlgui.model.UseCase;

/**
 *
 * @author Moe
 */
public class Explorer extends javax.swing.JPanel implements PropertyChangeListener {

    
    
    static int ELEMENT = 1;
    static int DIAGRAM = 2;
    static int PROJECT = 3;
    static int MODEL = 4;
    
    int nowExploring = -1;
    
    Project project;
    UmlModel model;
    UmlDiagram diagram;
    UmlElement element;
    
    /**
     * Creates new form DiagramExplorer
     */
    public Explorer(Project project) {
        initComponents();
        this.project = project;
        nowExploring = PROJECT;
        loadDiagram();
        setSelection(project);
        
        ArrayList q =new ArrayList();
        q.add(this);
        this.firePropertyChange("Explorer selection", q, project);
    }

    public Explorer(UmlModel model) {
        initComponents();
        this.model = model;
        nowExploring = MODEL;
        loadDiagram();
        setSelection(model);
        
        ArrayList q =new ArrayList();
        q.add(this);
        this.firePropertyChange("Explorer selection", q, model);
    }

    public Explorer(UmlDiagram diagram) {
        initComponents();
        this.diagram = diagram;
        nowExploring = DIAGRAM;
        loadDiagram();
        setSelection(diagram);
        
        ArrayList q =new ArrayList();
        q.add(this);
        this.firePropertyChange("Explorer selection", q, diagram);
    }

    public Explorer(UmlElement element) {
        initComponents();
        this.element = element;
        nowExploring = ELEMENT;
        loadDiagram();
        setSelection(element);
        
        ArrayList q =new ArrayList();
        q.add(this);
        this.firePropertyChange("Explorer selection", q, element);
    }
    
    ExplorerTreeModel treeModel = null;
    
    private void loadDiagram(){    
        if(project!=null){
            setBorder(new TitledBorder(project.getName()));  
            treeModel = new ExplorerTreeModel(project);
        }
        else if(model!=null){
            setBorder(new TitledBorder(model.getName()));        
            treeModel = new ExplorerTreeModel(model);
        }
        else if(diagram!=null){
            setBorder(new TitledBorder(diagram.getName()));        
            treeModel = new ExplorerTreeModel(diagram);
        }
        else if(element!=null){
            setBorder(new TitledBorder(element.getName()));        
            treeModel = new ExplorerTreeModel(element);
        }
        
        jTree.setModel(treeModel);
        jTree.setCellRenderer(new ExplorerTreeCellRenderer()); 
        
        
    }
    
    
    public void reload(){
        ((ExplorerTreeModel)jTree.getModel()).reload();
        //if (selection==null)...
    }

    Object selection;
    
    public void setSelection(Object selection){
        this.selection = selection;
        
        if(nowExploring==PROJECT){
            if(selection==project)
                jTree.setSelectionPath(new TreePath(treeModel.getPathToRoot((DefaultMutableTreeNode)treeModel.getRoot())));
            else if(UmlModel.class.isInstance(selection)){
                jTree.setSelectionPath(new TreePath(treeModel.getPathToRoot((DefaultMutableTreeNode)treeModel.getChild(treeModel.getRoot(), project.getModels().indexOf(selection)))));
            }
            else if(UmlDiagram.class.isInstance(selection)){
                DefaultMutableTreeNode modelNode = (DefaultMutableTreeNode)treeModel.getChild(treeModel.getRoot(), project.getModels().indexOf(((UmlDiagram)selection).getUmlModel()));
                jTree.setSelectionPath(new TreePath(treeModel.getPathToRoot((DefaultMutableTreeNode)treeModel.getChild(modelNode, ((UmlDiagram)selection).getUmlModel().getDiagrams().indexOf(selection)))));
            }
            else if(UmlElement.class.isInstance(selection)){
                DefaultMutableTreeNode modelNode = (DefaultMutableTreeNode)treeModel.getChild(treeModel.getRoot(), project.getModels().indexOf(((UmlElement)selection).getUmlDiagram().getUmlModel()));
                DefaultMutableTreeNode diagramNode = (DefaultMutableTreeNode)treeModel.getChild(modelNode, ((UmlElement)selection).getUmlDiagram().getUmlModel().getDiagrams().indexOf(selection));
                jTree.setSelectionPath(new TreePath(treeModel.getPathToRoot((DefaultMutableTreeNode)treeModel.getChild(diagramNode, ((UmlElement)selection).getUmlDiagram().getElementList().indexOf(selection)))));
            }
            else{ }
        }
        
        if(nowExploring==DIAGRAM){
            if(selection==diagram)
                jTree.setSelectionPath(new TreePath(treeModel.getPathToRoot((DefaultMutableTreeNode)treeModel.getRoot())));
            else if(UmlElement.class.isInstance(selection)){
                jTree.setSelectionPath(new TreePath(treeModel.getPathToRoot((DefaultMutableTreeNode)treeModel.getChild(treeModel.getRoot(), diagram.getElementList().indexOf(selection)))));
            }
            else{ }
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
        jTree = new javax.swing.JTree();
        jToolBar1 = new javax.swing.JToolBar();
        addButton = new javax.swing.JButton();
        removeButton = new javax.swing.JButton();
        upButton = new javax.swing.JButton();
        downButton = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jTree.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTreeMouseClicked(evt);
            }
        });
        jTree.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                jTreeValueChanged(evt);
            }
        });
        jTree.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTreeKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTree);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jToolBar1.setRollover(true);

        addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/16x16/Add.png"))); // NOI18N
        addButton.setEnabled(false);
        addButton.setFocusable(false);
        addButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(addButton);

        removeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/16x16/Remove.png"))); // NOI18N
        removeButton.setEnabled(false);
        removeButton.setFocusable(false);
        removeButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        removeButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        removeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(removeButton);

        upButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/16x16/go-up.png"))); // NOI18N
        upButton.setEnabled(false);
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
        downButton.setEnabled(false);
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
        Object selection = ((DefaultMutableTreeNode)jTree.getLastSelectedPathComponent()).getUserObject();
        
        ArrayList options = new ArrayList();
        //module, diagram
        
        //ucd
        options.add("Actor");options.add("Use Case");
        
        //context sensitive options
        if(selection != null && UmlElement.class.isInstance(selection)){
            if(UseCase.class.isInstance(selection) || Actor.class.isInstance(selection)){
                options.add("Association");
            }
            if(UseCase.class.isInstance(selection)){
                options.add("Include");
            }
        }
        String elType = (String)JOptionPane.showInputDialog(this, "Select element", "Insert", JOptionPane.INFORMATION_MESSAGE, null, options.toArray(), null);
        
        //if nothing or diagram is select (or multiple items), append element to end
        
        //if element is selected, prompt to insert before or after
        int newIndex = -1;
        if(UmlElement.class.isInstance(selection) &&
            !elType.equals("Association")  &&
            !elType.equals("Include") 
        ){
            String[] s = {"Before" , "After"};
            String ss = ((String)JOptionPane.showInputDialog(this, "Before or after", "Insert", JOptionPane.INFORMATION_MESSAGE, null, s, "After"));
            if(ss .equals("Before"))
                newIndex = ((UmlElement)selection).getUmlDiagram().getElementList().indexOf(selection);
            else
                newIndex = ((UmlElement)selection).getUmlDiagram().getElementList().indexOf(selection)+1;
        }
        
        UmlCoreElement newElement = null;
        if(elType.equals("Actor")){
            newElement = new Actor();
        }
        else if(elType.equals("Use Case")){
            newElement = new UseCase();
        }
        else if(elType.equals("Association")){
            newElement = new Association();
            ((Association)newElement).setPartyA((UmlElement)selection);
        }
        else if(elType.equals("Include")){
            newElement = new Include();
            ((Include)newElement).setPartyA((UmlElement)selection);
        }
        
        if(diagram!=null){
            try{
                if(newIndex !=-1 )
                    diagram.insertCoreElement(newIndex,newElement);            
                else 
                    diagram.addCoreElement(newElement);            
            }catch(ModelException e){
                JOptionPane.showMessageDialog(this, e);
                e.printStackTrace();
            }
        }
        
        ArrayList q =new ArrayList();
        q.add(this);
        firePropertyChange("Element inserted", q, newElement);
        //setSelection(newElement);
    }//GEN-LAST:event_addButtonActionPerformed

    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_removeButtonActionPerformed

    private void upButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upButtonActionPerformed
        try {
            diagram.moveUp((UmlElement)((DefaultMutableTreeNode)jTree.getLastSelectedPathComponent()).getUserObject());
            ArrayList q =new ArrayList();
            q.add(this);
            firePropertyChange("Element updated", q, (UmlElement)((DefaultMutableTreeNode)jTree.getLastSelectedPathComponent()).getUserObject());
        } catch (ModelException ex) {
            JOptionPane.showMessageDialog(this, ex);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_upButtonActionPerformed

    private void downButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downButtonActionPerformed
        try {
            diagram.moveDown((UmlElement)((DefaultMutableTreeNode)jTree.getLastSelectedPathComponent()).getUserObject());
            ArrayList q =new ArrayList();
            q.add(this);
            firePropertyChange("Element updated", q, (UmlElement)((DefaultMutableTreeNode)jTree.getLastSelectedPathComponent()).getUserObject());
        } catch (ModelException ex) {
            JOptionPane.showMessageDialog(this, ex);
            ex.printStackTrace();
        }
    }//GEN-LAST:event_downButtonActionPerformed

    private void jTreeValueChanged(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_jTreeValueChanged
        if(jTree.getLastSelectedPathComponent()==null)  return;
        Object selection = ((DefaultMutableTreeNode)jTree.getLastSelectedPathComponent()).getUserObject();
        
        if(Project.class.isInstance(selection)){
            upButton.setEnabled(false);
            downButton.setEnabled(false);
            addButton.setEnabled(true);//TODO model
            removeButton.setEnabled(true);
        }
        else if(UmlModel.class.isInstance(selection)){
            upButton.setEnabled(false);
            downButton.setEnabled(false);
            addButton.setEnabled(true);//TODO diagram
            removeButton.setEnabled(true);
        }
        else if(UmlDiagram.class.isInstance(selection)){
            upButton.setEnabled(false);
            downButton.setEnabled(false);
            addButton.setEnabled(true);
            removeButton.setEnabled(true);
        }        
        else if(UmlElement.class.isInstance(selection)){
            upButton.setEnabled(true);
            downButton.setEnabled(true);
            addButton.setEnabled(true);
            removeButton.setEnabled(true);
        }
        else{
            //upButton.setEnabled(true);
        }
        
        //if(jTree.getLeadSelectionRow()==treeModel.)  upButton.setEnabled(false);
        //else upButton.setEnabled(true);
        
        
//        Object selection = ((DefaultMutableTreeNode)jTree.getLastSelectedPathComponent()).getUserObject();
        
    }//GEN-LAST:event_jTreeValueChanged

    private void jTreeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTreeMouseClicked
        if(evt.getClickCount()==2){
            ArrayList q =new ArrayList();
            q.add(this);
            this.firePropertyChange("Explorer selection", q, ((DefaultMutableTreeNode)jTree.getLastSelectedPathComponent()).getUserObject());
        }
    }//GEN-LAST:event_jTreeMouseClicked

    private void jTreeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTreeKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode()== java.awt.event.KeyEvent.VK_ENTER){
            ArrayList q =new ArrayList();
            q.add(this);
            this.firePropertyChange("Explorer selection", q, ((DefaultMutableTreeNode)jTree.getLastSelectedPathComponent()).getUserObject());
        }
    }//GEN-LAST:event_jTreeKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton downButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTree jTree;
    private javax.swing.JButton removeButton;
    private javax.swing.JButton upButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        //TODO this or reload()?
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public boolean equals(Object o){
        return (hashCode()==o.hashCode());
    }
}
