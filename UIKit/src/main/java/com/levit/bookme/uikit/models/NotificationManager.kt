package com.levit.bookme.uikit.models

import android.view.View
import androidx.core.view.isInvisible
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.levit.bookme.uikit.utills.postDelayedOnUiThread

class NotificationManager(
    lifecycle: Lifecycle,
    private val notificationView: View
): LifecycleObserver {

    companion object {

        private const val SLIDE_IN_DELAY = 500L
        private const val SLIDE_OUT_DELAY = 2000L
    }

    private var hideNotificationCallback: Runnable? = null

    init {
        lifecycle.addObserver(this)

        notificationView.apply {
            isInvisible = true
            post { translationY = getHiddenStateTranslationY(this) }
            setOnClickListener {
                removeNotificationCallback()
                hideNotificationCallback?.run()
            }
        }
    }

    fun showNotification() {
        postDelayedOnUiThread(SLIDE_IN_DELAY) {
            removeNotificationCallback()

            notificationView.apply {

                isInvisible = false

                showAnimation()
                hideNotificationCallback = Runnable { hideAnimation() }
                postDelayed(hideNotificationCallback, SLIDE_OUT_DELAY)
            }
        }
    }

    private fun View.showAnimation() {
        animate()
            .setInterpolator(LinearOutSlowInInterpolator())
            .translationY(0f)
    }

    private fun View.hideAnimation() {
        animate()
            .setInterpolator(FastOutLinearInInterpolator())
            .translationY(getHiddenStateTranslationY(this))
    }

    private fun View.hideWithoutAnimation() {
        translationY = getHiddenStateTranslationY(this)
    }

    private fun getHiddenStateTranslationY(view: View): Float {
        return -(view.top + view.height).toFloat()
    }

    private fun removeNotificationCallback() {
        hideNotificationCallback?.let { notificationView.removeCallbacks(it) }
    }

    @SuppressWarnings("unused")
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private fun onStop() {
        removeNotificationCallback()

        notificationView.hideWithoutAnimation()
    }
}