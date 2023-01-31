/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package moe.umlgui.ui;

import moe.umlgui.model.*;

/**
 *
 * @author Moe
 */
public class Palette extends javax.swing.JPanel {

    Project project;
    
    public void setProject(Project project){
        this.project = project;
    }
    /**
     * Creates new form Palette
     */
    public Palette() {
        initComponents();
        add(new UmlElementLabel(new Actor() ));
        add(new UmlElementLabel(new UseCase() ));
        add(new UmlElementLabel(new Association() ));
        add(new UmlElementLabel(new Include() ));
        add(new UmlElementLabel(new Action() ));
        add(new UmlElementLabel(new moe.umlgui.model.CallActivity() ));
        add(new UmlElementLabel(new AcceptEvent() ));
        add(new UmlElementLabel(new AcceptTimeEvent() ));
        add(new UmlElementLabel(new SendSignal() ));
        add(new UmlElementLabel(new ActivityInitialNode() ));
        add(new UmlElementLabel(new ActivityFinalNode() ));
        add(new UmlElementLabel(new FlowFinalNode() ));
        add(new UmlElementLabel(new ConditionalBlock() ));
        add(new UmlElementLabel(new WhileLoop() ));
        add(new UmlElementLabel(new RepeatLoop() ));
        
        add(new UmlElementLabel(new Split() ));        
        add(new UmlElementLabel(new Fork() ));        
        
        add(new UmlElementLabel(new moe.umlgui.model.System() ));
        add(new UmlElementLabel(new BusinessSystem() ));
        add(new UmlElementLabel(new ItSystem() ));        
        add(new UmlElementLabel(new User() ));
        
        add(new UmlElementLabel(new Message() ));
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.GridLayout(0, 4));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
