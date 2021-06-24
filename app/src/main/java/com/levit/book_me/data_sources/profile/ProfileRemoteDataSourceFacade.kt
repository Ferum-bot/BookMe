package com.levit.book_me.data_sources.profile

import com.levit.book_me.core.models.Author
import com.levit.book_me.core.models.Genre
import com.levit.book_me.core.models.ProfileModel
import com.levit.book_me.core.models.quote.GoQuote
import com.levit.book_me.data_sources.profile.impl.BaseProfileRemoteDataSource
import com.levit.book_me.network.models.google_books.GoogleBook
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.repositories.firebase.FirebaseStorageUploadUriRepository
import kotlinx.coroutines.flow.SharedFlow

interface ProfileRemoteDataSourceFacade:
    FirebaseStorageUploadUriRepository, BaseProfileRemoteDataSource