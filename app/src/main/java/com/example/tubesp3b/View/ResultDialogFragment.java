package com.example.tubesp3b.View;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.example.tubesp3b.R;

public class ResultDialogFragment extends DialogFragment {
    protected FragmentListener listener;
    protected Button btn_close;
    protected TextView tv_res;

    public ResultDialogFragment() {
        // Required empty public constructor
    }

    public static ResultDialogFragment newInstance(){
        ResultDialogFragment rsf = new ResultDialogFragment();
        return rsf;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.result_dialog_fragment, container, false);
        TextView tv = view.findViewById(R.id.tv_result);
        this.btn_close = view.findViewById(R.id.btn_close);
        this.btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof FragmentListener){
            this.listener = (FragmentListener) context;
        }else{
            throw new ClassCastException(context.toString() + " must implement FragmentListener");
        }
    }
}
