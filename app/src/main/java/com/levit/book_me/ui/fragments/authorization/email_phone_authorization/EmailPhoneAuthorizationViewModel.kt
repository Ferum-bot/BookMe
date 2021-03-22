package com.levit.book_me.ui.fragments.authorization.email_phone_authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.levit.book_me.core.extensions.isValidTelephoneNumber
import com.levit.book_me.core.interfaces.ResourceProvider
import javax.inject.Inject

class EmailPhoneAuthorizationViewModel @Inject constructor(
    private val resources: ResourceProvider
): ViewModel() {

    private val _errorMessage: MutableLiveData<String?> = MutableLiveData(null)
    val errorMessage: LiveData<String?>
    get() = _errorMessage

    private val _isPhoneValid: MutableLiveData<Boolean> = MutableLiveData(false)
    val isPhoneValid: LiveData<Boolean>
    get() = _isPhoneValid
    
    private val _isEmailValid: MutableLiveData<Boolean> = MutableLiveData(false)
    val isEmailValid: LiveData<Boolean>
    get() = _isEmailValid

    var phoneNumber: String? = null
    private set

    var emailAddress: String? = null
    private set
    
    fun onEmailTextChanged(text: CharSequence, start: Int, before: Int, count: Int) {
        
    }

    fun onPhoneTextChanged(text: CharSequence, start: Int, before: Int, count: Int) {
        val phone = text.toString()
        phoneNumber = if (phone.isValidTelephoneNumber()) {
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