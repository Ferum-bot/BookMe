package com.levit.book_me.core_base.extensions

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

fun <T> Flow<T>.flowOnIO() = flowOn(Dispatchers.IO)

fun <T> Flow<T>.flowOnMain() = flowOn(Dispatchers.Main)
