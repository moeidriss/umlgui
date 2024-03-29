/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package moe.umlgui.ui.tree;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.GridBagLayout;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;
import static javax.swing.TransferHandler.COPY_OR_MOVE;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import moe.umlgui.controller.ReportEngine;
import moe.umlgui.model.AcceptEvent;
import moe.umlgui.model.AcceptTimeEvent;
import moe.umlgui.model.Activity;
import moe.umlgui.model.Action;
import moe.umlgui.model.ActivityDiagram;
import moe.umlgui.model.ActivityFinalNode;
import moe.umlgui.model.ActivityInitialNode;
import moe.umlgui.model.ActivityNode;
import moe.umlgui.model.Actor;
import moe.umlgui.model.Association;
import moe.umlgui.model.AttachmentOwner;
import moe.umlgui.model.BusinessSystem;
import moe.umlgui.model.CallActivity;
import moe.umlgui.model.ConditionalBlock;
import moe.umlgui.model.ControlNode;
import moe.umlgui.model.CoreObject;
import moe.umlgui.model.FlowFinalNode;
import moe.umlgui.model.Include;
import moe.umlgui.model.ItSystem;
import moe.umlgui.model.Message;
import moe.umlgui.model.ModelException;
import moe.umlgui.model.PackageDiagram;
import moe.umlgui.model.Project;
import moe.umlgui.model.RepeatLoop;
import moe.umlgui.model.SendSignal;
import moe.umlgui.model.SequenceDiagram;
import moe.umlgui.model.UmlCoreElement;
import moe.umlgui.model.UmlDiagram;
import moe.umlgui.model.UmlElement;
import moe.umlgui.model.UmlModel;
import moe.umlgui.model.UseCase;
import moe.umlgui.model.UseCaseDiagram;
import moe.umlgui.model.User;
import moe.umlgui.model.WhileLoop;
import moe.umlgui.ui.TransferrableImpl;
import moe.umlgui.ui.object.CoreObjectMethodSelector;

/**
 *
 * @author Moe
 */
public class Explorer extends javax.swing.JPanel implements PropertyChangeListener {

    
    
    //static int ELEMENT = 1;
    static int DIAGRAM = 2;
    static int PROJECT = 3;
    //static int MODEL = 4;
    
    int nowExploring = -1;
    
    Project project;
    //UmlModel model;
    UmlDiagram diagram;
    //UmlElement element;
    
    /**
     * Creates new form DiagramExplorer
     */
    public Explorer(Project project) {
        initComponents();
        this.project = project;
        nowExploring = PROJECT;
        loadExplorer();
        setSelection(project);
        
        ArrayList q =new ArrayList();
        q.add(this);
        this.firePropertyChange("Explorer selection", q, project);
    }


    public Explorer(UmlDiagram diagram) {
        initComponents();
        this.diagram = diagram;
        nowExploring = DIAGRAM;
        loadExplorer();
        setSelection(diagram);
        
        ArrayList q =new ArrayList();
        q.add(this);
        this.firePropertyChange("Explorer selection", q, diagram);
    }

    /*
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
    */
    
    ExplorerTreeModel treeModel = null;
    
    private void loadExplorer(){    
        if(project!=null){
            //setBorder(new TitledBorder(project.getName()));  
            treeModel = new ExplorerTreeModel(project);
        }
        else if(diagram!=null){
            //setBorder(new TitledBorder(diagram.getName()));        
            treeModel = new ExplorerTreeModel(diagram);
        }
        /*else if(model!=null){
            //setBorder(new TitledBorder(model.getName()));        
            treeModel = new ExplorerTreeModel(model);
        }
        else if(element!=null){
            //setBorder(new TitledBorder(element.getName()));        
            treeModel = new ExplorerTreeModel(element);
        }*/
        
        jTree.setModel(treeModel);
        jTree.setCellRenderer(new ExplorerTreeCellRenderer()); 
        jTree.setDragEnabled(true);
        jTree.setTransferHandler(new ElementExportHandler());
        
    }
    
    class ElementExportHandler extends TransferHandler{
        protected ElementExportHandler(){
            super();
        }
        @Override
        protected Transferable createTransferable(JComponent c){
            //System.out.println(c + ":" + c.getClass()+"-selection="+selection);
           // Object selection = ((DefaultMutableTreeNode)jTree.getLastSelectedPathComponent()).getUserObject();
            if(UmlElement.class.isInstance(selection))
                return new TransferrableImpl((UmlElement)selection);
            return null;
        }

        @Override
        protected void exportDone(JComponent source,Transferable data, int action){
            super.exportDone(source, data, action);
        }

        /**
         *
         * @param c
         * @return
         */
        public int getSourceActions(JComponent c){
            System.out.println("Explorer.getSourceActions::"+c + ":" + c.getClass()+"-selection="+selection);
            return COPY_OR_MOVE;
        }
    }
    
    public void reload(){
        ((ExplorerTreeModel)jTree.getModel()).reload();
        if (selection!=null){
            setSelection(selection);
        }
        if(project!=null)   java.lang.System.out.println(project.dump());

    }

    
    /*
    PROJECT
        Project
            Model
                Diagram
                    AttOwner
    DIAGRAM
        Diagram
            Element
                AttachedDiagram
                BO List
                    BO
                CN List
                    CN
    */
    
    Object selection;
    //TODO ...::
    public void setSelection(Object selection){
        this.selection = selection;
        
        
        if(nowExploring==PROJECT){
            if(selection==project)
                jTree.setSelectionPath(new TreePath(treeModel.getPathToRoot((DefaultMutableTreeNode)treeModel.getRoot())));
            else if(UmlModel.class.isInstance(selection)){
                jTree.setSelectionPath(new TreePath(treeModel.getPathToRoot((DefaultMutableTreeNode)treeModel.getChild(treeModel.getRoot(), project.getModels().indexOf(selection)))));
            }
            //top lev dgrms
            else if(UmlDiagram.class.isInstance(selection) && !((UmlDiagram)selection).isAttached() ){
                DefaultMutableTreeNode modelNode = (DefaultMutableTreeNode)treeModel.getChild(treeModel.getRoot(), project.getModels().indexOf(((UmlDiagram)selection).getUmlModel()));
                jTree.setSelectionPath(new TreePath(treeModel.getPathToRoot((DefaultMutableTreeNode)treeModel.getChild(modelNode, ((UmlDiagram)selection).getUmlModel().getDiagrams().indexOf(selection)))));
            }
            else if(UmlElement.class.isInstance(selection) && !CoreObject.class.isInstance(selection) ){
                DefaultMutableTreeNode modelNode = (DefaultMutableTreeNode)treeModel.getChild(treeModel.getRoot(), project.getModels().indexOf(((UmlElement)selection).getUmlDiagram().getUmlModel()));
                if(((UmlElement)selection).getUmlDiagram().getUmlModel().getDiagrams().indexOf(selection)!= -1){
                    DefaultMutableTreeNode diagramNode = (DefaultMutableTreeNode)treeModel.getChild(modelNode, ((UmlElement)selection).getUmlDiagram().getUmlModel().getDiagrams().indexOf(selection));
                    jTree.setSelectionPath(new TreePath(treeModel.getPathToRoot((DefaultMutableTreeNode)treeModel.getChild(diagramNode, ((UmlElement)selection).getUmlDiagram().getElementList().indexOf(selection)))));
                }
            }
            //attached dgrm
            else if(UmlDiagram.class.isInstance(selection) && !((UmlDiagram)selection).isAttached() ){
                
            }
            else{ }
        }
        
        else if(nowExploring==DIAGRAM){
            if(selection==diagram)
                jTree.setSelectionPath(new TreePath(treeModel.getPathToRoot((DefaultMutableTreeNode)treeModel.getRoot())));
            else if(UmlElement.class.isInstance(selection)){
                if(diagram.getElementList().indexOf(selection) != -1)
                    jTree.setSelectionPath(new TreePath(treeModel.getPathToRoot((DefaultMutableTreeNode)treeModel.getChild(treeModel.getRoot(), diagram.getElementList().indexOf(selection)))));
            }            
            else if(ControlNode.class.isInstance(selection)){
                //DefaultMutableTreeNode modelNode = (DefaultMutableTreeNode)treeModel.getChild(treeModel.getRoot(), project.getModels().indexOf(((ControlNode)selection).getUmlDiagram().getUmlModel()));
                if(diagram.getElementList().indexOf(selection) != -1)
                    jTree.setSelectionPath(new TreePath(treeModel.getPathToRoot((DefaultMutableTreeNode)treeModel.getChild(treeModel.getRoot(), diagram.getElementList().indexOf(selection)))));
            }
            
            //TODO check ActivityNode
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
        attachDiagramButton = new javax.swing.JButton();
        exportPptButton = new javax.swing.JButton();

        setLayout(new java.awt.BorderLayout());

        jTree.setDragEnabled(true);
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

        addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/16x16/Add.png"))); // NOI18N
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

        removeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/16x16/Remove.png"))); // NOI18N
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

        upButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/16x16/go-up.png"))); // NOI18N
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

        downButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/16x16/go-down.png"))); // NOI18N
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

        attachDiagramButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/16x16/DiagramLinked.png"))); // NOI18N
        attachDiagramButton.setToolTipText("Attach Diagram");
        attachDiagramButton.setEnabled(false);
        attachDiagramButton.setFocusable(false);
        attachDiagramButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        attachDiagramButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        attachDiagramButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                attachDiagramButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(attachDiagramButton);

        exportPptButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/16x16/application-vnd.ms-powerpoint.png"))); // NOI18N
        exportPptButton.setFocusable(false);
        exportPptButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        exportPptButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        exportPptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportPptButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(exportPptButton);

        add(jToolBar1, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        
        //elements only in D mode
        if(nowExploring==DIAGRAM && diagram!=null && diagram.getControlLevel()==UmlDiagram.FREE){
            ArrayList options = new ArrayList();

            //ucd
            if(diagram!=null & UseCaseDiagram.class.isInstance(diagram)){
                options.add("Actor");   
                options.add("Use Case");
            }
            else if(diagram!=null & ActivityDiagram.class.isInstance(diagram)){                
                options.add("Action");options.add("Call Activity");
                options.add("Accept Event");options.add("Accept Time Event");
                options.add("Send Signal");options.add("Activity Initial Node");
                options.add("Activity Final Node");options.add("Flow Final Node");
                options.add("Conditional Block");options.add("While Loop");
                options.add("Repeat Loop");
                options.add("Split");   options.add("Fork"); 
            }
            else if(diagram!=null & SequenceDiagram.class.isInstance(diagram)){
                options.add("Actor");   options.add("System");
                options.add("Business System");   options.add("IT System");
                options.add("User");    options.add("Message");
            }

            //context sensitive options
            if(selection != null && UmlElement.class.isInstance(selection)){
                if(UseCase.class.isInstance(selection)){
                    options.add("Association");
                    options.add("Include");
                }
                if(Actor.class.isInstance(selection) && UseCaseDiagram.class.isInstance(diagram)){
                    options.add("Association");
                }
            }


            String newElementType = (String)JOptionPane.showInputDialog(null, "Select element", "Insert", JOptionPane.INFORMATION_MESSAGE, null, options.toArray(), null);

            //if nothing or diagram is select (or multiple items), append element to end

            //if element is selected, prompt to insert before or after
            int newIndex = -1;
            if(UmlElement.class.isInstance(selection) &&
                !newElementType.equals("Association")  &&
                !newElementType.equals("Include") 
            ){
                String[] s = {"Before" , "After"};
                String ss = ((String)JOptionPane.showInputDialog(this, "Before or after", "Insert", JOptionPane.INFORMATION_MESSAGE, null, s, "After"));
                if(ss .equals("Before"))
                    newIndex = ((UmlElement)selection).getUmlDiagram().getElementList().indexOf(selection);
                else
                    newIndex = ((UmlElement)selection).getUmlDiagram().getElementList().indexOf(selection)+1;
            }

            UmlCoreElement newElement = null;
            if(newElementType.equals("Actor")){
                newElement = new Actor();
            }
            else if(newElementType.equals("System")){
                newElement = new moe.umlgui.model.System();
            }
            else if(newElementType.equals("Business System")){
                newElement = new BusinessSystem();
            }
            else if(newElementType.equals("IT System")){
                newElement = new ItSystem();
            }
            else if(newElementType.equals("User")){
                newElement = new User();
            }

            else if(newElementType.equals("Use Case")){
                newElement = new UseCase();
            }
            else if(newElementType.equals("Association")){
                newElement = new Association();
                ((Association)newElement).setPartyA((UmlElement)selection);
            }
            else if(newElementType.equals("Include")){
                newElement = new Include();
                ((Include)newElement).setPartyA((UmlElement)selection);
            }    

            else if(newElementType.equals("Action")){
                newElement = new Action();
            }
            else if(newElementType.equals("Call Activity")){
                newElement = new CallActivity();
            }
            else if(newElementType.equals("Accept Event")){
                newElement = new AcceptEvent();
            }
            else if(newElementType.equals("Accept Time Event")){
                newElement = new AcceptTimeEvent();
            }
            else if(newElementType.equals("Send Signal")){
                newElement = new SendSignal();
            }
            else if(newElementType.equals("Activity Initial Node")){
                newElement = new ActivityInitialNode();
            }
            else if(newElementType.equals("Activity Final Node")){
                newElement = new ActivityFinalNode();
            }
            else if(newElementType.equals("Flow Final Node")){
                newElement = new FlowFinalNode();
            }
            else if(newElementType.equals("Conditional Block")){
                newElement = new ConditionalBlock();
            }
            else if(newElementType.equals("While Loop")){
                newElement = new WhileLoop();
            }
            else if(newElementType.equals("Repeat Loop")){
                newElement = new RepeatLoop();
            }            
            else if(newElementType.equals("Message")){
                newElement = new Message();
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
        }
        else if(nowExploring==DIAGRAM && diagram!=null && diagram.getControlLevel()==UmlDiagram.CONSTRAINED){
            addConstrainedElement();
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void addConstrainedElement(){
        
        if(ActivityDiagram.class.isInstance(diagram)){
            JDialog d = new JDialog();
            
            //el type
            ArrayList options = new ArrayList();
            options.add("Action");options.add("Call Activity");
            options.add("Accept Event");options.add("Accept Time Event");
            options.add("Send Signal");options.add("Activity Initial Node");
            options.add("Activity Final Node");options.add("Flow Final Node");
            options.add("Conditional Block");options.add("While Loop");
            options.add("Repeat Loop");
            options.add("Split");   options.add("Fork");    
            
            JComboBox tC = new JComboBox(options.toArray());
            
            //if activity: method
            CoreObjectMethodSelector methSelector = new CoreObjectMethodSelector(diagram.getUmlModel().getProject());

            tC.addActionListener(new ActionListener(){                
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(tC.getSelectedIndex()!=-1  &&
                       !tC.getSelectedItem().equals("Action") &&
                       !tC.getSelectedItem().equals("Call Activity")&&
                       !tC.getSelectedItem().equals("Accept Event") &&
                       !tC.getSelectedItem().equals("Accept Time Event") &&
                       !tC.getSelectedItem().equals("Send Signal")
                    ){
                        methSelector.setSelectedMethod(null);
                        methSelector.setEnabled(false);
                    }
                    else{
                        methSelector.setEnabled(true);
                    }
                }
            });
            
            JButton okButton = new JButton("OK");
            okButton.addActionListener(new ActionListener(){                
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(tC.getSelectedIndex()!=-1 && methSelector.getSelectedIndex()!=-1){
                        String newElementType = (String)tC.getSelectedItem();
                        UmlCoreElement newElement = null;
                        if(newElementType.equals("Action")){
                            newElement = new Action();
                        }
                        else if(newElementType.equals("Call Activity")){
                            newElement = new CallActivity();
                        }
                        else if(newElementType.equals("Accept Event")){
                            newElement = new AcceptEvent();
                        }
                        else if(newElementType.equals("Accept Time Event")){
                            newElement = new AcceptTimeEvent();
                        }
                        else if(newElementType.equals("Send Signal")){
                            newElement = new SendSignal();
                        }
                        else if(newElementType.equals("Activity Initial Node")){
                            newElement = new ActivityInitialNode();
                        }
                        else if(newElementType.equals("Activity Final Node")){
                            newElement = new ActivityFinalNode();
                        }
                        else if(newElementType.equals("Flow Final Node")){
                            newElement = new FlowFinalNode();
                        }
                        else if(newElementType.equals("Conditional Block")){
                            newElement = new ConditionalBlock();
                        }
                        else if(newElementType.equals("While Loop")){
                            newElement = new WhileLoop();
                        }
                        else if(newElementType.equals("Repeat Loop")){
                            newElement = new RepeatLoop();
                        }   
                        
                        int newIndex = -1;
                        
                        if(UmlElement.class.isInstance(selection)){
                            String[] s = {"Before" , "After"};
                            String ss = ((String)JOptionPane.showInputDialog(Explorer.this, "Before or after", "Insert", JOptionPane.INFORMATION_MESSAGE, null, s, "After"));
                            if(ss .equals("Before"))
                                newIndex = ((UmlElement)selection).getUmlDiagram().getElementList().indexOf(selection);
                            else
                                newIndex = ((UmlElement)selection).getUmlDiagram().getElementList().indexOf(selection)+1;
                        }
                        
                        if(Activity.class.isInstance(newElement)){
                            newElement.setName(methSelector.getSelectedMethod().toFullString());
                            try{
                                if(newIndex !=-1 )
                                    diagram.insertCoreElement(newIndex,newElement);            
                                else 
                                    diagram.addCoreElement(newElement);            
                            }catch(ModelException ex){
                                JOptionPane.showMessageDialog(Explorer.this, ex);
                                ex.printStackTrace();
                            }
                            diagram.getConstraints().put(newElement, methSelector.getSelectedMethod());                            
                        }
                        else if(ControlNode.class.isInstance(newElement) ||
                                ActivityNode.class.isInstance(newElement)
                        ){
                            try{
                                if(newIndex !=-1 )
                                    diagram.insertCoreElement(newIndex,newElement);            
                                else 
                                    diagram.addCoreElement(newElement);            
                            }catch(ModelException ex){
                                JOptionPane.showMessageDialog(Explorer.this, ex);
                                ex.printStackTrace();
                            }
                        }
                        
                        
                        ArrayList q =new ArrayList();
                        q.add(this);
                        firePropertyChange("Element inserted", q, newElement);
                       
                        d.setVisible(false);
                    }
                }
            });
            
            d.add(tC, BorderLayout.NORTH);
            d.getContentPane().add(methSelector , BorderLayout.CENTER);
            d.add(okButton, BorderLayout.SOUTH);
            
            d.pack();
            d.setLocationRelativeTo(null);
            d.setVisible(true);
        }
        else if(SequenceDiagram.class.isInstance(diagram)){
            
            String elType = (String) JOptionPane.showInputDialog(this,
                    "Type", "Type" , JOptionPane.INFORMATION_MESSAGE ,
                    null , new String[]{"Actor","Controller/Business Object","Message"} , null);
            
            if(elType=="Controller/Business Object"){
                JDialog d = new JDialog();

                JList fList = new JList(diagram.getUmlModel().getCoreObjects().toArray());
                fList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                //TODO remove obj already in dgrm cnstrnts

                JButton okButton = new JButton("OK");
                okButton.addActionListener(new ActionListener(){                
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        CoreObject obj = (CoreObject) fList.getSelectedValue();
                        if(diagram.getConstraints().get(obj)==null){
                            /*int newIndex = -1;
                            String[] s = {"Before" , "After"};
                            String ss = ((String)JOptionPane.showInputDialog(Explorer.this, "Before or after", "Insert", JOptionPane.INFORMATION_MESSAGE, null, s, "After"));
                            if(ss .equals("Before"))
                                newIndex = ((UmlElement)selection).getUmlDiagram().getElementList().indexOf(selection);
                            else
                                newIndex = ((UmlElement)selection).getUmlDiagram().getElementList().indexOf(selection)+1;
                            */
                            //TODO fix 
                            Actor a = new Actor();
                            a.setName(obj.getName());                                
                            try {
                            //    if(newIndex !=-1 )  
                                //    diagram.insertCoreElement(newIndex,a);
                              //  else 
                                    diagram.addCoreElement(a); 
                            } catch (ModelException ex) {
                                JOptionPane.showMessageDialog(null, ex);
                                ex.printStackTrace();
                            }
                            diagram.getConstraints().put(a,obj);

                            ArrayList q =new ArrayList();
                            q.add(this);
                            firePropertyChange("Element inserted", q, a);
                            d.setVisible(false);
                        }
                    }
                });

                d.getContentPane().add(new JScrollPane(fList) , BorderLayout.CENTER);
                d.getContentPane().add(okButton , BorderLayout.SOUTH);
                d.pack();
                d.setLocationRelativeTo(null);
                d.setVisible(true);
            }
            else if(elType=="Message"){
                Message m = new Message();
                try {
                        diagram.addCoreElement(m); 
                } catch (ModelException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                    ex.printStackTrace();
                }               

                ArrayList q =new ArrayList();
                q.add(this);
                firePropertyChange("Element inserted", q, m);                
            }
            //TODO allow Actors that can receive no messages and can only send to cntlrs i.e userDel            
            else if(elType=="Actor"){
                Actor m = new Actor();
                try {
                        diagram.addCoreElement(m); 
                } catch (ModelException ex) {
                    JOptionPane.showMessageDialog(null, ex);
                    ex.printStackTrace();
                }               

                ArrayList q =new ArrayList();
                q.add(this);
                firePropertyChange("Element inserted", q, m);                
            }
        }
    }
    
    private void removeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeButtonActionPerformed
        if(UmlCoreElement.class.isInstance(selection)){
            ((UmlCoreElement)selection).getUmlDiagram().removeCoreElement((UmlCoreElement)selection);
            
            ArrayList q =new ArrayList();
            q.add(this);
            firePropertyChange("Element removed", q, selection);                
            
            setSelection(((UmlCoreElement)selection).getUmlDiagram());
        }
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
        selection = ((DefaultMutableTreeNode)jTree.getLastSelectedPathComponent()).getUserObject();
        
        if(Project.class.isInstance(selection)){
            upButton.setEnabled(false);
            downButton.setEnabled(false);
            addButton.setEnabled(true);//TODO model
            removeButton.setEnabled(true);
            attachDiagramButton.setEnabled(false);
            exportPptButton.setEnabled(false);
        }
        else if(UmlModel.class.isInstance(selection)){
            upButton.setEnabled(false);
            downButton.setEnabled(false);
            addButton.setEnabled(true);//TODO diagram
            removeButton.setEnabled(true);
            attachDiagramButton.setEnabled(false);
            exportPptButton.setEnabled(true);
        }
        else if(UmlDiagram.class.isInstance(selection)){
            upButton.setEnabled(false);
            downButton.setEnabled(false);
            addButton.setEnabled(true);
            removeButton.setEnabled(true);
            attachDiagramButton.setEnabled(false);
            exportPptButton.setEnabled(false);
        }        
        else if(UmlElement.class.isInstance(selection)){
            upButton.setEnabled(true);
            downButton.setEnabled(true);
            addButton.setEnabled(true);
            removeButton.setEnabled(true);
            exportPptButton.setEnabled(false);
            
            if( AttachmentOwner.class.isInstance(selection)){                    
                attachDiagramButton.setEnabled(true);
            }
            else    attachDiagramButton.setEnabled(false);
        }      
        else if(ControlNode.class.isInstance(selection)){
            upButton.setEnabled(true);
            downButton.setEnabled(true);
            addButton.setEnabled(true);
            removeButton.setEnabled(true);
            exportPptButton.setEnabled(false);
            
            if( AttachmentOwner.class.isInstance(selection)){                    
                attachDiagramButton.setEnabled(true);
            }
            else    attachDiagramButton.setEnabled(false);
        }
        else{//TODO element members
            upButton.setEnabled(false);
            downButton.setEnabled(false);
            addButton.setEnabled(false);
            removeButton.setEnabled(false);
            attachDiagramButton.setEnabled(false);
            exportPptButton.setEnabled(false);
        }
        

        
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

    private void attachDiagramButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_attachDiagramButtonActionPerformed
        if(AttachmentOwner.class.isInstance(selection)){
            UmlDiagram ud = null;
            UmlModel model = ((UmlCoreElement)selection).getUmlDiagram().getUmlModel();
            String[] ld = {"Use Case Diagram","Activity Diagram",
                        "Sequence Diagram" , "Package Diagram"};
            String s = (String)JOptionPane.showInputDialog(null, "Select type of diagram", "Attach Diagram", JOptionPane.INFORMATION_MESSAGE, null, ld, null);
            if(s.equals("Use Case Diagram")){
                ud = new UseCaseDiagram(model);            
            }
            else if(s.equals("Activity Diagram")){
                ud = new ActivityDiagram(model);
            }
            else if(s.equals("Sequence Diagram")){
                ud = new SequenceDiagram(model);
            }
            else if(s.equals("Package Diagram")){
                ud = new PackageDiagram(model);
            }
            
            if(ud !=  null){                
                String n = JOptionPane.showInputDialog(this, "Name it" , ud.getName());
                if(n!=null &&!n.isEmpty()) ud.setName(n);
                
                
                ud.setAttached(true);
                
                ((AttachmentOwner)selection).getAttachedDiagrams().add(ud);
                

                ArrayList q =new ArrayList();
                q.add(this);
                this.firePropertyChange("Diagram attached", q, ud);
                revalidate();
            }
        }
    }//GEN-LAST:event_attachDiagramButtonActionPerformed

    private void exportPptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportPptButtonActionPerformed
        if(UmlModel.class.isInstance(selection)){
            try{
                File f = ReportEngine.pptxReport(((UmlModel)selection));
                Desktop.getDesktop().open(f);
                System.out.println(f);
            }catch(IOException ex){
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
        
    }//GEN-LAST:event_exportPptButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton attachDiagramButton;
    private javax.swing.JButton downButton;
    private javax.swing.JButton exportPptButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTree jTree;
    private javax.swing.JButton removeButton;
    private javax.swing.JButton upButton;
    // End of variables declaration//GEN-END:variables

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public boolean equals(Object o){
        return (hashCode()==o.hashCode());
    }
}
