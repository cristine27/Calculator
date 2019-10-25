package com.example.tubesp3b.View;


import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;


import com.example.tubesp3b.Model.Operation;
import com.example.tubesp3b.Model.ResultOperation;
import com.example.tubesp3b.Presenter.MainPresenter;
import com.example.tubesp3b.R;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FragmentListener {
    protected SatuFragment satuFragment;
    protected DuaFragment duaFragment;
    protected FragmentManager fragmentManager;
    private OperationAdapter adapter;
    private ListView lst_view;
    protected MainPresenter mainPresenter;
    protected ResultOperation resultOperation;
    protected ResultDialogFragment rdf;
    protected FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.rdf = new ResultDialogFragment();
        this.resultOperation = new ResultOperation();
        this.satuFragment = new SatuFragment();
        this.duaFragment = new DuaFragment();
        this.lst_view = this.findViewById(R.id.list);
        this.adapter = new OperationAdapter(this);
        this.mainPresenter = new MainPresenter(this.adapter.getList());

        this.fragmentManager = getSupportFragmentManager();
        this.ft = this.fragmentManager.beginTransaction();
        ft.add(R.id.frame_container,this.satuFragment)
                .addToBackStack(null)
                .commit();



//        this.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        this.requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
    }

    @Override
    public void changePage(int page) {
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        if (page == 1) {
            if (this.satuFragment.isAdded()) {
                ft.show(this.satuFragment);
            } else {
                ft.add(R.id.frame_container, this.satuFragment);
            }
            if (this.duaFragment.isAdded()) {
                ft.hide(this.duaFragment);
            }
        } else if (page == 2) {
            if (this.duaFragment.isAdded()) {
                ft.show(this.duaFragment);
            } else {
                ft.add(R.id.frame_container, this.duaFragment);
            }
            if (this.satuFragment.isAdded()) {
                ft.hide(this.satuFragment);
            }
        }
        ft.commit();
    }


    @Override
    public void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

//        View view = this.getCurrentFocus();
//        if(view!=null){
//            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//            imm.hideSoftInputFromWindow(view.getWindowToken(),0)
//        }
    }

    @Override
    public void add(Operation operation) {
        this.satuFragment.add(operation);
    }

    @Override
    public int result(Operation operation) {
        return resultOperation.getResult(operation);
    }

    public String getRes(){
        return this.mainPresenter.res();
    }

    public void show(){
        this.rdf.show(this.ft,this.getRes());
    }
    public List<Operation> getList(){
        return this.adapter.getList();
    }
}
