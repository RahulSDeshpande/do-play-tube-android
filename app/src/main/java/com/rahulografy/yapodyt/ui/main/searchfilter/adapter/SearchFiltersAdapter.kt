package com.rahulografy.yapodyt.ui.main.searchfilter.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.rahulografy.yapodyt.R
import com.rahulografy.yapodyt.data.model.videocategories.VideoCategoryItem
import com.rahulografy.yapodyt.ui.base.adapter.BaseListAdapter
import com.rahulografy.yapodyt.ui.main.searchfilter.listener.VideoCategoryListListener

class SearchFiltersAdapter(
    private val videoCategoryListListener: VideoCategoryListListener? = null
) : BaseListAdapter<VideoCategoryItem, SearchFilterViewHolder>(
    VideoCategoryItemDiffUtilItemCallback()
) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ) = SearchFilterViewHolder(
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_search_filter,
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        viewHolder: SearchFilterViewHolder,
        position: Int
    ) = viewHolder.bind(
        videoCategoryItem = getItem(position),
        videoCategoryListListener = videoCategoryListListener
    )

    override fun setData(data: List<VideoCategoryItem>?) {
        submitList(data)
    }

    override fun addData(data: List<VideoCategoryItem>?) {}
}
