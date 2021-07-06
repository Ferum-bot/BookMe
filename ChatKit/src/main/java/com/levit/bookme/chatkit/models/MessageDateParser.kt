package com.levit.bookme.chatkit.models

import com.levit.bookme.chatkit.models.enums.MessageDateFormat
import java.text.SimpleDateFormat
import java.util.*

internal object MessageDateParser {

    private const val onlyTimePattern = "HH:mm"
    private const val onlyDatePattern = "dd.MM"
    private const val fullDatePattern = "dd.MM HH:mm"

    /**
     * Remove lately!
     * Use the locale from options or from method.
     */
    private val currentLocale = Locale.ENGLISH

    private val onlyTimeFormat = SimpleDateFormat(onlyTimePattern, currentLocale)
    private val onlyDateFormat = SimpleDateFormat(onlyDatePattern, currentLocale)
    private val fullDateFormat = SimpleDateFormat(fullDatePattern, currentLocale)

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