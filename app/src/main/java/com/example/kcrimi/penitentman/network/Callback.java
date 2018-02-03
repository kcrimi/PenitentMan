package com.example.kcrimi.penitentman.network;

/**
 * Created by kcrimi on 1/23/18.
 */

public interface Callback<T> {
    void update(T response);
}
