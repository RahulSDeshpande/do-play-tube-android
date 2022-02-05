package com.rahulografy.yapodyt.ui.main.videos.fragment

import androidx.databinding.ObservableBoolean
import com.rahulografy.yapodyt.data.model.VideoItem
import com.rahulografy.yapodyt.data.repo.youtube.videos.YouTubeVideosRepository
import com.rahulografy.yapodyt.ui.base.view.BaseViewModel
import com.rahulografy.yapodyt.util.Coroutines
import com.rahulografy.yapodyt.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VideosFragmentViewModel
@Inject constructor(
    private val videosRepository: YouTubeVideosRepository
) : BaseViewModel() {

    val isDataLoading = ObservableBoolean(false)

    // private var _videoItems = SingleLiveEvent<List<VideoItem>>()
    var videoItems = SingleLiveEvent<List<VideoItem>>()

    fun getVideos(
        force: Boolean = false,
        showLoader: Boolean = true
    ) {
        if (force || videoItems.value.isNullOrEmpty()) {

            if (showLoader) {
                isDataLoading.set(true)
            }

            Coroutines.main {
                val response = videosRepository.getMostPopularVideos()

                if (response.isSuccessful) {
                    if (response.body()?.items.isNullOrEmpty().not()) {
                        videoItems.postValue(response.body()?.items)
                    } else {
                        videoItems.postValue(listOf())
                    }
                }

                isDataLoading.set(false)
            }
        } else {
            if (showLoader) {
                isDataLoading.set(false)
            }
        }
    }
}