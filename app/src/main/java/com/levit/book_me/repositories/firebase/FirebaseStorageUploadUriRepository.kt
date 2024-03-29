package com.levit.book_me.repositories.firebase

import android.net.Uri
import com.google.firebase.storage.StorageReference
import com.levit.book_me.network.network_result_data.FirebaseStorageUploadResult
import kotlinx.coroutines.flow.SharedFlow

interface FirebaseStorageUploadUriRepository {

    val uploadToFirebaseStorageResult: SharedFlow<FirebaseStorageUploadResult>

    suspend fun upLoadUriToFirebaseStorage(uri: Uri)

}