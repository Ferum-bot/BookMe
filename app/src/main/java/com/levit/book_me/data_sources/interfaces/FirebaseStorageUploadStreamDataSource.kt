package com.levit.book_me.data_sources.interfaces

import com.google.firebase.storage.StorageReference
import com.levit.book_me.network.network_result_data.FirebaseStorageUploadResult
import kotlinx.coroutines.flow.Flow
import java.io.InputStream

interface FirebaseStorageUploadStreamDataSource {

    suspend fun loadStreamToFirebaseStorage(stream: InputStream, ref: StorageReference)

    val loadToFirebaseStorageResult: Flow<FirebaseStorageUploadResult>

}