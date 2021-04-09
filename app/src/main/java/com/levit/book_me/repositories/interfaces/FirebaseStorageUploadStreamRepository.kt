package com.levit.book_me.repositories.interfaces

import com.google.firebase.storage.StorageReference
import com.levit.book_me.network.network_result_data.FirebaseStorageUploadResult
import kotlinx.coroutines.flow.Flow
import java.io.File

interface FirebaseStorageUploadStreamRepository {

    val loadToFirebaseStorageResult: Flow<FirebaseStorageUploadResult>

    suspend fun upLoadFileToFirebaseStorage(file: File, ref: StorageReference)
}