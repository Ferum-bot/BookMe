package com.levit.book_me.ui.fragments.authorization.confirm_phone_code

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.levit.book_me.core.extensions.isValidPhoneAuthorizationCode
import javax.inject.Inject

class ConfirmPhoneCodeViewModel @Inject constructor(

): ViewModel() {

    private val _sentCode: MutableLiveData<String?> = MutableLiveData(null)
    val sentCode: LiveData<String?>
    get() = _sentCode

    fun onCodeChangeListener(text: CharSequence, start: Int, before: Int, count: Int) {
        val code = text.toString()
        if (code.isValidPhoneAuthorizationCode()) {
            _sentCode.value = code
        }
        else {
            _sentCode.value = null
        }
    }

}