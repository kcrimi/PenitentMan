package com.example.kcrimi.penitentman.presenter;

import java.lang.ref.WeakReference;

/**
 * Created by kcrimi on 2/3/18.
 */

public abstract class BasePresenter<T> implements Presenter {
    protected WeakReference<T> view;

    public BasePresenter(T view) {
        this.view = new WeakReference<>(view);
    }

    protected T getView() {
        return view != null ? view.get() : null;
    }
}
