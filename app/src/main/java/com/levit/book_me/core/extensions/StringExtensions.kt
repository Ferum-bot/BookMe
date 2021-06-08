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

fun String.isValidEmailPassword(): Boolean {
    if (isNotValidLength()) {
        return false
    }
    if (notContainsTwoDigits()) {
        return false
    }
    if (notContainsTwoLetters()) {
        return false
    }
    if (notExistsLowerCaseLetter() || notExistsUpperCaseLetter()) {
        return false
    }
    return true
}

private fun String.isNotValidLength(): Boolean =
    length < 8 || length > 14

private fun String.notContainsTwoDigits(): Boolean {
    var counter = 0
    forEach {
        if (it.isDigit()) {
            counter++
        }
    }
    return counter < 2
}

private fun String.notContainsTwoLetters(): Boolean {
    var counter = 0
    forEach {
        if (it.isLetter()) {
            counter++
        }
    }
    return counter < 2
}

private fun String.notExistsUpperCaseLetter(): Boolean {
    forEach {
        if (it.isUpperCase()) {
            return false
        }
    }
    return true
}

private fun String.notExistsLowerCaseLetter(): Boolean {
    forEach {
        if (it.isLowerCase()) {
            return false
        }
    }
    return true
}