package com.rahulografy.yapodyt.ui.main.searchfilter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.rahulografy.yapodyt.R
import com.rahulografy.yapodyt.databinding.FragmentSearchFiltersBinding
import com.rahulografy.yapodyt.ui.base.view.BaseDialogFragment
import com.rahulografy.yapodyt.ui.main.activity.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFiltersFragment :
    BaseDialogFragment<FragmentSearchFiltersBinding, SearchFiltersFragmentViewModel>() {

    override val layoutRes get() = R.layout.fragment_search_filters

    override val toolbarId get() = R.id.toolbar_search_filters

    override val vm: SearchFiltersFragmentViewModel by viewModels()

    private val mainActivityViewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) close()
        return super.onOptionsItemSelected(item)
    }

    override fun initUi() {
        initSearchFilterList()
    }

    private fun initSearchFilterList() {
        // vdb.radioGroupSearchFilters.apply {
        //
        //     check(mainActivityViewModel.videoSearchFilterCategoryId)
        //
        //     setOnCheckedChangeListener { group, checkedId ->
        //         toast(group.findViewById<AppCompatRadioButton>(checkedId).text.toString())
        //
        //         mainActivityViewModel.videoSearchFilterCategoryId = group.checkedRadioButtonId
        //         // mainActivityViewModel.videoSearchFilterCategory.postValue()
        //     }
        // }

        // mainActivityViewModel.videoCategoryItems?.forEach {
        //     vdb.chipGroupSearchFilters.addView(
        //         Chip(context).apply {
        //             id = it.id.toInt()
        //             text = it.snippet.title
        //         }
        //     )
        // }
        //
        // vdb.chipGroupSearchFilters.isSelectionRequired = true
        // vdb.chipGroupSearchFilters.isSingleSelection = true
        // vdb.chipGroupSearchFilters.isSingleLine = false
        //
        // vdb.chipGroupSearchFilters.setOnCheckedChangeListener { _, checkedId ->
        //     toast(checkedId.toString())
        //
        //     // mainActivityViewModel.selectedVideoCategoryItem =
        // }
    }
}
