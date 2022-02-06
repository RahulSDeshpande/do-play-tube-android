package com.rahulografy.yapodyt.ui.main.videos.fragment

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.viewModelScope
import com.rahulografy.yapodyt.data.model.videos.VideoItem
import com.rahulografy.yapodyt.data.model.videos.YouTubeVideosResponse
import com.rahulografy.yapodyt.data.repo.youtube.videos.YouTubeVideosRepository
import com.rahulografy.yapodyt.ui.base.view.BaseViewModel
import com.rahulografy.yapodyt.util.SingleLiveEvent
import com.rahulografy.yapodyt.util.ext.isNotNullOrBlank
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class VideosFragmentViewModel
@Inject constructor(
    private val videosRepository: YouTubeVideosRepository
) : BaseViewModel() {

    val isDataLoading = ObservableBoolean(false)

    var isLoading = false
    var isLastPage = false

    var videoItems = mutableListOf<VideoItem>()
    var videoItemsUpdated = SingleLiveEvent<Boolean>()

    private var youTubeVideosResponse: YouTubeVideosResponse? = null

    var nextPageToken: String? = null

    fun getVideos(
        refresh: Boolean = false,
        showLoader: Boolean = true,
        videoCategoryId: String? = null,
        pageToken: String? = null
    ) {
        if (showLoader) {
            isDataLoading.set(true)
        }

        viewModelScope.launch {
            val response =
                videosRepository.getMostPopularVideos(
                    videoCategoryId = videoCategoryId,
                    nextPageToken = if (refresh) null else nextPageToken
                )

            if (response.isSuccessful) {

                if (response.body() != null && response.body()?.items.isNullOrEmpty().not()) {

                    youTubeVideosResponse = response.body()

                    if (refresh) {
                        videoItems.clear()
                    }

                    videoItems.addAll(youTubeVideosResponse?.items!!)

                    if (youTubeVideosResponse?.nextPageToken.isNotNullOrBlank() &&
                        videoItems.size < youTubeVideosResponse?.pageInfo?.totalResults!!
                    ) {
                        isLastPage = false
                        nextPageToken = youTubeVideosResponse?.nextPageToken
                    } else {
                        isLastPage = true
                        nextPageToken = null
                    }

                    videoItemsUpdated.emit()
                } else {
                    // youTubeVideosResponse = null
                    // videoItems.postValue(null)
                }
            } else {
                // youTubeVideosResponse = null
                // videoItems.postValue(null)
            }

            isDataLoading.set(false)
        }
    }
}
