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

        val hours = calendar.get(Calendar.HOUR_OF_DAY)
        val minutes = calendar.get(Calendar.MINUTE)
        val seconds = calendar.get(Calendar.SECOND)

        return "$hours:$minutes:$seconds"
    }
}