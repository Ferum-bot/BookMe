package com.levit.book_me.interactors.interfaces

import android.net.Uri
import com.levit.book_me.network.network_result_data.FirebaseStorageUploadResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import java.io.File

interface UploadProfileImageInteractor {

    suspend fun uploadProfileImageToStorage(imageUri: Uri)

    val uploadResult: Flow<FirebaseStorageUploadResult>
}