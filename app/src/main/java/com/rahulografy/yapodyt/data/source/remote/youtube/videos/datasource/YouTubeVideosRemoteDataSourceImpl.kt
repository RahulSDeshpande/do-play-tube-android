package com.rahulografy.yapodyt.data.source.remote.youtube.videos.datasource

import com.rahulografy.yapodyt.data.source.remote.youtube.videos.service.YoutubeApiService
import javax.inject.Inject

class YouTubeVideosRemoteDataSourceImpl
@Inject constructor(
    private val youtubeApiService: YoutubeApiService
) : YouTubeVideosRemoteDataSource {

    override suspend fun getMostPopularVideos() =
        youtubeApiService.getMostPopularVideos()
}
