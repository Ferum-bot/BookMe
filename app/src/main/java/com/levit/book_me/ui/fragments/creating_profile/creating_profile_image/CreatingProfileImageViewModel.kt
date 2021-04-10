package com.levit.book_me.ui.fragments.creating_profile.creating_profile_image

import android.content.Context
import android.net.Uri
import androidx.lifecycle.*
import com.google.firebase.storage.UploadTask
import com.levit.book_me.R
import com.levit.book_me.core_base.di.CreatingProfileScope
import com.levit.book_me.interactors.interfaces.UploadProfileImageInteractor
import com.levit.book_me.network.network_result_data.FirebaseStorageUploadResult
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@CreatingProfileScope
class CreatingProfileImageViewModel @Inject constructor(
    private val uploadProfileImageInteractor: UploadProfileImageInteractor,
): ViewModel() {

    private val _isPhotoChosen: MutableLiveData<Boolean> = MutableLiveData(false)
    val isPhotoChosen: LiveData<Boolean> = _isPhotoChosen

    private val _imageUri: MutableLiveData<Uri?> = MutableLiveData(null)
    val imageUri: LiveData<Uri?> = _imageUri

    private val _errorMessage: MutableLiveData<String?> = MutableLiveData(null)
    val errorMessage: LiveData<String?> = _errorMessage

    private val _errorMessageId: MutableLiveData<Int?> = MutableLiveData(null)
    val errorMessageId: LiveData<Int?> = _errorMessageId

    private val _isPhotoUpLoaded: MutableLiveData<Boolean> = MutableLiveData(false)
    val isPhotoUpLoaded: LiveData<Boolean> = _isPhotoUpLoaded

    fun photoIsChosen(imageUri: Uri) {
        _isPhotoChosen.value = true
        _imageUri.value = imageUri
    }

    fun upLoadProfilePhoto(contextProvider: () -> Context) {
        val imageUri = if (_imageUri.value == null) {
            photoDoesNotExist()
            return
        }
        else {
            _imageUri.value!!
        }

        viewModelScope.launch {
            uploadProfileImageInteractor.uploadProfileImageToStorage(imageUri)
        }

        viewModelScope.launch {
            uploadProfileImageInteractor.uploadResult
                .collectLatest(this@CreatingProfileImageViewModel::handleUploadProfileResult)
        }
    }

    fun errorMessageHasShown() {
        _errorMessage.value = null
        _errorMessageId.value = null
    }

    private suspend fun handleUploadProfileResult(result: FirebaseStorageUploadResult) =
        when (result) {
            is FirebaseStorageUploadResult.Error ->
                handleErrorResult(result.exception)
            is FirebaseStorageUploadResult.Success ->
                handleSuccessResult(result.taskSnapshot)
        }

    private fun handleErrorResult(ex: Exception) {
        if (ex.message == null) {
            _errorMessageId.postValue(R.string.something_went_wrong)
        }
        else {
            _errorMessage.postValue(ex.message)
        }
    }

    private fun handleSuccessResult(snapshot: UploadTask.TaskSnapshot) {
        _isPhotoUpLoaded.postValue(true)
    }

    private fun photoDoesNotExist() {
        _errorMessageId.postValue(R.string.can_not_find_chosen_photo)
    }
}