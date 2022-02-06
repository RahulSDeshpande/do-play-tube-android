package com.rahulografy.yapodyt.ui.main.videos.fragment

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.viewModelScope
import com.rahulografy.yapodyt.data.model.videos.VideoItem
import com.rahulografy.yapodyt.data.model.videos.YouTubeVideosResponse
import com.rahulografy.yapodyt.data.repo.youtube.videos.YouTubeVideosRepository
import com.rahulografy.yapodyt.ui.base.view.BaseViewModel
import com.rahulografy.yapodyt.util.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class VideosFragmentViewModel
@Inject constructor(
    private val videosRepository: YouTubeVideosRepository
) : BaseViewModel() {

    val isDataLoading = ObservableBoolean(false)

    var videoItems = SingleLiveEvent<List<VideoItem>?>()

    private var youTubeVideosResponse: YouTubeVideosResponse? = null

    fun getVideos(
        force: Boolean = false,
        showLoader: Boolean = true,
        videoCategoryId: String? = null,
        nextPageToken: String? = null
    ) {
        if (force || youTubeVideosResponse?.items.isNullOrEmpty()) {

            if (showLoader) {
                isDataLoading.set(true)
            }

            viewModelScope.launch {
                val response =
                    videosRepository.getMostPopularVideos(
                        videoCategoryId = videoCategoryId,
                        nextPageToken = nextPageToken
                    )

                if (response.isSuccessful) {
                    if (response.body() != null && response.body()?.items.isNullOrEmpty().not()) {
                        youTubeVideosResponse = response.body()
                        videoItems.postValue(youTubeVideosResponse?.items)
                    } else {
                        youTubeVideosResponse = null
                        videoItems.postValue(null)
                    }
                } else {
                    youTubeVideosResponse = null
                    videoItems.postValue(null)
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
