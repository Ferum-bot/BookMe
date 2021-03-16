package com.levit.book_me.core.extensions

import android.app.Instrumentation
import androidx.activity.result.ActivityResult

internal fun ActivityResult.isDataAvailable(): Boolean {
    return this.data != null
}