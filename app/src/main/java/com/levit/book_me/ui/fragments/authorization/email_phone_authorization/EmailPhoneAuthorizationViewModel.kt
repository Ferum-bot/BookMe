package com.levit.book_me.ui.fragments.authorization.email_phone_authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.levit.book_me.core.extensions.isValidEmailAddress
import com.levit.book_me.core.models.MobileTelephone
import com.levit.book_me.core_base.di.AuthorizationScope
import javax.inject.Inject

@AuthorizationScope
class EmailPhoneAuthorizationViewModel @Inject constructor(): ViewModel() {

    private val _errorMessage: MutableLiveData<String?> = MutableLiveData(null)
    val errorMessage: LiveData<String?>
    get() = _errorMessage

    private val _isPhoneValid: MutableLiveData<Boolean> = MutableLiveData(false)
    val isPhoneValid: LiveData<Boolean>
    get() = _isPhoneValid
    
    private val _isEmailValid: MutableLiveData<Boolean> = MutableLiveData(false)
    val isEmailValid: LiveData<Boolean>
    get() = _isEmailValid

    var phoneNumber: MobileTelephone? = null
    private set

    var emailAddress: String? = null
    private set

    var emailPassword: String? = null
    private set

    fun onEmailTextChanged(text: CharSequence, start: Int, before: Int, count: Int) {
        val email = text.toString()
        emailAddress = if (email.isValidEmailAddress()) {
            _isEmailValid.value = true
            email
        }
        else {
            _isEmailValid.value = false
            null
        }
    }

    fun onPasswordTextChanged(text: CharSequence, start: Int, before: Int, count: Int) {
        val password = text.toString()
        emailPassword = password
    }

    fun onPhoneTextChanged(text: CharSequence, start: Int, before: Int, count: Int) {
        val phone = MobileTelephone.fromString(text.toString())
        phoneNumber = if (phone.isValidNumber()) {
            _isPhoneValid.value = true
            phone
        }
        else {
            _isPhoneValid.value = false
            null
        }
    }

    fun errorMessageHasShown() {
        _errorMessage.value = null
    }
}