package com.rahulografy.yapodyt.ui.base.view

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
import androidx.fragment.app.Fragment
import com.rahulografy.yapodyt.util.event.InternetConnectionEvent
import com.rahulografy.yapodyt.util.ext.isAppOnline
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

abstract class BaseFragment<VDB : ViewDataBinding, BVM : BaseViewModel> : Fragment() {

    protected lateinit var viewDataBinding: VDB

    protected abstract val viewModel: BVM

    abstract val bindingVariable: Int

    @get:LayoutRes
    protected abstract val layoutRes: Int

    @get:IdRes
    protected open val toolbarId: Int = 0

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
            lifecycleOwner = this@BaseFragment
            executePendingBindings()
        }

        viewModel.start()

        initToolBar()

        initUi()

        initSharedViewModels()

        initObservers()
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().register(this)
    }

    override fun onStop() {
        super.onStop()
        EventBus.getDefault().unregister(this)
    }

    override fun onDestroyView() {
        viewModel.stop()
        super.onDestroyView()
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

    fun isAppOnline() =
        isAppOnline(context).apply {
            /*if (not()) {
                toast(getString(R.string.msg_no_internet))
            }*/
        }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onInternetConnectionUpdate(event: InternetConnectionEvent) {
        onInternetConnectionUpdate(isActive = event.isActive)
    }

    open fun onInternetConnectionUpdate(isActive: Boolean) {}
}
