package com.levit.bookme.chatkit.models.chat_messages

import com.levit.bookme.chatkit.models.enums.MessageDateFormat
import java.text.SimpleDateFormat
import java.util.*

internal object MessageDateParser {

    private const val ONLY_TIME_PATTERN = "HH:mm"
    private const val ONLY_DATE_PATTERN = "dd.MM"
    private const val FULL_PATTERN = "dd.MM HH:mm"

    /**
     * Remove lately! TODO
     * Use the locale from options or from method.
     */
    private val currentLocale = Locale.ENGLISH

    private val onlyTimeFormat = SimpleDateFormat(ONLY_TIME_PATTERN, currentLocale)
    private val onlyDateFormat = SimpleDateFormat(ONLY_DATE_PATTERN, currentLocale)
    private val fullDateFormat = SimpleDateFormat(FULL_PATTERN, currentLocale)

    fun parseDateWithFormat(date: Date, format: MessageDateFormat): String
    = when(format) {
        MessageDateFormat.SHOW_ONLY_TIME -> parseWithOnlyTime(date)
        MessageDateFormat.SHOW_ONLY_DATE -> parseWithOnlyDate(date)
        MessageDateFormat.SHOW_ALL -> parseWithAll(date)
    }

    private fun parseWithOnlyTime(date: Date): String {
        return onlyTimeFormat.format(date)
    }

    private fun parseWithOnlyDate(date: Date): String {
        return onlyDateFormat.format(date)
    }

    private fun parseWithAll(date: Date): String {
        return fullDateFormat.format(date)
    }
}