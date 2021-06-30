package com.levit.book_me.core.extensions

import com.levit.book_me.core.utill.LocaleHelper.DEFAULT_LOCALE
import java.util.*

fun Date.getDayOfMonth(): Int {
    val calendar = Calendar.getInstance(DEFAULT_LOCALE)
    calendar.time = this
    return calendar.get(Calendar.DAY_OF_MONTH)
}

fun Date.getMonthOfYear(): Int {
    val calendar = Calendar.getInstance(DEFAULT_LOCALE)
    calendar.time = this
    return calendar.get(Calendar.MONTH) + 1
}

fun Date.getCurrentYear(): Int {
    val calendar = Calendar.getInstance(DEFAULT_LOCALE)
    calendar.time = this
    return calendar.get(Calendar.YEAR)
}

fun Date.getCurrentHours(): Int {
    val calendar = Calendar.getInstance(DEFAULT_LOCALE)
    calendar.time = this
    return calendar.get(Calendar.HOUR_OF_DAY)
}

fun Date.getCurrentMinutes(): Int {
    val calendar = Calendar.getInstance(DEFAULT_LOCALE)
    calendar.time = this
    return calendar.get(Calendar.MINUTE)
}

fun Date.getCurrentSeconds(): Int {
    val calendar = Calendar.getInstance(DEFAULT_LOCALE)
    calendar.time = this
    return calendar.get(Calendar.SECOND)
}

