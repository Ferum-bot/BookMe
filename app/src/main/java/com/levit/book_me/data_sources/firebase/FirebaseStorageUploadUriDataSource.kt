package com.levit.book_me.data_sources.firebase

import android.net.Uri
import com.google.firebase.storage.StorageReference
import com.levit.book_me.network.network_result_data.FirebaseStorageUploadResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow

interface FirebaseStorageUploadUriDataSource {

    suspend fun loadUriToFirebaseStorage(uri: Uri, ref: StorageReference)

    val loadToFirebaseStorageResult: SharedFlow<FirebaseStorageUploadResult>

}