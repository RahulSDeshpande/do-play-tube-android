package com.rahulografy.yapodyt.ui.main.searchfilter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.rahulografy.yapodyt.BR
import com.rahulografy.yapodyt.R
import com.rahulografy.yapodyt.data.model.videocategories.VideoCategoryItem
import com.rahulografy.yapodyt.databinding.FragmentSearchFiltersBinding
import com.rahulografy.yapodyt.ui.base.view.BaseDialogFragment
import com.rahulografy.yapodyt.ui.main.activity.MainActivityViewModel
import com.rahulografy.yapodyt.ui.main.searchfilter.adapter.SearchFiltersAdapter
import com.rahulografy.yapodyt.ui.main.searchfilter.listener.VideoCategoryListListener
import com.rahulografy.yapodyt.util.ext.isNotNullOrEmpty
import com.rahulografy.yapodyt.util.ext.list
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFiltersFragment :
    BaseDialogFragment<FragmentSearchFiltersBinding, SearchFiltersFragmentViewModel>(),
    VideoCategoryListListener {

    private lateinit var searchFiltersAdapter: SearchFiltersAdapter

    override val layoutRes get() = R.layout.fragment_search_filters

    override val bindingVariable = BR.viewModel

    override val vm: SearchFiltersFragmentViewModel by viewModels()

    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
    }

    private fun initToolbar() {
        vdb.toolbarSearchFilters.apply {
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
        initSearchFilterList()
    }

    private fun initSearchFilterList() {

        if (mainActivityViewModel.videoCategoryItems.value.isNotNullOrEmpty()) {
            searchFiltersAdapter = SearchFiltersAdapter(videoCategoryListListener = this)

            vdb.recyclerViewSearchFilters.adapter = searchFiltersAdapter
            vdb.recyclerViewSearchFilters.list()

            searchFiltersAdapter.submitList(mainActivityViewModel.videoCategoryItems.value)
        }
    }

    override fun onVideoCategoryClicked(
        listPosition: Int,
        videoCategoryItem: VideoCategoryItem
    ) {
        if (mainActivityViewModel.videoCategoryItem?.id != videoCategoryItem.id) {
            mainActivityViewModel.videoCategoryItems.value?.forEach {
                it.isChecked = it.id == videoCategoryItem.id
            }

            mainActivityViewModel.videoCategoryItem = videoCategoryItem

            mainActivityViewModel.videoCategoryItemUpdated.postValue(true)
        }

        close()
    }
}
