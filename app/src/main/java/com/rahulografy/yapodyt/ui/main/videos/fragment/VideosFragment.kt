package com.rahulografy.yapodyt.ui.main.videos.fragment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahulografy.yapodyt.BR
import com.rahulografy.yapodyt.R
import com.rahulografy.yapodyt.data.model.videos.VideoItem
import com.rahulografy.yapodyt.databinding.FragmentVideosBinding
import com.rahulografy.yapodyt.ui.base.view.BaseFragment
import com.rahulografy.yapodyt.ui.component.view.PaginationListener
import com.rahulografy.yapodyt.ui.main.activity.MainActivityViewModel
import com.rahulografy.yapodyt.ui.main.searchfilter.SearchFiltersFragment
import com.rahulografy.yapodyt.ui.main.videoplayer.VideoPlayerFragment
import com.rahulografy.yapodyt.ui.main.videos.adapter.VideosAdapter
import com.rahulografy.yapodyt.ui.main.videos.listener.VideoListListener
import com.rahulografy.yapodyt.util.ext.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideosFragment :
    BaseFragment<FragmentVideosBinding, VideosFragmentViewModel>(),
    VideoListListener {

    private var videosAdapter: VideosAdapter? = null

    private var searchFiltersFragment: SearchFiltersFragment? = null

    override val layoutRes get() = R.layout.fragment_videos

    override val bindingVariable = BR.viewModel

    override val vm: VideosFragmentViewModel by viewModels()

    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
    }

    private fun initToolbar() {
        vdb.toolbarVideos.apply {
            inflateMenu(R.menu.menu_videos)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.menu_action_filter -> {
                        openSearchFiltersFragment()
                        true
                    }
                    else -> false
                }
            }
        }
    }

    override fun initUi() {
        updateCategoryHeader()
        getVideos()
        initSwipeRefreshLayout()
    }

    override fun initObservers() {

        mainActivityViewModel
            .videoCategoryItems
            .observe(
                lifecycleOwner = this,
                observer = { videoCategoryItems ->
                    if (videoCategoryItems.isNotNullOrEmpty()) {
                        toast(
                            getString(
                                R.string.showing_videos_for_category,
                                mainActivityViewModel.videoCategoryItem?.snippet?.title
                            )
                        )
                        updateCategoryHeader()
                        vm.getVideos(videoCategoryId = getVideoCategoryId())
                    } else {
                        toast(getString(R.string.msg_no_video_categories))
                    }
                }
            )

        mainActivityViewModel
            .videoCategoryItemUpdated
            .observe(
                lifecycleOwner = this,
                observer = {
                    updateCategoryHeader()
                    vdb.recyclerViewVideos.smoothScrollToPosition(0)
                    vm.getVideos(
                        refresh = true,
                        videoCategoryId = getVideoCategoryId()
                    )
                }
            )

        vm.videoItemsUpdated
            .observe(
                lifecycleOwner = this,
                observer = {
                    updateVideos(videoItems = vm.videoItems)
                }
            )
    }

    private fun updateCategoryHeader() {
        vdb.textViewVideosCategory.text =
            mainActivityViewModel.videoCategoryItem?.snippet?.title
    }

    private fun getVideos() {

        if (mainActivityViewModel.videoCategoryItems.value.isNullOrEmpty() ||
            mainActivityViewModel.videoCategoryItem == null
        ) {
            vm.isDataLoading.set(true)
            mainActivityViewModel.getVideoCategories()
        } else {
            vm.getVideos(
                refresh = true,
                videoCategoryId = getVideoCategoryId()
            )
        }
    }

    private fun getVideoCategoryId() = mainActivityViewModel.videoCategoryItem?.id

    private fun initSwipeRefreshLayout() {
        vdb.swipeRefreshLayoutVideos.setOnRefreshListener {
            getVideos()
        }
    }

    private fun updateVideos(videoItems: List<VideoItem>?) {

        if (videoItems.isNullOrEmpty().not()) {

            if (videosAdapter == null) {
                videosAdapter = VideosAdapter(videoListListener = this)
                vdb.recyclerViewVideos.apply {
                    adapter = videosAdapter
                    list()

                    addOnScrollListener(
                        object : PaginationListener(layoutManager as LinearLayoutManager) {

                            override fun loadMoreItems() {
                                vm.isLoading = true
                                toast(
                                    text = getString(R.string.loading_more_data),
                                    duration = Toast.LENGTH_SHORT
                                )
                                vm.getVideos(videoCategoryId = getVideoCategoryId())
                            }

                            override val isLastPage: Boolean
                                get() = vm.isLastPage

                            override val isLoading: Boolean
                                get() = vm.isLoading
                        }
                    )
                }
            }

            // videosAdapter?.currentList?.clear()
            videosAdapter?.submitList(videoItems.toArrayList())
            vm.isLoading = false

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
            videoItem = videoItem
        )
    }

    private fun openSearchFiltersFragment() {
        if (searchFiltersFragment == null) {
            searchFiltersFragment = SearchFiltersFragment()
        }
        searchFiltersFragment?.show(childFragmentManager, searchFiltersFragment?.tag)
    }

    private fun openVideoPlayerFragment(
        listPosition: Int,
        videoItem: VideoItem
    ) {
        mainActivityViewModel.selectedVideoItem = videoItem

        val videoPlayerFragment = VideoPlayerFragment()
        videoPlayerFragment.show(childFragmentManager, videoPlayerFragment.tag)
    }
}
