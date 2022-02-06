package com.rahulografy.yapodyt.util.ext

import android.content.SharedPreferences
import com.rahulografy.yapodyt.util.Constants.Network.Pref.VIDEO_CATEGORY_ID

lateinit var prefs: SharedPreferences

fun SharedPreferences.put(name: String, any: Any?) {
    when (any) {
        is String -> edit().putString(name, any).apply()
        is Int -> edit().putInt(name, any).apply()
        is Boolean -> edit().putBoolean(name, any).apply()
    }
}

fun SharedPreferences.remove(name: String) {
    edit().remove(name).apply()
}

var videoCategoryId: String?
    get() = prefs.getString(VIDEO_CATEGORY_ID, "")
    set(value) {
        prefs.put(VIDEO_CATEGORY_ID, value)
    }
