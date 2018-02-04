package com.example.kcrimi.penitentman.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kcrimi.penitentman.R;
import com.example.kcrimi.penitentman.presenter.ArticleListPresenter;
import com.example.kcrimi.penitentman.view.adapter.ArticleAdapter;
import com.example.ui_utils.InfiniteRecyclerListener;

public class ArticleListFragment extends BaseFragment {

    private static final int COLUMN_COUNT = 3;
    private ArticleListPresenter presenter = new ArticleListPresenter(this);
    private ArticleAdapter adapter = new ArticleAdapter(presenter);

    public ArticleListFragment() {
    }

    public static ArticleListFragment newInstance() {
        return new ArticleListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onViewAttached();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            View view = super.onCreateView(inflater,container, savedInstanceState);
            RecyclerView recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), COLUMN_COUNT));
            recyclerView.setAdapter(adapter);
            recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if (dy > 0) {
                        GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                        int viewedItems = layoutManager.findLastVisibleItemPosition() + 1;
                        int totalItems = layoutManager.getItemCount();
                        if (viewedItems > .8 * totalItems);
                    }
                }
            });
            recyclerView.addOnScrollListener(new InfiniteRecyclerListener() {
                @Override
                public void triggerCallback() {
                    presenter.retrieveMoreArticles();
                }
            });
        return view;
    }

    public void refreshArticleList() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_article;
    }

    public void showArticleApiError() {
        Toast.makeText(getContext(), R.string.error_retrieving_articles, Toast.LENGTH_SHORT).show();
    }
}
