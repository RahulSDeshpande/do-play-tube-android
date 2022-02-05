package com.rahulografy.yapodyt.ui.main.videos.listener

import com.rahulografy.yapodyt.data.model.VideoItem

interface VideoEventListener {

    fun onVideoClicked(listPosition: Int, videoItem: VideoItem)
}
