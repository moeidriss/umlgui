/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package moe.umlgui.model;

/**
 *
 * @author Moe
 */

public class LogicalTest {

    public static String[] OPERATORS = {"=" , "!=" , ">" 
                                , "<" , "IS" , "IS NOT"};

    //TODO Operand, Operator classes
    String operandA ;
    String operandB;
    String operator;

    public LogicalTest(){

    }


    public LogicalTest(String operandA, String operandB, String operator){
        this.operandA = operandA;
        this.operandB = operandB;
        this.operator = operator;
    }


    public String getOperandA() {
        return operandA;
    }

    public void setOperandA(String operandA) {
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


    public boolean equals(Object another){
        try{
            return (((LogicalTest)another).operandA == operandA)
                    &&
                    (((LogicalTest)another).operandB == operandB)
                    &&
                    (((LogicalTest)another).operator == operator);
        }catch(ClassCastException cce){
            return false;
        }
    }

}

