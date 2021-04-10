package com.levit.book_me.data_sources.implementations

import android.net.Uri
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import com.levit.book_me.data_sources.interfaces.FirebaseStorageUploadUriDataSource
import com.levit.book_me.network.network_result_data.FirebaseStorageUploadResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FirebaseStorageUploadUriDataSourceImpl @Inject constructor(): FirebaseStorageUploadUriDataSource {

    private val _loadToFirebaseStorageResult = MutableSharedFlow<FirebaseStorageUploadResult>()
    override val loadToFirebaseStorageResult: SharedFlow<FirebaseStorageUploadResult> = _loadToFirebaseStorageResult

    override suspend fun loadUriToFirebaseStorage(uri: Uri, ref: StorageReference) {
        val uploadTask = ref.putFile(uri)
        uploadTask.addOnSuccessListener(this::onSuccess)
        uploadTask.addOnFailureListener(this::onFailure)
    }

    private fun onSuccess(snapshot: UploadTask.TaskSnapshot) =
    runBlocking {
        val successResult = FirebaseStorageUploadResult.Success(snapshot)
        val result = _loadToFirebaseStorageResult.emit(successResult)
    }

    private fun onFailure(ex: Exception) =
    runBlocking {
        val errorResult = FirebaseStorageUploadResult.Error(ex)
        val result = _loadToFirebaseStorageResult.emit(errorResult)
    }
}