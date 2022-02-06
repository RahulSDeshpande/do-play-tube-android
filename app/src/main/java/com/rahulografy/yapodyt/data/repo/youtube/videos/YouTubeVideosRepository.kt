package com.rahulografy.yapodyt.data.repo.youtube.videos

import com.rahulografy.yapodyt.data.source.remote.youtube.videos.datasource.YouTubeVideosRemoteDataSource
import javax.inject.Inject

class YouTubeVideosRepository @Inject constructor(
    private val youTubeVideosRemoteDataSource: YouTubeVideosRemoteDataSource,
) : YouTubeVideosRemoteDataSource {

    override suspend fun getMostPopularVideos(
        videoCategoryId: String?,
        nextPageToken: String?
    ) = youTubeVideosRemoteDataSource.getMostPopularVideos(
        videoCategoryId = videoCategoryId,
        nextPageToken = nextPageToken
    )

    override suspend fun getVideoCategories() =
        youTubeVideosRemoteDataSource.getVideoCategories()
}
