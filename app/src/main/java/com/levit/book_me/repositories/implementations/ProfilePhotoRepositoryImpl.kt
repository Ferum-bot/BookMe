package com.levit.book_me.repositories.implementations

import com.levit.book_me.core_base.di.CreatingProfileScope
import com.levit.book_me.network.ProfilePhotoLoadResult
import com.levit.book_me.repositories.interfaces.ProfilePhotoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.lang.Exception
import javax.inject.Inject

@CreatingProfileScope
class ProfilePhotoRepositoryImpl @Inject constructor(): ProfilePhotoRepository {

    override suspend fun loadProfileImage(stream: InputStream) {
        withContext(Dispatchers.IO) {

        }
    }

    private val _result = MutableSharedFlow<ProfilePhotoLoadResult>()
    override val result: Flow<ProfilePhotoLoadResult> = _result
}