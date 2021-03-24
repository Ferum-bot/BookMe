package com.levit.book_me.core.extensions

/**
 * Simple check for valid email address.
 * Refactor lately.
 */
fun String.isValidEmailAddress(): Boolean {
    val array = this.split("@")
    if (array.size != 2) {
        return false
    }
    if (array[0].length < 2) {
        return false
    }
    if (array[1].length < 2) {
        return false
    }
    return true
}

fun String.isValidPhoneAuthorizationCode(): Boolean =
    length == 6