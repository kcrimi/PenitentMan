package com.example.kcrimi.penitentman.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.kcrimi.penitentman.R;
import com.example.kcrimi.penitentman.presenter.SavedSearchListPresenter;

public class SavedSearchAdapter extends RecyclerView.Adapter<SavedSearchAdapter.SavedSearchViewHolder> {

    private final SavedSearchListPresenter presenter;
    private final Context context;

    public SavedSearchAdapter(Context context, SavedSearchListPresenter presenter) {
        this.context = context;
        this.presenter = presenter;
    }

    @Override
    public SavedSearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vh_saved_search, parent, false);
        return new SavedSearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SavedSearchViewHolder holder, int position) {
        presenter.bindSavedSearch(holder, position);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO handle selecting saved search
            }
        });
    }

    @Override
    public int getItemCount() {
        return presenter.getSavedSearchCount();
    }

    public class SavedSearchViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView nameText;
        public final View view;

        public SavedSearchViewHolder(View view) {
            super(view);
            this.view = view;
            imageView = view.findViewById(R.id.image);
            nameText = view.findViewById(R.id.name);
        }

        public void setNameText(String name) {
            nameText.setText(name);
        }


        public void setImage(String url) {
            Glide.with(context)
                    .load(url)
                    .into(imageView);
        }
    }
}
