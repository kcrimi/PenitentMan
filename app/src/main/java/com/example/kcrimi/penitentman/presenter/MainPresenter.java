package com.example.kcrimi.penitentman.presenter;

import com.example.kcrimi.penitentman.view.MainActivity;

/**
 * Created by kcrimi on 2/3/18.
 */

public class MainPresenter extends BasePresenter<MainActivity> {
    public MainPresenter(MainActivity view) {
        super(view);
    }

    @Override
    public void onViewAttached() {
        //NO-OP
    }

    @Override
    public void onViewDetached() {
        //NO-OP
    }
}
