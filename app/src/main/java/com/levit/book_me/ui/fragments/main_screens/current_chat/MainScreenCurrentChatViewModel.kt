package com.levit.book_me.ui.fragments.main_screens.current_chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.levit.book_me.core.enums.sockets.SocketConnectionStatus
import com.levit.book_me.core.models.chat_kit.Message
import com.levit.book_me.core.models.profile.ProfileModel
import com.levit.book_me.core.models.profile.UserModel
import com.levit.book_me.core_base.di.MainScreenScope
import com.levit.book_me.interactors.main_screen.CurrentChatInteractor
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.repositories.result_models.BaseRepositoryResult
import com.levit.book_me.ui.base.BaseMainScreenViewModel
import com.levit.book_me.ui.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@MainScreenScope
class MainScreenCurrentChatViewModel @Inject constructor(
    private val interactor: CurrentChatInteractor,
): BaseMainScreenViewModel() {

    enum class Status {
        LOADING, ERROR,
        CONNECTION_ESTABLISHED, CONNECTION_LOST,
        LOADED_FROM_REMOTE, LOADED_FROM_CACHE;
    }

    private val _currentStatus: MutableLiveData<Status> = MutableLiveData(Status.LOADING)
    val currentStatus: LiveData<Status> = _currentStatus

    private val _messages: MutableLiveData<List<Message>> = MutableLiveData()
    val messages: LiveData<List<Message>> = _messages

    private val _interlocutor: MutableLiveData<ProfileModel> = MutableLiveData()
    val interlocutor: LiveData<ProfileModel> = _interlocutor

    private val _isInterlocutorOnline: MutableLiveData<Boolean> = MutableLiveData(false)
    val isInterlocutorOnline: LiveData<Boolean> = _isInterlocutorOnline

    private val _isInterlocutorTyping: MutableLiveData<Boolean> = MutableLiveData(false)
    val isInterlocutorTyping: LiveData<Boolean> = _isInterlocutorTyping

    init {
        viewModelScope.launch {
            interactor.messages.collect { messagesResult ->
                handleBaseMessagesResult(messagesResult)
            }
        }

        viewModelScope.launch {
            interactor.interlocutor.collect { interlocutorResult ->
                handleErrorRepositoryResult(interlocutorResult)
                handleBaseInterlocutorResult(interlocutorResult)
            }
        }

        viewModelScope.launch {
            interactor.isInterlocutorOnline.collect {  isOnline ->
                handleOnlineEvent(isOnline)
            }
        }

        viewModelScope.launch {
            interactor.isInterlocutorTyping.collect { isTyping ->
                handleTypingEvent(isTyping)
            }
        }

        viewModelScope.launch {
            interactor.currentConnectionStatus.collect { connectionStatus ->
                handleConnectionStatus(connectionStatus)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()

        interactor.stopListeningSocket()
    }

    fun getChatConnection(chatId: Long) {
        viewModelScope.launch {
            interactor.startListeningSocket(chatId)
        }
    }

    fun updateConnection() {
        viewModelScope.launch {
            interactor.reconnectToSocket()
        }
    }

    fun sendMessage(text: String) {
        viewModelScope.launch {
            interactor.sendMessage(text)
        }
    }

    fun retrySendingMessage(message: Message) {
        viewModelScope.launch {
            interactor.retrySendingMessage(message)
        }
    }

    private fun handleBaseMessagesResult(result: BaseRepositoryResult<List<Message>>) {
        when(result) {
            is BaseRepositoryResult.RemoteResult ->
                handleRemoteRepositoryResult(result)
        }
    }

    private fun handleBaseInterlocutorResult(result: BaseRepositoryResult<ProfileModel>) {
        when(result) {
            is BaseRepositoryResult.RemoteResult ->
                handleRemoteProfileResult(result)
        }
    }

    private fun handleRemoteRepositoryResult(result: BaseRepositoryResult.RemoteResult<List<Message>>) {
        val remoteResult = result.result
        if (remoteResult is RetrofitResult.Success) {
            val messages = remoteResult.data
            _messages.postValue(messages)
            _currentStatus.postValue(Status.LOADED_FROM_REMOTE)
        }
    }

    private fun handleRemoteProfileResult(result: BaseRepositoryResult.RemoteResult<ProfileModel>) {
        val remoteResult = result.result
        if (remoteResult is RetrofitResult.Success) {
            val profile = remoteResult.data
            _interlocutor.postValue(profile)
            _currentStatus.postValue(Status.LOADED_FROM_REMOTE)
        }
    }

    private fun handleTypingEvent(isTyping: Boolean) {
        _isInterlocutorTyping.postValue(isTyping)
    }

    private fun handleOnlineEvent(isOnline: Boolean) {
        _isInterlocutorOnline.postValue(isOnline)
    }

    private fun handleConnectionStatus(status: SocketConnectionStatus) {
        when(status) {
            SocketConnectionStatus.CONNECTING ->
                _currentStatus.postValue(Status.LOADING)
            SocketConnectionStatus.CONNECTED ->
                _currentStatus.postValue(Status.LOADED_FROM_REMOTE)
        }
    }
}