package com.levit.book_me.ui.fragments.main_screens.user_profile

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.levit.book_me.R
import com.levit.book_me.core.models.Author
import com.levit.book_me.core.models.Genre
import com.levit.book_me.core.models.ProfileModel
import com.levit.book_me.core.models.quote.GoQuote
import com.levit.book_me.core_base.di.MainScreenScope
import com.levit.book_me.interactors.main_screen.UserProfileInteractor
import com.levit.book_me.network.models.google_books.GoogleBook
import com.levit.book_me.network.network_result_data.FirebaseStorageUploadResult
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.repositories.result_models.BaseRepositoryResult
import com.levit.book_me.ui.base.BaseMainScreenViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@MainScreenScope
class MainScreenUserProfileViewModel @Inject constructor(
    private val interactor: UserProfileInteractor,
): BaseMainScreenViewModel() {

    enum class Status {
        LOADING, PROFILE_MODEL_FROM_CACHE,
        PROFILE_MODEL_FROM_REMOTE, NOTHING_TO_SHOW
    }

    private val _profileModel: MutableLiveData<ProfileModel> = MutableLiveData()
    val profileModel: LiveData<ProfileModel> = _profileModel

    private val _currentStatus: MutableLiveData<Status> = MutableLiveData()
    val currentStatus: LiveData<Status> = _currentStatus

    init {
        viewModelScope.launch {
            interactor.uploadPhotoResult.collect { result ->
                handleUploadPhotoResult(result)
            }
        }

        viewModelScope.launch {
            interactor.profileModel.collect { result ->
                handleErrorRepositoryResult(result)
                handleRepositoryResult(result)
            }
        }

        getProfile()
    }

    fun getProfile() {
        _currentStatus.value = Status.LOADING
        viewModelScope.launch {
            interactor.getProfile()
        }
    }

    fun updateName(name: String) {
        _currentStatus.value = Status.LOADING
        viewModelScope.launch {
            interactor.updateName(name)
        }
    }

    fun updateSurname(surname: String) {
        _currentStatus.value = Status.LOADING
        viewModelScope.launch {
            interactor.updateSurname(surname)
        }
    }

    fun updateWordsAboutPerson(wordsAboutPerson: String) {
        _currentStatus.value = Status.LOADING
        viewModelScope.launch {
            interactor.updateWordsAboutPerson(wordsAboutPerson)
        }
    }

    fun updateQuote(quote: GoQuote) {
        _currentStatus.value = Status.LOADING
        viewModelScope.launch {
            interactor.updateQuote(quote)
        }
    }

    fun updateFavoriteGenres(genres: List<Genre>) {
        _currentStatus.value = Status.LOADING
        viewModelScope.launch {
            interactor.updateFavoriteGenres(genres)
        }
    }

    fun updateFavoriteAuthors(authors: List<Author>) {
        _currentStatus.value = Status.LOADING
        viewModelScope.launch {
            interactor.updateFavoriteAuthors(authors)
        }
    }

    fun updateFavoriteBooks(books: List<GoogleBook>) {
        _currentStatus.value = Status.LOADING
        viewModelScope.launch {
            interactor.updateFavoriteBooks(books)
        }
    }

    fun updateWantToReadBooks(books: List<GoogleBook>) {
        _currentStatus.value = Status.LOADING
        viewModelScope.launch {
            interactor.updateWantToReadBooks(books)
        }
    }

    fun uploadNewProfilePhoto(uri: Uri) {
        _currentStatus.postValue(Status.LOADING)
        viewModelScope.launch {
            interactor.uploadProfileImageToStorage(uri)
        }
    }

    private fun handleUploadPhotoResult(result: FirebaseStorageUploadResult) {
        when (result) {
            is FirebaseStorageUploadResult.Success ->
                handleSuccessPhotoResult(result)
            is FirebaseStorageUploadResult.Error ->
                handleErrorPhotoResult(result)
        }
    }

    private fun handleSuccessPhotoResult(result: FirebaseStorageUploadResult.Success) {
        _currentStatus.postValue(Status.PROFILE_MODEL_FROM_REMOTE)
        _successMessageId.postValue(R.string.new_photo_uploaded)
        val newModel = _profileModel.value?.copyWithNew(profilePhotoUrl = "") ?: ProfileModel()
        _profileModel.postValue(newModel)
    }

    private fun handleErrorPhotoResult(result: FirebaseStorageUploadResult.Error) {
        _currentStatus.postValue(Status.PROFILE_MODEL_FROM_CACHE)
        _errorMessageId.postValue(R.string.new_photo_not_uploaded)
    }

    private fun handleRepositoryResult(result: BaseRepositoryResult<ProfileModel>) {
        if (result is BaseRepositoryResult.RemoteResult) {
            handleRemoteResult(result.result)
            return
        }
        if (result is BaseRepositoryResult.CacheResult) {
            handleCacheResult(result)
            return
        }
    }

    private fun handleRemoteResult(result: RetrofitResult<ProfileModel>) {
        if (result !is RetrofitResult.Success) {
            return
        }
        val profile = result.data
        _profileModel.postValue(profile)
        _currentStatus.postValue(Status.PROFILE_MODEL_FROM_REMOTE)
    }

    private fun handleCacheResult(result: BaseRepositoryResult.CacheResult<ProfileModel>) {
        val profile = result.result
        _profileModel.postValue(profile)
        _currentStatus.postValue(Status.PROFILE_MODEL_FROM_CACHE)
    }
}