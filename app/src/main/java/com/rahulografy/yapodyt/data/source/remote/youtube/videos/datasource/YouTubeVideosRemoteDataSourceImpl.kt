package com.rahulografy.yapodyt.data.source.remote.youtube.videos.datasource

import com.rahulografy.yapodyt.data.source.remote.youtube.videos.service.YouTubeApiService
import javax.inject.Inject

class YouTubeVideosRemoteDataSourceImpl
@Inject constructor(
    private val youTubeApiService: YouTubeApiService
) : YouTubeVideosRemoteDataSource {

    override suspend fun getMostPopularVideos() =
        youTubeApiService.getMostPopularVideos()

    override suspend fun getVideoCategories() =
        youTubeApiService.getVideoCategories()
}
