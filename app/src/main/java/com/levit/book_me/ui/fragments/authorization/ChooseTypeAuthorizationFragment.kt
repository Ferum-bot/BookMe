package com.levit.book_me.ui.fragments.authorization

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.levit.book_me.R
import com.levit.book_me.core.extensions.isDataAvailable
import com.levit.book_me.core_presentation.base.BaseFragment
import com.levit.book_me.databinding.FragmentChooseTypeAuthorizationBinding

class ChooseTypeAuthorizationFragment: BaseFragment(R.layout.fragment_choose_type_authorization) {

    private val binding by lazy { FragmentChooseTypeAuthorizationBinding.inflate(layoutInflater) }

    private lateinit var googleSignInClient: GoogleSignInClient

    private val firebaseAuth by lazy { Firebase.auth }


    private val googleSignInLauncher: ActivityResultLauncher<Intent> by lazy {
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult? ->
            if (result == null) {
                showError(R.string.something_went_wrong)
                return@registerForActivityResult
            }
            if (result.isDataAvailable()) {
                tryToAuthWithGoogle(result.data!!)
            }
            else {
                showError(R.string.something_went_wrong)
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureGoogleServices()
        setAllClickListeners()
    }

    private fun configureGoogleServices() {
        val gso = getGoogleSignInOptions()
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
    }

    private fun getGoogleSignInOptions() =
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestEmail()
            .build()

    private fun setAllClickListeners() {
        binding.signInWithGoogleButton.setOnClickListener {
            googleSignInLauncher.launch(null)
        }

        binding.signInWithFacebookButton.setOnClickListener {
            Toast.makeText(requireContext(), "fffff", Toast.LENGTH_LONG).show()
        }

        binding.signInWithEmailOrTelephoneButton.setOnClickListener {

        }

        binding.googleIcon.setOnClickListener {
            binding.signInWithGoogleButton.performClick()
        }

        binding.facebookIcon.setOnClickListener {
            binding.signInWithFacebookButton.performClick()
        }

        binding.emailIcon.setOnClickListener {
            binding.signInWithEmailOrTelephoneButton.performClick()
        }
    }

    private fun tryToAuthWithGoogle(intent: Intent) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(intent)
        try {
            val account = task.getResult(ApiException::class.java)
            firebaseAuthWithGoogle(account!!.idToken!!)
        }
        catch (ex: ApiException) {
            val defaultErrorMessage = getString(R.string.something_went_wrong)
            showError(ex.message ?: defaultErrorMessage)
        }
        catch (ex: NullPointerException) {
            showError(R.string.something_went_wrong)
        }
    }

    private fun firebaseAuthWithGoogle(token: String) {
        val credential = GoogleAuthProvider.getCredential(token, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(requireActivity()) { task ->
            if (task.isSuccessful) {
                userIsSignedIn(firebaseAuth.currentUser!!)
            }
            else {
                val errorMessage = task.exception?.message
                val defaultErrorMessage = getString(R.string.something_went_wrong)
                showError(errorMessage ?: defaultErrorMessage)
            }
        }
    }

    private fun userIsSignedIn(user: FirebaseUser) {
        showMessage("Everything is good!")
    }
}