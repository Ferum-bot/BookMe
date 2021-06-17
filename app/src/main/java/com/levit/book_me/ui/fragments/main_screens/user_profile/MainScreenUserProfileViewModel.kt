package com.levit.book_me.ui.fragments.main_screens.user_profile

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.levit.book_me.R
import com.levit.book_me.core.models.ProfileModel
import com.levit.book_me.core_base.di.MainScreenScope
import com.levit.book_me.interactors.main_screen.UserProfileInteractor
import com.levit.book_me.network.network_result_data.FirebaseStorageUploadResult
import com.levit.book_me.ui.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@MainScreenScope
class MainScreenUserProfileViewModel @Inject constructor(
    private val interactor: UserProfileInteractor,
): BaseViewModel() {

    enum class Status {
        LOADING, PROFILE_MODEL_FROM_CACHE,
        PROFILE_MODEL_FROM_REMOTE, NOTHING_TO_SHOW
    }

    private val _profileModel: MutableLiveData<ProfileModel> = MutableLiveData()
    val profileModel: LiveData<ProfileModel> = _profileModel

    private val _currentStatus: MutableLiveData<Status> = MutableLiveData()
    val currentStatus: LiveData<Status> = _currentStatus

    private var profileModelJob: Job? = null

    init {
        viewModelScope.launch {
            interactor.profileModel.collect { model ->
                _currentStatus.postValue(Status.PROFILE_MODEL_FROM_CACHE)
                _profileModel.postValue(model)
            }
        }

        viewModelScope.launch {
            interactor.uploadPhotoResult.collect { result ->
                handleUploadPhotoResult(result)
            }
        }

        updateAllData()
    }

    fun updateAllData() {
        _currentStatus.postValue(Status.LOADING)
        profileModelJob?.cancel()
        profileModelJob = viewModelScope.launch {
            interactor.getProfile()
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
}