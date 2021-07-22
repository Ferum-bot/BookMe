package com.levit.book_me.ui.fragments.main_screens.user_profile

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.levit.book_me.R
import com.levit.book_me.core.models.Author
import com.levit.book_me.core.models.Genre
import com.levit.book_me.core.models.profile.ProfileModel
import com.levit.book_me.core.models.quote.GoQuote
import com.levit.book_me.core_base.di.MainScreenScope
import com.levit.book_me.di.DIConstants
import com.levit.book_me.interactors.main_screen.UserProfileInteractor
import com.levit.book_me.network.models.google_books.GoogleBook
import com.levit.book_me.network.network_result_data.FirebaseStorageUploadResult
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.repositories.result_models.BaseRepositoryResult
import com.levit.book_me.ui.base.BaseMainScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@MainScreenScope
class MainScreenUserProfileViewModel @Inject constructor(
    @Named(DIConstants.MAIN_SCREEN_BASE_PROFILE_INTERACTOR)
    private val interactor: UserProfileInteractor,
): BaseMainScreenViewModel() {

    enum class Status {
        LOADING, PROFILE_MODEL_FROM_CACHE,
        PROFILE_MODEL_FROM_REMOTE, NO_AVAILABLE_DATA
    }

    /**
     * @Important
     * I found strange enough behavior with ViewModel class and viewModelScope.
     * What I mean: when user peaks photo or another file through ActivityResult API,
     * Google lifecycle library called @onCleared method on this viewModel, but after that
     * new instance of viewModel doesn't created.
     *
     * So, @onCleared method cancels all collecting jobs and @init block not called again.
     * That's why viewModel just doesn't collect data from interactor after the application
     * received @Uri of new profile photo.
     *
     * That's why I created own viewModelScope. This scope is controlled from Fragment through
     * @onResume method.
     * May be the reason of this strange behavior is badly tuned dagger's scopes.
     */
    private var customViewModelScope: CoroutineScope? = null

    private val _profileModel: MutableLiveData<ProfileModel> = MutableLiveData()
    val profileModel: LiveData<ProfileModel> = _profileModel

    private val _currentStatus: MutableLiveData<Status> = MutableLiveData()
    val currentStatus: LiveData<Status> = _currentStatus

    private var needToShowSuccessMessage: Boolean = false

    init {
        initCollectors()
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
        needToShowSuccessMessage = true
        viewModelScope.launch {
            interactor.updateName(name)
        }
    }

    fun updateSurname(surname: String) {
        _currentStatus.value = Status.LOADING
        needToShowSuccessMessage = true
        viewModelScope.launch {
            interactor.updateSurname(surname)
        }
    }

    fun updateWordsAboutPerson(wordsAboutPerson: String) {
        _currentStatus.value = Status.LOADING
        needToShowSuccessMessage = true
        viewModelScope.launch {
            interactor.updateWordsAboutPerson(wordsAboutPerson)
        }
    }

    fun updateQuote(quote: GoQuote) {
        _currentStatus.value = Status.LOADING
        needToShowSuccessMessage = true
        viewModelScope.launch {
            interactor.updateQuote(quote)
        }
    }

    fun updateFavoriteGenres(genres: List<Genre>) {
        _currentStatus.value = Status.LOADING
        needToShowSuccessMessage = true
        viewModelScope.launch {
            interactor.updateFavoriteGenres(genres)
        }
    }

    fun updateFavoriteAuthors(authors: List<Author>) {
        _currentStatus.value = Status.LOADING
        needToShowSuccessMessage = true
        viewModelScope.launch {
            interactor.updateFavoriteAuthors(authors)
        }
    }

    fun updateFavoriteBooks(books: List<GoogleBook>) {
        _currentStatus.value = Status.LOADING
        needToShowSuccessMessage = true
        viewModelScope.launch {
            interactor.updateFavoriteBooks(books)
        }
    }

    fun updateWantToReadBooks(books: List<GoogleBook>) {
        _currentStatus.value = Status.LOADING
        needToShowSuccessMessage = true
        viewModelScope.launch {
            interactor.updateWantToReadBooks(books)
        }
    }

    fun uploadNewProfilePhoto(uri: Uri) {
        _currentStatus.postValue(Status.LOADING)
        needToShowSuccessMessage = true
        viewModelScope.launch {
            interactor.uploadProfileImageToStorage(uri)
        }
    }

    fun initCollectors() {
        if (customViewModelScope == null) {
            customViewModelScope = CoroutineScope(Dispatchers.Main.immediate)
        } else {
            return
        }

        customViewModelScope?.launch {
            interactor.uploadPhotoResult.collect { result ->
                handleUploadPhotoResult(result)
            }
        }

        customViewModelScope?.launch {
            interactor.profileModel.collect { result ->
                handleErrorRepositoryResult(result)
                handleRepositoryResult(result)
            }
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
        if (needToShowSuccessMessage) {
            _successMessageId.postValue(R.string.profile_updated)
            needToShowSuccessMessage = false
        }
        val profile = result.data
        _profileModel.postValue(profile)
        _currentStatus.postValue(Status.PROFILE_MODEL_FROM_REMOTE)
    }

    private fun handleCacheResult(result: BaseRepositoryResult.CacheResult<ProfileModel>) {
        needToShowSuccessMessage = false
        val profile = result.result
        _profileModel.postValue(profile)
        _currentStatus.postValue(Status.PROFILE_MODEL_FROM_CACHE)
    }

    override fun onCleared() {
        super.onCleared()

        customViewModelScope?.cancel()
        customViewModelScope = null
    }
}