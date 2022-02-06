package com.rahulografy.yapodyt.ui.base.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.rahulografy.yapodyt.R
import com.rahulografy.yapodyt.util.event.InternetConnectionEvent
import com.rahulografy.yapodyt.util.ext.isAppOnline
import com.rahulografy.yapodyt.util.ext.toast
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

abstract class BaseFragment<VDB : ViewDataBinding, BVM : BaseViewModel> : Fragment() {

    protected lateinit var vdb: VDB

    protected abstract val vm: BVM

    @get:LayoutRes
    protected abstract val layoutRes: Int

    abstract val bindingVariable: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        vdb =
            DataBindingUtil
                .inflate(
                    inflater,
                    layoutRes,
                    container,
                    false
                )
        return vdb.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vdb.apply {
            setVariable(
                bindingVariable,
                vm
            )
            lifecycleOwner = this@BaseFragment
            executePendingBindings()
        }

        vm.start()

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
        vm.stop()
        super.onDestroyView()
    }

    abstract fun initUi()

    open fun initSharedViewModels() {}

    open fun initObservers() {}

    fun isAppOnline() =
        isAppOnline(context).apply {
            if (not()) {
                toast(getString(R.string.msg_no_internet))
            }
        }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onInternetConnectionUpdate(event: InternetConnectionEvent) {
        onInternetConnectionUpdate(isActive = event.isActive)
    }

    open fun onInternetConnectionUpdate(isActive: Boolean) {}
}
