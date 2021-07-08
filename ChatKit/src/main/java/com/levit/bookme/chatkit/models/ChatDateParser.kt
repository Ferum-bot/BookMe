package com.levit.bookme.chatkit.models

import com.levit.bookme.chatkit.models.enums.MessageDateFormat
import java.text.SimpleDateFormat
import java.util.*

internal object ChatDateParser {

    private const val ONLY_TIME_PATTERN = "HH:mm"
    private const val ONLY_DATE_PATTERN = "dd.MM"
    private const val FULL_PATTERN = "dd.MM HH:mm"

    /**
     * Remove lately!
     * Use the locale from options or from method.
     */
    private val currentLocale = Locale.ENGLISH

    private val onlyTimeFormat = SimpleDateFormat(ONLY_TIME_PATTERN, currentLocale)
    private val onlyDateFormat = SimpleDateFormat(ONLY_DATE_PATTERN, currentLocale)
    private val fullDateFormat = SimpleDateFormat(FULL_PATTERN, currentLocale)

    fun parseDateWithFormat(date: Date?, format: MessageDateFormat): String
    = when(format) {
        MessageDateFormat.SHOW_ONLY_TIME -> parseOnlyTime(date)
        MessageDateFormat.SHOW_ONLY_DATE -> parseOnlyDate(date)
        MessageDateFormat.SHOW_ALL -> parseFull(date)
    }

    private fun parseOnlyTime(date: Date?): String {
        date ?: return ""
        return onlyTimeFormat.format(date)
    }

    private fun parseOnlyDate(date: Date?): String {
        date ?: return ""
        return onlyDateFormat.format(date)
    }

    private fun parseFull(date: Date?): String {
        date ?: return ""
        return fullDateFormat.format(date)
    }
}