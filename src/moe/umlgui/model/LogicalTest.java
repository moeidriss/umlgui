/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

/**
 *
 * @author Moe
 */

public class LogicalTest  implements java.io.Serializable{

    public static String[] OPERATORS = {null,"=" , "!=" , ">" 
                                , "<" , "IS" , "IS NOT"};


    String condition;//NA for while loop
    CoreObjectProperty operandA ;
    String operandB;
    String operator;
    String label;

    public LogicalTest(){

    }


    public LogicalTest(CoreObjectProperty operandA, String operandB, String operator){
        this.operandA = operandA;
        this.operandB = operandB;
        this.operator = operator;
    }


    public CoreObjectProperty getOperandA() {
        return operandA;
    }

    public void setOperandA(CoreObjectProperty operandA) {
        this.operandA = operandA;
    }

    public String getOperandB() {
        return operandB;
    }

    public void setOperandB(String operandB) {
        this.operandB = operandB;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    
    

    public boolean equals(Object another){
        try{
            return (((LogicalTest)another).operandA == operandA)
                    &&
                    (((LogicalTest)another).operandB == operandB)
                    &&
                    (((LogicalTest)another).operator == operator)
                    &&
                    (((LogicalTest)another).condition == condition)
                    &&
                    (((LogicalTest)another).label == label);
        }catch(ClassCastException cce){
            return false;
        }
    }

}

