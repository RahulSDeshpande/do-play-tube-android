package com.rahulografy.yapodyt.ui.main.videos.listener

import com.rahulografy.yapodyt.data.model.videos.VideoItem

interface VideoListListener {

    fun onVideoClicked(listPosition: Int, videoItem: VideoItem)
}
