package com.rahulografy.yapodyt.util

object Constants {

    object Network {

        object Api {
            const val URL_BASE = "https://www.googleapis.com/"
        }

        object Key {
            // TODO | STORE THIS KEY AT A SECURE PLACE
            const val YT_API_KEY = "AIzaSyC3OdQwhX3J-ZSH8EDbF2PD3NPL90KNLtc"
        }

        object Timeout {
            const val CONNECTION = 10L
            const val WRITE = 10L
            const val READ = 30L
        }

        object Cache {
            const val NAME = "uapod-yt-cache"
        }

        object Argument {
            const val YOUTUBE_VIDEO_CHANNEL_NAME = "YOUTUBE_VIDEO_CHANNEL_NAME"
            const val YOUTUBE_VIDEO_ID = "YOUTUBE_VIDEO_ID"
        }
    }
}
