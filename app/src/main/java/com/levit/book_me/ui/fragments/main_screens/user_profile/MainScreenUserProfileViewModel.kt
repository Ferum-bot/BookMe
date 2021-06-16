package com.levit.book_me.ui.fragments.main_screens.user_profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.levit.book_me.core.models.ProfileModel
import com.levit.book_me.core_base.di.MainScreenScope
import com.levit.book_me.interactors.main_screen.UserProfileInteractor
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

        updateAllData()
    }

    fun updateAllData() {
        profileModelJob?.cancel()
        profileModelJob = viewModelScope.launch {
            _currentStatus.postValue(Status.LOADING)
            interactor.getProfile()
        }
    }
}