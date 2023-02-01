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
        
        //definitions
        sb.append(getDefinitions(umlDiagram));
        
        //interactins
        sb.append(getInteractions(umlDiagram));
        
        sb.append("@enduml");
        
        umlDiagram.setUmlCode(sb.toString());
        updateImage(umlDiagram);
    }
    
    
    
    public static String getDefinitions(UmlDiagram umlDiagram){
        StringBuffer sb = new StringBuffer();
        
        if(UseCaseDiagram.class.isInstance(umlDiagram) && 
                                    !umlDiagram.getPackages().isEmpty()){    
            ArrayList processed = new ArrayList();
            for(Iterator<moe.umlgui.model.Package> i =umlDiagram.getPackages().iterator() ; i.hasNext() ; ){
                sb.append(getPackageDefinition(i.next() , processed));
            }            
        }
        else{
            for(Iterator<UmlCoreElement> i= umlDiagram.getCoreElementList().iterator(); i.hasNext();){
                UmlCoreElement el = i.next();
                el.setUmlCode(getElementDefinition(el));
                sb.append(el.getUmlCode());        
            }
        }
        
        return sb.toString();
    }
    
    
    public static String getInteractions(UmlDiagram umlDiagram){
        StringBuffer sb =  new StringBuffer();
        
        if(SequenceDiagram.class.isInstance(umlDiagram) && 
                                    !umlDiagram.getPackages().isEmpty()){    
            ArrayList processed = new ArrayList();
            
            for(int i=0 ; i<umlDiagram.getCoreElementList().size(); i++){
                UmlCoreElement el = umlDiagram.getCoreElementList().get(i);
                //if(processed.contains(el))  continue;
                
                //if pckg exists
                if(el.getPckage()!=null){
                    UmlCoreElement nxl = null;
                    if(i<(umlDiagram.getCoreElementList().size()-1)) nxl = umlDiagram.getCoreElementList().get(i+1);
                    //el.setUmlCode
                    sb.append(getPackagedMessageInteraction(el,nxl,processed));
                }
                else{
                    el.setUmlCode(getInteractionDefinition(el));
                    sb.append(el.getUmlCode());        
                }
            }
        }
        else{
            for(Iterator<UmlCoreElement> i= umlDiagram.getCoreElementList().iterator(); i.hasNext();){
                UmlCoreElement el = i.next();
                el.setUmlCode(getInteractionDefinition(el));
                sb.append(el.getUmlCode());        
            }
        }
        
        return sb.toString();
    }
    
    
    
    public static String getPackageDefinition(moe.umlgui.model.Package o , ArrayList processed){
        StringBuffer sb =  new StringBuffer();
        if(!processed.contains(o)){
            sb.append("package ").append(o.getName()).append("{\n");

            for(Iterator<moe.umlgui.model.Package> i = o.getChildren().iterator() ; i.hasNext() ; ){
                sb.append(getPackageDefinition(i.next() , processed));
            }

            for(Iterator<UmlCoreElement> i= o.getCoreElements().iterator(); i.hasNext();){
                UmlCoreElement el = i.next();
                el.setUmlCode(getElementDefinition(el));
                sb.append(el.getUmlCode());  
            }
            sb.append("}\n");
            processed.add(o);
        }
        return sb.toString();
    }
    
    
    
    public static String getElementDefinition(UmlCoreElement el){
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
            if(((ActivityDiagram)el.getUmlDiagram()).isUseSwimlanes()){
                sb.append("|").append(((ActivityDiagram)el.getUmlDiagram()).getSwimlane(el)).append("|").append("\n");
            }
            
            sb.append(":").append(el.getName()).append(";");

            sb.append("\n");
        }

        else if(CallActivity.class.isInstance(el)){
            if(((ActivityDiagram)el.getUmlDiagram()).isUseSwimlanes()){
                sb.append("|").append(((ActivityDiagram)el.getUmlDiagram()).getSwimlane(el)).append("|").append("\n");
            }
            
            sb.append(":").append(el.getName()).append(" <&vertical-align-top>;");

            sb.append("\n");
        }

        else if(AcceptEvent.class.isInstance(el)){
            if(((ActivityDiagram)el.getUmlDiagram()).isUseSwimlanes()){
                sb.append("|").append(((ActivityDiagram)el.getUmlDiagram()).getSwimlane(el)).append("|").append("\n");
            }
            
            sb.append(":").append(el.getName()).append("<");

            sb.append("\n");
        }

        else if(AcceptTimeEvent.class.isInstance(el)){
            if(((ActivityDiagram)el.getUmlDiagram()).isUseSwimlanes()){
                sb.append("|").append(((ActivityDiagram)el.getUmlDiagram()).getSwimlane(el)).append("|").append("\n");
            }
            
            sb.append(":").append(el.getName()).append(" <&timer> <");

            sb.append("\n");
        }

        else if(SendSignal.class.isInstance(el)){
            if(((ActivityDiagram)el.getUmlDiagram()).isUseSwimlanes()){
                sb.append("|").append(((ActivityDiagram)el.getUmlDiagram()).getSwimlane(el)).append("|").append("\n");
            }
            
            sb.append(":").append(el.getName()).append(">");

            sb.append("\n");
        }

        else if(ActivityFinalNode.class.isInstance(el)){
            if(((ActivityDiagram)el.getUmlDiagram()).isUseSwimlanes()){
                sb.append("|").append(((ActivityDiagram)el.getUmlDiagram()).getSwimlane(el)).append("|").append("\n");
            }
            
            sb.append("stop");

            sb.append("\n");
        }

        else if(FlowFinalNode.class.isInstance(el)){
            if(((ActivityDiagram)el.getUmlDiagram()).isUseSwimlanes()){
                sb.append("|").append(((ActivityDiagram)el.getUmlDiagram()).getSwimlane(el)).append("|").append("\n");
            }
            
            sb.append("end");

            sb.append("\n");
        }

        else if(ActivityInitialNode.class.isInstance(el)){
            if(((ActivityDiagram)el.getUmlDiagram()).isUseSwimlanes()){
                sb.append("|").append(((ActivityDiagram)el.getUmlDiagram()).getSwimlane(el)).append("|").append("\n");
            }
            
            sb.append("start");

            sb.append("\n");
        }


        else if(ConditionalBlock.class.isInstance(el)){
            ConditionalBlock cn = (ConditionalBlock)el;
            sb.append(getConditionalBlockDefinition(cn));
        }

        else if(WhileLoop.class.isInstance(el)){
            WhileLoop cn = (WhileLoop)el;
            sb.append(getWhileLoopDefinition(cn));
        }

        else if(RepeatLoop.class.isInstance(el)){
            RepeatLoop cn = (RepeatLoop)el;
            sb.append(getRepeatDefinition(cn));
        }

        else if(Split.class.isInstance(el)){
            Split cn = (Split)el;
            sb.append(getSplitDefinition(cn));
        }

        else if(Fork.class.isInstance(el)){
            Fork cn = (Fork)el;
            sb.append(getForkDefinition(cn));
        }

        
        

        else if(BusinessObject.class.isInstance(el)
        ){
            sb.append("entity ");
            sb.append(el.getName().replace(" ", ""));

            sb.append("\n");
        }

        else if(Controller.class.isInstance(el)
        ){
            sb.append("control ");
            sb.append(el.getName().replace(" ", ""));

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
    
    
    public static String getPackagedMessageInteraction(UmlCoreElement el , UmlCoreElement nxtEl , ArrayList processed){
        StringBuffer sb =  new StringBuffer();
        if(processed.contains(el))  return sb.toString();
        
        if(Message.class.isInstance(el) &&
            ((Message)el).getFrom() != null &&
            ((Message)el).getTo() != null
        ){  
            //start pcge
            
            java.lang.System.out.println("PUML:getPackagedMessageInteraction");
            java.lang.System.out.println("el:" + el);
            java.lang.System.out.println("pack:" + el.getPckage());
            java.lang.System.out.println("nxtEl:" + nxtEl);
            if(nxtEl!=null) java.lang.System.out.println("nxtEl pack:" + nxtEl.getPckage());
            java.lang.System.out.print(el + "::" + el.getPckage().getCoreElements().indexOf(el));
            java.lang.System.out.print("/");
            java.lang.System.out.println(el.getPckage().getCoreElements().size() + " in " +  el.getPckage() );
            java.lang.System.out.println();

            
            processed.add(el);   
        
            //if first msg in group
            if(el.getPckage().getCoreElements().indexOf(el)==0){
                sb.append("group ").append(el.getPckage().getName()).append("\n");
            }


            sb.append(getInteractionDefinition(el));
            
            boolean nested = false; boolean nestingDone = false;
            
            if(!el.getPckage().getChildren().isEmpty()) nested = true;
            if(nested && nxtEl==null)   nestingDone=true;
            else if(nested && nxtEl.getPckage()==null)   nestingDone=true;
            else if(nested && nxtEl!=null &&
                    el.getPckage().getChildren().contains(nxtEl.getPckage())){   
                moe.umlgui.model.Package chPkg = nxtEl.getPckage();
                for(int i=0 ; i<chPkg.getCoreElements().size() ; i++){
                    UmlCoreElement nl = null; 
                    if(i<(chPkg.getCoreElements().size()-1))
                            nl = chPkg.getCoreElements().get(i+1);
                    sb.append(getPackagedMessageInteraction(chPkg.getCoreElements().get(i),nl, processed ));
                }                        
                
                nestingDone=true;
            }
            
            
            //end only if last message in package & child packges done
            if(el.getPckage().getCoreElements().indexOf(el)==(el.getPckage().getCoreElements().size()-1)
                    &&
                (!nested || nestingDone)
            ){
                sb.append("end\n");
            }

        }
             
        return sb.toString();
    }
    
    
    public static String getInteractionDefinition(UmlCoreElement el){
        StringBuffer sb =  new StringBuffer();
        
        if(Association.class.isInstance(el) &&
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
        
        //TODO notes on interactions
        
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
    public static String getConditionalBlockDefinition(ConditionalBlock cn){
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
                    sb.append(getElementDefinition(ac));
                }
            }   
            //CASE#1 / 3
            else if((term1.equals("if") || term1.equals("elseif"))){
                sb.append(" (").append(test.getOperandA().getFqName()).append(" ").append(test.getOperator()).append(" ").append(test.getOperandB()).append(") ");
                sb.append(" then ");
                sb.append("(").append(test.getLabel()).append(")\n");

                for(Iterator<UmlCoreElement> i = testActivityFlow.iterator() ;i.hasNext() ; ){
                    UmlCoreElement ac = i.next();
                    sb.append(getElementDefinition(ac));
                }
            }


            //'else'
            else{
                sb.append(" (").append(test.getLabel()).append(")").append("\n");

                for(Iterator<UmlCoreElement> i = testActivityFlow.iterator() ;i.hasNext() ; ){
                    UmlCoreElement ac = i.next();
                    sb.append(getElementDefinition(ac));
                    sb.append("\n");
                }
            }


        }

        if(!sb.toString().isEmpty())    sb.append("endif\n");
        
        return sb.toString();
    }
    
    
    public static String getWhileLoopDefinition(WhileLoop el){
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
                sb.append(getElementDefinition(ac));
            }
        }
        
        if(!sb.toString().isEmpty())    sb.append("endwhile\n");
        
        return sb.toString();
    }
 
    
    //TODO backward repeat, break
    public static String getRepeatDefinition(RepeatLoop el){
        StringBuffer sb = new StringBuffer();
        
        if(el.getLogicalTest().getOperandA() != null &&
           el.getLogicalTest().getOperator() != null &&
           el.getLogicalTest().getOperandB() != null &&
           !el.getActivityFlow().isEmpty()
        ){
            sb.append("repeat\n");

            for(Iterator<UmlCoreElement> i = el.getActivityFlow().iterator() ;i.hasNext() ; ){
                UmlCoreElement ac = i.next();
                sb.append(getElementDefinition(ac));
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
    public static String getSplitDefinition(Split el){
        StringBuffer sb = new StringBuffer();
        
        if(el.getActivityFlows().size() > 0){
            for(ListIterator<ActivityFlow> i = el.getActivityFlows().listIterator(); i.hasNext() ;){
                if(!i.hasPrevious())    sb.append("split\n");
                else    sb.append("split again\n");
                
                ActivityFlow f = i.next();
                for(Iterator<UmlCoreElement> ii = f.iterator() ;ii.hasNext() ; ){
                    UmlCoreElement ac = ii.next();
                    sb.append(getElementDefinition(ac));
                }
            }
            
            sb.append("end split\n");
        }
        
        return sb.toString();
    }
    
    public static String getForkDefinition(Fork el){
        StringBuffer sb = new StringBuffer();
        
        if(el.getActivityFlows().size() > 0){
            for(ListIterator<ActivityFlow> i = el.getActivityFlows().listIterator(); i.hasNext() ;){
                if(!i.hasPrevious())    sb.append("fork\n");
                else    sb.append("fork again\n");
                
                ActivityFlow f = i.next();
                for(Iterator<UmlCoreElement> ii = f.iterator() ;ii.hasNext() ; ){
                    UmlCoreElement ac = ii.next();
                    sb.append(getElementDefinition(ac));
                }
            }
            
            sb.append("end fork\n");
        }
        
        return sb.toString();
    }
    
    
    
    
    public static void updateImage(UmlDiagram umlDiagram) throws IOException{        
        SourceStringReader reader = new SourceStringReader(umlDiagram.getUmlCode());
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        reader.outputImage(os);
        os.flush();os.close();
        umlDiagram.setImage(os.toByteArray());
    }
    
    
    
    
}
