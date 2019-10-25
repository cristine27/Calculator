package com.example.tubesp3b.Presenter;

//import com.example.tubesp3b.Model.;
import android.util.Log;

import com.example.tubesp3b.Model.Operation;

import java.util.List;

public class MainPresenter {
    protected List<Operation> operations;

    public MainPresenter(List<Operation> newList){
        this.operations = newList;
    }

    public String res(){
        Operation op;
        double temp =0;
        int idx = 0;
        while(idx<this.operations.size()){
            op = operations.get(idx);
            System.out.println("operator "+op.getOperator());
            if(op.getOperator().equals("+")){
                temp = temp + Double.parseDouble(op.getOperand());
            }
            else if(op.getOperator().equals("-")){
                temp = temp - Double.parseDouble(op.getOperand());
            }
            else if(op.getOperator().equals("*")){
                temp = temp * Double.parseDouble(op.getOperand());
            }
            else{
                temp = temp/Double.parseDouble(op.getOperand());
            }
            idx++;
        }
        Log.d("tag", "res: "+temp);
        return Double.toString(temp);
    }
}
