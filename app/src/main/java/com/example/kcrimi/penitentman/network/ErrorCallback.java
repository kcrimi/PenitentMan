package com.example.kcrimi.penitentman.network;

import retrofit2.Response;

/**
 * Created by kcrimi on 1/23/18.
 */

public interface ErrorCallback {
    void error(Response response, Throwable throwable);
}
