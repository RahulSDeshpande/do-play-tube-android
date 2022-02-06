package com.rahulografy.yapodyt.ui.main.searchfilter.adapter

import androidx.recyclerview.widget.DiffUtil
import com.rahulografy.yapodyt.data.model.videocategories.VideoCategoryItem

class VideoCategoryItemDiffUtilItemCallback : DiffUtil.ItemCallback<VideoCategoryItem>() {

    override fun areItemsTheSame(
        oldItem: VideoCategoryItem,
        newItem: VideoCategoryItem
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: VideoCategoryItem,
        newItem: VideoCategoryItem
    ) = oldItem.id == newItem.id
}
