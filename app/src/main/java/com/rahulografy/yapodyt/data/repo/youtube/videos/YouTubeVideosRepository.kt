package com.rahulografy.yapodyt.data.repo.youtube.videos

import com.rahulografy.yapodyt.data.source.remote.youtune.videos.datasource.YouTubeVideosRemoteDataSource
import javax.inject.Inject

class YouTubeVideosRepository @Inject constructor(
    private val youTubeVideosRemoteDataSource: YouTubeVideosRemoteDataSource,
) : YouTubeVideosRemoteDataSource {

    override suspend fun getMostPopularVideos() =
        youTubeVideosRemoteDataSource.getMostPopularVideos()
}
