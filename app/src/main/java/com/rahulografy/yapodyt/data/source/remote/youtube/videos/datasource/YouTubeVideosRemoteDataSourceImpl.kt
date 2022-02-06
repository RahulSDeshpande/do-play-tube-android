package com.rahulografy.yapodyt.data.source.remote.youtube.videos.datasource

import com.rahulografy.yapodyt.data.source.remote.youtube.videos.service.YouTubeApiService
import javax.inject.Inject

class YouTubeVideosRemoteDataSourceImpl
@Inject constructor(
    private val youTubeApiService: YouTubeApiService
) : YouTubeVideosRemoteDataSource {

    override suspend fun getMostPopularVideos(
        videoCategoryId: String?,
        nextPageToken: String?
    ) = youTubeApiService.getMostPopularVideos(
        videoCategoryId = videoCategoryId,
        pageToken = nextPageToken
    )

    override suspend fun getVideoCategories() =
        youTubeApiService.getVideoCategories()
}
