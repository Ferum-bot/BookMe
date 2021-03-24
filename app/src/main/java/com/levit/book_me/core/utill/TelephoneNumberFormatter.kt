package com.levit.book_me.core.utill

import com.levit.book_me.core.models.MobileTelephone

object TelephoneNumberFormatter {

    fun formatNumberWithBrackets(telephoneNumber: MobileTelephone): String {
        val code = telephoneNumber.regionCode.code
        val firstThreeDigits = telephoneNumber.telephoneNumber.substring(0, 3)
        val secondThreeDigits = telephoneNumber.telephoneNumber.substring(3, 6)
        val firstHalfOfSuffix = telephoneNumber.telephoneNumber.substring(6, 8)
        val secondHalfOfSuffix = telephoneNumber.telephoneNumber.substring(8, 10)
        return StringBuilder()
            .append(code)
            .append(SPACE)
            .append(OPEN_BRACKET)
            .append(firstThreeDigits)
            .append(CLOSE_BRACKET)
            .append(SPACE)
            .append(secondThreeDigits)
            .append(SPACE)
            .append(firstHalfOfSuffix)
            .append(SPACE)
            .append(secondHalfOfSuffix)
            .toString()
    }

    private const val SPACE = " "
    private const val OPEN_BRACKET = "("
    private const val CLOSE_BRACKET = ")"
}