package com.rahulografy.yapodyt.ui.main.videos.fragment

import androidx.fragment.app.viewModels
import com.rahulografy.yapodyt.R
import com.rahulografy.yapodyt.data.model.VideoItem
import com.rahulografy.yapodyt.databinding.FragmentVideosBinding
import com.rahulografy.yapodyt.ui.base.view.BaseFragment
import com.rahulografy.yapodyt.ui.main.videos.adapter.VideosAdapter
import com.rahulografy.yapodyt.ui.main.videos.listener.VideoEventListener
import com.rahulografy.yapodyt.util.ext.list
import com.rahulografy.yapodyt.util.ext.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideosFragment :
    BaseFragment<FragmentVideosBinding, VideosFragmentViewModel>(),
    VideoEventListener {

    private lateinit var videosAdapter: VideosAdapter

    override val layoutRes get() = R.layout.fragment_videos

    override val toolbarId: Int get() = R.id.toolbar_videos

    override val vm: VideosFragmentViewModel by viewModels()

    override fun initUi() {

        videosAdapter = VideosAdapter(videoEventListener = this)

        vm.getVideos(force = true)
    }

    override fun initObservers() {
        vm.apply {
            videoItems
                .observe(
                    lifecycleOwner = this@VideosFragment,
                    observer = { videoItems ->
                        initVideosRecyclerView(videoItems = videoItems)
                    }
                )
        }
    }

    private fun initVideosRecyclerView(videoItems: List<VideoItem>?) {
        if (videoItems.isNullOrEmpty().not()) {
            vdb.recyclerViewVideos.adapter = videosAdapter
            vdb.recyclerViewVideos.list()
            videosAdapter.submitList(videoItems)
            showVideosRecyclerView(show = true)
        } else {
            showVideosRecyclerView(show = false)
        }
    }

    private fun showVideosRecyclerView(show: Boolean) {
        vdb.recyclerViewVideos.show(show = show)
        vdb.layoutNoData.show(show = show.not())
    }

    override fun onVideoClicked(
        listPosition: Int,
        videoItem: VideoItem
    ) {
        openVideoPlayerFragment(
            listPosition = listPosition,
            postId = videoItem
        )
    }

    private fun openVideoPlayerFragment(
        listPosition: Int,
        postId: VideoItem
    ) {
        // TODO | OPEN VIDEO PLAYER BOTTOM SHEET
    }
}
