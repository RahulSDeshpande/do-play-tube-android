package com.rahulografy.yapodyt.data.source.remote.youtune.videos.datasource

import com.rahulografy.yapodyt.data.model.YoutubeApiResponse
import retrofit2.Response

interface YouTubeVideosRemoteDataSource {

    suspend fun getMostPopularVideos(): Response<YoutubeApiResponse>
}
