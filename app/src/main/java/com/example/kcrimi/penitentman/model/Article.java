package com.example.kcrimi.penitentman.model;

import com.example.kcrimi.penitentman.network.grailed.Grailed;
import com.google.gson.annotations.SerializedName;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by kcrimi on 1/22/18.
 */

public class Article {
    long id;
    String url;
    String title;

    @SerializedName("published_at")
    Date publishedAt;
    boolean published;
    String hero;
    List<String> listings; // Don't know the data type, assuming String for now

    @SerializedName("tag_list")
    List<String> tags;
    String franchise;
    String slug;
    String author;

    @SerializedName("content_type")
    String contentType;
    String position;

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublishedAt() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd");
        return simpleDateFormat.format(publishedAt);
    }

    public String getHero() {
        return Grailed.getResizedImageUrl(hero, 300);
    }
}
