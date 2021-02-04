package com.chanho.basic.util;


import androidx.recyclerview.widget.RecyclerView;


public abstract class RecyclerViewScrollListener extends RecyclerView.OnScrollListener {
    private int previousTotal = 0; // The total number of items in the dataset after the last load
    private boolean loading = true; // True if we are still waiting for the last set of data to load.
    private final int visibleThreshold = 10; // The minimum amount of items to have below your current scroll position before loading more.
    private static final String TAG = RecyclerViewScrollListener.class.getSimpleName();
    int firstVisibleItem, visibleItemCount, totalItemCount, lastVisibleItem;
    private boolean controlsFirst = true;
    RecyclerViewPositionHelper mRecyclerViewHelper;
    private final int currentPage = 1;
    private final LoadMorePosition mLoadMorePosition;

    public RecyclerViewScrollListener(LoadMorePosition loadMorePosition) {
        mLoadMorePosition = loadMorePosition;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        mRecyclerViewHelper = RecyclerViewPositionHelper.createHelper(recyclerView);
        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mRecyclerViewHelper.getItemCount();
        firstVisibleItem = mRecyclerViewHelper.findFirstVisibleItemPosition();
        lastVisibleItem = mRecyclerViewHelper.findLastCompletelyVisibleItemPosition();
            if (firstVisibleItem == 0) {
            controlsFirst = true;
            onVisibleFirst();
        }

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }
        if (LoadMorePosition.TOP == mLoadMorePosition) {
            if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                // End has been reached
                // Do something
                onLoadMore();
                loading = true;
            }
        } else {
            if (!loading && (firstVisibleItem < visibleThreshold)) {
                // End has been reached
                // Do something
                onLoadMore();
                loading = true;
            }
        }
    }

    public void setPreviousTotal(int previousTotal) {
        this.previousTotal = previousTotal;
    }

    //Start loading
    public abstract void onLoadMore();

    public abstract void onVisibleFirst();

    public enum LoadMorePosition {
        TOP, BOTTOM
    }

}