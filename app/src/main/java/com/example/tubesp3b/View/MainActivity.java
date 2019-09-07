package com.example.tubesp3b.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ListView;


import com.example.tubesp3b.Model.Operation;
import com.example.tubesp3b.Presenter.MainPresenter;
import com.example.tubesp3b.R;

import java.util.List;

public class MainActivity extends AppCompatActivity implements FragmentListener,IMainActivity {
    protected SatuFragment satuFragment;
    protected DuaFragment duaFragment;
    protected FragmentListener fragmentListener;
    protected FragmentManager fragmentManager;
    private OperationAdapter adapter;
    private ListView lst_view;
    protected MainPresenter mainPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.satuFragment = new SatuFragment();
        this.duaFragment = new DuaFragment();
        this.lst_view = this.findViewById(R.id.list);
        this.mainPresenter = new MainPresenter(this);
//        this.mainPresenter.loadData(); bagian loaddata mainpresenter salah
        this.adapter = new OperationAdapter(this);
//        this.lst_view.setAdapter(this.adapter);
//bagian set adapter salah
        this.fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = this.fragmentManager.beginTransaction();
        ft.add(R.id.frame_container,this.satuFragment)
                .addToBackStack(null)
                .commit();
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
    }

    @Override
    public void updateList(List<Operation> operations) {
//        adapter.addList(operations);
    }

    @Override
    public void resetAddForm() {
        this.lst_view.removeAllViews();
        this.satuFragment.tv_total.setText("0");
    }
}
