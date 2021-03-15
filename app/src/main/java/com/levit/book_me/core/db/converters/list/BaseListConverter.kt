package com.levit.book_me.core.db.converters.list

import androidx.room.TypeConverter

abstract class BaseListConverter<T> {

    companion object {
        private const val SEPARATOR = ","
    }

    @TypeConverter
    fun fromList(values: List<T>?): String? =
        values?.joinToString(separator = SEPARATOR) { it.toString() }

    @TypeConverter
    fun toList(value: String?): List<T>? = value?.split(SEPARATOR)
        ?.map { convertStringToValue(it) }

    abstract fun convertStringToValue(string: String): T
}
