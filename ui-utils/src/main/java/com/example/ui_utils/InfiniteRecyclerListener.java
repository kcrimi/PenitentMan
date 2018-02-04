package com.example.ui_utils;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by kcrimi on 2/4/18.
 */

public abstract class InfiniteRecyclerListener extends RecyclerView.OnScrollListener {
    private double scrollPercentage = 0.8;
    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        if (dy > 0) {
            int viewedItems, totalItems;
            if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
                GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                viewedItems = layoutManager.findLastVisibleItemPosition() + 1;
                totalItems = layoutManager.getItemCount();
            } else if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                viewedItems = layoutManager.findLastVisibleItemPosition() + 1;
                totalItems = layoutManager.getItemCount();
            } else {
                throw new RuntimeException("LayoutManager must be GridLayout or LinearLayout");
            }

            if (viewedItems > scrollPercentage * totalItems) {
                triggerCallback();
            }
        }
    }

    /**
     * This callback method will be triggered when the user has scrolled past 0.8 of the items
     * loaded into the recycler
     */
    public abstract void triggerCallback();

    /**
     * This sets the amount you need to scroll through the currently loaded recycler before
     * triggering the trigger callback.
     * Must be a double between 0-1 (default is 0.8)
     */
    public void setTriggeringScrollPercentage(double percentage) {
        if (percentage < 0 || percentage > 1) {
            throw new RuntimeException("Scroll percentage must be between 0 and 1");
        }

    }
}
