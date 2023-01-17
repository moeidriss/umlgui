/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.ui.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

    
    //static int ELEMENT = 1;
    static int DIAGRAM = 2;
    static int PROJECT = 3;
    //static int MODEL = 4;
    
    int nowExploring = -1;
    
    Project project;
    //UmlModel model;
    UmlDiagram diagram;
    //UmlElement element;
    
    public ExplorerTreeModel(Project project){
        super(new  DefaultMutableTreeNode(project));
        this.project = project;
        nowExploring = PROJECT;
    }
    
    
    public ExplorerTreeModel(UmlDiagram diagram){
        super(new  DefaultMutableTreeNode(diagram));
        this.diagram = diagram;
        nowExploring = DIAGRAM;
    }
    
    /*
    public ExplorerTreeModel(UmlModel model){
        super(new  DefaultMutableTreeNode(model));
        this.model = model;
    }
    
    public ExplorerTreeModel(UmlElement element){
        super(new  DefaultMutableTreeNode(element));
        this.element = element;
    }
    */
    

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
            //BO
            if(BusinessObjectOwner.class.isInstance(parentUserObject) ||
               AttachmentOwner.class.isInstance(parentUserObject))
                //&& index==index of BO array in elementSubLists
                //TODO which need to be emptied at some point
            {
                for(Iterator<ArrayList> i = elementSubLists.keySet().iterator() ; i.hasNext() ; ){
                    ArrayList l = i.next();
                    if(elementSubLists.get(l)==index){
                        return new DefaultMutableTreeNode(l);
                    }
                }
                //ArrayList child = ((BusinessObjectOwner)parentUserObject).getBusinessObjects();
                //if(index==new ArrayList(elementSubLists.keySet()).indexOf(child))
                //return new DefaultMutableTreeNode(((BusinessObjectOwner)parentUserObject).getBusinessObjects());
            }
            //att
            /*if(AttachmentOwner.class.isInstance(parentUserObject))
                //&& index==index of AttOnrs array in elementSubLists
                //TODO which need to be emptied at some point
            {
                //ArrayList child = ((AttachmentOwner)parentUserObject).getAttachedDiagrams();
                //if(index==new ArrayList(elementSubLists.keySet()).indexOf(child))
                return new DefaultMutableTreeNode(((AttachmentOwner)parentUserObject).getAttachedDiagrams());
            }*/
        }
        
        //subElement collctions
        else if(ArrayList.class.isInstance(parentUserObject)){
            for(Iterator<ArrayList> i = elementSubLists.keySet().iterator() ; i.hasNext();){
                ArrayList l = i.next();
                if(l.toString().equals(parentUserObject.toString()))
                        return new DefaultMutableTreeNode(l.get(index));
            }
        }
        
        //subElement
        else if(BusinessObject.class.isInstance(parentUserObject)){
            
        }
        
        return null;
    }

    HashMap<ArrayList,Integer> elementSubLists =  new HashMap();
    
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
            if(nowExploring==DIAGRAM){
                return ((UmlDiagram)parentUserObject).getElementList().size();
            }
            else if(nowExploring==PROJECT){
                /*ArrayList.class.isInstance(getParent(parent)) &&
                    getParent(parent).toString().equals("Attached Diagrams"))*/
                // TODO LINKED DIAGRAMS
                
                return ((UmlDiagram)parentUserObject).getAttachmentOwnerList().size();
                //
            }
        }
        else if(UmlElement.class.isInstance(parentUserObject)){
            int counter = 0;
            
            //BO
            if(BusinessObjectOwner.class.isInstance(parentUserObject)){
                elementSubLists.put(((BusinessObjectOwner)parentUserObject).getBusinessObjects() , counter);
                counter++;
            }
            //attached diagrams
            if(AttachmentOwner.class.isInstance(parentUserObject)){
                elementSubLists.put(((AttachmentOwner)parentUserObject).getAttachedDiagrams(), counter);
                counter++;
            }
            
            return counter;
        }
        
        //subElement collctions
        else if(ArrayList.class.isInstance(parentUserObject)){// && 
            for(Iterator<ArrayList> i = elementSubLists.keySet().iterator() ; i.hasNext();){
                ArrayList l = i.next();
                if(l.toString().equals(parentUserObject.toString()))
                        return l.size();
            }
            
        }
        
        return -1;
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
            
            //BO
            if(BusinessObjectOwner.class.isInstance(pUserObject)){
                return elementSubLists.get((ArrayList)cUserObject);
            }
            if(AttachmentOwner.class.isInstance(pUserObject)){
                return elementSubLists.get((ArrayList)cUserObject);
            }
        }
        
        //subElement, ie collctions
        else if(ArrayList.class.isInstance(pUserObject)){// && 
            for(Iterator<ArrayList> i = elementSubLists.keySet().iterator() ; i.hasNext();){
                ArrayList l = i.next();
                if(l.toString().equals(pUserObject.toString()))
                        return l.indexOf(cUserObject);
            }            
        }
        
        return -1;
    }

    
    @Override
    public boolean isLeaf(Object node) {
        Object userObject = ((DefaultMutableTreeNode)node).getUserObject();
        
        
        if(UmlDiagram.class.isInstance(userObject)){
            //TODO exclude AttachmentOwner within
            if(nowExploring==PROJECT)   return true;
        }
        
        //TODO fix nullpointerexcp thrown when elmnt node
        //is expanded whule both arrays empty
        else if(UmlElement.class.isInstance(userObject)){
            if(!BusinessObjectOwner.class.isInstance(userObject) &&
                !AttachmentOwner.class.isInstance(userObject))
                return true;
            
            return false;
        }
        
        //subElement
        else if(BusinessObject.class.isInstance(userObject))    return true;
        //else if(AttachmentDiagram.class.isInstance(userObject))    return true;
        
        return false;
    }
    
    
    private DefaultMutableTreeNode getParent(Object child){
        return null;
    }
    
}
