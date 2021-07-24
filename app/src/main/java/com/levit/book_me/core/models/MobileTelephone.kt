package com.levit.book_me.core.models

import android.os.Parcelable
import com.levit.book_me.core.enums.PhoneRegionCodes
import kotlinx.parcelize.Parcelize


@Parcelize
data class MobileTelephone(
        val regionCode: PhoneRegionCodes,
        val telephoneNumber: String
): Parcelable {

    companion object {

        fun fromString(string: String): MobileTelephone {
            val array = string.split(SEPARATOR)
            val code = PhoneRegionCodes.getFromCode(array[0])
            return MobileTelephone(code, array[1])
        }

        private const val SEPARATOR = " "
    }

    override fun toString(): String {
        return regionCode.code + SEPARATOR + telephoneNumber
    }

    fun toStringWithOutSeparator(): String {
        return regionCode.code + telephoneNumber
    }

    fun isValidNumber(): Boolean =
        telephoneNumber.length == 10
}