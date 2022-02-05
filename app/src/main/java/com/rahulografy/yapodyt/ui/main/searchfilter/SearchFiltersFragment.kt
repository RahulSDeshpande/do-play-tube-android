package com.rahulografy.yapodyt.ui.main.searchfilter

import android.view.MenuItem
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.fragment.app.viewModels
import com.rahulografy.yapodyt.R
import com.rahulografy.yapodyt.databinding.FragmentSearchFiltersBinding
import com.rahulografy.yapodyt.ui.base.view.BaseDialogFragment
import com.rahulografy.yapodyt.util.ext.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFiltersFragment :
    BaseDialogFragment<FragmentSearchFiltersBinding, SearchFiltersFragmentViewModel>() {

    override val layoutRes get() = R.layout.fragment_search_filters

    override val toolbarId get() = R.id.toolbar_search_filters

    override val vm: SearchFiltersFragmentViewModel by viewModels()

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) close()
        return super.onOptionsItemSelected(item)
    }

    override fun initUi() {
        initSearchFilterList()
    }

    private fun initSearchFilterList() {
        vdb.radioGroupSearchFilters.setOnCheckedChangeListener { group, checkedId ->
            toast(group.findViewById<AppCompatRadioButton>(checkedId).text.toString())
        }
    }

    private fun close() {
        dismissAllowingStateLoss()
    }
}
