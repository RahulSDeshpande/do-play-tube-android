package com.rahulografy.yapodyt.ui.main.activity

import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import com.rahulografy.yapodyt.R
import com.rahulografy.yapodyt.databinding.ActivityHomeBinding
import com.rahulografy.yapodyt.ui.base.view.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_home.*

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityHomeBinding, MainActivityViewModel>() {

    override val layoutRes: Int get() = R.layout.activity_home

    override val vm: MainActivityViewModel by viewModels()

    override fun initUi() {}

    fun showSnackbar(message: String) {
        Snackbar
            .make(
                coordinatorLayoutMain,
                message,
                Snackbar.LENGTH_LONG
            ).show()
    }
}
