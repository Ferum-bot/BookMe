package com.levit.book_me.repositories.implementations

import com.google.firebase.storage.StorageReference
import com.levit.book_me.data_sources.interfaces.FirebaseStorageUploadStreamDataSource
import com.levit.book_me.network.network_result_data.FirebaseStorageUploadResult
import com.levit.book_me.repositories.interfaces.FirebaseStorageUploadStreamRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileInputStream
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.CoroutineContext

class FirebaseStorageUploadStreamRepositoryImpl @Inject constructor(
    private val storageDataSource: FirebaseStorageUploadStreamDataSource,

    @Named("IODispatcherContext")
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