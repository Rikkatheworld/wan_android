package com.rikkatheworld.wan_android.widget.snap

import android.util.Log

private const val DebugLog = false

internal object SnapperLog {
    inline fun d(tag: String = "SnapperFlingBehavior", message: () -> String) {
        if (DebugLog) {
            Log.d(tag, message())
        }
    }
}