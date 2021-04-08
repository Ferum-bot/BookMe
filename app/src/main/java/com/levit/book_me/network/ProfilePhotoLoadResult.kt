package com.levit.book_me.network

import com.google.firebase.storage.StreamDownloadTask
import java.lang.Exception

sealed class ProfilePhotoLoadResult {

    data class Success(
        val taskSnapshot: StreamDownloadTask.TaskSnapshot
    ): ProfilePhotoLoadResult()

    data class Error(
        val exception: Exception
    ): ProfilePhotoLoadResult()
}