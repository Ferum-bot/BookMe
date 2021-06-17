package com.levit.book_me.interactors.main_screen.impl

import android.net.Uri
import com.levit.book_me.core.models.ProfileModel
import com.levit.book_me.core.utill.FirebaseStorageReferences
import com.levit.book_me.data_sources.profile.CacheProfileDataSource
import com.levit.book_me.interactors.main_screen.UserProfileInteractor
import com.levit.book_me.network.network_result_data.FirebaseStorageUploadResult
import com.levit.book_me.repositories.firebase.FirebaseStorageUploadUriRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TestUserProfileInteractor @Inject constructor(
    private val dataSource: CacheProfileDataSource,
    private val uploadPhotoStorage: FirebaseStorageUploadUriRepository,
): UserProfileInteractor {

    override val profileModel: Flow<ProfileModel> = dataSource.profile

    override val uploadPhotoResult: Flow<FirebaseStorageUploadResult> = uploadPhotoStorage.uploadToFirebaseStorageResult

    override suspend fun getProfile() {
        dataSource.getProfile()
    }

    override suspend fun uploadProfileImageToStorage(imageUri: Uri) {
        val profileImageRef = FirebaseStorageReferences.getStorageProfileImageReference(imageUri)
        uploadPhotoStorage.upLoadUriToFirebaseStorage(imageUri, profileImageRef)
    }
}