package com.rahulografy.yapodyt.data.source.remote.youtube.videos.service

import com.rahulografy.yapodyt.data.model.videocategories.YouTubeVideoCategoriesResponse
import com.rahulografy.yapodyt.data.model.videos.YouTubeVideosResponse
import com.rahulografy.yapodyt.util.Constants.Network.Api.MAX_RESULTS_PER_PAGE
import com.rahulografy.yapodyt.util.Constants.Network.Api.REGION_CODE
import com.rahulografy.yapodyt.util.Constants.Network.Key.YT_API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface YouTubeApiService {

    @GET("youtube/v3/videos")
    suspend fun getMostPopularVideos(
        @Query("part") part: String = "snippet,contentDetails,statistics",
        @Query("chart") chart: String = "mostPopular",
        @Query("regionCode") regionCode: String = REGION_CODE,
        @Query("order") order: String = "date",
        @Query("maxResults") maxResults: Int = MAX_RESULTS_PER_PAGE,
        @Query("videoCategoryId") videoCategoryId: String? = null,
        @Query("pageToken") pageToken: String? = null,
        @Query("key") key: String = YT_API_KEY
    ): Response<YouTubeVideosResponse>

    @GET("youtube/v3/videoCategories")
    suspend fun getVideoCategories(
        @Query("part") part: String = "snippet",
        @Query("regionCode") regionCode: String = REGION_CODE,
        @Query("key") key: String = YT_API_KEY
    ): Response<YouTubeVideoCategoriesResponse>
}
