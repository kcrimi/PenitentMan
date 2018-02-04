package com.example.kcrimi.penitentman.presenter;

/**
 * Created by kcrimi on 2/3/18.
 */

public abstract class BasePresenter<T> implements Presenter {
    protected T view;

    public BasePresenter(T view) {
        this.view = view;
    }
}
