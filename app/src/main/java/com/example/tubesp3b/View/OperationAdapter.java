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
    private List<Operation> list_operation;
    private Activity activity;
    protected MainPresenter mp;
    protected String save;

    public OperationAdapter(Activity activity) {
        this.activity = activity;
        this.list_operation = new ArrayList<Operation>();
        this.save="";
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
        view=LayoutInflater.from(this.activity).inflate(R.layout.fragment_list, viewGroup, false);
        ViewHolder viewHolder=new ViewHolder((view));
        final Operation currOperation=(Operation) this.getItem(i);
        viewHolder.updateView(currOperation);
        ImageButton delete=viewHolder.ibTrash;
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list_operation.remove(currOperation);
                notifyDataSetChanged();
            }
        });
        return view;
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

    public void Clear(){
        this.list_operation.clear();
        this.notifyDataSetChanged();
    }

    public List<Operation> getList(){
        return this.list_operation;
    }

    public String getSave(){
        for(int i = 0;i<list_operation.size();i++){
            save +=list_operation.get(i).getOperator()+list_operation.get(i).getOperand();
        }
        return save;
    }
}

class ViewHolder{
    protected ImageButton ibTrash;
    protected TextView tv_list;
    protected Operation operation;


//    added mainpresenter, int idx
    public ViewHolder(View view){
        this.ibTrash=view.findViewById(R.id.ib_trash);
        this.tv_list=view.findViewById(R.id.tv_list);
    }

    public void updateView(Operation operation){
        this.operation=operation;
        this.tv_list.setText(operation.getOperator() + operation.getOperand());
    }
}