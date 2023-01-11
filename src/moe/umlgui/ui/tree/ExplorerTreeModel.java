/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.ui.tree;

import javax.swing.event.TreeModelListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import moe.umlgui.model.*;

/**
 * UmlElement tree. Context sensitive.
 * Root can be project, model, diagram OR element;
 * 
 * @author Moe
 */
public class ExplorerTreeModel extends DefaultTreeModel {

    
    Project project;
    UmlModel model;
    UmlDiagram diagram;
    UmlElement element;
    
    public ExplorerTreeModel(Project project){
        super(new  DefaultMutableTreeNode(project));
        this.project = project;
    }
    
    public ExplorerTreeModel(UmlModel model){
        super(new  DefaultMutableTreeNode(model));
        this.model = model;
    }
    
    public ExplorerTreeModel(UmlDiagram diagram){
        super(new  DefaultMutableTreeNode(diagram));
        this.diagram = diagram;
    }
    
    public ExplorerTreeModel(UmlElement element){
        super(new  DefaultMutableTreeNode(element));
        this.element = element;
    }
    

    @Override
    public Object getChild(Object parent, int index) {
        Object parentUserObject = ((DefaultMutableTreeNode)parent).getUserObject();
        
        if(Project.class.isInstance(parentUserObject)){
            return new DefaultMutableTreeNode(((Project)parentUserObject).getModels().get(index));
        }
        else if(UmlModel.class.isInstance(parentUserObject)){
            return new DefaultMutableTreeNode(((UmlModel)parentUserObject).getDiagrams().get(index));
        }
        else if(UmlDiagram.class.isInstance(parentUserObject)){
            return new DefaultMutableTreeNode(((UmlDiagram)parentUserObject).getElementList().get(index));
        }
        else if(UmlElement.class.isInstance(parentUserObject)){
            //elements
        }
        
        return null;
    }

    @Override
    public int getChildCount(Object parent) {
        Object parentUserObject = ((DefaultMutableTreeNode)parent).getUserObject();
        
        if(Project.class.isInstance(parentUserObject)){
            return ((Project)parentUserObject).getModels().size();
        }
        else if(UmlModel.class.isInstance(parentUserObject)){
            return ((UmlModel)parentUserObject).getDiagrams().size();
        }
        else if(UmlDiagram.class.isInstance(parentUserObject)){
            return ((UmlDiagram)parentUserObject).getElementList().size();
        }
        else if(UmlElement.class.isInstance(parentUserObject)){
            //elements
            return 0;
        }
        
        return -1;
    }

    @Override
    public boolean isLeaf(Object node) {
        Object userObject = ((DefaultMutableTreeNode)node).getUserObject();
        
        if(UmlElement.class.isInstance(userObject)){
            //
            //return true;
        }
        
        return false;
    }

    @Override
    public int getIndexOfChild(Object parent, Object child) {
        Object pUserObject = ((DefaultMutableTreeNode)parent).getUserObject();
        Object cUserObject = ((DefaultMutableTreeNode)child).getUserObject();
        
        if(Project.class.isInstance(pUserObject)){
            return ((Project)pUserObject).getModels().indexOf(cUserObject);
        }
        else if(UmlModel.class.isInstance(pUserObject)){
            return ((UmlModel)pUserObject).getDiagrams().indexOf(cUserObject);
        }
        else if(UmlDiagram.class.isInstance(pUserObject)){
            return ((UmlDiagram)pUserObject).getElementList().indexOf(cUserObject);
        }
        else if(UmlElement.class.isInstance(pUserObject)){
            //relationships
            
            //control nodes
            
            return 0;
        }
        
        return -1;
    }

    
}
