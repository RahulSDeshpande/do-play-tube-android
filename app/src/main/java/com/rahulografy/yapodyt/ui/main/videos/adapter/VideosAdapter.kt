package com.rahulografy.yapodyt.ui.main.videos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.rahulografy.yapodyt.R
import com.rahulografy.yapodyt.data.model.videos.VideoItem
import com.rahulografy.yapodyt.ui.base.adapter.BaseListAdapter
import com.rahulografy.yapodyt.ui.main.videos.listener.VideoListListener

class VideosAdapter(
    private val videoListListener: VideoListListener? = null
) : BaseListAdapter<VideoItem, VideoViewHolder>(VideosDiffUtilItemCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VideoViewHolder(
            binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_video,
                parent,
                false
            )
        )

    override fun onBindViewHolder(viewHolder: VideoViewHolder, position: Int) =
        viewHolder.bind(
            videoItem = getItem(position),
            videoListListener = videoListListener
        )

    override fun setData(data: List<VideoItem>?) {
        submitList(data)
    }

    override fun addData(data: List<VideoItem>?) {}
}
