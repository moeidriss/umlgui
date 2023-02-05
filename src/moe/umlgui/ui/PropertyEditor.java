/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package moe.umlgui.ui;

import moe.umlgui.ui.object.CoreObjectPanel;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import moe.umlgui.model.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Moe
 */
public class PropertyEditor extends javax.swing.JPanel {

    UmlCoreElement umlCoreElement;
    UmlModel model;
    UmlDiagram umlDiagram;
    Project project;
    
    static int ELEMENT = 1;
    static int DIAGRAM = 2;
    static int PROJECT = 3;
    static int MODEL = 4;
    
    int nowEditing = -1;
    
    /**
     * Creates new form PropertyEditor
     * @param context if null, context is global library
     */
    public PropertyEditor() {
        initComponents();
    }
    
    /**
     *
     * @param umlCoreElement
     * @param umlDiagram if null, context is project or global lib (outside any diagram there)
     */
    public void edit(UmlCoreElement umlCoreElement){
        this.umlCoreElement = umlCoreElement;
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

    public void edit(UmlModel model){
        this.model = model;
        nowEditing = MODEL;
        loadForm();
    }
    
    public void showToolbar(boolean b){
        remove(saveButton);
        if(b)   add(saveButton , BorderLayout.NORTH);
    }
    
    
    private void loadElement(){
        setBorder(javax.swing.BorderFactory.createTitledBorder(umlCoreElement.getName()));
        editPanel.removeAll();
        
        java.awt.GridBagConstraints gridBagConstraints;
        int yCounter = 0;
        
        nameTextField.setText(umlCoreElement.getName());
        nameTextField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Name"), 
                BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED)));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        
        gridBagConstraints.gridy = yCounter;    yCounter++;
        editPanel.add(nameTextField, gridBagConstraints);

        //TODO constrained ...
        if(umlCoreElement.getUmlDiagram().getControlLevel()==UmlDiagram.CONSTRAINED){
            nameTextField.setEditable(false);
        }
        else{
            nameTextField.setEditable(true);
        }
        
        //Packages & swimlanes
        try{
        //Package        
        if(umlCoreElement.getUmlDiagram()!=null){
            //ucd:actor,uc
            //..
            if( (UseCase.class.isInstance(umlCoreElement) && 
                    UseCaseDiagram.class.isInstance(umlCoreElement.getUmlDiagram()))
                    ||
                (Actor.class.isInstance(umlCoreElement) && 
                    UseCaseDiagram.class.isInstance(umlCoreElement.getUmlDiagram()))
                ||
                (Message.class.isInstance(umlCoreElement) && 
                    SequenceDiagram.class.isInstance(umlCoreElement.getUmlDiagram()))
            ){
                PackageSelector ps = new PackageSelector(umlCoreElement.getUmlDiagram());
                ps.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createTitledBorder("Package"), 
                            BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED)));
                ps.addPropertyChangeListener(new PropertyChangeListener(){
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        if(evt.getPropertyName().equals("Package selected")){
                            umlCoreElement.setPckage((moe.umlgui.model.Package)evt.getNewValue());
                        }
                    }
                });
                if(umlCoreElement.getPckage()!=null)    
                    ps.setSelectedPackage(umlCoreElement.getPckage());
                
                gridBagConstraints = new java.awt.GridBagConstraints();
                gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
                gridBagConstraints.weightx = 1.0;
                gridBagConstraints.gridx = GridBagConstraints.RELATIVE;
                gridBagConstraints.gridy = yCounter;    yCounter++;
                editPanel.add(ps, gridBagConstraints);
            }
            
            //swimlanes
            if( (Activity.class.isInstance(umlCoreElement) && 
                    ActivityDiagram.class.isInstance(umlCoreElement.getUmlDiagram()))
                    ||
                (ControlNode.class.isInstance(umlCoreElement) && 
                ActivityDiagram.class.isInstance(umlCoreElement.getUmlDiagram()))
                    ||
                (ActivityNode.class.isInstance(umlCoreElement) && 
                ActivityDiagram.class.isInstance(umlCoreElement.getUmlDiagram()))
            ){
                JComboBox cb =  new JComboBox(((ActivityDiagram)umlCoreElement.getUmlDiagram()).getSwimlanes().keySet().toArray());
                cb.addActionListener(new ActionListener(){                    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(cb.getSelectedIndex()!=-1)
                        ((ActivityDiagram)umlCoreElement.getUmlDiagram()).addToSwimlane(umlCoreElement, (String)cb.getSelectedItem());
                    }
                });
                String s = ((ActivityDiagram)umlCoreElement.getUmlDiagram()).getSwimlane(umlCoreElement);
                if(s!=null) cb.setSelectedItem(s);
                
                gridBagConstraints = new java.awt.GridBagConstraints();
                gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
                gridBagConstraints.weightx = 1.0;
                gridBagConstraints.gridx = GridBagConstraints.RELATIVE;
                gridBagConstraints.gridy = yCounter;    yCounter++;
                editPanel.add(cb, gridBagConstraints);
            }
        }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex);
                    ex.printStackTrace();
        }
        
        
        if(UseCase.class.isInstance(umlCoreElement)){
            
            try {
                Method m1 = UseCase.class.getDeclaredMethod("setDescription", String.class);
                Method m2 = UseCase.class.getDeclaredMethod("getDescription");
                Method m3 = UseCase.class.getDeclaredMethod("setDescriptionNumColumns", Integer.class);
                Method m4 = UseCase.class.getDeclaredMethod("getDescriptionNumColumns");
                descriptionMultilineTextComponent.setUmlCoreElement(umlCoreElement,m1,m2,m3,m4);
                descriptionMultilineTextComponent.setBorder(BorderFactory.createTitledBorder("Description"));
                
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

        else if(ConditionalBlock.class.isInstance(umlCoreElement)){
            ConditionalBlockPanel tPanel = 
                    new ConditionalBlockPanel((ConditionalBlock)umlCoreElement);
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            
            //gridBagConstraints.gridheight = 5;
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(tPanel, gridBagConstraints);
            
        }
        
        else if(WhileLoop.class.isInstance(umlCoreElement)){
            LogicalTestComponent tComp = 
                    new LogicalTestComponent(((WhileLoop)umlCoreElement).getLogicalTest() , umlCoreElement.getUmlDiagram() );
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(tComp, gridBagConstraints);
            
            //need to set ActivityFlow diagram despite constructor init 
            //since cnstcr is called at RepeatLoop init with null dgrm... UmlFiagram.addCoreElement
            ((WhileLoop)umlCoreElement).getActivityFlow().setDiagram(umlCoreElement.getUmlDiagram());
            
            
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(new ActivityFlowComponent(((WhileLoop)umlCoreElement).getActivityFlow()), gridBagConstraints);
        }
        
        
        else if(RepeatLoop.class.isInstance(umlCoreElement)){
            //need to set ActivityFlow diagram despite constructor init 
            //since cnstcr is called at RepeatLoop init with null dgrm... UmlFiagram.addCoreElement
            ((RepeatLoop)umlCoreElement).getActivityFlow().setDiagram(umlCoreElement.getUmlDiagram());
            
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;            
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(new ActivityFlowComponent(((RepeatLoop)umlCoreElement).getActivityFlow()), gridBagConstraints);

            LogicalTestComponent tComp = 
                    new LogicalTestComponent(((RepeatLoop)umlCoreElement).getLogicalTest() , umlCoreElement.getUmlDiagram() );
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(tComp, gridBagConstraints);
        }
        
        else if(Split.class.isInstance(umlCoreElement)){
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(new ParallelFlowsComponent(((Split)umlCoreElement).getActivityFlows(),umlDiagram), gridBagConstraints);
        }
        
        else if(Fork.class.isInstance(umlCoreElement)){
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(new ParallelFlowsComponent(((Fork)umlCoreElement).getActivityFlows(),umlDiagram), gridBagConstraints);
        }
        
        
        else if(Message.class.isInstance(umlCoreElement)){
            if(umlCoreElement.getUmlDiagram().getControlLevel()==UmlDiagram.FREE){
                fromComboBox.setModel(new DefaultComboBoxModel(umlCoreElement.getUmlDiagram().getElementList().toArray()));
                if(((Message)umlCoreElement).getFrom() != null){
                    fromComboBox.setSelectedItem(((Message)umlCoreElement).getFrom());
                }
                gridBagConstraints = new java.awt.GridBagConstraints();
                gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
                gridBagConstraints.weightx = 1.0;
                gridBagConstraints.gridy = yCounter;    yCounter++;
                editPanel.add(fromComboBox, gridBagConstraints);

                toComboBox.setModel(new DefaultComboBoxModel(umlCoreElement.getUmlDiagram().getElementList().toArray()));
                if(((Message)umlCoreElement).getTo() != null){
                    toComboBox.setSelectedItem(((Message)umlCoreElement).getTo());
                }
                gridBagConstraints = new java.awt.GridBagConstraints();
                gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
                gridBagConstraints.weightx = 1.0;
                gridBagConstraints.gridy = yCounter;    yCounter++;
                editPanel.add(toComboBox, gridBagConstraints);
            }
            else{
                //from: actor (bo)
                fromComboBox.setModel(new DefaultComboBoxModel(((SequenceDiagram)umlCoreElement.getUmlDiagram()).getActors().toArray()));
                if(((Message)umlCoreElement).getFrom() != null){
                    fromComboBox.setSelectedItem(((Message)umlCoreElement).getFrom());
                }
                
                //to: actor (bo.meth)
                toComboBox.setModel(new DefaultComboBoxModel(((SequenceDiagram)umlCoreElement.getUmlDiagram()).getObjectActors().toArray()));
                if(((Message)umlCoreElement).getTo() != null){
                    toComboBox.setSelectedItem(((Message)umlCoreElement).getTo());
                }
                
                JComboBox mC = new JComboBox();
                
                toComboBox.addActionListener(new ActionListener(){                    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ((Message)umlCoreElement).setTo((Actor) toComboBox.getSelectedItem());
                        CoreObject obj =  (CoreObject) umlCoreElement.getUmlDiagram().getConstraints().get(toComboBox.getSelectedItem());
                        mC.setModel(new DefaultComboBoxModel(obj.getMethods().toArray()));
                    }
                });
                
                mC.addActionListener(new ActionListener(){                    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ((Message)umlCoreElement).setTo((Actor) toComboBox.getSelectedItem());
                        umlCoreElement.getUmlDiagram().getConstraints().put(umlCoreElement, mC.getSelectedItem());
                        umlCoreElement.setName(((CoreObjectMethod)mC.getSelectedItem()).getName() + "()");
                        nameTextField.setText(umlCoreElement.getName());
                    }
                });
                
                gridBagConstraints = new java.awt.GridBagConstraints();
                gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
                gridBagConstraints.weightx = 1.0;
                gridBagConstraints.gridy = yCounter;    yCounter++;
                editPanel.add(fromComboBox, gridBagConstraints);
                
                gridBagConstraints = new java.awt.GridBagConstraints();
                gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
                gridBagConstraints.weightx = 1.0;
                gridBagConstraints.gridy = yCounter;    yCounter++;
                editPanel.add(toComboBox, gridBagConstraints);
                
                gridBagConstraints = new java.awt.GridBagConstraints();
                gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
                gridBagConstraints.weightx = 1.0;
                gridBagConstraints.gridy = yCounter;    yCounter++;
                editPanel.add(mC, gridBagConstraints);
            }
        }
        
        //TODO Note Element

        else if(Association.class.isInstance(umlCoreElement)){
            fromComboBox.setModel(new DefaultComboBoxModel(umlCoreElement.getUmlDiagram().getElementList().toArray()));
            if(((Association)umlCoreElement).getPartyA() != null){
                fromComboBox.setSelectedItem(((Association)umlCoreElement).getPartyA());
            }
            fromComboBox.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createTitledBorder("From"), 
                            BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED)));
                    
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(fromComboBox, gridBagConstraints);

            toComboBox.setModel(new DefaultComboBoxModel(umlCoreElement.getUmlDiagram().getElementList().toArray()));
            if(((Association)umlCoreElement).getPartyB() != null){
                toComboBox.setSelectedItem(((Association)umlCoreElement).getPartyA());
            }
            toComboBox.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createTitledBorder("To"), 
                            BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED)));
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(toComboBox, gridBagConstraints);
        }

        else if(Include.class.isInstance(umlCoreElement)){
            fromComboBox.setModel(new DefaultComboBoxModel(umlCoreElement.getUmlDiagram().getElementList().toArray()));
            if(((Include)umlCoreElement).getPartyA() != null){
                fromComboBox.setSelectedItem(((Include)umlCoreElement).getPartyA());
            }
            fromComboBox.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createTitledBorder("From"), 
                            BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED)));
            
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(fromComboBox, gridBagConstraints);

            toComboBox.setModel(new DefaultComboBoxModel(umlCoreElement.getUmlDiagram().getElementList().toArray()));
            if(((Include)umlCoreElement).getPartyB() != null){
                toComboBox.setSelectedItem(((Include)umlCoreElement).getPartyA());
            }
            toComboBox.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createTitledBorder("To"), 
                            BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED)));
            
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(toComboBox, gridBagConstraints);
        }
        
        
        
        //NOTE
        //applies to:
        //does NOT apply to:association, include
        if( ! Association.class.isInstance(umlCoreElement)
            &&
            ! Include.class.isInstance(umlCoreElement)
        ){
                    
            
            noteMultilineTextComponent.setUmlCoreElement(umlCoreElement);
            noteMultilineTextComponent.setBorder(BorderFactory.createTitledBorder("Note"));
            
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            
            //gridBagConstraints.gridheight = 5;
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(noteMultilineTextComponent, gridBagConstraints);
        }
            
        //EMBEDDED BIZ OBJECTS
        /*if( UseCase.class.isInstance(umlCoreElement)
            ||
            Actor.class.isInstance(umlCoreElement)
            ||
            Action.class.isInstance(umlCoreElement)
            ||
            Message.class.isInstance(umlCoreElement)
        ){*/
        if(BusinessObjectOwner.class.isInstance(umlCoreElement)){

            CoreObjectPanel tPanel = 
                    new CoreObjectPanel((BusinessObjectOwner)umlCoreElement);
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            
            //gridBagConstraints.gridheight = 5;
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(tPanel, gridBagConstraints);
            
        }
        
        if(ControllerOwner.class.isInstance(umlCoreElement)){

            CoreObjectPanel tPanel = 
                    new CoreObjectPanel((ControllerOwner)umlCoreElement );
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            
            //gridBagConstraints.gridheight = 5;
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(tPanel, gridBagConstraints);
            
        }
        
        //filler
        /*gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridy = yCounter;
        editPanel.add(new javax.swing.JPanel(), gridBagConstraints);
        this.firePropertyChange("form loaded", null, null);*/
    }
    
    
    
    private void loadProject(){
        setBorder(javax.swing.BorderFactory.createTitledBorder(project.getName()));
        editPanel.removeAll();
        propertyComponents.clear();
        
        java.awt.GridBagConstraints gridBagConstraints;
        int yCounter = 0;
        
        nameTextField.setText(project.getName());
        nameTextField.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createTitledBorder("Name"), 
                            BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED)));
            
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridy = yCounter;    yCounter++;
        editPanel.add(nameTextField, gridBagConstraints);
        propertyComponents.put("name" , nameTextField);
        
        if(project.getFolder() != null)
                    folderButton.setText("Folder: " + project.getFolder().getName());
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

        //name
        nameTextField.setText(umlDiagram.getName());
        nameTextField.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createTitledBorder("Name"), 
                            BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED)));
            
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridy = yCounter;    yCounter++;
        editPanel.add(nameTextField, gridBagConstraints);
        
        //lo detail
        //TODO levelOfDetail combo box        
        if(SequenceDiagram.class.isInstance(umlDiagram) ||
            ActivityDiagram.class.isInstance(umlDiagram)
        ){
            JComboBox controlCb = new JComboBox(new Object[]{"Free" , "Constrained"});
            controlCb.setBorder(BorderFactory.createCompoundBorder(
                            BorderFactory.createTitledBorder("Control Level"), 
                            BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED)));
            
            controlCb.addActionListener(new ActionListener(){                
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(controlCb.getSelectedIndex()==-1)    return;
                    umlDiagram.setControlLevel(controlCb.getSelectedIndex());
                }
            });
            controlCb.setSelectedIndex(umlDiagram.getControlLevel());
            
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(controlCb, gridBagConstraints);
        }
        
        
        //pckg
        if(UseCaseDiagram.class.isInstance(umlDiagram) ||
            SequenceDiagram.class.isInstance(umlDiagram) ||
            PackageDiagram.class.isInstance(umlDiagram)
        ){
            JList pckgList = new JList();
            DefaultListModel lm = new DefaultListModel();
            for(Iterator<moe.umlgui.model.Package> i = umlDiagram.getPackages().iterator() ; i.hasNext();){
                lm.addElement(i.next());            
            }
            pckgList.setModel(lm);

            JToolBar jToolBar1 = new JToolBar();
            jToolBar1.setOrientation(JToolBar.VERTICAL);

            JButton addButton = new JButton();
            addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/16x16/Add.png"))); // NOI18N
            addButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    moe.umlgui.model.Package parent = null;
                    if(pckgList.getSelectedIndex()!=-1){
                        parent = (moe.umlgui.model.Package)pckgList.getSelectedValue();            
                    }

                    String n = JOptionPane.showInputDialog("Name it");
                    if(n!=null && !n.isEmpty()){
                        moe.umlgui.model.Package p = new moe.umlgui.model.Package(parent);
                        p.setName(n);
                        umlDiagram.getPackages().add(p);
                        lm.removeAllElements();
                        for(Iterator<moe.umlgui.model.Package> i = umlDiagram.getPackages().iterator() ; i.hasNext();){
                            lm.addElement(i.next());            
                        }
                        pckgList.setSelectedValue(p,true);
                    }
                }
            });
            jToolBar1.add(addButton);

            JButton removeButton = new JButton();
            removeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/16x16/Remove.png"))); // NOI18N
            jToolBar1.add(removeButton);

            //TODO iLabel -> EditorPanw. get html from file
            JLabel iLabel = new JLabel();
            if(UseCaseDiagram.class.isInstance(umlDiagram)){
                iLabel.setText("Use packages in Use Case diagrams to group actors and use cases");
            }

            JPanel pnl = new JPanel();
            pnl.setLayout(new BorderLayout());
            pnl.setBorder(BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder("Packages"), 
                                BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED)));

            pnl.add(iLabel , BorderLayout.NORTH);
            pnl.add(new JScrollPane(pckgList) , BorderLayout.CENTER);
            pnl.add(jToolBar1, java.awt.BorderLayout.EAST);

            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;

            //gridBagConstraints.gridheight = 5;
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(pnl, gridBagConstraints);

        }
        
        //AcD: swimlanes
        if(ActivityDiagram.class.isInstance(umlDiagram)){
            JCheckBox cb1 = new JCheckBox("Use Swimlanes");
            cb1.setSelected(((ActivityDiagram)umlDiagram).isUseSwimlanes());
            cb1.addActionListener(new ActionListener(){                
                @Override
                public void actionPerformed(ActionEvent e) {
                    ((ActivityDiagram)umlDiagram).setUseSwimlanes(cb1.isSelected());
                }
            });
            
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(cb1, gridBagConstraints);
            
            JCheckBox cb2 = new JCheckBox("Swimlanes for actors");
            cb2.setSelected(((ActivityDiagram)umlDiagram).isAutoActorsForSwimlanes());
            cb2.addActionListener(new ActionListener(){                
                @Override
                public void actionPerformed(ActionEvent e) {
                    ((ActivityDiagram)umlDiagram).setAutoActorsForSwimlanes(cb2.isSelected());
                    if(cb2.isSelected() && !cb1.isSelected())   cb1.setSelected(true);
                }
            });
            
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(cb2, gridBagConstraints);
        
            //swimlanee
            JList swList = new JList(((ActivityDiagram)umlDiagram).getSwimlanes().keySet().toArray());

            JToolBar jToolBar1 = new JToolBar();
            jToolBar1.setOrientation(JToolBar.VERTICAL);

            JButton addButton = new JButton();
            addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/16x16/Add.png"))); // NOI18N
            addButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    String n = JOptionPane.showInputDialog("Name it");
                    if(n!=null && !n.isEmpty()){
                        ((DefaultListModel)swList.getModel()).addElement(n);
                        swList.setSelectedValue(n,true);
                    }
                }
            });
            jToolBar1.add(addButton);

            JButton removeButton = new JButton();
            removeButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/16x16/Remove.png"))); // NOI18N
            jToolBar1.add(removeButton);

            //TODO iLabel -> EditorPanw. get html from file
            JLabel iLabel = new JLabel();
            if(UseCaseDiagram.class.isInstance(umlDiagram)){
                iLabel.setText("...");
            }

            if(((ActivityDiagram)umlDiagram).isAutoActorsForSwimlanes()){
                addButton.setEnabled(false);    removeButton.setEnabled(false);
            }

            JPanel pnl = new JPanel();
            pnl.setLayout(new BorderLayout());
            pnl.setBorder(BorderFactory.createCompoundBorder(
                                BorderFactory.createTitledBorder("Swimlanes"), 
                                BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED)));

            pnl.add(iLabel , BorderLayout.NORTH);
            pnl.add(new JScrollPane(swList) , BorderLayout.CENTER);
            pnl.add(jToolBar1, java.awt.BorderLayout.EAST);

            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(pnl, gridBagConstraints);
            
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
    
    
    private void loadForm(){
        if(nowEditing == ELEMENT)   loadElement();
        else if(nowEditing == DIAGRAM)   loadDiagram();
        else if(nowEditing == PROJECT)   loadProject();
    }
        
    //TODO 
    HashMap<String,JComponent> propertyComponents = new HashMap();
    
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

        else if(UmlRelationship.class.isInstance(umlCoreElement) && umlCoreElement.getUmlDiagram() != null){
            ((UmlRelationship)umlCoreElement).setPartyA((UmlElement)fromComboBox.getSelectedItem());
            ((UmlRelationship)umlCoreElement).setPartyB((UmlElement)toComboBox.getSelectedItem());
        }

        else if(Message.class.isInstance(umlCoreElement) && umlCoreElement.getUmlDiagram() != null){
            ((Message)umlCoreElement).setFrom((Actor)fromComboBox.getSelectedItem());
            ((Message)umlCoreElement).setTo((Actor)toComboBox.getSelectedItem());
        }
        
        
        //while loop        
        else if(WhileLoop.class.isInstance(umlCoreElement)){
            for(int i=0 ; i<editPanel.getComponentCount() ; i++){
                if(LogicalTestComponent.class.isInstance(editPanel.getComponent(i))){
                    LogicalTestComponent comp = (LogicalTestComponent)editPanel.getComponent(i);
                    comp.save();
                }
            }
        }
        
        //repeat loop        
        else if(RepeatLoop.class.isInstance(umlCoreElement)){
            for(int i=0 ; i<editPanel.getComponentCount() ; i++){
                if(LogicalTestComponent.class.isInstance(editPanel.getComponent(i))){
                    LogicalTestComponent comp = (LogicalTestComponent)editPanel.getComponent(i);
                    comp.save();
                }
            }
        }
        
        if(ControlNode.class.isInstance(umlCoreElement)){
            ((ControlNode)umlCoreElement).setComplete(true);
        }
        
        ArrayList q =new ArrayList();
        q.add(this);
        this.firePropertyChange("Element updated", q, umlCoreElement);
    }
    
    
    private void saveDiagram(){
        umlDiagram.setName(nameTextField.getText());
        ArrayList q =new ArrayList();
        q.add(this);
        this.firePropertyChange("Diagram updated", q, umlCoreElement);
    }
    
    private void saveProject(){        
        project.setName(nameTextField.getText());
        ArrayList q =new ArrayList();
        q.add(this);
        this.firePropertyChange("Project updated", q, project);
    }
    
    public void save(){
        if(validateForm()){
            if(nowEditing == ELEMENT)   saveElement();       
            else if(nowEditing == DIAGRAM)   saveDiagram();
            else if(nowEditing == PROJECT)   saveProject();
        }
    }
    
    //TODO 
    //ActivityDiagram: useSwimlanes? -> all elements assigned swimlanes
    private boolean validateForm(){
        
        return true;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameTextField = new javax.swing.JTextField();
        fromComboBox = new javax.swing.JComboBox<>();
        toComboBox = new javax.swing.JComboBox<>();
        noteMultilineTextComponent = new moe.umlgui.ui.MultilineTextComponent();
        descriptionMultilineTextComponent = new moe.umlgui.ui.MultilineTextComponent();
        folderButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        editPanel = new javax.swing.JPanel();

        nameTextField.setText("jTextField1");

        folderButton.setText("Select folder");
        folderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                folderButtonActionPerformed(evt);
            }
        });

        setBorder(javax.swing.BorderFactory.createTitledBorder("Properties"));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLayout(new java.awt.BorderLayout());

        saveButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/24x24/media-floppy.png"))); // NOI18N
        saveButton.setText("Save");
        saveButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        saveButton.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
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
    private moe.umlgui.ui.MultilineTextComponent descriptionMultilineTextComponent;
    private javax.swing.JPanel editPanel;
    private javax.swing.JButton folderButton;
    private javax.swing.JComboBox<String> fromComboBox;
    private javax.swing.JTextField nameTextField;
    private moe.umlgui.ui.MultilineTextComponent noteMultilineTextComponent;
    private javax.swing.JButton saveButton;
    private javax.swing.JComboBox<String> toComboBox;
    // End of variables declaration//GEN-END:variables
}
