package com.example.kcrimi.penitentman.network.grailed.model;

import com.google.gson.annotations.SerializedName;

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
}
