package com.levit.book_me.core_base.extensions

import com.levit.book_me.core_base.utils.Optional

fun <T> T?.asOptional(): Optional<T> = Optional.of(this)
