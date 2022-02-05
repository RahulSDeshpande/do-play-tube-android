package com.rahulografy.yapodyt.data.source.remote.youtube.videos.datasource

import com.rahulografy.yapodyt.data.model.YouTubeApiResponse
import retrofit2.Response

interface YouTubeVideosRemoteDataSource {

    suspend fun getMostPopularVideos(): Response<YouTubeApiResponse>
}
