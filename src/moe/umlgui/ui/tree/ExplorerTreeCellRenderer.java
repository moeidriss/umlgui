/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.ui.tree;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import moe.umlgui.model.*;

/**
 *
 * @author Moe
 */
public class ExplorerTreeCellRenderer extends DefaultTreeCellRenderer {
    
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus){
        JLabel l = (JLabel)super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        
        Object uObj = ((DefaultMutableTreeNode)value).getUserObject();
        
        if(Project.class.isInstance(uObj)) 
            l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/16x16/ProjectOpen.png")));
        else if(UmlModel.class.isInstance(uObj)) 
            l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/16x16/view-presentation.png")));
        else if(UmlDiagram.class.isInstance(uObj)) 
            l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/16x16/Diagram.png")));
        else if(UmlElement.class.isInstance(uObj)) 
            l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/16x16/Element.png")));

        return l;
    }
    
    
}
