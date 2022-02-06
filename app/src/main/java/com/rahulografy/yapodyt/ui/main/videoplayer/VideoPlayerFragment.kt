package com.rahulografy.yapodyt.ui.main.videoplayer

import android.view.MenuItem
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
import com.rahulografy.yapodyt.util.ext.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideoPlayerFragment :
    BaseDialogFragment<FragmentVideoPlayerBinding, VideoPlayerFragmentViewModel>() {

    private var youTubeVideoId = ""

    override val layoutRes get() = R.layout.fragment_video_player

    override val bindingVariable = BR.viewModel

    override val vm: VideoPlayerFragmentViewModel by viewModels()

    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) close()
        return super.onOptionsItemSelected(item)
    }

    override fun initUi() {

        getVideoInfo()

        initYouTubeVideoPlayer()
    }

    private fun getVideoInfo() {

        val videoItem = mainActivityViewModel.selectedVideoItem

        if (videoItem != null) {
            vdb.toolbarVideoPlayer.title = videoItem.snippet.channelTitle

            val videoId = videoItem.id

            if (videoId.isNotBlank()) {
                youTubeVideoId = videoId
            } else {
                showVideoError()
            }
        }
    }

    private fun initYouTubeVideoPlayer() {

        vdb.youtubePlayerView.apply {
            lifecycle.addObserver(vdb.youtubePlayerView)

            addYouTubePlayerListener(
                object : AbstractYouTubePlayerListener() {

                    override fun onError(
                        youTubePlayer: YouTubePlayer,
                        error: PlayerConstants.PlayerError
                    ) {
                        showVideoError()
                    }

                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.loadVideo(youTubeVideoId, 0f)
                    }
                }
            )
        }
    }

    private fun showVideoError() {
        toast("Error occurred while loading this Youtube video, please try again.")
    }
}
