package com.rahulografy.yapodyt.data.model.videos

data class YouTubeVideosResponse(
    val items: MutableList<VideoItem>,
    val prevPageToken: String? = null,
    val nextPageToken: String? = null,
    val pageInfo: PageInfo
)
