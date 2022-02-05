package com.rahulografy.yapodyt.data.model.videos

import com.rahulografy.yapodyt.util.ext.isNotNullOrBlank

data class Thumbnails(
    val default: Default?,
    val high: High?,
    val medium: Medium?,
    val standard: Standard?,
    val maxRes: MaxRes?
) {
    fun hiRes() =
        if (maxRes != null && maxRes.url.isNotNullOrBlank()) {
            maxRes.url
        } else if (standard != null && standard.url.isNotNullOrBlank()) {
            standard.url
        } else if (high != null && high.url.isNotNullOrBlank()) {
            high.url
        } else if (medium != null && medium.url.isNotNullOrBlank()) {
            medium.url
        } else if (default != null && default.url.isNotNullOrBlank()) {
            default.url
        } else ""
}
