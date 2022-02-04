package com.rahulografy.yapodyt

import android.app.Application
import android.content.Context
import android.preference.PreferenceManager
import androidx.multidex.MultiDex
import com.facebook.stetho.Stetho
import com.rahulografy.yapodyt.util.ext.prefs
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class App : Application() {

    override fun onCreate() {
        super.onCreate()

        // Init Stetho
        Stetho.initializeWithDefaults(this)

        prefs = PreferenceManager.getDefaultSharedPreferences(this)
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)

        MultiDex.install(this)
    }
}
