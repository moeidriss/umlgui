/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.controller;

import moe.umlgui.model.*;
import java.io.ByteArrayOutputStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import net.sourceforge.plantuml.SourceStringReader;
import org.w3c.dom.Document;


/**
 *
 * @author Moe
 */
public class PUMLDriver {
    
    
    
    
    public static void update(UmlDiagram umlDiagram) throws IOException{
        StringBuffer sb = new StringBuffer();
        sb.append("@startuml");
        sb.append("\n");
        
        //define elements
        for(Iterator<UmlCoreElement> i= umlDiagram.getCoreElementList().iterator(); i.hasNext();){
            UmlCoreElement el = i.next();
            el.setUmlCode(getCoreElementUmlCode(el));
            sb.append(el.getUmlCode());        
        }
        //TODO Note Element

        //TODO Package

        
        sb.append("@enduml");
        
        umlDiagram.setUmlCode(sb.toString());
        updateImage(umlDiagram);
    }
    
    
    
    public static void updateImage(UmlDiagram umlDiagram) throws IOException{        
        SourceStringReader reader = new SourceStringReader(umlDiagram.getUmlCode());
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        reader.outputImage(os);
        os.flush();os.close();
        umlDiagram.setImage(os.toByteArray());
    }
    
    
    /*
    UseCase
    Actor
    Actor
    CallActivity
    AcceptEvent
    AcceptTimeEvent
    SendSignal
    ActivityFinalNode
    FlowFinalNode
    ActivityInitialNode
    ConditionalBlock
    Association
    Include
    */
    
    public static String getCoreElementUmlCode(UmlCoreElement el){
        StringBuffer sb =  new StringBuffer();
        
        if(UseCase.class.isInstance(el)){
            sb.append("usecase ").append(el.getId()).append(" as \"").append(el.getName());
            if(((UseCase)el).getDescription() != null && !((UseCase)el).getDescription().isEmpty()){
                sb.append("\n--\n" + ((UseCase)el).getDescription()) ;
            }
            sb.append("\"");
            

            sb.append("\n");
        }

        else if(Actor.class.isInstance(el) ){            
            if(moe.umlgui.model.ItSystem.class.isInstance(el)){
                sb.append("participant ").append(el.getName().replace(" ", "")).append(" as ").append(el.getId());
                sb.append("\n");
            }
            else if(moe.umlgui.model.BusinessSystem.class.isInstance(el)){
                sb.append("participant ").append(el.getName().replace(" ", "")).append(" as ").append(el.getId());
                sb.append("\n");
            }
            else if(moe.umlgui.model.User.class.isInstance(el)){
                sb.append("participant ").append(el.getName().replace(" ", "")).append(" as ").append(el.getId());
                sb.append("\n");
            }    
            else if(moe.umlgui.model.System.class.isInstance(el)){
                sb.append("participant ").append(el.getName().replace(" ", "")).append(" as ").append(el.getId());
                sb.append("\n");
            }
            else{
                sb.append("actor ").append(el.getName().replace(" ", "")).append(" as ").append(el.getId());
                sb.append("\n");
            }
        }

         

        else if(Action.class.isInstance(el)){
            sb.append(":").append(el.getName()).append(";");

            sb.append("\n");
        }

        else if(CallActivity.class.isInstance(el)){
            sb.append(":").append(el.getName()).append(" <&vertical-align-top>;");

            sb.append("\n");
        }

        else if(AcceptEvent.class.isInstance(el)){
            sb.append(":").append(el.getName()).append("<");

            sb.append("\n");
        }

        else if(AcceptTimeEvent.class.isInstance(el)){
            sb.append(":").append(el.getName()).append(" <&timer> <");

            sb.append("\n");
        }

        else if(SendSignal.class.isInstance(el)){
            sb.append(":").append(el.getName()).append(">");

            sb.append("\n");
        }

        else if(ActivityFinalNode.class.isInstance(el)){
            sb.append("stop");

            sb.append("\n");
        }

        else if(FlowFinalNode.class.isInstance(el)){
            sb.append("end");

            sb.append("\n");
        }

        else if(ActivityInitialNode.class.isInstance(el)){
            sb.append("start");

            sb.append("\n");
        }


        else if(ConditionalBlock.class.isInstance(el)){
            ConditionalBlock cn = (ConditionalBlock)el;
            sb.append(getConditionalBlockUmlCode(cn));
        }

        else if(WhileLoop.class.isInstance(el)){
            WhileLoop cn = (WhileLoop)el;
            sb.append(getWhileLoopUmlCode(cn));
        }

        else if(RepeatLoop.class.isInstance(el)){
            RepeatLoop cn = (RepeatLoop)el;
            sb.append(getRepeatLoopUmlCode(cn));
        }

        else if(Split.class.isInstance(el)){
            Split cn = (Split)el;
            sb.append(getSplitUmlCode(cn));
        }

        else if(Fork.class.isInstance(el)){
            Fork cn = (Fork)el;
            sb.append(getForkUmlCode(cn));
        }

        
        else if(Association.class.isInstance(el) &&
            ((Association)el).getPartyA() != null &&
            ((Association)el).getPartyB() != null
        ){
            sb.append(((Association)el).getPartyA().getId());
            sb.append("--");
            sb.append(((Association)el).getPartyB().getId());

            sb.append("\n");
        }

        else if(Include.class.isInstance(el) &&
            ((Include)el).getPartyA() != null &&
            ((Include)el).getPartyB() != null
        ){
            sb.append(((Include)el).getPartyA().getId());
            sb.append("-->");
            sb.append(((Include)el).getPartyB().getId());
            sb.append(" : include");

            sb.append("\n");
        }

        else if(Message.class.isInstance(el) &&
            ((Message)el).getFrom() != null &&
            ((Message)el).getTo() != null
        ){
            sb.append(((Message)el).getFrom().getId());
            sb.append("->");
            sb.append(((Message)el).getTo().getId());
            sb.append(" : ").append(el.getName());

            if(!((Message)el).getBusinessObjects().isEmpty()){
                sb.append("(");
                for(Iterator i = ((Message)el).getBusinessObjects().iterator() ; i.hasNext();){
                    sb.append(i.next());
                    if(i.hasNext()) sb.append(" , ");
                }
                sb.append(")");
            }
            sb.append("\n");
        }

        else if(BusinessObject.class.isInstance(el)
        ){
            sb.append("entity ");
            sb.append(el.getName());

            sb.append("\n");
        }

        else if(Controller.class.isInstance(el)
        ){
            sb.append("control ");
            sb.append(el.getName());

            sb.append("\n");
        }

        //note
        //does NOT apply to:association, include
        if(el.getNote() != null && ! el.getNote().trim().isEmpty()){
            String pos = "left";
            if(el.getPosition() == UmlCoreElement.RIGHT){
                pos = "right";
            }
            else if(el.getPosition() == UmlCoreElement.UP){
                pos = "top";
            }
            else if(el.getPosition() == UmlCoreElement.DOWN){
                pos = "bottom";
            }

            sb.append(" note ").append(pos).append(" of ").append(el.getId()).append("\n");
            sb.append(el.getNote());sb.append("\n");
            sb.append("end note");
            sb.append("\n");
        }


        return sb.toString();
    }
    
    
    
    


        /*
        CASE#1
        if (<expression>) then (label)
        <action>
        else (label)
        <action>
        endif

        CASE#2
        if (opA) is/= (opB) then
        <action>
        else
        <action>

        CASE#3
        if (<expression>) then (label)
        <action>
        elseif (<expression>) then (label)
        <action>
        else (label)
        <action>
        endif

        */
    public static String getConditionalBlockUmlCode(ConditionalBlock cn){
        StringBuffer sb = new StringBuffer();
        
        for(ListIterator<LogicalTest> it = cn.getTestList().listIterator(); it.hasNext();){


            LogicalTest test = it.next();
            String term1 = test.getCondition().toLowerCase().replace(" ", "");
            ActivityFlow testActivityFlow = cn.getTestMap().get(test);

            sb.append(term1);

            //CASE#2 / 3
            if((term1.equals("if") || term1.equals("elseif"))
                    &&
                (test.getOperator().equals("IS") || test.getOperator().equals("="))
            ){
                sb.append(" (").append(test.getOperandA().getFqName()).append(") ");
                sb.append(test.getOperator().replace("=", "equals"));
                sb.append(" (").append(test.getOperandB()).append(") ");

                sb.append(" then\n");

                for(Iterator<UmlCoreElement> i = testActivityFlow.iterator() ;i.hasNext() ; ){
                    UmlCoreElement ac = i.next();
                    sb.append(getCoreElementUmlCode(ac));
                }
            }   
            //CASE#1 / 3
            else if((term1.equals("if") || term1.equals("elseif"))){
                sb.append(" (").append(test.getOperandA().getFqName()).append(" ").append(test.getOperator()).append(" ").append(test.getOperandB()).append(") ");
                sb.append(" then ");
                sb.append("(").append(test.getLabel()).append(")\n");

                for(Iterator<UmlCoreElement> i = testActivityFlow.iterator() ;i.hasNext() ; ){
                    UmlCoreElement ac = i.next();
                    sb.append(getCoreElementUmlCode(ac));
                }
            }


            //'else'
            else{
                sb.append(" (").append(test.getLabel()).append(")").append("\n");

                for(Iterator<UmlCoreElement> i = testActivityFlow.iterator() ;i.hasNext() ; ){
                    UmlCoreElement ac = i.next();
                    sb.append(getCoreElementUmlCode(ac));
                    sb.append("\n");
                }
            }


        }

        if(!sb.toString().isEmpty())    sb.append("endif\n");
        
        return sb.toString();
    }
    
    
    public static String getWhileLoopUmlCode(WhileLoop el){
        StringBuffer sb = new StringBuffer();
        
        if(el.getLogicalTest().getOperandA() != null &&
           el.getLogicalTest().getOperator() != null &&
           el.getLogicalTest().getOperandB() != null &&
           !el.getActivityFlow().isEmpty()
        ){
            sb.append("while ");
            if(el.getLogicalTest().getOperator().equals("IS") ||
                el.getLogicalTest().getOperator().equals("=")
            ){
                sb.append("(").append(el.getLogicalTest().getOperandA().getFqName()).append(") ");
                sb.append(el.getLogicalTest().getOperator().toLowerCase().replace("=", "equals")).append(" ");
                sb.append("(").append(el.getLogicalTest().getOperandB()).append(")");
                sb.append("\n");
            }
            else{
                sb.append("(").append(el.getLogicalTest().getOperandA().getFqName()).append(" ");
                sb.append(el.getLogicalTest().getOperator().toLowerCase().replace("=", "equals")).append(" ");
                sb.append(el.getLogicalTest().getOperandB()).append(")");
                sb.append("\n");
            }

            for(Iterator<UmlCoreElement> i = el.getActivityFlow().iterator() ;i.hasNext() ; ){
                UmlCoreElement ac = i.next();
                sb.append(getCoreElementUmlCode(ac));
            }
        }
        
        if(!sb.toString().isEmpty())    sb.append("endwhile\n");
        
        return sb.toString();
    }
 
    
    //TODO backward repeat, break
    public static String getRepeatLoopUmlCode(RepeatLoop el){
        StringBuffer sb = new StringBuffer();
        
        if(el.getLogicalTest().getOperandA() != null &&
           el.getLogicalTest().getOperator() != null &&
           el.getLogicalTest().getOperandB() != null &&
           !el.getActivityFlow().isEmpty()
        ){
            sb.append("repeat\n");

            for(Iterator<UmlCoreElement> i = el.getActivityFlow().iterator() ;i.hasNext() ; ){
                UmlCoreElement ac = i.next();
                sb.append(getCoreElementUmlCode(ac));
            }
            
            sb.append("repeat while ");
            
            if(el.getLogicalTest().getOperator().equals("IS") ||
                el.getLogicalTest().getOperator().equals("=")
            ){
                sb.append("(").append(el.getLogicalTest().getOperandA().getFqName()).append(") ");
                sb.append(el.getLogicalTest().getOperator().toLowerCase().replace("=", "equals")).append(" ");
                sb.append("(").append(el.getLogicalTest().getOperandB()).append(")");
                sb.append("\n");
            }
            else{
                sb.append("(").append(el.getLogicalTest().getOperandA().getFqName()).append(" ");
                sb.append(el.getLogicalTest().getOperator().toLowerCase().replace("=", "equals")).append(" ");
                sb.append(el.getLogicalTest().getOperandB()).append(")");
                sb.append("\n");
            }
        }
        
        return sb.toString();
    }
    
    //TODO if split is first diagram element -> hidden
    public static String getSplitUmlCode(Split el){
        StringBuffer sb = new StringBuffer();
        
        if(el.getActivityFlows().size() > 0){
            for(ListIterator<ActivityFlow> i = el.getActivityFlows().listIterator(); i.hasNext() ;){
                if(!i.hasPrevious())    sb.append("split\n");
                else    sb.append("split again\n");
                
                ActivityFlow f = i.next();
                for(Iterator<UmlCoreElement> ii = f.iterator() ;ii.hasNext() ; ){
                    UmlCoreElement ac = ii.next();
                    sb.append(getCoreElementUmlCode(ac));
                }
            }
            
            sb.append("end split\n");
        }
        
        return sb.toString();
    }
    
    public static String getForkUmlCode(Fork el){
        StringBuffer sb = new StringBuffer();
        
        if(el.getActivityFlows().size() > 0){
            for(ListIterator<ActivityFlow> i = el.getActivityFlows().listIterator(); i.hasNext() ;){
                if(!i.hasPrevious())    sb.append("fork\n");
                else    sb.append("fork again\n");
                
                ActivityFlow f = i.next();
                for(Iterator<UmlCoreElement> ii = f.iterator() ;ii.hasNext() ; ){
                    UmlCoreElement ac = ii.next();
                    sb.append(getCoreElementUmlCode(ac));
                }
            }
            
            sb.append("end fork\n");
        }
        
        return sb.toString();
    }
    
}
