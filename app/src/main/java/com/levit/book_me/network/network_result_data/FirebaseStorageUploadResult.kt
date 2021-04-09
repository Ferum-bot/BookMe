package com.levit.book_me.network.network_result_data

import com.google.firebase.storage.UploadTask
import java.lang.Exception

sealed class FirebaseStorageUploadResult {

    data class Success(
        val taskSnapshot: UploadTask.TaskSnapshot
    ): FirebaseStorageUploadResult()

    data class Error(
        val exception: Exception
    ): FirebaseStorageUploadResult()
}