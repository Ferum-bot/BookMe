package com.levit.bookme.chatkit.extensions

/**
 * Do some action if Collection is empty.
 */
internal fun <T: Any> Collection<T>.ifEmpty(action: () -> Unit) {
    if (size == 0) {
        action.invoke()
    }
}

/**
 * Do some action if Collection is not empty.
 */
internal fun <T: Any> Collection<T>.ifNotEmpty(action: () -> Unit) {
    if (size > 0) {
        action.invoke()
    }
}