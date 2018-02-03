package com.example.kcrimi.penitentman.network.grailed;

import com.example.kcrimi.penitentman.network.grailed.model.ApiResponse;
import com.example.kcrimi.penitentman.model.Article;
import com.example.kcrimi.penitentman.model.SavedSearch;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kcrimi on 1/22/18.
 */

public interface GrailedService {

    @GET("articles/ios_index")
    Call<ApiResponse<List<Article>>> getArticles(@Query("page") int page);

    @GET("merchandise/marquee")
    Call<ApiResponse<List<SavedSearch>>> getSavedSearches();
}
