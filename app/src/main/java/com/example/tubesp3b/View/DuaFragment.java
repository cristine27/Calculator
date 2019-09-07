package com.example.tubesp3b.View;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

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
    protected OperationAdapter op;

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
        System.out.println("test");
        System.out.println(this.et_operand.getText().toString());
        this.sp_operator = view.findViewById(R.id.spn_dropdown);
//        this.op = new OperationAdapter();

        this.btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity ma = (MainActivity)getActivity();
//                System.out.println("test");
//                System.out.println(et_operand.getText().toStr;ing());
//                op = new OperationAdapter(ma);
//                String operand = et_operand.getText().toString();
//
//                String operator= sp_operator.getSelectedItem().toString();

//                Operation newOp = new Operation(operand, operator);
//                op.add(newOp);
                ma.changePage(1);
            }
        });

        return view;
    }

}
