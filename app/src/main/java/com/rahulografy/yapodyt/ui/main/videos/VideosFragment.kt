package com.rahulografy.yapodyt.ui.main.videos

import androidx.fragment.app.viewModels
import com.rahulografy.yapodyt.R
import com.rahulografy.yapodyt.databinding.FragmentVideosBinding
import com.rahulografy.yapodyt.ui.base.view.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideosFragment : BaseFragment<FragmentVideosBinding, VideosFragmentViewModel>() {

    override val layoutRes get() = R.layout.fragment_videos

    override val toolbarId: Int get() = R.id.toolbarHome

    override val vm: VideosFragmentViewModel by viewModels()

    override fun initUi() {}
}
