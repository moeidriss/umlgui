/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package moe.umlgui.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import moe.umlgui.model.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
    
    Project context;
    
    /**
     * Creates new form PropertyEditor
     * @param context if null, context is global library
     */
    public PropertyEditor() {
        initComponents();
    }

    public void setContext(Project context){
        this.context = context;
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
    
    private Object[] getElementList(){
        ArrayList<Object> l = new ArrayList();
        l.addAll(context.getElementList());
        return (Object[])l.toArray();
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
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridy = yCounter;    yCounter++;
        editPanel.add(nameTextField, gridBagConstraints);


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
                gridBagConstraints.gridwidth = 2;
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
            LogicalTestPanel tPanel = 
                    new LogicalTestPanel((ConditionalBlock)umlCoreElement , context);
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagConstraints.gridwidth = 2;
            //gridBagConstraints.gridheight = 5;
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(tPanel, gridBagConstraints);
            
        }
        
        else if(WhileLoop.class.isInstance(umlCoreElement)){
            LogicalTestComponent tComp = 
                    new LogicalTestComponent(((WhileLoop)umlCoreElement).getLogicalTest(),context );
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagConstraints.gridwidth = 2;
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(tComp, gridBagConstraints);
            
            
            Activity ac = new Action();
            JComboBox cb = new JComboBox();
            cb.setModel(new DefaultComboBoxModel(context.getActivityList().toArray()));
            
            JButton newActivityButton = new JButton("New ...");
            newActivityButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    Object sel = JOptionPane.showInputDialog(null, "Select Actvity Type" , 
                            "Actvity Type", JOptionPane.INFORMATION_MESSAGE, null, 
                            new String[] {"Action","Flow End","Stop"}, "Action");
                    Activity ac = null;
                    if(sel.equals("Action")){
                        ac = new Action();
                    }
                    else if(sel.equals("Flow End")){
                        ac = new FlowFinalNode();
                    }
                    else if(sel.equals("Stop")){
                        ac = new ActivityFinalNode();
                    }                

                    if(ac!=null){
                        editNewActivity(ac,cb);
                    }
                }            
            });

            JPanel pp = new JPanel();
            pp.add(new JLabel("Action: "));
            pp.add(cb);
            pp.add(newActivityButton);
            
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagConstraints.gridwidth = 2;
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(pp, gridBagConstraints);

        }
        
        
        else if(RepeatLoop.class.isInstance(umlCoreElement)){
            Activity ac = new Action();
            JComboBox cb = new JComboBox();
            cb.setModel(new DefaultComboBoxModel(context.getActivityList().toArray()));
            
            JButton newActivityButton = new JButton("New ...");
            newActivityButton.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    Object sel = JOptionPane.showInputDialog(null, "Select Actvity Type" , 
                            "Actvity Type", JOptionPane.INFORMATION_MESSAGE, null, 
                            new String[] {"Action","Flow End","Stop"}, "Action");
                    Activity ac = null;
                    if(sel.equals("Action")){
                        ac = new Action();
                    }
                    else if(sel.equals("Flow End")){
                        ac = new FlowFinalNode();
                    }
                    else if(sel.equals("Stop")){
                        ac = new ActivityFinalNode();
                    }                

                    if(ac!=null){
                        editNewActivity(ac,cb);
                    }
                }            
            });

            JPanel pp = new JPanel();
            pp.add(new JLabel("Action: "));
            pp.add(cb);
            pp.add(newActivityButton);
            
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagConstraints.gridwidth = 2;
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(pp, gridBagConstraints);

            LogicalTestComponent tComp = 
                    new LogicalTestComponent(((RepeatLoop)umlCoreElement).getLogicalTest() ,context);
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagConstraints.gridwidth = 2;
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(tComp, gridBagConstraints);
        }
        
        else if(Message.class.isInstance(umlCoreElement)){
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints.gridy = yCounter;
            editPanel.add(fromLabel, gridBagConstraints);

            fromComboBox.setModel(new DefaultComboBoxModel(getElementList()));
            if(((Message)umlCoreElement).getFrom() != null){
                fromComboBox.setSelectedItem(((Message)umlCoreElement).getFrom());
            }
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(fromComboBox, gridBagConstraints);

            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints.gridy = yCounter;
            editPanel.add(toLabel, gridBagConstraints);

            toComboBox.setModel(new DefaultComboBoxModel(getElementList()));
            if(((Message)umlCoreElement).getTo() != null){
                toComboBox.setSelectedItem(((Message)umlCoreElement).getTo());
            }
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(toComboBox, gridBagConstraints);
        }
        
        //TODO Note Element

        //TODO Package

        else if(Association.class.isInstance(umlCoreElement)){
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints.gridy = yCounter;
            editPanel.add(fromLabel, gridBagConstraints);

            fromComboBox.setModel(new DefaultComboBoxModel(getElementList()));
            if(((Association)umlCoreElement).getPartyA() != null){
                fromComboBox.setSelectedItem(((Association)umlCoreElement).getPartyA());
            }
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(fromComboBox, gridBagConstraints);

            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints.gridy = yCounter;
            editPanel.add(toLabel, gridBagConstraints);

            toComboBox.setModel(new DefaultComboBoxModel(getElementList()));
            if(((Association)umlCoreElement).getPartyB() != null){
                toComboBox.setSelectedItem(((Association)umlCoreElement).getPartyA());
            }
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(toComboBox, gridBagConstraints);
        }

        else if(Include.class.isInstance(umlCoreElement)){
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints.gridy = yCounter;
            editPanel.add(fromLabel, gridBagConstraints);

            fromComboBox.setModel(new DefaultComboBoxModel(getElementList()));
            if(((Include)umlCoreElement).getPartyA() != null){
                fromComboBox.setSelectedItem(((Include)umlCoreElement).getPartyA());
            }
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.gridx = 1;
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(fromComboBox, gridBagConstraints);

            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
            gridBagConstraints.gridy = yCounter;
            editPanel.add(toLabel, gridBagConstraints);

            toComboBox.setModel(new DefaultComboBoxModel(getElementList()));
            if(((Include)umlCoreElement).getPartyB() != null){
                toComboBox.setSelectedItem(((Include)umlCoreElement).getPartyA());
            }
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(toComboBox, gridBagConstraints);
        }
        
        else if(BusinessObject.class.isInstance(umlCoreElement)){
            JList propertyList = new JList(new DefaultListModel());
            //((DefaultListModel)propertyList.getModel()).
            //((BusinessObject)umlCoreElement).getProperties())
            JList methodList = new JList(new DefaultListModel());
            
            JToolBar propToolBar = new javax.swing.JToolBar();
            JButton addPropertyButton = new javax.swing.JButton();
            JButton deletePropertyButton = new javax.swing.JButton();
            
            JToolBar methToolBar = new javax.swing.JToolBar();
            JButton addMethodButton = new javax.swing.JButton();
            JButton deleteMethodButton = new javax.swing.JButton();
        
            ActionListener addPropertyActionListner = new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    BusinessObjectProperty prop = new BusinessObjectProperty((BusinessObject)umlCoreElement);
                    BusinessObjectPropertyComponent pComp = new BusinessObjectPropertyComponent(prop);

                    JDialog pD = new JDialog();
                    JButton pB = new JButton("OK");
                    pB.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            pComp.save();
                            ((BusinessObject)umlCoreElement).getProperties().add(prop);
                            ((DefaultListModel)propertyList.getModel()).addElement(prop);
                            pD.setVisible(false);
                        }                    
                    });

                    pD.getContentPane().add(pComp , BorderLayout.CENTER);
                    pD.getContentPane().add(pB , BorderLayout.SOUTH);
                    pD.pack();
                    pD.setLocationRelativeTo(null);
                    pD.setVisible(true);
                }            
            };
            addPropertyButton.addActionListener(addPropertyActionListner);


            ActionListener addMethodActionListner = new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    BusinessObjectMethod meth = new BusinessObjectMethod((BusinessObject)umlCoreElement);
                    BusinessObjectMethodComponent mComp = new BusinessObjectMethodComponent(meth);

                    JDialog pD = new JDialog();
                    JButton pB = new JButton("OK");
                    pB.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            mComp.save();
                            ((BusinessObject)umlCoreElement).getMethods().add(meth);
                            ((DefaultListModel)methodList.getModel()).addElement(meth);
                            pD.setVisible(false);
                        }                    
                    });

                    pD.getContentPane().add(mComp , BorderLayout.CENTER);
                    pD.getContentPane().add(pB , BorderLayout.SOUTH);
                    pD.pack();
                    pD.setLocationRelativeTo(null);
                    pD.setVisible(true);
                }            
            };
            addMethodButton.addActionListener(addMethodActionListner);

            propToolBar.add(addPropertyButton);
            propToolBar.add(deletePropertyButton);
            methToolBar.add(addMethodButton);
            methToolBar.add(deleteMethodButton);
            
            addPropertyButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/16x16/Add.png")));
            deletePropertyButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/16x16/Remove.png")));
            addMethodButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/16x16/Add.png")));
            deleteMethodButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/16x16/Remove.png")));
            
            JPanel pPanel =  new JPanel();
            pPanel.setLayout(new BorderLayout());
            pPanel.setBorder(BorderFactory.createTitledBorder("Properties"));
            pPanel.add(propToolBar , BorderLayout.NORTH);
            pPanel.add(new JScrollPane(propertyList) , BorderLayout.CENTER);
                        
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagConstraints.gridwidth = 2;
            //gridBagConstraints.gridheight = 5;
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(pPanel, gridBagConstraints);
            
            JPanel mPanel =  new JPanel();
            mPanel.setLayout(new BorderLayout());
            mPanel.setBorder(BorderFactory.createTitledBorder("Methods"));
            mPanel.add(methToolBar , BorderLayout.NORTH);
            mPanel.add(new JScrollPane(methodList) , BorderLayout.CENTER);
            
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagConstraints.gridwidth = 2;
            //gridBagConstraints.gridheight = 5;
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(mPanel, gridBagConstraints);
            
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
            gridBagConstraints.gridwidth = 2;
            //gridBagConstraints.gridheight = 5;
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(noteMultilineTextComponent, gridBagConstraints);
        }
            
        //EMBEDDED BIZ OBJECTS
        if( UseCase.class.isInstance(umlCoreElement)
            ||
            Actor.class.isInstance(umlCoreElement)
            ||
            Action.class.isInstance(umlCoreElement)
            ||
            Message.class.isInstance(umlCoreElement)
        ){
            

            BusinessObjectPanel tPanel = 
                    new BusinessObjectPanel((BusinessObjectOwner)umlCoreElement , context);
            gridBagConstraints = new java.awt.GridBagConstraints();
            gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
            gridBagConstraints.weightx = 1.0;
            gridBagConstraints.weighty = 1.0;
            gridBagConstraints.gridwidth = 2;
            //gridBagConstraints.gridheight = 5;
            gridBagConstraints.gridy = yCounter;    yCounter++;
            editPanel.add(tPanel, gridBagConstraints);
            
        }
        
        //filler
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.gridy = yCounter;
        editPanel.add(new javax.swing.JPanel(), gridBagConstraints);
        this.firePropertyChange("form loaded", null, null);
    }
    
    
    
    private void loadProject(){
        setBorder(javax.swing.BorderFactory.createTitledBorder(project.getName()));
        editPanel.removeAll();
        
        java.awt.GridBagConstraints gridBagConstraints;
        int yCounter = 0;
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.gridy = yCounter;
        editPanel.add(nameLabel, gridBagConstraints);

        nameTextField.setText(project.getName());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridy = yCounter;    yCounter++;
        editPanel.add(nameTextField, gridBagConstraints);
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.gridy = yCounter;
        editPanel.add(folderLabel, gridBagConstraints);

        if(project.getFolder() != null)
                    folderButton.setText(project.getFolder().getName());
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
        
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.gridy = yCounter;
        editPanel.add(nameLabel, gridBagConstraints);

        nameTextField.setText(umlDiagram.getName());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridy = yCounter;    yCounter++;
        editPanel.add(nameTextField, gridBagConstraints);
        
        
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
    
    //TODO find a better solution for initializing, loading and persisting
    //from components, see WhileLoop
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
                else if(JPanel.class.isInstance(editPanel.getComponent(i))){
                    for(int j=0 ; j <((JPanel)editPanel.getComponent(i)).getComponentCount() ; j++){
                        if(JComboBox.class.isInstance(((JPanel)editPanel.getComponent(i)).getComponent(j))){
                            JComboBox cb = (JComboBox)((JPanel)editPanel.getComponent(i)).getComponent(j);
                            ((WhileLoop)umlCoreElement).getActivityFlow().add((Activity)cb.getSelectedItem());
                        }
                    }
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
                else if(JPanel.class.isInstance(editPanel.getComponent(i))){
                    for(int j=0 ; j <((JPanel)editPanel.getComponent(i)).getComponentCount() ; j++){
                        if(JComboBox.class.isInstance(((JPanel)editPanel.getComponent(i)).getComponent(j))){
                            JComboBox cb = (JComboBox)((JPanel)editPanel.getComponent(i)).getComponent(j);
                            ((RepeatLoop)umlCoreElement).getActivityFlow().add((Activity)cb.getSelectedItem());
                        }
                    }
                }
            }
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
        if(nowEditing == ELEMENT)   saveElement();       
        else if(nowEditing == DIAGRAM)   saveDiagram();
        else if(nowEditing == PROJECT)   saveProject();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameLabel = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        fromLabel = new javax.swing.JLabel();
        fromComboBox = new javax.swing.JComboBox<>();
        toLabel = new javax.swing.JLabel();
        toComboBox = new javax.swing.JComboBox<>();
        noteLabel = new javax.swing.JLabel();
        descriptionLabel = new javax.swing.JLabel();
        noteMultilineTextComponent = new moe.umlgui.ui.MultilineTextComponent();
        descriptionMultilineTextComponent = new moe.umlgui.ui.MultilineTextComponent();
        folderLabel = new javax.swing.JLabel();
        folderButton = new javax.swing.JButton();
        saveButton = new javax.swing.JButton();
        editPanel = new javax.swing.JPanel();

        nameLabel.setText("Name");

        nameTextField.setText("jTextField1");

        fromLabel.setText("From");

        toLabel.setText("To");

        noteLabel.setText("Note");

        descriptionLabel.setText("Description");

        folderLabel.setText("Folder");

        folderButton.setText("Select folder");
        folderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                folderButtonActionPerformed(evt);
            }
        });

        setBorder(javax.swing.BorderFactory.createTitledBorder("Properties"));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLayout(new java.awt.BorderLayout());

        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/moe/umlgui/img/24x24/media-floppy.png"))); // NOI18N
        saveButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        saveButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
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

    
    
    private void editNewActivity(Activity ac ,JComboBox cb ){
        PropertyEditor pe = new PropertyEditor();
        pe.setContext(context);
        pe.edit(ac);
        pe.showToolbar(false);
        
        JDialog acDialog = new JDialog();
        acDialog.getContentPane().add(pe , BorderLayout.CENTER);
        
        JPanel buttonsP = new JPanel();
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                pe.save();
                context.addCoreElement(ac);
                cb.setModel(new DefaultComboBoxModel(context.getActivityList().toArray()));
                cb.setSelectedItem(ac);
                cb.revalidate();
                acDialog.setVisible(false);
            }            
        });
        buttonsP.add(okButton);
        
        acDialog.getContentPane().add(buttonsP , BorderLayout.SOUTH);
        acDialog.pack();
        acDialog.setSize(600 , acDialog.getSize().height);
        acDialog.setLocationRelativeTo(null);
        
        acDialog.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel descriptionLabel;
    private moe.umlgui.ui.MultilineTextComponent descriptionMultilineTextComponent;
    private javax.swing.JPanel editPanel;
    private javax.swing.JButton folderButton;
    private javax.swing.JLabel folderLabel;
    private javax.swing.JComboBox<String> fromComboBox;
    private javax.swing.JLabel fromLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JLabel noteLabel;
    private moe.umlgui.ui.MultilineTextComponent noteMultilineTextComponent;
    private javax.swing.JButton saveButton;
    private javax.swing.JComboBox<String> toComboBox;
    private javax.swing.JLabel toLabel;
    // End of variables declaration//GEN-END:variables
}
