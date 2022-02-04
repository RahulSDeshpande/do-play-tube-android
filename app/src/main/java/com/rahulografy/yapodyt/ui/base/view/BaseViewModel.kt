package com.rahulografy.yapodyt.ui.base.view

import androidx.lifecycle.ViewModel
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    private var compositeDisposable: CompositeDisposable = CompositeDisposable()

    open fun start() {}

    open fun stop() {
        compositeDisposable.dispose()
    }

    protected fun addDisposable(disposable: Disposable?) {
        disposable?.let {
            if (it.isDisposed.not()) {
                compositeDisposable.add(disposable)
            }
        }
    }

    protected fun scheduleInMainThread(): Scheduler = AndroidSchedulers.mainThread()
}
