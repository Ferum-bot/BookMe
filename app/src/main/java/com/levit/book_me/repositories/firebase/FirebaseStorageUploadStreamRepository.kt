package com.levit.book_me.repositories.firebase

import com.google.firebase.storage.StorageReference
import com.levit.book_me.network.network_result_data.FirebaseStorageUploadResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import java.io.File

interface FirebaseStorageUploadStreamRepository {

    val loadToFirebaseStorageResult: SharedFlow<FirebaseStorageUploadResult>

    suspend fun upLoadFileToFirebaseStorage(file: File, ref: StorageReference)
}