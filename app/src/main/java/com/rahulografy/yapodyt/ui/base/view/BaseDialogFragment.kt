package com.rahulografy.yapodyt.ui.base.view

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.rahulografy.yapodyt.util.ext.isAppOnline

abstract class BaseDialogFragment<VDB : ViewDataBinding, BVM : BaseViewModel> :
    DialogFragment() {

    protected lateinit var viewDataBinding: VDB

    protected abstract val viewModel: BVM

    abstract val bindingVariable: Int

    @get:LayoutRes
    protected abstract val layoutRes: Int

    @get:IdRes
    protected open val toolbarId: Int = 0

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        BottomSheetDialog(requireContext(), theme)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding =
            DataBindingUtil
                .inflate(
                    inflater,
                    layoutRes,
                    container,
                    false
                )
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewDataBinding.apply {
            setVariable(
                bindingVariable,
                viewModel
            )
            lifecycleOwner = this@BaseDialogFragment
            executePendingBindings()
        }

        viewModel.start()

        initToolBar()

        initUi()

        initSharedViewModels()

        initObservers()
    }

    private fun initToolBar() {
        if (toolbarId != 0 && view != null) {
            getSupportActionBar(requireView().findViewById(toolbarId))
            setHasOptionsMenu(true)
        }
    }

    private fun getSupportActionBar(toolbar: Toolbar): ActionBar? {
        val activity = (activity as AppCompatActivity)
        activity.setSupportActionBar(toolbar)
        return activity.supportActionBar
    }

    abstract fun initUi()

    open fun initSharedViewModels() {}

    open fun initObservers() {}

    override fun onDestroyView() {
        viewModel.stop()
        super.onDestroyView()
    }

    fun isAppOnline() =
        isAppOnline(context).apply {
            /*if (not()) {
                toast(getString(R.string.msg_no_internet))
            }*/
        }
}
