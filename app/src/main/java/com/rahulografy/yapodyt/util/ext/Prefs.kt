package com.rahulografy.yapodyt.util.ext

import android.content.SharedPreferences

lateinit var prefs: SharedPreferences

fun SharedPreferences.put(name: String, any: Any) {
    when (any) {
        is String -> edit().putString(name, any).apply()
        is Int -> edit().putInt(name, any).apply()
        is Boolean -> edit().putBoolean(name, any).apply()
    }
}

fun SharedPreferences.remove(name: String) {
    edit().remove(name).apply()
}
