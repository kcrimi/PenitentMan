package com.example.kcrimi.penitentman.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kcrimi.penitentman.R;
import com.example.kcrimi.penitentman.presenter.ArticleListPresenter;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    private final ArticleListPresenter presenter;

    public ArticleAdapter(ArticleListPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vh_article, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ArticleViewHolder holder, int position) {
        presenter.bindArticle(holder, position);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO handle selecting article
            }
        });
    }

    @Override
    public int getItemCount() {
        return presenter.getArticlesCount();
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder {
        private final TextView titleText;
        private final TextView authorText;
        public final View view;

        public ArticleViewHolder(View view) {
            super(view);
            this.view = view;
            titleText = view.findViewById(R.id.title_text);
            authorText = view.findViewById(R.id.author_text);
        }

        public void setTitleText(String name) {
            titleText.setText(name);
        }

        public void setAuthorText(String author) {
            authorText.setText(author);
        }
    }
}
