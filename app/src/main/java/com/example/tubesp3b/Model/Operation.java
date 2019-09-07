package com.example.tubesp3b.Model;

public class Operation {
    protected String operand;
    protected String operator;

    public Operation(String operand, String operator){
        this.operand=operand;
        this.operator=operator;
    }

    public String getOperator() {
        return operator;
    }

    public String getOperand() {
        return operand;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setOperand(String operand) {
        this.operand = operand;
    }
}
