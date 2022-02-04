package com.rahulografy.yapodyt.ui.base.view

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.rahulografy.yapodyt.R
import com.rahulografy.yapodyt.util.event.InternetConnectionEvent
import com.rahulografy.yapodyt.util.ext.isAppOnline
import org.greenrobot.eventbus.EventBus
import org.imaginativeworld.oopsnointernet.ConnectionCallback
import org.imaginativeworld.oopsnointernet.NoInternetSnackbar

abstract class BaseActivity<VDB : ViewDataBinding, BVM : BaseViewModel> :
    AppCompatActivity() {

    private lateinit var vdb: VDB

    protected abstract val vm: BVM

    private var noInternetSnackbar: NoInternetSnackbar? = null

    @get:LayoutRes
    protected abstract val layoutRes: Int

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        vdb = DataBindingUtil.setContentView(this, layoutRes)

        vdb.apply {
            lifecycleOwner = this@BaseActivity
            executePendingBindings()
        }

        vm.start()

        initUi()

        initNetworkMonitorSnackbar()
    }

    abstract fun initUi()

    override fun onPause() {
        super.onPause()
        noInternetSnackbar?.destroy()
    }

    override fun onDestroy() {
        vm.stop()
        super.onDestroy()
    }

    private fun initNetworkMonitorSnackbar() {
        noInternetSnackbar =
            NoInternetSnackbar
                .Builder(
                    activity = this,
                    parent = findViewById(android.R.id.content)
                )
                .apply {
                    indefinite = true
                    noInternetConnectionMessage = getString(R.string.msg_no_internet)
                    onAirplaneModeMessage = getString(R.string.msg_airplane_mode_is_turned_on)
                    snackbarActionText = getString(R.string.settings)
                    showActionToDismiss = true
                    snackbarDismissActionText = getString(R.string.ok)
                    connectionCallback = object : ConnectionCallback {
                        override fun hasActiveConnection(hasActiveConnection: Boolean) {
                            // isAppOnline = hasActiveConnection
                            onInternetConnectionUpdate(isActive = hasActiveConnection)
                        }
                    }
                }
                .build()
    }

    fun isAppOnline() =
        isAppOnline(this).apply {
            /*if (not()) {
                toast(getString(R.string.msg_no_internet))
            }*/
        }

    open fun onInternetConnectionUpdate(isActive: Boolean) {
        EventBus.getDefault().post(InternetConnectionEvent(isActive = isActive))
    }
}
