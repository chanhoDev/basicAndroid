package com.chanho.basic.util

import androidx.recyclerview.widget.RecyclerView
import com.chanho.basic.util.RecyclerViewPositionHelper.Companion.createHelper

abstract class RecyclerViewScrollListener : RecyclerView.OnScrollListener() {
    private var previousTotal =
        0 // The total number of items in the dataset after the last load
    private var loading =
        true // True if we are still waiting for the last set of data to load.
    var firstVisibleItem = 0
    var visibleItemCount = 0
    var totalItemCount = 0
    var mRecyclerViewHelper: RecyclerViewPositionHelper? = null
    private var currentPage = 1
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        mRecyclerViewHelper = createHelper(recyclerView)
        visibleItemCount = recyclerView.childCount
        totalItemCount = mRecyclerViewHelper!!.itemCount
        firstVisibleItem = mRecyclerViewHelper!!.findFirstVisibleItemPosition()
        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }
        if (!loading) {
            loading = true
            onLoadMore(currentPage++)
        }
        if (totalItemCount == 0) {
            previousTotal = 0
            currentPage = 1
        }
    }

    //Start loading
    abstract fun onLoadMore(currentPage: Int)

    companion object {
        private val TAG = RecyclerViewScrollListener::class.java.simpleName
    }
}