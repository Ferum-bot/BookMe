package com.levit.book_me.data_sources.firebase

import com.google.firebase.storage.StorageReference
import com.levit.book_me.network.network_result_data.FirebaseStorageUploadResult
import kotlinx.coroutines.flow.SharedFlow
import java.io.InputStream

interface FirebaseStorageUploadStreamDataSource {

    suspend fun loadStreamToFirebaseStorage(stream: InputStream, ref: StorageReference)

    val loadToFirebaseStorageResult: SharedFlow<FirebaseStorageUploadResult>

}