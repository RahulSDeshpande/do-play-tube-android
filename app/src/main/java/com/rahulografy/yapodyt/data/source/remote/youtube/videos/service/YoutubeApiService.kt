package com.rahulografy.yapodyt.data.source.remote.youtube.videos.service

import com.rahulografy.yapodyt.data.model.YoutubeApiResponse
import com.rahulografy.yapodyt.util.Constants.Network.Key.YT_API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YoutubeApiService {

    @GET("youtube/v3/videos")
    suspend fun getMostPopularVideos(
        @Query("part") part: String = "snippet,contentDetails,statistics",
        @Query("chart") chart: String = "mostPopular",
        @Query("regionCode") regionCode: String = "UK",
        @Query("order") order: String = "date",
        @Query("maxResults") maxResults: Int = 20,
        @Query("key") key: String = YT_API_KEY
    ): Response<YoutubeApiResponse>
}
