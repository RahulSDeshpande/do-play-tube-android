package com.rahulografy.yapodyt.ui.main.videoplayer

import androidx.fragment.app.viewModels
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.rahulografy.yapodyt.R
import com.rahulografy.yapodyt.databinding.FragmentVideoPlayerBinding
import com.rahulografy.yapodyt.ui.base.view.BaseDialogFragment
import com.rahulografy.yapodyt.util.Constants.Network.Argument.YOUTUBE_VIDEO_CHANNEL_NAME
import com.rahulografy.yapodyt.util.Constants.Network.Argument.YOUTUBE_VIDEO_ID
import com.rahulografy.yapodyt.util.ext.isNotNullOrBlank
import com.rahulografy.yapodyt.util.ext.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideoPlayerFragment :
    BaseDialogFragment<FragmentVideoPlayerBinding, VideoPlayerFragmentViewModel>() {

    private var youTubeVideoId = ""

    override val layoutRes get() = R.layout.fragment_video_player

    override val vm: VideoPlayerFragmentViewModel by viewModels()

    override fun initUi() {

        getVideoInfo()

        initYouTubeVideoPlayer()
    }

    private fun getVideoInfo() {

        val channelName = arguments?.getString(YOUTUBE_VIDEO_CHANNEL_NAME)

        if (channelName.isNotNullOrBlank()) {
            vdb.toolbarVideoPlayer.title = channelName
        }

        val videoId = arguments?.getString(YOUTUBE_VIDEO_ID)

        if (!videoId.isNullOrBlank()) {
            youTubeVideoId = videoId
        } else {
            showVideoError()
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

    private fun close() {
        dismissAllowingStateLoss()
    }
}
