package com.levit.book_me.ui.fragments.authorization.choose_type_authorization

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.levit.book_me.R
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.databinding.FragmentChooseTypeAuthorizationBinding
import com.levit.book_me.ui.activities.creating_profile.CreatingProfileActivity
import com.levit.book_me.ui.activities.onboarding.OnBoardingActivity
import com.levit.book_me.ui.activities.onboarding.OnBoardingActivity.Companion.LAUNCH_FROM_AUTHORIZATION_KEY
import com.levit.book_me.ui.base.BaseAuthorizationFragment

class ChooseTypeAuthorizationFragment: BaseAuthorizationFragment(R.layout.fragment_choose_type_authorization) {

    private val viewModel by viewModels<ChooseTypeAuthorizationViewModel> { authorizationComponent.viewModelFactory() }

    private val binding by viewBinding { FragmentChooseTypeAuthorizationBinding.bind(it) }

    private val firebaseAuth by lazy { Firebase.auth }

    private lateinit var googleSignInClient: GoogleSignInClient

    private val googleSignInLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult? ->
            viewModel.googleSignInActivityResultCallback(result)
        }


    private lateinit var facebookCallbackManager: CallbackManager

    override fun onAttach(context: Context) {
        super.onAttach(context)

        authorizationComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureGoogleServices()
        configureFacebookSdk()
        setAllClickListeners()
        setAllObservers()
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

    private fun configureFacebookSdk() {
        facebookCallbackManager = CallbackManager.Factory.create()
        configureFacebookSdkButton()
        registerFacebookButtonCallback()
    }

    private fun registerFacebookButtonCallback() {
        binding.facebookSdkButton.registerCallback(facebookCallbackManager, object: FacebookCallback<LoginResult> {

            override fun onSuccess(result: LoginResult) {
                val token = result.accessToken
                viewModel.firebaseAuthWithFacebook(token)
            }

            override fun onCancel() {
                showMessage(R.string.registration_was_canceled)
            }

            override fun onError(error: FacebookException?) {
                val defaultErrorMessage = getString(R.string.something_went_wrong)
                showError(error?.message ?: defaultErrorMessage)
            }

        })
    }

    /**
     * Deprecated but needed to correct work with Facebook sdk.
     * Can't use Activity Result API, because Facebook SDK
     * does't provide intent for launch
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        facebookCallbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun setAllClickListeners() {
        binding.signInWithGoogleButton.setOnClickListener {
            if (networkNotAvailableAndShowError()) {
                return@setOnClickListener
            }
            val googleSignIntent = googleSignInClient.signInIntent
            googleSignInLauncher.launch(googleSignIntent)
        }

        binding.signInWithFacebookButton.setOnClickListener {
            if (networkNotAvailableAndShowError()) {
                return@setOnClickListener
            }
            binding.facebookSdkButton.performClick()
        }

        binding.signInWithEmailOrTelephoneButton.setOnClickListener {
            if (networkNotAvailableAndShowError()) {
                return@setOnClickListener
            }
            navigateToPhoneEmailAuthorization()
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

        binding.backButton.setOnClickListener {
            navigateToOnBoarding()
            finishActivity()
        }
    }

    private fun setAllObservers() {
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { message ->
            if (message != null) {
                showError(message)
                viewModel.errorMessageHasShown()
            }
        })

        viewModel.credential.observe(viewLifecycleOwner, Observer { credential ->
            if (credential != null) {
                tryToSignUserWithCredential(credential)
            }
        })

        viewModel.errorMessageId.observe(viewLifecycleOwner, Observer { messageId ->
            if (messageId != null) {
                showError(messageId)
                viewModel.errorMessageHasShown()
            }
        })
    }

    private fun navigateToPhoneEmailAuthorization() {
        val action = ChooseTypeAuthorizationFragmentDirections
            .actionChooseTypeAuthorizationFragmentToEmailPhoneAuthorizationContainerFragment()
        findNavController().navigate(action)
    }

    private fun navigateToOnBoarding() {
        val intent = Intent(requireContext(), OnBoardingActivity::class.java).apply {
            putExtra(LAUNCH_FROM_AUTHORIZATION_KEY, true)
        }
        startActivity(intent)
    }

    private fun navigateToCreatingProfile() {
        val intent = Intent(requireContext(), CreatingProfileActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    private fun finishActivity() {
        requireActivity().finish()
    }

    private fun tryToSignUserWithCredential(credential: AuthCredential) {
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(requireActivity()) { task ->
            if (task.isSuccessful) {
                navigateToCreatingProfile()
            }
            else {
                val errorMessage = task.exception?.message
                val defaultErrorMessage = getString(R.string.something_went_wrong)
                showError(errorMessage ?: defaultErrorMessage)
            }
        }
    }

    private fun configureFacebookSdkButton() {
        val facebookSdkButton = binding.facebookSdkButton
        facebookSdkButton.setReadPermissions("email", "public_profile")
        facebookSdkButton.fragment = this
    }

    private fun userIsSignedIn(user: FirebaseUser) {
        showSuccessMessage("Every thing is good")
    }
}