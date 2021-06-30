package com.levit.book_me.core.utill

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object TimeParser {

    private const val dateFormat = "dd.MM.yyyy HH:mm:ss"
    private const val timeFormat = "HH:mm:ss"

    val baseDateFormat = SimpleDateFormat(dateFormat, LocaleHelper.DEFAULT_LOCALE)

    fun getDateInCurrentUTC(dateUTC0: String): Date {
        return try {
            baseDateFormat.parse(dateUTC0) ?: Date()
        } catch (ex: ParseException) {
            Date()
        }
    }

    fun getTimeFromBasePattern(time: Long): String {
        val calendar = Calendar.getInstance(Locale.ENGLISH)
        calendar.timeInMillis = time

        val hours = calendar.get(Calendar.HOUR_OF_DAY).adaptTime()
        val minutes = calendar.get(Calendar.MINUTE).adaptTime()
        val seconds = calendar.get(Calendar.SECOND).adaptTime()

        return "$hours:$minutes:$seconds"
    }

    private fun Int.adaptTime(): String {
        val currentTime = toString()
        return if (currentTime.length == 1) {
            "0$currentTime"
        } else {
            currentTime
        }
    }
}