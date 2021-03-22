package com.levit.book_me.core.extensions


fun String.isValidTelephoneNumber(): Boolean {
    val array = this.split(" ")
    if (array.size != 2) {
        return false;
    }
    val rowTelephoneNumber = array[1]
    if (rowTelephoneNumber.length == 10) {
        return true
    }
    return false
}