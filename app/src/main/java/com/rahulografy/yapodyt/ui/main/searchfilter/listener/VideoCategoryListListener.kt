package com.rahulografy.yapodyt.ui.main.searchfilter.listener

import com.rahulografy.yapodyt.data.model.videocategories.VideoCategoryItem

interface VideoCategoryListListener {

    fun onVideoCategoryClicked(
        listPosition: Int,
        videoCategoryItem: VideoCategoryItem
    )
}
