@file:JvmName("ThreadUtils")

package com.levit.bookme.uikit.utills

import android.os.Handler
import android.os.Looper

private val mainThreadHandler = Handler(Looper.getMainLooper())

/** Returns true if the current thread is the UI thread. */
val isMainThread: Boolean
    get() = Looper.myLooper() === Looper.getMainLooper()

/** Checks that the current thread is the UI thread. Otherwise throws an exception. */
fun checkMainThread() {
    check(isMainThread) { "Must be called on the UI thread" }
}

/** Posts the [runnable] on the main thread or executes directly if we already on the main thread. */
fun runOnMainThread(runnable: Runnable) {
    if (isMainThread) {
        runnable.run()
    } else {
        postOnUiThread(runnable)
    }
}

/** Posts the [block] on the main thread or executes directly if we already on the main thread. */
fun runOnMainThread(block: () -> Unit) = runOnMainThread(Runnable(block))

/** Posts the [runnable] on the main thread. */
fun postOnUiThread(runnable: Runnable) {
    mainThreadHandler.post(runnable)
}

/** Posts the [block] on the main thread. */
fun postOnUiThread(block: () -> Unit) = postOnUiThread(Runnable(block))

/** Posts the [block] on the main thread delayed by [delayMillis] milliseconds. */
fun postDelayedOnUiThread(delayMillis: Long, block: () -> Unit) = mainThreadHandler.postDelayed(block, delayMillis)
