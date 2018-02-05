package com.example.kcrimi.penitentman.presenter;

import com.example.kcrimi.penitentman.model.Article;
import com.example.kcrimi.penitentman.network.grailed.Grailed;
import com.example.kcrimi.penitentman.network.grailed.model.ApiResponse;
import com.example.kcrimi.penitentman.view.ArticleListFragment;
import com.example.kcrimi.penitentman.view.adapter.ArticleAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Created by kcrimi on 2/3/18.
 */

public class ArticleListPresenter extends BasePresenter<ArticleListFragment> {
    List<Article> articles = new ArrayList<>();
    private int lastLoadedPage = 0;

    public ArticleListPresenter(ArticleListFragment view) {
        super(view);
    }

    @Override
    public void onViewAttached() {
        retrieveArticlesForPage(lastLoadedPage);
    }

    @Override
    public void onViewDetached() {
        // NO-OP
    }

    public void retrieveArticlesForPage(int page) {
        Grailed.getInstance().getArticles(page, new SingleObserver<ApiResponse<List<Article>>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(ApiResponse<List<Article>> response) {
                lastLoadedPage = response.getMetadata().getPagination().getCurrentPage();
                articles.addAll(response.getData());
                getView().refreshList();
            }

            @Override
            public void onError(Throwable e) {
                getView().showArticleApiError();

            }
        });
    }

    public void retrieveMoreArticles() {
        retrieveArticlesForPage(lastLoadedPage + 1);
    }

    public int getArticlesCount() {
        return articles.size();
    }

    public void bindArticle(ArticleAdapter.ArticleViewHolder holder, int position) {
        Article article = articles.get(position);
        holder.setTitleText(article.getTitle());
        holder.setPublishedText(article.getPublishedAt());
        holder.setHeroImage(article.getHero());
    }
}
