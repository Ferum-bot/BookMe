package com.levit.book_me.core_presentation.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

fun ViewModel.launchOnMain(
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job {
    return viewModelScope.launch(Dispatchers.Main, start, block)
}

fun ViewModel.launchOnIO(
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job {
    return viewModelScope.launch(Dispatchers.IO, start, block)
}
