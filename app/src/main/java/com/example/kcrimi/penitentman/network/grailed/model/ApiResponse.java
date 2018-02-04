package com.example.kcrimi.penitentman.network.grailed.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kcrimi on 1/22/18.
 */

public class ApiResponse<T> {
    T data;
    Metadata metadata;

    public class Metadata {
        Pagination pagination;
        public class Pagination {
            @SerializedName("next_page")
            String nextPage;
            @SerializedName("current_page")
            String currentPage;
            @SerializedName("previous_page")
            String previousPage;

            public int getCurrentPage() {
                return pathToPageNumber(currentPage);
            }

            public int getNextPage() {
                return pathToPageNumber(nextPage);
            }

            private int pathToPageNumber(String path) {
                String[] segments = path.split("page=");
                return Integer.valueOf(segments[segments.length - 1]);
            }
        }

        public Pagination getPagination() {
            return pagination;
        }
    }

    public T getData() {
        return data;
    }

    public Metadata getMetadata() {
        return metadata;
    }
}
