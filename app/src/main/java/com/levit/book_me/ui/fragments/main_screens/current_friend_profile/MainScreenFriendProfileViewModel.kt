package com.levit.book_me.ui.fragments.main_screens.current_friend_profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.levit.book_me.core.models.FriendProfileModel
import com.levit.book_me.core_base.di.MainScreenScope
import com.levit.book_me.interactors.main_screen.CurrentFriendInteractor
import com.levit.book_me.repositories.result_models.BaseRepositoryResult
import com.levit.book_me.ui.base.BaseMainScreenViewModel
import com.levit.book_me.ui.base.BaseViewModel
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
    }

    fun loadCurrentFriend(id: Long) {
        if (id < 0) { return }

        _currentStatus.value = Statuses.LOADING
        viewModelScope.launch {
            interactor.getFriend(id)
        }
    }

    private fun handleFriendModelResult(result: BaseRepositoryResult<FriendProfileModel>) {
        when (result) {

        }
    }
}