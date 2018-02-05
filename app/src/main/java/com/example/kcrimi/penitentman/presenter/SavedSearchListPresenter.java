package com.example.kcrimi.penitentman.presenter;

import com.example.kcrimi.penitentman.model.SavedSearch;
import com.example.kcrimi.penitentman.network.Callback;
import com.example.kcrimi.penitentman.network.ErrorCallback;
import com.example.kcrimi.penitentman.network.grailed.Grailed;
import com.example.kcrimi.penitentman.view.SavedSearchListFragment;
import com.example.kcrimi.penitentman.view.adapter.SavedSearchAdapter;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import retrofit2.Response;

/**
 * Created by kcrimi on 2/4/18.
 */

public class SavedSearchListPresenter extends BasePresenter<SavedSearchListFragment> {
        List<SavedSearch> savedSearches = new ArrayList<>();

        public SavedSearchListPresenter(SavedSearchListFragment view) {
            super(view);
        }

        @Override
        public void onViewAttached() {
            retrieveSavedSearches();
        }

        @Override
        public void onViewDetached() {
            // NO-OP
        }

        public void retrieveSavedSearches() {

            Grailed.getInstance().getSavedSearches(new SingleObserver<List<SavedSearch>>() {

                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onSuccess(List<SavedSearch> results) {
                    savedSearches.addAll(results);
                    getView().refreshList();
                }

                @Override
                public void onError(Throwable e) {
                    getView().showSavedSearchError();
                }
            });
        }

        public int getSavedSearchCount() {
            return savedSearches.size();
        }

        public void bindSavedSearch(SavedSearchAdapter.SavedSearchViewHolder holder, int position) {
            SavedSearch savedSearch = savedSearches.get(position);
            holder.setNameText(savedSearch.getName());
            holder.setImage(savedSearch.getImageUrl());
        }
    }

