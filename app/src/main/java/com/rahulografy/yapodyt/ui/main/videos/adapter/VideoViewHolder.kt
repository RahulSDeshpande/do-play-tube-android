package com.rahulografy.yapodyt.ui.main.videos.adapter

import androidx.recyclerview.widget.RecyclerView
import com.rahulografy.yapodyt.data.model.VideoItem
import com.rahulografy.yapodyt.databinding.ItemVideoBinding
import com.rahulografy.yapodyt.ui.main.videos.listener.VideoEventListener

class VideoViewHolder(
    private val binding: ItemVideoBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        videoItem: VideoItem,
        videoEventListener: VideoEventListener?
    ) {
        binding.apply {

            root.setOnClickListener {
                videoEventListener?.onVideoClicked(
                    listPosition = adapterPosition,
                    videoItem = videoItem
                )
            }

            executePendingBindings()
        }
    }
}
