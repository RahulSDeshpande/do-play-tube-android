package com.rahulografy.yapodyt.ui.main.videos.adapter

import androidx.recyclerview.widget.RecyclerView
import com.rahulografy.yapodyt.R
import com.rahulografy.yapodyt.data.model.videos.VideoItem
import com.rahulografy.yapodyt.databinding.ItemVideoBinding
import com.rahulografy.yapodyt.ui.main.videos.listener.VideoEventListener
import com.squareup.picasso.Picasso

class VideoViewHolder(
    private val binding: ItemVideoBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        videoItem: VideoItem,
        videoEventListener: VideoEventListener?
    ) {
        binding.apply {

            Picasso
                .get()
                .load(videoItem.snippet.thumbnails.hiRes())
                .placeholder(R.drawable.ic_launcher_foreground_grey)
                .into(imageViewVideoThumbnail)

            textViewVideoDuration.text = videoItem.contentDetails.duration

            textViewVideoTitle.text = videoItem.snippet.title

            textViewVideoChannelName.text = videoItem.snippet.channelTitle

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
