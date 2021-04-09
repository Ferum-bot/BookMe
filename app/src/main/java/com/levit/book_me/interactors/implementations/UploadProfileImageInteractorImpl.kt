package com.levit.book_me.interactors.implementations

import com.levit.book_me.core.utill.FirebaseStorageReferences
import com.levit.book_me.core_base.di.CreatingProfileScope
import com.levit.book_me.data_sources.interfaces.FirebaseStorageUploadStreamDataSource
import com.levit.book_me.interactors.interfaces.UploadProfileImageInteractor
import com.levit.book_me.network.network_result_data.FirebaseStorageUploadResult
import com.levit.book_me.repositories.interfaces.FirebaseStorageUploadStreamRepository
import kotlinx.coroutines.flow.Flow
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import javax.inject.Inject

@CreatingProfileScope
class UploadProfileImageInteractorImpl @Inject constructor(
    private val storageStreamRepository: FirebaseStorageUploadStreamRepository
): UploadProfileImageInteractor {

    override val uploadResult: Flow<FirebaseStorageUploadResult>
    = storageStreamRepository.loadToFirebaseStorageResult

    override suspend fun uploadProfileImageToStorage(file: File) {
        val profileImageRef = FirebaseStorageReferences.getStorageProfileImageReference(file)
        storageStreamRepository.upLoadFileToFirebaseStorage(file, profileImageRef)
    }
}