package com.example.tubesp3b.Presenter;

import com.example.tubesp3b.Model.MockOpeator;
import com.example.tubesp3b.Model.Operation;
import com.example.tubesp3b.View.IMainActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainPresenter {
    protected List<Operation> operations;
    protected IMainActivity ui;

    public MainPresenter(IMainActivity ui){
        this.ui = ui;
    }

    public void loadData(){
        this.operations = new ArrayList<Operation>(Arrays.asList(MockOpeator.opearationArr));
//        this.ui.updateList(this.operations);
//        bagian update list salah
    }

    public void deleteList(int i){
        this.operations.remove(i);
        this.ui.updateList(operations);
    }

    public void addList(String operator,String operand){
        Operation op = new Operation(operand,operator);
        this.operations.add(op);
        this.ui.updateList(this.operations);
        this.ui.resetAddForm();
    }
}
