package com.example.kcrimi.penitentman.network.grailed;

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

    public void getArticles () {
        getArticles(0);
    }

    public void getArticles(int page) {
        //TODO retrieve articles
    }

    public void getSavedSearches() {
        //TODO retrieve saved searches
    }
}
