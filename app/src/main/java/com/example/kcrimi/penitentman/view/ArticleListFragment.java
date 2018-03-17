package com.example.kcrimi.penitentman.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.kcrimi.penitentman.R;
import com.example.kcrimi.penitentman.presenter.ArticleListPresenter;
import com.example.kcrimi.penitentman.view.adapter.ArticleAdapter;
import com.example.ui_utils.recycler_view.InfiniteRecyclerListener;
import com.example.ui_utils.recycler_view.decoration.DividerItemDecoration;

public class ArticleListFragment extends BaseFragment {

    private ArticleListPresenter presenter = new ArticleListPresenter(this);
    private ArticleAdapter adapter;
    private RecyclerView recyclerView;

    public ArticleListFragment() {
    }

    public static ArticleListFragment newInstance() {
        return new ArticleListFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRetainInstance(true);
        adapter = new ArticleAdapter(getContext(), presenter);
        recyclerView.setAdapter(adapter);
        presenter.onViewAttached();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
            View view = super.onCreateView(inflater,container, savedInstanceState);
            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.addOnScrollListener(new InfiniteRecyclerListener() {
                @Override
                public void triggerCallback() {
                    presenter.retrieveMoreArticles();
                }
            });
            recyclerView.addItemDecoration(new DividerItemDecoration(getContext()));
        return view;
    }


    public void refreshList() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_feed;
    }

    public void showArticleApiError() {
        Toast.makeText(getContext(), R.string.error_retrieving_articles, Toast.LENGTH_SHORT).show();
    }

    public void displaySelection(String s) {
        Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
    }
}
