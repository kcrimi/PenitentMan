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
import com.example.kcrimi.penitentman.presenter.SavedSearchListPresenter;
import com.example.kcrimi.penitentman.view.adapter.SavedSearchAdapter;
import com.example.ui_utils.recycler_view.InfiniteRecyclerListener;
import com.example.ui_utils.recycler_view.decoration.DividerItemDecoration;

public class SavedSearchListFragment extends BaseFragment {

    private SavedSearchListPresenter presenter = new SavedSearchListPresenter(this);
    private SavedSearchAdapter adapter;
    private RecyclerView recyclerView;

    public SavedSearchListFragment() {
    }

    public static SavedSearchListFragment newInstance() {
        return new SavedSearchListFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRetainInstance(true);
        adapter = new SavedSearchAdapter(getContext(), presenter);
        recyclerView.setAdapter(adapter);
        presenter.onViewAttached();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = super.onCreateView(inflater,container, savedInstanceState);
        recyclerView = (RecyclerView) view;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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

    public void showSavedSearchError() {
        Toast.makeText(getContext(), R.string.error_retrieving_articles, Toast.LENGTH_SHORT).show();
    }
}
