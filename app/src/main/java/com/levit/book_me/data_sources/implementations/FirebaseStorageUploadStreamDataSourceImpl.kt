package com.levit.book_me.data_sources.implementations

import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.levit.book_me.network.network_result_data.FirebaseStorageUploadResult
import com.levit.book_me.data_sources.interfaces.FirebaseStorageUploadStreamDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import java.io.InputStream
import javax.inject.Inject


class FirebaseStorageUploadStreamDataSourceImpl @Inject constructor(): FirebaseStorageUploadStreamDataSource {

    override suspend fun loadStreamToFirebaseStorage(stream: InputStream, ref: StorageReference) {
        val uploadTask = ref.putStream(stream)
        uploadTask.addOnSuccessListener(
            this@FirebaseStorageUploadStreamDataSourceImpl::onSuccess
        )
        uploadTask.addOnFailureListener(
            this@FirebaseStorageUploadStreamDataSourceImpl::onFailure
        )
    }

    private fun onSuccess(task: UploadTask.TaskSnapshot) {
        val successResult = FirebaseStorageUploadResult.Success(task)
        _loadToFirebaseStorageResult.tryEmit(successResult)
    }

    private fun onFailure(ex: Exception) {
        val errorResult = FirebaseStorageUploadResult.Error(ex)
        _loadToFirebaseStorageResult.tryEmit(errorResult)
    }

    private val _loadToFirebaseStorageResult = MutableSharedFlow<FirebaseStorageUploadResult>()
    override val loadToFirebaseStorageResult: Flow<FirebaseStorageUploadResult> = _loadToFirebaseStorageResult
}