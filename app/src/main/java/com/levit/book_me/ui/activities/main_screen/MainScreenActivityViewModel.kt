package com.levit.book_me.ui.activities.main_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.levit.book_me.core_base.di.MainScreenScope
import com.levit.book_me.interactors.main_screen.MainScreenInteractor
import com.levit.book_me.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@MainScreenScope
class MainScreenActivityViewModel @Inject constructor(
    private val interactor: MainScreenInteractor
): BaseViewModel() {

    private val _currentInterlocutorId: MutableLiveData<Long?> = MutableLiveData(null)
    val currentInterlocutorId: LiveData<Long?> = _currentInterlocutorId

    private val _chatAndInterlocutorId: MutableLiveData<Pair<Long, Long>?> = MutableLiveData(null)
    val chatAndInterlocutorId: LiveData<Pair<Long, Long>?> = _chatAndInterlocutorId

    private val _openGeneralChat: MutableLiveData<Boolean> = MutableLiveData(true)
    val openGeneralChat: LiveData<Boolean> = _openGeneralChat

    private val _showTopButtons: MutableLiveData<Boolean> = MutableLiveData(true)
    val showTopButtons: LiveData<Boolean> = _showTopButtons

    init {

        viewModelScope.launch {
            interactor.startListeningAllChats()
        }
    }

    fun searchNewFriend() {

    }

    fun openGeneralChat() {
        _openGeneralChat.postValue(true)
    }

    fun openInterlocutorProfile(interlocutorId: Long) {
        _currentInterlocutorId.postValue(interlocutorId)
    }

    fun openChatWithInterlocutor(chatId: Long, interlocutorId: Long) {
        _chatAndInterlocutorId.postValue(chatId to interlocutorId)
    }

    fun interlocutorProfileOpened() {
        _currentInterlocutorId.postValue(null)
    }

    fun currentChatOpened() {
        _chatAndInterlocutorId.postValue(null)
    }

    fun generalChatHasOpened() {
        _openGeneralChat.postValue(false)
    }

    fun showTopButtons(show: Boolean) {
        _showTopButtons.value = show
    }

    override fun onCleared() {
        super.onCleared()

        interactor.stopListeningAllChats()
    }
}