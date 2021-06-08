package com.levit.book_me.ui.fragments.authorization.confirm_phone_code

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.levit.book_me.core.extensions.isValidPhoneAuthorizationCode
import com.levit.book_me.core_base.di.AuthorizationScope
import com.levit.book_me.ui.base.BaseViewModel
import javax.inject.Inject

@AuthorizationScope
class ConfirmPhoneCodeViewModel @Inject constructor(): BaseViewModel() {

    private val _sentCode: MutableLiveData<String?> = MutableLiveData(null)
    val sentCode: LiveData<String?> = _sentCode

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