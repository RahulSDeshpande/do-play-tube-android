package com.rahulografy.yapodyt.ui.main.videoplayer

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.rahulografy.yapodyt.BR
import com.rahulografy.yapodyt.R
import com.rahulografy.yapodyt.databinding.FragmentVideoPlayerBinding
import com.rahulografy.yapodyt.ui.base.view.BaseDialogFragment
import com.rahulografy.yapodyt.ui.main.activity.MainActivityViewModel
import com.rahulografy.yapodyt.util.ext.isNotNullOrBlank
import com.rahulografy.yapodyt.util.ext.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideoPlayerFragment :
    BaseDialogFragment<FragmentVideoPlayerBinding, VideoPlayerFragmentViewModel>() {

    override val layoutRes get() = R.layout.fragment_video_player

    override val bindingVariable = BR.viewModel

    override val vm: VideoPlayerFragmentViewModel by viewModels()

    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
    }

    private fun initToolbar() {
        vdb.toolbarVideoPlayer.apply {
            inflateMenu(R.menu.menu_close)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.menu_action_close -> {
                        close()
                        true
                    }
                    else -> false
                }
            }
        }
    }

    override fun initUi() {

        getVideoInfo()

        initYouTubeVideoPlayer()

        initVideoInfo()
    }

    private fun getVideoInfo() {

        vm.videoItem = mainActivityViewModel.selectedVideoItem

        if (vm.videoItem != null) {
            vdb.toolbarVideoPlayer.title = vm.videoItem?.snippet?.channelTitle

            val videoId = vm.videoItem?.id

            if (videoId.isNotNullOrBlank()) {
                vm.youTubeVideoId = videoId
            } else {
                showVideoError()
            }
        }
    }

    private fun initYouTubeVideoPlayer() {

        vdb.youtubePlayerView.apply {

            lifecycle.addObserver(this)

            addYouTubePlayerListener(
                object : AbstractYouTubePlayerListener() {

                    override fun onError(
                        youTubePlayer: YouTubePlayer,
                        error: PlayerConstants.PlayerError
                    ) {
                        showVideoError()
                    }

                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.loadVideo(
                            videoId = vm.youTubeVideoId.toString(),
                            startSeconds = 0f
                        )
                    }
                }
            )
        }
    }

    private fun initVideoInfo() {
        vdb.apply {

            textViewVideoTitle.text = vm.videoItem?.snippet?.title
            textViewVideoChannelName.text = vm.videoItem?.snippet?.channelTitle

            textViewVideoLikes.text = vm.videoItem?.statistics?.likeCount
            textViewVideoViews.text = vm.videoItem?.statistics?.viewCount
            textViewVideoComments.text = vm.videoItem?.statistics?.commentCount

            textViewVideoDescription.text = vm.videoItem?.snippet?.description
            textViewVideoDescription.movementMethod = ScrollingMovementMethod()
        }
    }

    private fun showVideoError() {
        toast(getString(R.string.error_occurred_while_loading_video))
    }
}
