package com.levit.book_me.data_sources.firebase.impl

import android.net.Uri
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.levit.book_me.data_sources.firebase.FirebaseStorageUploadUriDataSource
import com.levit.book_me.network.network_result_data.FirebaseStorageUploadResult
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class FirebaseStorageUploadUriDataSourceImpl @Inject constructor(): FirebaseStorageUploadUriDataSource {

    companion object {

        private const val REPLAY_COUNT = 1
        private const val EXTRA_CAPACITY = 0
    }

    private val _loadToFirebaseStorageResult = MutableSharedFlow<FirebaseStorageUploadResult>(
        replay = REPLAY_COUNT,
        extraBufferCapacity = EXTRA_CAPACITY,
        onBufferOverflow = BufferOverflow.DROP_OLDEST,
    )
    override val loadToFirebaseStorageResult: SharedFlow<FirebaseStorageUploadResult> = _loadToFirebaseStorageResult

    override suspend fun loadUriToFirebaseStorage(uri: Uri, ref: StorageReference) {
        val uploadTask = ref.putFile(uri)
        uploadTask.addOnSuccessListener(this::onSuccess)
        uploadTask.addOnFailureListener(this::onFailure)
    }

    private fun onSuccess(snapshot: UploadTask.TaskSnapshot) =
    runBlocking {
        val successResult = FirebaseStorageUploadResult.Success(snapshot)
        _loadToFirebaseStorageResult.emit(successResult)
    }

    private fun onFailure(ex: Exception) =
    runBlocking {
        val errorResult = FirebaseStorageUploadResult.Error(ex)
        _loadToFirebaseStorageResult.emit(errorResult)
    }
}