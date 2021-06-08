package com.levit.book_me.data_sources.implementations

import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.levit.book_me.network.network_result_data.FirebaseStorageUploadResult
import com.levit.book_me.data_sources.interfaces.FirebaseStorageUploadStreamDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import java.io.InputStream
import javax.inject.Inject


class FirebaseStorageUploadStreamDataSourceImpl @Inject constructor(): FirebaseStorageUploadStreamDataSource {

    private val _loadToFirebaseStorageResult = MutableSharedFlow<FirebaseStorageUploadResult>()
    override val loadToFirebaseStorageResult: SharedFlow<FirebaseStorageUploadResult> = _loadToFirebaseStorageResult

    override suspend fun loadStreamToFirebaseStorage(stream: InputStream, ref: StorageReference) {
        val uploadTask = ref.putStream(stream)
        uploadTask.addOnSuccessListener(this::onSuccess)
        uploadTask.addOnFailureListener(this::onFailure)
    }

    private fun onSuccess(task: UploadTask.TaskSnapshot) {
        val successResult = FirebaseStorageUploadResult.Success(task)
        _loadToFirebaseStorageResult.tryEmit(successResult)
    }

    private fun onFailure(ex: Exception) {
        val errorResult = FirebaseStorageUploadResult.Error(ex)
        _loadToFirebaseStorageResult.tryEmit(errorResult)
    }
}