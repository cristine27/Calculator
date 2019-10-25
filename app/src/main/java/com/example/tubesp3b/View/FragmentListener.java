package com.example.tubesp3b.View;

import com.example.tubesp3b.Model.Operation;

public interface FragmentListener {
    public void changePage(int page);
    public void closeKeyboard();
    public void add(Operation operation);
    public int result(Operation operation);

}
