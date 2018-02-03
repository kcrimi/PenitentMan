package com.example.kcrimi.penitentman.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by kcrimi on 1/22/18.
 */

public class SavedSearch {

    long id;
    String name;

    @SerializedName("image_url")
    String imageUrl;

    @SerializedName("item_type")
    String itemType;
    boolean published;

    @SerializedName("published_at")
    Date publishedAt;
    Long article; // Unknown type, assuming this is an article id
    Search search;

    public class Search {
        String uuid;

        @SerializedName("index_name")
        String indexName;

        @SerializedName("defaul_name")
        String defaultName;
        String query;
        Filter filters;

        public class Filter {
            List<String> strata;
            List<Integer> designers;

            @SerializedName("category_paths")
            List<String> categoryPaths;

        }

    }
}
