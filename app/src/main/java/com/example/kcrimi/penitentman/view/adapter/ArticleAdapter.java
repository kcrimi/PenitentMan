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
import com.example.kcrimi.penitentman.presenter.ArticleListPresenter;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    private final ArticleListPresenter presenter;
    private final Context context;

    public ArticleAdapter(Context context, ArticleListPresenter presenter) {
        this.context = context;
        this.presenter = presenter;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vh_article, parent, false);
        ArticleViewHolder vh = new ArticleViewHolder(view);
        view.setOnClickListener(vh);
        return vh;
    }

    @Override
    public void onBindViewHolder(final ArticleViewHolder holder, int position) {
        presenter.bindArticle(holder, position);
    }

    @Override
    public int getItemCount() {
        return presenter.getArticlesCount();
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final ImageView heroImage;
        private final TextView titleText;
        private final TextView publishedText;
        public final View view;

        public ArticleViewHolder(View view) {
            super(view);
            this.view = view;
            heroImage = view.findViewById(R.id.hero);
            titleText = view.findViewById(R.id.title_text);
            publishedText = view.findViewById(R.id.published_text);

        }

        public void setTitleText(String name) {
            titleText.setText(name);
        }

        public void setPublishedText(String date) {
            publishedText.setText(date);
        }

        public void setHeroImage(String url) {
            Glide.with(context)
                    .load(url)
                    .into(heroImage);
        }

        @Override
        public void onClick(View v) {
            presenter.selectArticle(getAdapterPosition());
        }
    }
}
