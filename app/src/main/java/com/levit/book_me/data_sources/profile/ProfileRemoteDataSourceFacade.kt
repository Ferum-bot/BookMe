package com.levit.book_me.data_sources.profile

import com.levit.book_me.data_sources.profile.impl.BaseProfileRemoteDataSource
import com.levit.book_me.repositories.firebase.FirebaseStorageUploadUriRepository

interface ProfileRemoteDataSourceFacade:
    FirebaseStorageUploadUriRepository, BaseProfileRemoteDataSource