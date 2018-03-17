package com.example.kcrimi.penitentman.network.grailed;

import com.example.kcrimi.penitentman.network.grailed.model.ApiResponse;
import com.example.kcrimi.penitentman.model.Article;
import com.example.kcrimi.penitentman.model.SavedSearch;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kcrimi on 1/22/18.
 */

public class Grailed {

    private static final String BASE_URL = "https://www.grailed.com/api/";
    private static final String CDN_FORMAT = "https://cdn.fs.grailed.com/AJdAgnqCST4iPtnUxiGtTz/" +
            "rotate=deg:exif/rotate=deg:0/resize=width:%d," +
            "fit:crop/output=format:jpg,compress:true,quality:95/%s";
    private static Grailed instance;
    private final GrailedService grailedService = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .build()
                .create(GrailedService.class);

    public static Grailed getInstance() {
        if (instance == null) {
            instance = new Grailed();
        }
        return instance;
    }

    public void getArticles(int page, final SingleObserver<ApiResponse<List<Article>>> observer) {
        grailedService.getArticles(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(foo -> {});
    }

    public void getSavedSearches(final SingleObserver<List<SavedSearch>>observer) {
        grailedService.getSavedSearches()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<ApiResponse<List<SavedSearch>>, List<SavedSearch>>() {
                    @Override
                    public List<SavedSearch> apply(ApiResponse<List<SavedSearch>> listApiResponse) throws Exception {
                        return listApiResponse.getData();
                    }
                })
                .subscribe(observer);
    }

    public static String getResizedImageUrl(String url, int width) {
        return String.format(CDN_FORMAT, width, url);
    }

    public interface IGrailed {

    }
}
