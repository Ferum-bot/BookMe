package com.levit.book_me.ui.fragments.authorization.email_sign_up

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.levit.book_me.core.extensions.isValidEmailAddress
import com.levit.book_me.core.extensions.isValidEmailPassword
import com.levit.book_me.core_base.di.AuthorizationScope
import com.levit.book_me.ui.base.BaseViewModel
import javax.inject.Inject

@AuthorizationScope
class EmailSignUpViewModel @Inject constructor(): BaseViewModel() {

    private val _isEmailValid: MutableLiveData<Boolean> = MutableLiveData(false)
    val isEmailValid: LiveData<Boolean> = _isEmailValid

    private val _isPasswordValid: MutableLiveData<Boolean> = MutableLiveData(false)
    val isPasswordValid: LiveData<Boolean> = _isPasswordValid

    private val _isPasswordsMatch: MutableLiveData<Boolean> = MutableLiveData(false)
    val isPasswordsMatch: LiveData<Boolean> = _isPasswordsMatch

    private var repeatPassword: String? = null

    var email: String? = null
    private set

    var password: String? = null
    private set

    fun onEmailChanged(text: CharSequence, start: Int, before: Int, count: Int) {
        val email = text.toString()
        this.email = if (email.isValidEmailAddress()) {
            _isEmailValid.value = true
            email
        }
        else {
            _isEmailValid.value = false
            null
        }
    }

    fun onPasswordChanged(text: CharSequence, start: Int, before: Int, count: Int) {
        val password = text.toString()
        this.password = if (password.isValidEmailPassword()) {
            _isPasswordValid.value = true
            password
        }
        else {
            _isPasswordValid.value = false
            null
        }
    }

    fun onRepeatPasswordChanged(text: CharSequence, start: Int, before: Int, count: Int) {
        val repeatPassword = text.toString()
        this.repeatPassword = repeatPassword
        _isPasswordsMatch.value = passwordMismatch()
    }

    fun passwordMismatch(): Boolean {
        if (password == null) {
            return true
        }
        if (repeatPassword == null) {
            return true
        }
        if (password != repeatPassword) {
            return true
        }
        return false
    }
}