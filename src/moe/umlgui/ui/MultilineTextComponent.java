/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package moe.umlgui.ui;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.swing.JOptionPane;
import moe.umlgui.model.*;

/**
 *
 * @author Moe
 */
public class MultilineTextComponent extends javax.swing.JPanel {

    //TODO multi lines (!)
    public static int ENTITY = 1;
    public static int PROPERTY = 2;
    
    int scope = ENTITY;
    
    
    /**
     * Creates new form MultilineTextComponent
     */
    public MultilineTextComponent() {
        initComponents();
    }
    
    
    UmlCoreElement umlCoreElement;
    
    /**
     * Creates new form MultilineTextComponent
     */
    public MultilineTextComponent(UmlCoreElement umlCoreElement) {
        this.umlCoreElement = umlCoreElement;
        initComponents();
        
        try{
            loadComponent();
        }catch( IllegalAccessException | IllegalArgumentException | InvocationTargetException ex){
            JOptionPane.showMessageDialog(this, "Unable to load", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    Method setter; Method getter; Method numColSetter; Method numColGetter;
    
    /**
     * Creates new form MultilineTextComponent
     */
    public MultilineTextComponent(UmlCoreElement umlCoreElement, Method setter, Method getter, Method numColSetter, Method numColGetter) {
        this.umlCoreElement = umlCoreElement;
        this.setter = setter;
        this.getter = getter;
        this.numColSetter = numColSetter;
        this.numColGetter = numColGetter;
        scope = PROPERTY;
        initComponents();
        
        try{
            loadComponent();
        }catch( IllegalAccessException | IllegalArgumentException | InvocationTargetException ex){
            JOptionPane.showMessageDialog(this, "Unable to load", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    
    
    public void setUmlCoreElement (UmlCoreElement umlCoreElement) {
        this.umlCoreElement = umlCoreElement;
        
        try{
            loadComponent();
        }catch( IllegalAccessException | IllegalArgumentException | InvocationTargetException ex){
            JOptionPane.showMessageDialog(this, "Unable to load", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    
    
    public void setUmlCoreElement (UmlCoreElement umlCoreElement, Method setter, Method getter, Method numColSetter, Method numColGetter) {
        this.umlCoreElement = umlCoreElement;
        this.setter = setter;
        this.getter = getter;
        this.numColSetter = numColSetter;
        this.numColGetter = numColGetter;
        scope = PROPERTY;
        
        try{
            loadComponent();
        }catch( IllegalAccessException | IllegalArgumentException | InvocationTargetException ex){
            JOptionPane.showMessageDialog(this, "Unable to load", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }
    
    
    private void loadComponent() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        if(scope == ENTITY){
            textArea.setText(umlCoreElement.getNote());
            numColumnsSlider.setValue(umlCoreElement.getNoteNumColumns());
            positionComboBox.setSelectedIndex(umlCoreElement.getPosition());
            
            positionComboBox.setEnabled(true);
        }
        else if (scope == PROPERTY){
            textArea.setText((String)getter.invoke(umlCoreElement, null));
            numColumnsSlider.setValue((int)numColGetter.invoke(umlCoreElement, null));
            
            positionComboBox.setEnabled(false);
        }
    }
    
    public void save() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
        if(scope == ENTITY){
            umlCoreElement.setNote(textArea.getText());
            umlCoreElement.setNoteNumColumns(numColumnsSlider.getValue());
            umlCoreElement.setPosition(positionComboBox.getSelectedIndex());
        }
        else if (scope == PROPERTY){
            setter.invoke(umlCoreElement, textArea.getText());
            numColSetter.invoke(umlCoreElement, numColumnsSlider.getValue());
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
        textArea = new javax.swing.JTextArea();
        jToolBar1 = new javax.swing.JToolBar();
        numColumnsSlider = new javax.swing.JSlider();
        positionComboBox = new javax.swing.JComboBox<>();

        setLayout(new java.awt.BorderLayout());

        textArea.setColumns(20);
        textArea.setRows(5);
        jScrollPane1.setViewportView(textArea);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jToolBar1.setRollover(true);

        numColumnsSlider.setMajorTickSpacing(5);
        numColumnsSlider.setMaximum(20);
        numColumnsSlider.setMinimum(1);
        numColumnsSlider.setMinorTickSpacing(1);
        numColumnsSlider.setPaintLabels(true);
        numColumnsSlider.setPaintTicks(true);
        numColumnsSlider.setToolTipText("");
        numColumnsSlider.setValue(1);
        numColumnsSlider.setBorder(javax.swing.BorderFactory.createTitledBorder("Number of columns"));
        jToolBar1.add(numColumnsSlider);

        positionComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Left", "Right", "Top", "Bottom" }));
        positionComboBox.setBorder(javax.swing.BorderFactory.createTitledBorder("Position"));
        jToolBar1.add(positionComboBox);

        add(jToolBar1, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JSlider numColumnsSlider;
    private javax.swing.JComboBox<String> positionComboBox;
    private javax.swing.JTextArea textArea;
    // End of variables declaration//GEN-END:variables
}