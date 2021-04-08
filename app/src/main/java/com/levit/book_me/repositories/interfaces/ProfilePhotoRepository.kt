package com.levit.book_me.repositories.interfaces

import com.levit.book_me.network.ProfilePhotoLoadResult
import kotlinx.coroutines.flow.Flow
import java.io.InputStream

interface ProfilePhotoRepository {

    suspend fun loadProfileImage(stream: InputStream)

    val result: Flow<ProfilePhotoLoadResult>

}