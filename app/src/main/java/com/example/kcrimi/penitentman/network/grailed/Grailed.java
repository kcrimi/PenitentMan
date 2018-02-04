package com.example.kcrimi.penitentman.network.grailed;

import android.util.Log;

import com.example.kcrimi.penitentman.network.Callback;
import com.example.kcrimi.penitentman.network.ErrorCallback;
import com.example.kcrimi.penitentman.network.grailed.model.ApiResponse;
import com.example.kcrimi.penitentman.model.Article;
import com.example.kcrimi.penitentman.model.SavedSearch;

import java.io.IOException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kcrimi on 1/22/18.
 */

public class Grailed {

    private static final String BASE_URL = "https://www.grailed.com/api/";
    private static Grailed instance;
    private final GrailedService grailedService = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GrailedService.class);

    public static Grailed getInstance() {
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
                    Log.d("KEVIN", "code "+response.code());
                    callback.update(response.body());
                } else {
                    Log.d("KEVIN", "err code "+response.code());
                    errorCallback.error(response, new IOException(response.message()));
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<List<Article>>> call, Throwable t) {
                t.printStackTrace();
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
