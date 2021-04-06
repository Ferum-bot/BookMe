package com.levit.book_me.ui.fragments.creating_profile.creating_name_surname

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.levit.book_me.core_base.di.CreatingProfileScope
import javax.inject.Inject

@CreatingProfileScope
class CreatingNameSurnameViewModel @Inject constructor(): ViewModel() {

    private val _isNameCorrect: MutableLiveData<Boolean> = MutableLiveData(false)
    val isNameCorrect: LiveData<Boolean> = _isNameCorrect

    private val _isSurnameCorrect: MutableLiveData<Boolean> = MutableLiveData(false)
    val isSurnameCorrect: LiveData<Boolean> = _isSurnameCorrect

    fun onNameChanged(text: CharSequence, start: Int, before: Int, count: Int) {

    }

    fun onSurnameChanged(text: CharSequence, start: Int, before: Int, count: Int) {

    }
}