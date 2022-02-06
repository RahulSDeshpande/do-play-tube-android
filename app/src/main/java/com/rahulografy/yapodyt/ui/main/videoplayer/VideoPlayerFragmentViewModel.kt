package com.rahulografy.yapodyt.ui.main.videoplayer

import com.rahulografy.yapodyt.data.model.videos.VideoItem
import com.rahulografy.yapodyt.ui.base.view.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VideoPlayerFragmentViewModel
@Inject constructor() : BaseViewModel() {

    var youTubeVideoId: String? = null

    var videoItem: VideoItem? = null
}
