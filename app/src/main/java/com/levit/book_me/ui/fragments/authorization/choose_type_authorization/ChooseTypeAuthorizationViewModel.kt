package com.levit.book_me.ui.fragments.authorization.choose_type_authorization

import android.content.Intent
import androidx.activity.result.ActivityResult
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.facebook.AccessToken
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.GoogleAuthProvider
import com.levit.book_me.R
import com.levit.book_me.core.extensions.isDataAvailable
import com.levit.book_me.core_base.di.AuthorizationScope
import com.levit.book_me.ui.base.BaseViewModel
import javax.inject.Inject

@AuthorizationScope
class ChooseTypeAuthorizationViewModel @Inject constructor(): BaseViewModel() {

    private val _credential: MutableLiveData<AuthCredential?> = MutableLiveData(null)
    val credential: LiveData<AuthCredential?> = _credential
    
    fun googleSignInActivityResultCallback(result: ActivityResult?) {
        if (result == null) {
            _errorMessageId.value = R.string.something_went_wrong
            return
        }
        if (result.isDataAvailable()) {
            tryToAuthWithGoogle(result.data!!)
        } else {
            _errorMessageId.value = R.string.something_went_wrong
            return
        }
    }

    fun firebaseAuthWithFacebook(token: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(token.token)
        _credential.value = credential
    }

    private fun tryToAuthWithGoogle(intent: Intent) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(intent)
        try {
            val account = task.getResult(ApiException::class.java)
            firebaseAuthWithGoogle(account!!.idToken!!)
        }
        catch (ex: ApiException) {
            val defaultErrorMessage = R.string.something_went_wrong
            if (ex.message.isNullOrBlank()) {
                _errorMessageId.value = defaultErrorMessage
            } else {
                _errorMessage.value = ex.message
            }
        }
        catch (ex: NullPointerException) {
            _errorMessageId.value = R.string.something_went_wrong
        }
    }

    private fun firebaseAuthWithGoogle(token: String) {
        val credential = GoogleAuthProvider.getCredential(token, null)
        _credential.value = credential
    }
}