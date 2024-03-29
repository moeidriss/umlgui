/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package moe.umlgui.ui;

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListDataListener;
import moe.umlgui.model.*;
import moe.umlgui.ui.tree.Explorer;
import moe.umlgui.ui.tree.ExplorerTreeCellRenderer;
import moe.umlgui.ui.tree.ExplorerTreeCellRenderer;
import moe.umlgui.ui.tree.ExplorerTreeModel;
import moe.umlgui.ui.tree.ExplorerTreeModel;

/**
 *
 * @author Moe
 */
public class DiagramExplorer extends javax.swing.JPanel implements PropertyChangeListener{

    UmlDiagram umlDiagram;
    
    /**
     * Creates new form DiagramExplorer
     */
    public DiagramExplorer() {
        initComponents();
    }

    Project project;
    
    public void setProject(Project project){
        this.project = project;
    }
    
    
    public void exploreDiagram(UmlDiagram umlDiagram){
        this.umlDiagram = umlDiagram;
        loadDiagram();
    }
    
    Explorer explorer;
    
    private void loadDiagram(){    
        explorer = new Explorer(umlDiagram);
        explorer.addPropertyChangeListener(this);
        add(explorer , BorderLayout.CENTER);
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

        setLayout(new java.awt.BorderLayout());
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    
    public boolean equals(Object o){
        return (hashCode()==o.hashCode());
    }
    
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(!evt.getPropertyName().equals("Element updated") &&
            !evt.getPropertyName().equals("Element inserted")  &&
            !evt.getPropertyName().equals("Element removed")  &&
            !evt.getPropertyName().equals("Diagram updated") &&
            !evt.getPropertyName().equals("Diagram attached")  &&
            !evt.getPropertyName().equals("Explorer selection") 
        ){
            return;
        }
        
        
        //PropertyEditor events
        if(evt.getPropertyName().equals("Element updated") && !((ArrayList)evt.getOldValue()).contains(this)){
            ((ArrayList)evt.getOldValue()).add(this);
            explorer.reload();
            explorer.setSelection(evt.getNewValue());
            firePropertyChange("Element updated", evt.getOldValue(), evt.getNewValue());            
        }
        
        //events
        else if(evt.getPropertyName().equals("Element inserted") && !((ArrayList)evt.getOldValue()).contains(this)){
            ((ArrayList)evt.getOldValue()).add(this);
            
            explorer.reload();
            explorer.setSelection(evt.getNewValue());
            firePropertyChange("Element inserted", evt.getOldValue(), evt.getNewValue());
        }
        else if(evt.getPropertyName().equals("Element removed")){
            ((ArrayList)evt.getOldValue()).add(this);
            
            explorer.reload();
            firePropertyChange("Element removed", evt.getOldValue(), evt.getNewValue());
        }
        
        else if(evt.getPropertyName().equals("Explorer selection") && !((ArrayList)evt.getOldValue()).contains(this)){
            ((ArrayList)evt.getOldValue()).add(this);
            explorer.reload();
            explorer.setSelection(evt.getNewValue());
            firePropertyChange("Explorer selection", evt.getOldValue(), evt.getNewValue());
        }
        else if(evt.getPropertyName().equals("Diagram attached") && !((ArrayList)evt.getOldValue()).contains(this)){
            ((ArrayList)evt.getOldValue()).add(this);
            explorer.reload();
            explorer.setSelection(evt.getNewValue());
            firePropertyChange("Diagram attached", evt.getOldValue(), evt.getNewValue());
        }
        if(evt.getPropertyName().equals("Diagram updated") && !((ArrayList)evt.getOldValue()).contains(this)){
            ((ArrayList)evt.getOldValue()).add(this);
            explorer.reload();
            explorer.setSelection(evt.getNewValue());
            firePropertyChange("Diagram updated", evt.getOldValue(), evt.getNewValue());            
        }
    }
}
