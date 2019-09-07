package com.example.tubesp3b.View;

import android.app.Activity;
import android.graphics.Path;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.tubesp3b.Model.Operation;
import com.example.tubesp3b.Presenter.MainPresenter;
import com.example.tubesp3b.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class OperationAdapter extends BaseAdapter {
    private ArrayList<Operation> list_operation;
    private Activity activity;
    protected TextView tv_operator;
    protected TextView tv_operand;
    protected Operation operation;
    protected MainPresenter mp;

    public OperationAdapter(Activity activity) {
        this.activity = activity;
        this.list_operation = new ArrayList<Operation>();

    }

    @Override
    public int getCount() {
        return this.list_operation.size();
    }

    @Override
    public Object getItem(int i) {
        return this.list_operation.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh = new ViewHolder(view);
        Operation op = (Operation)this.getItem(i);
        if(view==null){
            view = LayoutInflater.from(this.activity).inflate(R.layout.fragment_list,viewGroup,false);
//            vh = new ViewHolder(view);
            view.setTag(vh);
        }
        else{
            vh =(ViewHolder) view.getTag();
        }
        vh.updateView(op);

        return view;
    }

//    added
    public void setPresenter(MainPresenter mp){
        this.mp = mp;
    }

    public void add(Operation newOp){
        this.list_operation.add(newOp);
        this.notifyDataSetChanged();
    }

    public void addList(List<Operation> newOp){
        this.list_operation.clear();
        for(Operation op: newOp){
            this.list_operation.add(op);
        }
        this.notifyDataSetChanged();
    }

    public void update(List<Operation> getOperation){

    }
}

class ViewHolder{
    protected ImageButton ibTrash;
    protected TextView tv_operator;
    protected TextView tv_operand;
//    protected Operation currOperation;
//    commented
    protected MainPresenter mp;
    protected int idx;

//    added mainpresenter, int idx
    public ViewHolder(View view, final MainPresenter mp){
        this.ibTrash=view.findViewById(R.id.ib_trash);
        this.tv_operator=view.findViewById(R.id.tv_operator);
        this.tv_operand=view.findViewById(R.id.tv_operan);
        this.mp = mp;

        this.ibTrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == ibTrash.getId()){
                    mp.deleteList(idx);
                }
            }
        });
    }

    public void updateView(Operation operation,int i){
//        this.currOperation = operation;
//        commented
        this.tv_operand.setText(operation.getOperand());
        this.tv_operator.setText(operation.getOperator());
    }
}