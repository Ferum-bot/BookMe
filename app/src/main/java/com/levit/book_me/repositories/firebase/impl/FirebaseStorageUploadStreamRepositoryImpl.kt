package com.levit.book_me.repositories.firebase.impl

import com.google.firebase.storage.StorageReference
import com.levit.book_me.data_sources.firebase.FirebaseStorageUploadStreamDataSource
import com.levit.book_me.di.DIConstants
import com.levit.book_me.network.network_result_data.FirebaseStorageUploadResult
import com.levit.book_me.repositories.firebase.FirebaseStorageUploadStreamRepository
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileInputStream
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

class FirebaseStorageUploadStreamRepositoryImpl @Inject constructor(
    private val storageDataSource: FirebaseStorageUploadStreamDataSource,

    @Named(DIConstants.IO_DISPATCHER_CONTEXT)
    private val launchContext: CoroutineContext,
): FirebaseStorageUploadStreamRepository {

    override val loadToFirebaseStorageResult: SharedFlow<FirebaseStorageUploadResult>
        = storageDataSource.loadToFirebaseStorageResult

    /**
     * Running blocking method in Coroutine context
     */
    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun upLoadFileToFirebaseStorage(file: File, ref: StorageReference)
    = withContext(launchContext) {
        val inputStream = FileInputStream(file)
        storageDataSource.loadStreamToFirebaseStorage(inputStream, ref)
    }

}