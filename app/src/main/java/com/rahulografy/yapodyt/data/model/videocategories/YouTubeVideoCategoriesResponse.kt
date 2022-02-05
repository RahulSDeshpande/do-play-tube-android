package com.rahulografy.yapodyt.data.model.videocategories

data class YouTubeVideoCategoriesResponse(
    val etag: String,
    val items: List<VideoCategoryItem>,
    val kind: String
)
