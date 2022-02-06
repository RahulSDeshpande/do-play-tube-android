package com.rahulografy.yapodyt.ui.main.videos.adapter

import androidx.recyclerview.widget.DiffUtil
import com.rahulografy.yapodyt.data.model.videos.VideoItem

class VideosDiffUtilItemCallback : DiffUtil.ItemCallback<VideoItem>() {

    override fun areItemsTheSame(
        oldItem: VideoItem,
        newItem: VideoItem
    ) = oldItem.id == newItem.id

    override fun areContentsTheSame(
        oldItem: VideoItem,
        newItem: VideoItem
    ) = oldItem.id == newItem.id
}
