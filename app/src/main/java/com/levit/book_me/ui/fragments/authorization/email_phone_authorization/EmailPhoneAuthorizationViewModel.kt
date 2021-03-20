package com.levit.book_me.ui.fragments.authorization.email_phone_authorization

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.levit.book_me.core.interfaces.ResourceProvider
import javax.inject.Inject

class EmailPhoneAuthorizationViewModel @Inject constructor(
    private val resources: ResourceProvider
): ViewModel() {

    private val _errorMessage: MutableLiveData<String?> = MutableLiveData(null)
    val errorMessage: LiveData<String?>
    get() = _errorMessage

    var currentAuthorizationType: EmailPhoneAuthorizationContainerFragment.AuthorizationType =
        EmailPhoneAuthorizationContainerFragment.AuthorizationType.PHONE
    set(value) {
        field = value
    }

    fun onEmailTextChanged(text: CharSequence, start: Int, before: Int, count: Int) {

    }

    fun onPhoneTextChanged(text: CharSequence, start: Int, before: Int, count: Int) {

    }

    fun errorMessageHasShown() {
        _errorMessage.value = null
    }
}