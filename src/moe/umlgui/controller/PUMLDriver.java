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
            
            if(UseCase.class.isInstance(el)){
                String desc = "";
                if(((UseCase)el).getDescription() != null){
                    desc = "\n--\n" + ((UseCase)el).getDescription() ;
                }
                sb.append("usecase ").append(el.getId()).append(" as \"").append(el.getName()).append(desc).append("\"");
                
                sb.append("\n");
            }
            
            else if(Actor.class.isInstance(el)){
                sb.append(":").append(el.getName()).append(": as ").append(el.getId());
                
                sb.append("\n");
            }
            
            else if(Action.class.isInstance(el)){
                sb.append(":").append(el.getName()).append(";");
                
                sb.append("\n");
            }
            
            else if(CallActivity.class.isInstance(el)){
                sb.append(":").append(el.getName()).append(" <&vertical-align-top>;");
                
                sb.append("\n");
            }
            
            else if(X.class.isInstance(el)){
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
            
           
        

            
            if(ConditionalNode.class.isInstance(el)){
                ConditionalNode cn = (ConditionalNode)el;
                
                for(ListIterator<LogicalTest> it = cn.getTestList().listIterator(); it.hasNext();){
                    String term1 = null;
                    if( !it.hasPrevious())  term1 = "if ";
                    else if(cn.getTestList().size() > 2 && it.nextIndex() < (cn.getTestList().size()-1))
                        term1 = "elseif ";
                    else    term1 = "else ";
                    
                    sb.append(term1);
                    
                    LogicalTest test = it.next();
                    
                    //if's and elseif's
                    if(it.hasNext()){
                        //equals / is:if (opA) is/equals (opB) then (act)
                        //all else:if (opA op opB) then (act)
                        if(test.getOperator().equals("IS")
                                ||
                            test.getOperator().equals("=")
                        ){
                            sb.append("(").append(test.getOperandA()).append(") ");
                            sb.append(test.getOperator().replace("=", "equals"));
                            sb.append(" (").append(test.getOperandB()).append(") ");
                            
                            sb.append(" then\n");
                            sb.append(":").append(cn.getTestMap().get(test)).append(";");//TODO customize box per activity subclass
                        }   
                        else{
                            sb.append("(").append(test.getOperandA()).append(" ").append(test.getOperator()).append(" ").append(test.getOperandB()).append(") ");
                            sb.append(" then ");
                            sb.append("(").append(cn.getTestMap().get(test)).append(")\n");
                            sb.append(":").append(cn.getTestMap().get(test)).append(";");//TODO customize box per activity subclass
                        }
                    }
                    
                    //'else'
                    else{
                        sb.append("\n").append(":").append(cn.getTestMap().get(test)).append(";");
                    }
                    
                    
                    sb.append("\n");
                }
            }
            
            
                
            
            //note
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
        
        }
        //TODO Note Element

        //TODO Package


        //associations messages
        for(Iterator<UmlRelationship> i= umlDiagram.getRelationshipList().iterator(); i.hasNext();){
            UmlRelationship el = i.next();
            
            if(Association.class.isInstance(el) &&
                ((Association)el).getPartyA() != null &&
                ((Association)el).getPartyB() != null
            ){
                sb.append(((Association)el).getPartyA().getId());
                sb.append("--");
                sb.append(((Association)el).getPartyB().getId());
                
                sb.append("\n");
            }
            
            if(Include.class.isInstance(el) &&
                ((Include)el).getPartyA() != null &&
                ((Include)el).getPartyB() != null
            ){
                sb.append(((Include)el).getPartyA().getId());
                sb.append("-->");
                sb.append(((Include)el).getPartyB().getId());
                sb.append(" : include");
                
                sb.append("\n");
            }
                
            //note
            //applies to:
            //does NOT apply to:association, include
            /*if(el.getNote() != null && ! el.getNote().trim().isEmpty()){
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
                
                sb.append(" note ").append(pos).append("\n");
                sb.append(el.getNote());sb.append("\n");
                sb.append("end note");
                sb.append("\n");
            }
            */
        }
        
        
        sb.append("@enduml");
        
        SourceStringReader reader = new SourceStringReader(sb.toString());
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        reader.outputImage(os);
        os.flush();os.close();
        umlDiagram.setImage(os.toByteArray());
        
        System.out.println(sb.toString());
        System.out.println(os.toByteArray());
    }
    
    
    public static void delete(UmlDiagram umlDiagram){
        
    }
    
    
    
}
