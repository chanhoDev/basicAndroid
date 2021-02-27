
package com.chanho.basic.util;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by sy-02 on 2018-04-19.
 */

public abstract class RecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    private int previousTotal = 0; // The total number of items in the dataset after the last load
    private boolean loading = true; // True if we are still waiting for the last set of data to load.
    private static final String TAG = RecyclerViewScrollListener.class.getSimpleName();
    int firstVisibleItem, visibleItemCount, totalItemCount;
    RecyclerViewPositionHelper mRecyclerViewHelper;
    private int currentPage = 1;

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        mRecyclerViewHelper = RecyclerViewPositionHelper.createHelper(recyclerView);
        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mRecyclerViewHelper.getItemCount();
        firstVisibleItem = mRecyclerViewHelper.findFirstVisibleItemPosition();

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }

        if (!loading) {
            loading = true;
            onLoadMore(currentPage++);
        }

        if (totalItemCount == 0) {
            previousTotal = 0;
            currentPage = 1;
        }
    }

    //Start loading
    public abstract void onLoadMore(int currentPage);

}

