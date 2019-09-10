package com.example.tubesp3b.View;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.tubesp3b.R;

import java.sql.BatchUpdateException;


/**
 * A simple {@link Fragment} subclass.
 */
public class SatuFragment extends Fragment {
    protected TextView tv_title;
    protected TextView tv_total;
    protected Button btn_add;
    protected Button btn_res;
    protected Button btn_clear;
    protected Button btn_save;
    protected ListView lst_view;
    
    public SatuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_satu, container, false);

        this.btn_add = view.findViewById(R.id.btn_add);
        this.btn_clear = view.findViewById(R.id.btn_clear);
        this.btn_res = view.findViewById(R.id.btn_res);
        this.btn_save = view.findViewById(R.id.btn_save);
        this.lst_view = view.findViewById(R.id.list);
        this.tv_title = view.findViewById(R.id.tv_title);
        this.tv_total = view.findViewById(R.id.tv_total);

        this.btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String
            }
        });

        this.btn_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ResultDialogFragment rdf = new ResultDialogFragment();
                rdf.show(ft,"");
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

            }
        });
        return view;
    }

    protected void onPause(){
        super.onPause();
        this.pnd.saveBarang(etBarang.getText().toString());
        this.pnd.saveHarga(etHarga.getText().toString());
        this.pnd.saveKet(etKet.getText().toString());
    }

    protected void onResume(){
        super.onResume();
        this.etBarang.setText(this.pnd.getBarang());
        this.etHarga.setText(this.pnd.getHarga());
        this.etKet.setText(this.pnd.getKet());
    }

}
