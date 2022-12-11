/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package moe.umlgui.ui;

import java.awt.BorderLayout;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import moe.umlgui.model.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

/**
 *
 * @author Moe
 */
public class PropertyEditor extends javax.swing.JPanel {

    UmlCoreElement umlCoreElement;
    UmlDiagram umlDiagram;
    Project project;
    
    static int ELEMENT = 1;
    static int DIAGRAM = 2;
    static int PROJECT = 3;
    
    int nowEditing = -1;
    
    Project context;
    
    /**
     * Creates new form PropertyEditor
     * @param context if null, context is global library
     */
    public PropertyEditor() {
        initComponents();
    }

    public void setContext(Project context){
        this.context = context;
    }
    
    /**
     *
     * @param umlCoreElement
     * @param umlDiagram if null, context is project or global lib (outside any diagram there)
     */
    public void edit(UmlCoreElement umlCoreElement, UmlDiagram umlDiagram){
        this.umlCoreElement = umlCoreElement;
        this.umlDiagram = umlDiagram;
        nowEditing = ELEMENT;
        loadForm();
    }

    public void edit(UmlDiagram umlDiagram){
        this.umlDiagram = umlDiagram;
        nowEditing = DIAGRAM;
        loadForm();
    }

    public void edit(Project project){
        this.project = project;
        nowEditing = PROJECT;
        loadForm();
    }
    
    public void showToolbar(boolean b){
        remove(saveButton);
        if(b)   add(saveButton , BorderLayout.NORTH);
    }
    
    private Object[] getElementList(){
        ArrayList<Object> l = new ArrayList();
        //l.add(null);
        if(nowEditing==ELEMENT && UmlDiagram.class.isInstance(context)){
            l.addAll(umlDiagram.getElementList());
        }
        
        return (Object[])l.toArray();
    }

    
    private void loadElement(){
        setBorder(javax.swing.BorderFactory.createTitledBorder(umlCoreElement.getName()));
        editPanel.removeAll();
        
        java.awt.GridBagConstraints gridBagConstraints;
        int yCounter = 0;
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.gridy = yCounter;
        editPanel.add(nameLabel, gridBagConstraints);

        nameTextField.setText(umlCoreElement.getName());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridy = yCounter;    yCounter++;
        editPanel.add(nameTextField, gridBagConstraints);


        if(UseCase.class.isInstance(umlCoreElement)){
            try {
                gridBagConstraints = new java.awt.GridBagConstraints();
                gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
                gridBagConstraints.gridy = yCounter;
                editPanel.add(descriptionLabel, gridBagConstraints);

                Method m1 = UseCase.class.getDeclaredMethod("setDescription", String.class);
                Method m2 = UseCase.class.getDeclaredMethod("getDescription");
                Method m3 = UseCase.class.getDeclaredMethod("setDescriptionNumColumns", Integer.class);
                Method m4 = UseCase.class.getDeclaredMethod("getDescriptionNumColumns");
                descriptionMultilineTextComponent.setUmlCoreElement(umlCoreElement,m1,m2,m3,m4);

                gridBagConstraints = new java.awt.GridBagConstraints();
                gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
                gridBagConstraints.weightx = 1.0;
                gridBagConstraints.weighty = 1.0;
                //gridBagConstraints.gridheight = 5;
                gridBagConstraints.gridy = yCounter;    yCounter++;
                editPanel.add(descriptionMultilineTextComponent, gridBagConstraints);
            } catch (NoSuchMethodException ex) {
                Logger.getLogger(PropertyEditor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(PropertyEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        else if(ConditionalNode.class.isInstance(umlCoreElement)){
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints.gridy = yCounter;
            editPanel.add(new JLabel(), gridBagConstraints);
            
            LogicalTestPanel tPanel = 
                    new LogicalTestPanel((ConditionalNode)umlCoreElement , context);
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            //gridBagConstraints.gridheight = 5;
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(tPanel, gridBagConstraints);
            
        }
        //TODO Note Element

        //TODO Package

        else if(Association.class.isInstance(umlCoreElement)){
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridy = 1;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints.gridy = yCounter;
            editPanel.add(fromLabel, gridBagConstraints);

            fromComboBox.setModel(new DefaultComboBoxModel(getElementList()));
            if(((Association)umlCoreElement).getPartyA() != null){
                fromComboBox.setSelectedItem(((Association)umlCoreElement).getPartyA());
            }
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridy = 1;
            //gridBagConstraints.gridx = 1;
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(fromComboBox, gridBagConstraints);

            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridy = 2;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints.gridy = yCounter;
            editPanel.add(toLabel, gridBagConstraints);

            toComboBox.setModel(new DefaultComboBoxModel(getElementList()));
            if(((Association)umlCoreElement).getPartyB() != null){
                toComboBox.setSelectedItem(((Association)umlCoreElement).getPartyA());
            }
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridy = 2;
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(toComboBox, gridBagConstraints);
        }

        if(Include.class.isInstance(umlCoreElement)){
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridy = 1;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints.gridy = yCounter;
            editPanel.add(fromLabel, gridBagConstraints);

            fromComboBox.setModel(new DefaultComboBoxModel(getElementList()));
            if(((Include)umlCoreElement).getPartyA() != null){
                fromComboBox.setSelectedItem(((Include)umlCoreElement).getPartyA());
            }
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridy = 1;
            gridBagConstraints.gridx = 1;
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(fromComboBox, gridBagConstraints);

            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridy = 2;
            gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints.gridy = yCounter;
            editPanel.add(toLabel, gridBagConstraints);

            toComboBox.setModel(new DefaultComboBoxModel(getElementList()));
            if(((Include)umlCoreElement).getPartyB() != null){
                toComboBox.setSelectedItem(((Include)umlCoreElement).getPartyA());
            }
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridy = 2;
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(toComboBox, gridBagConstraints);
        }
        
        
        //TODO Note field
        //note on relationships
        //applies to:
        //does NOT apply to:association, include
        if( ! Association.class.isInstance(umlCoreElement)
            &&
            ! Include.class.isInstance(umlCoreElement)
        ){
                    
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints.gridy = yCounter;
            editPanel.add(noteLabel, gridBagConstraints);

            noteMultilineTextComponent.setUmlCoreElement(umlCoreElement);
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            //gridBagConstraints.gridheight = 5;
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(noteMultilineTextComponent, gridBagConstraints);
        }
            
        //filler
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridx = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridy = yCounter;
        editPanel.add(new javax.swing.JPanel(), gridBagConstraints);
        this.firePropertyChange("form loaded", null, null);
    }
    
    
    
    private void loadProject(){
        setBorder(javax.swing.BorderFactory.createTitledBorder(project.getName()));
        editPanel.removeAll();
        
        java.awt.GridBagConstraints gridBagConstraints;
        int yCounter = 0;
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.gridy = yCounter;
        editPanel.add(nameLabel, gridBagConstraints);

        nameTextField.setText(project.getName());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridy = yCounter;    yCounter++;
        editPanel.add(nameTextField, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.gridy = yCounter;
        editPanel.add(folderLabel, gridBagConstraints);

        if(project.getFolder() != null)
                    folderButton.setText(project.getFolder().getName());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        //gridBagConstraints.gridheight = 5;
        gridBagConstraints.gridy = yCounter;    yCounter++;
        editPanel.add(folderButton, gridBagConstraints);
        
        //filler
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridx = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridy = yCounter;
        editPanel.add(new javax.swing.JPanel(), gridBagConstraints);
        this.firePropertyChange("form loaded", null, null);
    }
    
    
    
    private void loadDiagram(){
        setBorder(javax.swing.BorderFactory.createTitledBorder(umlDiagram.getName()));
        editPanel.removeAll();
        
        java.awt.GridBagConstraints gridBagConstraints;
        int yCounter = 0;
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.gridy = yCounter;
        editPanel.add(nameLabel, gridBagConstraints);

        nameTextField.setText(umlDiagram.getName());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridy = yCounter;    yCounter++;
        editPanel.add(nameTextField, gridBagConstraints);
        
        
        //filler
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridx = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridy = yCounter;
        editPanel.add(new javax.swing.JPanel(), gridBagConstraints);
        this.firePropertyChange("form loaded", null, null);
    }
    
    
    private void loadForm(){
        if(nowEditing == ELEMENT)   loadElement();
        else if(nowEditing == DIAGRAM)   loadDiagram();
        else if(nowEditing == PROJECT)   loadProject();
    }
    
    
    private void saveElement(){        
        umlCoreElement.setName(nameTextField.getText());

        try {
            noteMultilineTextComponent.save();
        } catch (IllegalAccessException | IllegalArgumentException |InvocationTargetException ex) {
            Logger.getLogger(PropertyEditor.class.getName()).log(Level.SEVERE, null, ex);
        }

        //use case desc
        if(UseCase.class.isInstance(umlCoreElement)){
            try {
                descriptionMultilineTextComponent.save();
            } catch (IllegalAccessException | IllegalArgumentException |InvocationTargetException ex) {
                Logger.getLogger(PropertyEditor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if(UmlRelationship.class.isInstance(umlCoreElement)){
            ((UmlRelationship)umlCoreElement).setPartyA((UmlElement)fromComboBox.getSelectedItem());
            ((UmlRelationship)umlCoreElement).setPartyB((UmlElement)toComboBox.getSelectedItem());
        }

        this.firePropertyChange("Element updated", null, umlCoreElement);
    }
    
    
    private void saveDiagram(){
        umlDiagram.setName(nameTextField.getText());
        this.firePropertyChange("Diagram updated", null, umlCoreElement);
    }
    
    private void saveProject(){        
        project.setName(nameTextField.getText());
        this.firePropertyChange("Project updated", null, project);
    }
    
    public void save(){
        if(nowEditing == ELEMENT)   saveElement();       
        else if(nowEditing == DIAGRAM)   saveDiagram();
        else if(nowEditing == PROJECT)   saveProject();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        fromLabel = new javax.swing.JLabel();
        fromComboBox = new javax.swing.JComboBox<>();
        toLabel = new javax.swing.JLabel();
        toComboBox = new javax.swing.JComboBox<>();
        noteLabel = new javax.swing.JLabel();
        descriptionLabel = new javax.swing.JLabel();
        noteMultilineTextComponent = new moe.umlgui.ui.MultilineTextComponent();
        descriptionMultilineTextComponent = new moe.umlgui.ui.MultilineTextComponent();
        folderLabel = new javax.swing.JLabel();
        folderButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        editPanel = new javax.swing.JPanel();

        nameLabel.setText("Name");

        nameTextField.setText("jTextField1");

        fromLabel.setText("From");

        toLabel.setText("To");

        noteLabel.setText("Note");

        descriptionLabel.setText("Description");

        folderLabel.setText("Folder");

        folderButton.setText("Select folder");
        folderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                folderButtonActionPerformed(evt);
            }
        });

        setBorder(javax.swing.BorderFactory.createTitledBorder("Properties"));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLayout(new java.awt.BorderLayout());

        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/24x24/media-floppy.png"))); // NOI18N
        saveButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });
        add(saveButton, java.awt.BorderLayout.NORTH);

        editPanel.setLayout(new java.awt.GridBagLayout());
        add(editPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        save();
    }//GEN-LAST:event_saveButtonActionPerformed

    private void folderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_folderButtonActionPerformed
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int i = fc.showOpenDialog(this );
        if(i == JFileChooser.APPROVE_OPTION){
            project.setFolder(fc.getSelectedFile());
            folderButton.setText(project.getFolder().getName());
            folderButton.revalidate();
        }
    }//GEN-LAST:event_folderButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel descriptionLabel;
    private moe.umlgui.ui.MultilineTextComponent descriptionMultilineTextComponent;
    private javax.swing.JPanel editPanel;
    private javax.swing.JButton folderButton;
    private javax.swing.JLabel folderLabel;
    private javax.swing.JComboBox<String> fromComboBox;
    private javax.swing.JLabel fromLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JLabel noteLabel;
    private moe.umlgui.ui.MultilineTextComponent noteMultilineTextComponent;
    private javax.swing.JButton saveButton;
    private javax.swing.JComboBox<String> toComboBox;
    private javax.swing.JLabel toLabel;
    // End of variables declaration//GEN-END:variables
}
