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
import com.levit.book_me.core.interfaces.ResourceProvider
import com.levit.book_me.core_base.di.AuthorizationScope
import javax.inject.Inject

@AuthorizationScope
class ChooseTypeAuthorizationViewModel @Inject constructor(
    private val resources: ResourceProvider
): ViewModel() {

    private val _errorMessage: MutableLiveData<String?> = MutableLiveData(null)
    val errorMessage: LiveData<String?>
    get() = _errorMessage

    private val _credential: MutableLiveData<AuthCredential?> = MutableLiveData(null)
    val credential: LiveData<AuthCredential?>
    get() = _credential
    
    fun googleSignInActivityResultCallback(result: ActivityResult?) {
        if (result == null) {
            val errorMessage = resources.string(R.string.something_went_wrong)
            _errorMessage.value = errorMessage
            return
        }
        if (result.isDataAvailable()) {
            tryToAuthWithGoogle(result.data!!)
        }
        else {
            val errorMessage = resources.string(R.string.something_went_wrong)
            _errorMessage.value = errorMessage
            return
        }
    }

    fun firebaseAuthWithFacebook(token: AccessToken) {
        val credential = FacebookAuthProvider.getCredential(token.token)
        _credential.value = credential
    }

    fun errorMessageHasShown() {
        _errorMessage.value = null
    }

    private fun tryToAuthWithGoogle(intent: Intent) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(intent)
        try {
            val account = task.getResult(ApiException::class.java)
            firebaseAuthWithGoogle(account!!.idToken!!)
        }
        catch (ex: ApiException) {
            val defaultErrorMessage = resources.string(R.string.something_went_wrong)
            _errorMessage.value = ex.message ?: defaultErrorMessage
        }
        catch (ex: NullPointerException) {
            val errorMessage = resources.string(R.string.something_went_wrong)
            _errorMessage.value = errorMessage
        }
    }

    private fun firebaseAuthWithGoogle(token: String) {
        val credential = GoogleAuthProvider.getCredential(token, null)
        _credential.value = credential
    }
}