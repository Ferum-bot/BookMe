package com.levit.book_me.interactors.creating_profile

import android.net.Uri
import com.levit.book_me.network.network_result_data.FirebaseStorageUploadResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import java.io.File

interface UploadProfileImageInteractor {

    suspend fun uploadProfileImageToStorage(imageUri: Uri)

    val uploadPhotoResult: Flow<FirebaseStorageUploadResult>
}