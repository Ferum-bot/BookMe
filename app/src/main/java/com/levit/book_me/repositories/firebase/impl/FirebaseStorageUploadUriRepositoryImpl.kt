package com.levit.book_me.repositories.firebase.impl

import android.net.Uri
import com.levit.book_me.core.utill.FirebaseStorageReferences
import com.levit.book_me.data_sources.firebase.FirebaseStorageUploadUriDataSource
import com.levit.book_me.di.DIConstants
import com.levit.book_me.network.network_result_data.FirebaseStorageUploadResult
import com.levit.book_me.repositories.firebase.FirebaseStorageUploadUriRepository
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

class FirebaseStorageUploadUriRepositoryImpl @Inject constructor(
    private val dataSource: FirebaseStorageUploadUriDataSource,

    @Named(DIConstants.IO_DISPATCHER_CONTEXT)
    private val launchContext: CoroutineContext,
): FirebaseStorageUploadUriRepository {


    override val uploadToFirebaseStorageResult: SharedFlow<FirebaseStorageUploadResult>
        = dataSource.loadToFirebaseStorageResult

    override suspend fun upLoadUriToFirebaseStorage(uri: Uri)
    = withContext(launchContext) {
        val ref = FirebaseStorageReferences.getStorageProfileImageReference(uri)
        dataSource.loadUriToFirebaseStorage(uri, ref)
    }
}