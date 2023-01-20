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
        
        
        else if(AcceptEvent.class.isInstance(uObj)) 
            l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/16x16/AcceptEvent.png")));
        else if(AcceptTimeEvent.class.isInstance(uObj)) 
            l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/16x16/AcceptTimeEvent.png")));
        else if(Action.class.isInstance(uObj)) 
            l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/16x16/Action.png")));
        else if(ActivityFinalNode.class.isInstance(uObj)) 
            l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/16x16/ActivityFinalNode.png")));
        else if(ActivityInitialNode.class.isInstance(uObj)) 
            l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/16x16/ActivityInitialNode.png")));
        else if(Association.class.isInstance(uObj)) 
            l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/16x16/Association.png")));
        else if(BusinessObject.class.isInstance(uObj)) 
            l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/16x16/BusinessObject.png")));
        else if(CallActivity.class.isInstance(uObj)) 
            l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/16x16/CallActivity.png")));
        else if(ConditionalBlock.class.isInstance(uObj)) 
            l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/16x16/ConditionalBlock.png")));
        else if(FlowFinalNode.class.isInstance(uObj)) 
            l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/16x16/FlowFinalNode.png")));
        else if(Actor.class.isInstance(uObj)) 
            l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/16x16/Actor.png")));
        else if(Message.class.isInstance(uObj)) 
            l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/16x16/Message.png")));
        else if(Note.class.isInstance(uObj)) 
            l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/16x16/Note.png")));
        else if(RepeatLoop.class.isInstance(uObj)) 
            l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/16x16/RepeatLoop.png")));
        else if(SendSignal.class.isInstance(uObj)) 
            l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/16x16/SendSignal.png")));
        else if(UseCase.class.isInstance(uObj)) 
            l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/16x16/UseCase.png")));
        else if(moe.umlgui.model.System.class.isInstance(uObj)) 
            l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/16x16/System.png")));
        //else if(WorkerClass.class.isInstance(uObj)) 
            //l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/16x16/WorkerClass.png")));
        else if(WhileLoop.class.isInstance(uObj)) 
            l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/16x16/WhileLoop.png")));
        
        else if(UmlElement.class.isInstance(uObj)) 
            l.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/16x16/Element.png")));
        
        
        return l;
    }
    
    
}
