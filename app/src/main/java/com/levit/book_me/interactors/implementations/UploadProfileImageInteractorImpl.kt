package com.levit.book_me.interactors.implementations

import android.net.Uri
import com.levit.book_me.core.utill.FirebaseStorageReferences
import com.levit.book_me.core_base.di.CreatingProfileScope
import com.levit.book_me.data_sources.interfaces.FirebaseStorageUploadStreamDataSource
import com.levit.book_me.interactors.interfaces.UploadProfileImageInteractor
import com.levit.book_me.network.network_result_data.FirebaseStorageUploadResult
import com.levit.book_me.repositories.interfaces.FirebaseStorageUploadStreamRepository
import com.levit.book_me.repositories.interfaces.FirebaseStorageUploadUriRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import javax.inject.Inject

@CreatingProfileScope
class UploadProfileImageInteractorImpl @Inject constructor(
    private val storageUriRepository: FirebaseStorageUploadUriRepository
): UploadProfileImageInteractor {

    override val uploadResult: Flow<FirebaseStorageUploadResult>
    = storageUriRepository.uploadToFirebaseStorageResult

    override suspend fun uploadProfileImageToStorage(imageUri: Uri) {
        val profileImageRef = FirebaseStorageReferences.getStorageProfileImageReference(imageUri)
        storageUriRepository.upLoadUriToFirebaseStorage(imageUri, profileImageRef)
    }
}