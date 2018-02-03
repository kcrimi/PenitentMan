package com.example.kcrimi.penitentman.network.grailed;

import com.example.kcrimi.penitentman.network.Callback;
import com.example.kcrimi.penitentman.network.ErrorCallback;
import com.example.kcrimi.penitentman.network.grailed.model.ApiResponse;
import com.example.kcrimi.penitentman.model.Article;
import com.example.kcrimi.penitentman.model.SavedSearch;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kcrimi on 1/22/18.
 */

public class Grailed {

    private static Grailed instance;

    private static GrailedService grailedService = new Retrofit.Builder()
                .baseUrl("https://www.grailed.com/api")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GrailedService.class);

    private static Grailed getInstance() {
        if (instance == null) {
            instance = new Grailed();
        }
        return instance;
    }

    public void getArticles (Callback<ApiResponse<List<Article>>> callback, ErrorCallback errorCallback) {
        getArticles(0, callback, errorCallback);
    }

    public void getArticles(int page, final Callback<ApiResponse<List<Article>>> callback, final ErrorCallback errorCallback) {
        grailedService.getArticles(page).enqueue(new retrofit2.Callback<ApiResponse<List<Article>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<Article>>> call, Response<ApiResponse<List<Article>>> response) {
                if (response.isSuccessful()) {
                    callback.update(response.body());
                } else {
                    errorCallback.error(response, new IOException(response.message()));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Article>>> call, Throwable t) {
                errorCallback.error(null, t);
            }
        });
    }

    public void getSavedSearches(final Callback<List<SavedSearch>> callback, final ErrorCallback errorCallback) {
        grailedService.getSavedSearches().enqueue(new retrofit2.Callback<ApiResponse<List<SavedSearch>>>() {
            @Override
            public void onResponse(Call<ApiResponse<List<SavedSearch>>> call, Response<ApiResponse<List<SavedSearch>>> response) {
                if (response.isSuccessful()) {
                    callback.update(response.body().getData());
                } else {
                    errorCallback.error(response, new IOException(response.message()));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<SavedSearch>>> call, Throwable t) {
                errorCallback.error(null, t);
            }
        });
    }
}
