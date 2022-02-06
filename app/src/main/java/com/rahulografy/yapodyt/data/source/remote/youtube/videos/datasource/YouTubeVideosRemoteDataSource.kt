package com.rahulografy.yapodyt.data.source.remote.youtube.videos.datasource

import com.rahulografy.yapodyt.data.model.videocategories.YouTubeVideoCategoriesResponse
import com.rahulografy.yapodyt.data.model.videos.YouTubeVideosResponse
import retrofit2.Response

interface YouTubeVideosRemoteDataSource {

    suspend fun getMostPopularVideos(
        videoCategoryId: String? = null,
        nextPageToken: String? = null
    ): Response<YouTubeVideosResponse>

    suspend fun getVideoCategories(): Response<YouTubeVideoCategoriesResponse>
}
