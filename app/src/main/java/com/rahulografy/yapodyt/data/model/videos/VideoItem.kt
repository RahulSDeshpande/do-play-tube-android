package com.rahulografy.yapodyt.data.model.videos

data class VideoItem(
    val etag: String,
    val id: String,
    val kind: String,
    val snippet: Snippet,
    val contentDetails: ContentDetails,
    val statistics: Statistics
)
