package com.example.ui_utils.recycler_view.decoration;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by kcrimi on 11/22/16.
 */

public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int mSpanCount = 1;
    private int mVertical;
    private int mHorizontal;

    public SpacesItemDecoration(int verticalDp, int horizontalDp, int spanCount) {
        mSpanCount = spanCount;
        mVertical = verticalDp;
        mHorizontal = horizontalDp;
    }
    public SpacesItemDecoration(int verticalDp, int horizontalDp) {
        new SpacesItemDecoration(verticalDp, horizontalDp, 1);
    }

    public SpacesItemDecoration(int spaceDp) {
        new SpacesItemDecoration(spaceDp, spaceDp);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
        int position = parent.getChildAdapterPosition(view);
        if (position % mSpanCount == 0) {
            outRect.left = mHorizontal;
        } else {
            outRect.left = mHorizontal / 2;
        }

        if ((position + 1) % mSpanCount == 0) {
            outRect.right = mHorizontal;
        } else {
            outRect.right = mHorizontal / 2;
        }
        outRect.bottom = mVertical;

        // Add top margin only for the first item to avoid double space between items
        if (position < mSpanCount) {
            outRect.top = mVertical;
        } else {
            outRect.top = 0;
        }
    }
}