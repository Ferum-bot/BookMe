package com.levit.book_me.ui.fragments.main_screens.current_friend_profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.levit.book_me.core.models.profile.FriendProfileModel
import com.levit.book_me.core_base.di.MainScreenScope
import com.levit.book_me.interactors.main_screen.CurrentFriendInteractor
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.repositories.result_models.BaseRepositoryResult
import com.levit.book_me.ui.base.BaseMainScreenViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@MainScreenScope
class MainScreenFriendProfileViewModel @Inject constructor(
    private val interactor: CurrentFriendInteractor,
): BaseMainScreenViewModel() {

    enum class Statuses {
        PROFILE_FROM_CACHE, PROFILE_FROM_REMOTE,
        LOADING, USER_HAS_NO_FRIENDS, NO_AVAILABLE_DATA;
    }

    private val _currentFriend: MutableLiveData<FriendProfileModel> = MutableLiveData()
    val currentFriend: LiveData<FriendProfileModel> = _currentFriend

    private val _currentStatus: MutableLiveData<Statuses> = MutableLiveData()
    val currentStatus: LiveData<Statuses> = _currentStatus

    init {
        viewModelScope.launch {
            interactor.friendModel.collect { result ->
                handleErrorRepositoryResult(result)
                handleFriendModelResult(result)
            }
        }

        loadCurrentFriend(100)
    }

    fun loadCurrentFriend(id: Long) {
        if (id < 0) { return }

        _currentStatus.value = Statuses.LOADING
        viewModelScope.launch {
            interactor.getFriend(id)
        }
    }

    private fun handleFriendModelResult(result: BaseRepositoryResult<FriendProfileModel>) =
    when (result) {
        is BaseRepositoryResult.RemoteResult ->
            handleFriendRemoteResult(result)
        is BaseRepositoryResult.CacheResult ->
            handleFriendCacheResult(result)
        is BaseRepositoryResult.Error ->
            handleFriendErrorResult(result)
        is BaseRepositoryResult.EmptySuccess ->
            handleEmptySuccess()
    }

    private fun handleFriendCacheResult(result: BaseRepositoryResult.CacheResult<FriendProfileModel>) {
        _currentStatus.postValue(Statuses.PROFILE_FROM_CACHE)
        val friendProfile = result.result
        _currentFriend.postValue(friendProfile)
    }

    private fun handleFriendRemoteResult(result: BaseRepositoryResult.RemoteResult<FriendProfileModel>) {
        _currentStatus.postValue(Statuses.PROFILE_FROM_REMOTE)
        val retrofitResult = result.result
        if (retrofitResult !is RetrofitResult.Success) {
            return
        }
        val friendProfile = retrofitResult.data
        _currentFriend.postValue(friendProfile)
    }

    private fun handleFriendErrorResult(result: BaseRepositoryResult.Error) {
        _currentStatus.postValue(Statuses.NO_AVAILABLE_DATA)
    }

    private fun handleEmptySuccess() {
        _currentStatus.postValue(Statuses.USER_HAS_NO_FRIENDS)
    }
}