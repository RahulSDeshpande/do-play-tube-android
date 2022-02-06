package com.rahulografy.yapodyt.util.ext

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log

var isOnline = true

private const val TAG = "NetworkConnection"

fun isAppOnline(context: Context?): Boolean {
    val service = Context.CONNECTIVITY_SERVICE
    val manager = context?.getSystemService(service) as ConnectivityManager?
    val network = manager?.activeNetworkInfo
    Log.d(TAG, "isAppOnline: ${(network != null)}")
    return (network?.isConnected) ?: false
}
