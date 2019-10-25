package com.example.tubesp3b.View;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.tubesp3b.Model.Operation;
import com.example.tubesp3b.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DuaFragment extends Fragment {
    protected EditText et_operand;
    protected Spinner sp_operator;
    protected Button btn_submit;
    protected FragmentListener listener;


    public DuaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dua, container, false);

        this.btn_submit = view.findViewById(R.id.btn_submit);
        this.et_operand = view.findViewById(R.id.et_operand);
        System.out.println(this.et_operand.getText().toString());
        this.sp_operator = view.findViewById(R.id.spn_dropdown);

        this.btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String operand = et_operand.getText().toString();
                String operator= sp_operator.getSelectedItem().toString();
                if(operand.equals("")==false){
                    Operation operation=new Operation(operand,operator);
                    listener.add(operation);
                    et_operand.setText("");
                    listener.closeKeyboard();
                    listener.changePage(1);
                }

            }
        });

        return view;
    }

    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof FragmentListener){
            this.listener = (FragmentListener) context;
        }else{
            throw new ClassCastException(context.toString() + " must implement FragmentListener");
        }
    }

    private class ViewHolder implements View.OnClickListener{

        public ViewHolder(){

        }

        @Override
        public void onClick(View view) {

        }
    }
}
