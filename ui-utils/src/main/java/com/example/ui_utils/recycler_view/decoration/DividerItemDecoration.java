package com.example.ui_utils.recycler_view.decoration;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by kcrimi on 9/19/16.
 */
public class DividerItemDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    private Drawable mDrawable;
    private boolean mAboveFirstItem = false;
    private boolean mBelowLastItem = true;
    private int mAdditionalPadding = 0;

    /**
     * Default divider will be used
     */

    public DividerItemDecoration(Context context) {
        final TypedArray styleAttributes = context.obtainStyledAttributes(ATTRS);
        mDrawable = styleAttributes.getDrawable(0);
        styleAttributes.recycle();
    }

    public DividerItemDecoration(Context context, boolean aboveFirstItem) {
        this(context);
        mAboveFirstItem = aboveFirstItem;
    }

    public void setBelowLastItem(boolean belowLastItem) {
        mBelowLastItem = belowLastItem;
    }

    /**
     * Custom divider will be used
     */
    public DividerItemDecoration(Context context, boolean aboveFirstItem, int resId) {
        mAboveFirstItem = aboveFirstItem;
        mDrawable = ContextCompat.getDrawable(context, resId);
    }

    public void setDrawable(Drawable drawable) {
        mDrawable = drawable;
    }

    public void setAdditionalPadding(Context context, int dimenRes) {
        mAdditionalPadding = (int) context.getResources().getDimension(dimenRes);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = mDrawable.getIntrinsicHeight();
        if (parent.getChildAdapterPosition(view) == 0 && mAboveFirstItem) {
            outRect.top = mDrawable.getIntrinsicHeight();
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft() + mAdditionalPadding;
        int right = parent.getWidth() - parent.getPaddingRight() - mAdditionalPadding;

        int childCount = state.getItemCount();
        if (!mBelowLastItem) {
            childCount--;
        }
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            if(child != null) {
                RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

                if (i == 0 && mAboveFirstItem) {
                    int topDividerBottom = child.getTop() - params.topMargin;
                    int topDividerTop = topDividerBottom - mDrawable.getIntrinsicHeight();
                    mDrawable.setBounds(left, topDividerTop, right, topDividerBottom);
                    mDrawable.draw(c);
                }

                int top = child.getBottom() + params.bottomMargin;
                int bottom = top + mDrawable.getIntrinsicHeight();

                mDrawable.setBounds(left, top, right, bottom);
                mDrawable.draw(c);
            }
        }
    }


}