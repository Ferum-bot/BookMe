package com.levit.book_me.ui.fragments.main_screens.chats

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.levit.book_me.core.models.chat_kit.UserChat
import com.levit.book_me.core_base.di.MainScreenScope
import com.levit.book_me.interactors.main_screen.ChatsInteractor
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.repositories.result_models.BaseRepositoryResult
import com.levit.book_me.ui.base.BaseMainScreenViewModel
import com.levit.book_me.ui.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@MainScreenScope
class MainScreenChatsViewModel @Inject constructor(
    private val interactor: ChatsInteractor
): BaseMainScreenViewModel() {

    enum class Status {
        LOADING, LOADED_FROM_CACHE, LOADED_FROM_REMOTE,
        NO_AVAILABLE_CHATS, NO_AVAILABLE_DATA,
    }

    private val _chats: MutableLiveData<List<UserChat>> = MutableLiveData()
    val chats: LiveData<List<UserChat>> = _chats

    private val _currentStatus: MutableLiveData<Status> = MutableLiveData()
    val currentStatus: LiveData<Status> = _currentStatus

    init {

        viewModelScope.launch {
            interactor.userChats.collect { repositoryResult ->
                handleErrorRepositoryResult(repositoryResult)
                handleRepositoryResult(repositoryResult)
            }
        }

        refreshChats()
    }

    fun refreshChats() {
        _currentStatus.value = Status.LOADING
        viewModelScope.launch {
            interactor.getAllChats()
        }
    }

    private fun handleRepositoryResult(result: BaseRepositoryResult<List<UserChat>>) {
        when(result) {
            is BaseRepositoryResult.CacheResult -> handleCacheResult(result)
            is BaseRepositoryResult.RemoteResult -> handleRemoteResult(result)
            is BaseRepositoryResult.Error -> handleErrorResult(result)
            is BaseRepositoryResult.EmptySuccess -> handleEmptySuccess(result)
        }
    }

    private fun handleCacheResult(result: BaseRepositoryResult.CacheResult<List<UserChat>>) {
        val chats = result.result
        if (chats.isEmpty()) {
            _currentStatus.postValue(Status.NO_AVAILABLE_DATA)
            return
        }
        _currentStatus.postValue(Status.LOADED_FROM_CACHE)
        _chats.postValue(chats)
    }

    private fun handleRemoteResult(result: BaseRepositoryResult.RemoteResult<List<UserChat>>) {
        val remoteResult = result.result
        if (remoteResult !is RetrofitResult.Success) {
            _currentStatus.postValue(Status.NO_AVAILABLE_DATA)
            return
        }
        val chats = remoteResult.data
        if (chats.isEmpty()) {
            _currentStatus.postValue(Status.NO_AVAILABLE_CHATS)
            return
        }
        _currentStatus.postValue(Status.LOADED_FROM_REMOTE)
        _chats.postValue(chats)
    }

    private fun handleErrorResult(result: BaseRepositoryResult.Error) {
        _currentStatus.postValue(Status.NO_AVAILABLE_DATA)
    }

    private fun handleEmptySuccess(result: BaseRepositoryResult.EmptySuccess) {
        _currentStatus.postValue(Status.NO_AVAILABLE_CHATS)
    }
}