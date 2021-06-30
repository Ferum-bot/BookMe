package com.levit.book_me.core.utill

import android.icu.util.Calendar
import android.icu.util.TimeZone
import android.os.CountDownTimer
import android.widget.TextView
import java.util.*

class FriendTimeLeftAdapter(
    private val timeView: TextView,
    private val friendLaunchTimeUTC0: String,
    private val onTimerComplete: () -> Unit
) {

    companion object {

        private const val COUNT_DOWN_INTERVAL = 1000L
        private const val FRIENDSHIP_DURATION = 60L * 60L * 24L
    }

    private val timer: CountDownTimer

    init {
        val launchDate = TimeParser.getDateInCurrentUTC(friendLaunchTimeUTC0)
        val currentDate = getCurrentDate()
        val leftTimeMillis = calculateLeftTime(launchDate, currentDate).time

        timer = object: CountDownTimer(leftTimeMillis, COUNT_DOWN_INTERVAL) {

            override fun onTick(millisUntilFinished: Long) {
                val newTime = TimeParser.getTimeFromBasePattern(millisUntilFinished)
                timeView.text = newTime
            }

            override fun onFinish() {
                onTimerComplete.invoke()
            }

        }
    }

    fun start() {
        timer.start()
    }

    fun cancel() {
        timer.cancel()
    }

    private fun calculateLeftTime(launchDate: Date, currentDate: Date): Date {
        val launchTime = launchDate.time
        val currentTime = currentDate.time
        return Date(launchTime + FRIENDSHIP_DURATION - currentTime)
    }

    private fun getCurrentDate(): Date {
        val calendar = java.util.Calendar.getInstance(LocaleHelper.DEFAULT_LOCALE)
       return calendar.time
    }
}