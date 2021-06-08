package com.levit.book_me.repositories.implementations

import android.net.Uri
import com.google.firebase.storage.StorageReference
import com.levit.book_me.data_sources.interfaces.FirebaseStorageUploadUriDataSource
import com.levit.book_me.di.DIConstants
import com.levit.book_me.network.network_result_data.FirebaseStorageUploadResult
import com.levit.book_me.repositories.interfaces.FirebaseStorageUploadUriRepository
import kotlinx.coroutines.Dispatchers
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

    override suspend fun upLoadUriToFirebaseStorage(uri: Uri, ref: StorageReference)
    = withContext(launchContext) {
        dataSource.loadUriToFirebaseStorage(uri, ref)
    }
}