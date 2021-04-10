package com.levit.book_me.repositories.implementations

import android.net.Uri
import com.google.firebase.storage.StorageReference
import com.levit.book_me.data_sources.interfaces.FirebaseStorageUploadUriDataSource
import com.levit.book_me.network.network_result_data.FirebaseStorageUploadResult
import com.levit.book_me.repositories.interfaces.FirebaseStorageUploadUriRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FirebaseStorageUploadUriRepositoryImpl @Inject constructor(
    private val dataSource: FirebaseStorageUploadUriDataSource
): FirebaseStorageUploadUriRepository {


    override val uploadToFirebaseStorageResult: SharedFlow<FirebaseStorageUploadResult>
        = dataSource.loadToFirebaseStorageResult

    override suspend fun upLoadUriToFirebaseStorage(uri: Uri, ref: StorageReference)
    = withContext(Dispatchers.IO) {
        dataSource.loadUriToFirebaseStorage(uri, ref)
    }
}