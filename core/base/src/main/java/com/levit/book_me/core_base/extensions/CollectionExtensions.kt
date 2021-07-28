package com.levit.book_me.core_base.extensions

/**
 * Do some action if Collection is empty.
 */
fun <T: Any> Collection<T>.ifEmpty(action: () -> Unit) {
    if (size == 0) {
        action.invoke()
    }
}

/**
 * Do some action if Collection is not empty.
 */
fun <T: Any> Collection<T>.ifNotEmpty(action: () -> Unit) {
    if (size > 0) {
        action.invoke()
    }
}