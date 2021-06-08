package com.levit.book_me.ui.fragments.creating_profile.creating_name_surname

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.levit.book_me.R
import com.levit.book_me.core.models.quote.GoQuote
import com.levit.book_me.core_base.di.CreatingProfileScope
import com.levit.book_me.ui.base.BaseViewModel
import com.levit.book_me.ui.fragments.creating_profile.utills.CreatingProfileConstants
import javax.inject.Inject

@CreatingProfileScope
class CreatingNameSurnameViewModel @Inject constructor(): BaseViewModel() {

    private val _isNameCorrect: MutableLiveData<Boolean> = MutableLiveData(true)
    val isNameCorrect: LiveData<Boolean> = _isNameCorrect

    private val _isSurnameCorrect: MutableLiveData<Boolean> = MutableLiveData(true)
    val isSurnameCorrect: LiveData<Boolean> = _isSurnameCorrect

    private val _isWordsAboutYouCorrect: MutableLiveData<Boolean> = MutableLiveData(true)
    val isWordsAboutYouCorrect: LiveData<Boolean> = _isWordsAboutYouCorrect

    private val _isWordsAboutYouErrorStringId: MutableLiveData<Int> = MutableLiveData(R.string.write_some_words_about_you)
    val isWordsAboutYouErrorStringId: LiveData<Int> = _isWordsAboutYouErrorStringId

    private val _isQuoteChosen: MutableLiveData<Boolean> = MutableLiveData(true)
    val isQuoteChosen: LiveData<Boolean> = _isQuoteChosen

    var chosenQuote: GoQuote? = null
    set(value) {
        if (value != null) {
            _isQuoteChosen.postValue(true)
        }
        field = value
    }

    fun onNameChanged(text: CharSequence, start: Int, before: Int, count: Int) {
        val inputName = text.toString()
        if (inputName.isNameValid()) {
            _isNameCorrect.postValue(true)
        }
    }

    fun onSurnameChanged(text: CharSequence, start: Int, before: Int, count: Int) {
        val inputSurname = text.toString()
        if (inputSurname.isNameValid()) {
            _isSurnameCorrect.postValue(true)
        }
    }

    fun onAboutYouChanged(text: CharSequence, start: Int, before: Int, count: Int) {
        val inputText = text.toString().filter { it != ' ' }
        if (inputText.isBlank()) {
            _isWordsAboutYouErrorStringId.postValue(R.string.write_some_words_about_you)
            return
        }
        if (inputText.length <= CreatingProfileConstants.WORDS_ABOUT_YOU_MIN_LENGTH / 2) {
            _isWordsAboutYouErrorStringId.postValue(R.string.minimum_length_is)
            return
        }
        if (inputText.length >= CreatingProfileConstants.WORDS_ABOUT_YOU_MAX_LENGTH / 2) {
            _isWordsAboutYouErrorStringId.postValue(R.string.maximum_length_is)
            return
        }
        _isWordsAboutYouCorrect.postValue(true)
    }

    fun checkIsEverythingIsValid(
        name: String,
        surname: String,
        wordsAboutYou: String
    ): Boolean {
        if (!name.isNameValid()) {
            _isNameCorrect.postValue(false)
            return false
        }
        if(!surname.isNameValid()) {
            _isSurnameCorrect.postValue(false)
            return false
        }
        if(!wordsAboutYou.isWordsAboutYouValid()) {
            _isWordsAboutYouCorrect.postValue(false)
            return false
        }
        if (chosenQuote == null) {
            _isQuoteChosen.postValue(false)
            return false
        }
        return true
    }

    private fun String.isNameValid(): Boolean {
        if (isNotBlank()) {
            return true
        }
        return false
    }

    private fun String.isWordsAboutYouValid(): Boolean {
        val text = filter { it != ' ' }
        if (text.isBlank()) {
            return false
        }
        if (text.length <= CreatingProfileConstants.WORDS_ABOUT_YOU_MIN_LENGTH / 2) {
            return false
        }
        if (text.length >= CreatingProfileConstants.WORDS_ABOUT_YOU_MAX_LENGTH / 2) {
            return false
        }
        return true
    }
}