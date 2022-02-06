package com.rahulografy.yapodyt.ui.component.view

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rahulografy.yapodyt.util.Constants.Network.Api.MAX_RESULTS_PER_PAGE

/**
 * Supporting only LinearLayoutManager for now.
 */
abstract class PaginationListener(
    private val layoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (!isLoading && !isLastPage) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount &&
                firstVisibleItemPosition >= 0 && totalItemCount >= PAGE_SIZE
            ) {
                loadMoreItems()
            }
        }
    }

    protected abstract fun loadMoreItems()

    abstract val isLastPage: Boolean

    abstract val isLoading: Boolean

    companion object {
        private const val PAGE_SIZE = MAX_RESULTS_PER_PAGE
    }
}
