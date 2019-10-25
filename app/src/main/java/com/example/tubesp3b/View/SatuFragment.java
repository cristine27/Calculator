package com.example.tubesp3b.View;


import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tubesp3b.Model.Operation;
import com.example.tubesp3b.Model.ResultOperation;
import com.example.tubesp3b.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.BatchUpdateException;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SatuFragment extends Fragment {
    protected TextView tv_title;
    protected TextView tv_total;
    protected ImageButton btn_add;
    protected Button btn_res;
    protected Button btn_clear;
    protected Button btn_save;
    protected ListView lst_view;
    protected OperationAdapter adapter;
    protected ResultOperation resultOperation;
    protected Operation op;
    protected Double result;


    public SatuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_satu, container, false);

        resultOperation = new ResultOperation();
        this.btn_add = view.findViewById(R.id.btn_add);
        this.btn_clear = view.findViewById(R.id.btn_clear);
        this.btn_res = view.findViewById(R.id.btn_res);
        this.btn_save = view.findViewById(R.id.btn_save);
        this.lst_view = view.findViewById(R.id.list);
        this.tv_title = view.findViewById(R.id.tv_title);
        this.tv_total = view.findViewById(R.id.tv_total);
        this.adapter=new OperationAdapter(getActivity());

        this.lst_view.setAdapter(this.adapter);

        FragmentManager fm = this.getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        this.btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity main = (MainActivity) getActivity();
                List<Operation> list = main.getList();
                File file;
                FileOutputStream fos = null;
                String isi = "";
                try{
                    file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"CalculatorSave.txt");
                    fos = new FileOutputStream(file);

                    isi=adapter.getSave();
                    System.out.println(isi);
                    byte[] contentinBytes = isi.getBytes();

                    fos.write(contentinBytes);
                    fos.flush();
                    fos.close();
                }catch(IOException e){
                    e.printStackTrace();
                }finally{
                    try{
                        if(fos!=null){
                            fos.close();
                        }
                    }catch(IOException e){
                        e.printStackTrace();
                    }
                }
            }
        });

        this.btn_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                double temp =0;
                int idx = 0;
                while(idx<adapter.getCount()){
                    op = (Operation) adapter.getItem(idx);

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
                result = temp;
                String hasil = Double.toString(temp);

                ResultDialogFragment rdf = new ResultDialogFragment();

                rdf.show(ft,hasil);
            }
        });

        this.btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity ma = (MainActivity)getActivity();
                ma.changePage(2);
            }
        });

        this.btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                adapter.Clear();
            }
        });
        return view;
    }

    public void add(Operation operation){
        this.adapter.add(operation);
    }

    public void onResume() {

        super.onResume();
        FileInputStream fis = null;
        File file;
        String content="";
        try{
            file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"CalculatorSave.txt");
//            file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),"test.txt")
            fis = new FileInputStream(file);

//            while(fis.available()>0){
//                content+=(char)fis.read();
//            }

            while(fis.available()>0){
                content+=(char)fis.read();
            }

            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.adapter.Clear();
        String operand = "";
        String operator = "";
        for(int i=0; i<content.length(); i++){
            if(!(content.charAt(i)=='+' || content.charAt(i)=='-' || content.charAt(i)== '*' || content.charAt(i)=='/')){
                operand+=content.charAt(i);
                this.adapter.add(new Operation(operand,operator));
                operand="";
                operator="";
            }
            else{
                operator+=content.charAt(i);
            }
        }

    }
}
