package com.rahulografy.yapodyt.ui.main.activity

import androidx.lifecycle.viewModelScope
import com.rahulografy.yapodyt.data.model.videocategories.VideoCategoryItem
import com.rahulografy.yapodyt.data.model.videos.VideoItem
import com.rahulografy.yapodyt.data.repo.youtube.videos.YouTubeVideosRepository
import com.rahulografy.yapodyt.ui.base.view.BaseViewModel
import com.rahulografy.yapodyt.util.SingleLiveEvent
import com.rahulografy.yapodyt.util.ext.isNotNullOrBlank
import com.rahulografy.yapodyt.util.ext.videoCategoryId
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MainActivityViewModel
@Inject constructor(
    private val videosRepository: YouTubeVideosRepository
) : BaseViewModel() {

    var videoCategoryItems = SingleLiveEvent<List<VideoCategoryItem>>()

    var videoCategoryItem: VideoCategoryItem? = null
    var videoCategoryItemUpdated = SingleLiveEvent<Boolean>()

    var selectedVideoItem: VideoItem? = null

    fun getVideoCategories(force: Boolean = false) {

        if (force || videoCategoryItems.value.isNullOrEmpty()) {

            viewModelScope.launch {

                val response = videosRepository.getVideoCategories()

                if (response.isSuccessful) {

                    val items = response.body()?.items

                    videoCategoryItems.postValue(
                        if (response.body() != null && items.isNullOrEmpty().not()) {

                            if (videoCategoryId.isNotNullOrBlank()) {
                                val item = items?.find { it.id == videoCategoryId }
                                item?.isChecked = true
                                videoCategoryItem = item
                            } else {
                                items?.first()?.isChecked = true
                                videoCategoryItem = items?.first()
                                videoCategoryId = videoCategoryItem?.id
                            }

                            items?.first()?.isChecked = true

                            items
                        } else listOf()
                    )
                } else videoCategoryItems.postValue(listOf())
            }
        }
    }
}
