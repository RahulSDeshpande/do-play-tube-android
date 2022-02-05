package com.rahulografy.yapodyt.data.model.videos

data class YouTubeVideosResponse(
    val items: List<VideoItem>,
    val prevPageToken: String? = null,
    val nextPageToken: String? = null,
    val pageInfo: PageInfo
)
