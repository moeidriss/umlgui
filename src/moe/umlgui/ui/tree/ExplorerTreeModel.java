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
        buildTree();
    }
    
    
    public ExplorerTreeModel(UmlDiagram diagram){
        super(new  DefaultMutableTreeNode(diagram));
        this.diagram = diagram;
        nowExploring = DIAGRAM;
        buildTree();
    }
    
    
    
    public void buildTree(){
        ((DefaultMutableTreeNode)root).removeAllChildren();
        
        if(nowExploring==PROJECT){
            for(Iterator<UmlModel> i = project.getModels().iterator() ; i.hasNext() ;){
                UmlModel m = i.next();
                DefaultMutableTreeNode mN = new DefaultMutableTreeNode(m);
                ((DefaultMutableTreeNode)root).add(mN);
                for(Iterator<UmlDiagram> j = m.getDiagrams().iterator() ; j.hasNext() ;){
                    UmlDiagram d = j.next();
                    DefaultMutableTreeNode dN = new DefaultMutableTreeNode(d);
                    mN.add(dN);
                    buildDiagramNode(dN);
                }
            }
            for(Iterator<CoreObject> i= project.getCoreObjects().iterator() ; i.hasNext() ;){
                ((DefaultMutableTreeNode)root).add(new DefaultMutableTreeNode(i.next()));
            }
        }
        else if(nowExploring==DIAGRAM){
            buildDiagramNode((DefaultMutableTreeNode)root);
        }
    }
    
    /*
    PROJECT
        Project
            Model
                Diagram
                    AttOwner
                TODO CoreObject List
                    CoreObject
    DIAGRAM
        Element
            Diagram
            BO List
                BO
            CN List
                CN
    
        ConditionalBlock:
            LogicalTest / Action
        WhileLoop
            
    */
    
    
    /*
    UmlDiagram
    case1: nowExploring==PROJECT; parent:Model, ch: non-empty AttachmentOwner (UmlEntity)
    case2: nowExploring==PROJECT; parent:AttachmentOwner, ch: NA
    case3: nowExploring==DIAGRAM; parent:NA, ch: UmlEntity
    case4: nowExploring==DIAGRAM; parent:ArrayList[AttachmentOwner (UmlEntity)], ch: AttachmentOwner (UmlEntity)
    
    */
    private void buildDiagramNode(DefaultMutableTreeNode dN){
        UmlDiagram d = (UmlDiagram)dN.getUserObject();
        
        if(nowExploring==PROJECT){
            //if(UmlModel.class.isInstance(((DefaultMutableTreeNode)dN.getParent()).getUserObject())){
                for(Iterator<AttachmentOwner> i = d.getAttachmentOwnerList().iterator() ; i.hasNext() ;){
                    AttachmentOwner a = i.next();
                    DefaultMutableTreeNode aN = new DefaultMutableTreeNode(a);
                    dN.add(aN);
                    for(Iterator<UmlDiagram> j = a.getAttachedDiagrams().iterator() ; j.hasNext() ; ){
                        UmlDiagram dd = j.next();
                        DefaultMutableTreeNode ddN = new DefaultMutableTreeNode(dd);
                        aN.add(ddN);
                        buildDiagramNode(ddN);
                    }
                }
            /*}
            else if(AttachmentOwner.class.isInstance(((DefaultMutableTreeNode)dN.getParent()).getUserObject())){
                
            }*/
        }
        else if(nowExploring==DIAGRAM){
            if(d.equals(diagram)){//OR if parent is null
                for(Iterator<UmlCoreElement> i = d.getCoreElementList().iterator() ; i.hasNext() ;){
                    UmlCoreElement e = i.next();
                    DefaultMutableTreeNode eN = new DefaultMutableTreeNode(e);
                    dN.add(eN);
                    buildElementNode(eN);
                }
            }
            else{//
                
            }
        }
    }
    
    
    /*
    UmlEntity
    case1: nowExploring==PROJECT; parent:case#1 D, ch: case#2 D
    case2: nowExploring==DIAGRAM; parent:case#3 D, ch: various
        case2.1: BusinessObjectOwner ch:ArrayList[CoreObject]
        case2.2: AttachmentOwnerOwner ch:case#4 D ArrayList[UmlDiagram]
        case2.3: WorkerClassOwner ch:ArrayList[WorkerClass]
        case2.4: ConditionalBlock ch:ArrayList[LogicalTest]
        case2.5: RepeatLoop ch:LogicalTest , ArrayList[Activity]
        case2.6: WhileLoop ch:LogicalTest , ArrayList[Activity]    
        case2.999 LEAF: ch:NA        
    case3 LEAF: nowExploring==DIAGRAM; parent:ArrayList, ch: NA
    
    ArrayList
        AttachmentOwner
        BusinessObjectOwner
    
    LogicalTest
    
    */
    private void buildElementNode(DefaultMutableTreeNode eN){
        UmlCoreElement e = (UmlCoreElement)eN.getUserObject();
        
        if(nowExploring==DIAGRAM){
            if(UmlDiagram.class.isInstance(((DefaultMutableTreeNode)eN.getParent()).getUserObject())){
                if(UmlElement.class.isInstance(e)){
                    if(BusinessObjectOwner.class.isInstance(e)){
                        DefaultMutableTreeNode aN = new DefaultMutableTreeNode(((BusinessObjectOwner)e).getBusinessObjects());
                        eN.add(aN);
                        for(Iterator<CoreObject> i = ((BusinessObjectOwner)e).getBusinessObjects().iterator() ; i.hasNext() ;){
                            aN.add(new DefaultMutableTreeNode(i.next()));
                        }
                    }
                    if(ControllerOwner.class.isInstance(e)){
                        DefaultMutableTreeNode aN = new DefaultMutableTreeNode(((ControllerOwner)e).getControllers());
                        eN.add(aN);
                        for(Iterator<CoreObject> i = ((ControllerOwner)e).getControllers().iterator() ; i.hasNext() ;){
                            aN.add(new DefaultMutableTreeNode(i.next()));
                        }
                    }
                    if(AttachmentOwner.class.isInstance(e)){
                        DefaultMutableTreeNode aN = new DefaultMutableTreeNode(((AttachmentOwner)e).getAttachedDiagrams());
                        eN.add(aN);
                        for(Iterator<UmlDiagram> i = ((AttachmentOwner)e).getAttachedDiagrams().iterator() ; i.hasNext() ;){
                            aN.add(new DefaultMutableTreeNode(i.next()));
                        }
                    }

                    //TODO Message - seq diagrams
                }
                else if(ConditionalBlock.class.isInstance(e)){
                    ConditionalBlock cb = (ConditionalBlock)e;
                    for(Iterator<LogicalTest> i = ((ConditionalBlock)e).getTestList().iterator() ; i.hasNext() ;){
                        LogicalTest t = i.next();
                        DefaultMutableTreeNode tN = new DefaultMutableTreeNode(t);
                        eN.add(tN);
                        DefaultMutableTreeNode aN = new DefaultMutableTreeNode(cb.getTestMap().get(t));
                        tN.add(aN);
                        for(Iterator j = cb.getTestMap().get(t).iterator() ; j.hasNext() ;){
                            aN.add(new DefaultMutableTreeNode(j.next()));
                        }
                    }
                }
                
                if(RepeatLoop.class.isInstance(e)){
                    RepeatLoop rl = (RepeatLoop)e;
                    LogicalTest t = rl.getLogicalTest();
                    DefaultMutableTreeNode tN = new DefaultMutableTreeNode(t);
                    eN.add(tN);
                    DefaultMutableTreeNode aN = new DefaultMutableTreeNode(rl.getActivityFlow());
                    tN.add(aN);
                    for(Iterator j = rl.getActivityFlow().iterator() ; j.hasNext() ;){
                        aN.add(new DefaultMutableTreeNode(j.next()));
                    }
                }
                
                if(WhileLoop.class.isInstance(e)){
                    WhileLoop rl = (WhileLoop)e;
                    LogicalTest t = rl.getLogicalTest();
                    DefaultMutableTreeNode tN = new DefaultMutableTreeNode(t);
                    eN.add(tN);
                    DefaultMutableTreeNode aN = new DefaultMutableTreeNode(rl.getActivityFlow());
                    tN.add(aN);
                    for(Iterator j = rl.getActivityFlow().iterator() ; j.hasNext() ;){
                        aN.add(new DefaultMutableTreeNode(j.next()));
                    }
                }
                
                if(Split.class.isInstance(e)){
                    Split s = (Split)e;            
                    for(Iterator<ActivityFlow> i = s.getActivityFlows().iterator(); i.hasNext() ;){
                        ActivityFlow f = i.next();
                        DefaultMutableTreeNode tN = new DefaultMutableTreeNode(f);
                        eN.add(tN);
                        for(Iterator<UmlCoreElement> ii = f.iterator() ;ii.hasNext() ; ){
                            tN.add(new DefaultMutableTreeNode(ii.next()));
                        }
                    }
                }
                
                if(Fork.class.isInstance(e)){
                    Fork s = (Fork)e;            
                    for(Iterator<ActivityFlow> i = s.getActivityFlows().iterator(); i.hasNext() ;){
                        ActivityFlow f = i.next();
                        DefaultMutableTreeNode tN = new DefaultMutableTreeNode(f);
                        eN.add(tN);
                        for(Iterator<UmlCoreElement> ii = f.iterator() ;ii.hasNext() ; ){
                            tN.add(new DefaultMutableTreeNode(ii.next()));
                        }
                    }
                }
                
            }
        }
        
        
    }
    
    public void reload(){
        buildTree();
        super.reload();
    }
    
    
}
