package com.example.kcrimi.penitentman.presenter;

import com.example.kcrimi.penitentman.model.Article;
import com.example.kcrimi.penitentman.network.Callback;
import com.example.kcrimi.penitentman.network.ErrorCallback;
import com.example.kcrimi.penitentman.network.grailed.Grailed;
import com.example.kcrimi.penitentman.network.grailed.model.ApiResponse;
import com.example.kcrimi.penitentman.view.ArticleListFragment;
import com.example.kcrimi.penitentman.view.adapter.ArticleAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

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
        Grailed.getInstance().getArticles(page, new Callback<ApiResponse<List<Article>>>() {
            @Override
            public void update(ApiResponse<List<Article>> response) {
                lastLoadedPage = response.getMetadata().getPagination().getCurrentPage();
                articles.addAll(response.getData());
                view.refreshArticleList();
            }
        }, new ErrorCallback() {
            @Override
            public void error(Response response, Throwable throwable) {
                view.showArticleApiError();
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
        holder.setAuthorText(article.getAuthor());
    }
}
