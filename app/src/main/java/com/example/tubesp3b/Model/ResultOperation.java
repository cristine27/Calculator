package com.example.tubesp3b.Model;

import java.util.List;

public class ResultOperation {
    int res= 0;

    public int getResult(Operation op){
        int temp = 0;
            if(op.getOperator().equals("+")){
                res = temp + Integer.parseInt(op.getOperand());
            }
            else if(op.getOperator().equals("-")){
                res = temp - Integer.parseInt(op.getOperand());
            }
            else if(op.getOperator().equals("*")){
                temp=1;
                temp = temp * Integer.parseInt(op.getOperand());
                System.out.println("nilai temp resultOperation = " + temp);
                res = res * temp;
                System.out.println("nilai res = " + res);
            }
            else{
                res = res/Integer.parseInt(op.getOperand());
            }
        return res;
    }
}
