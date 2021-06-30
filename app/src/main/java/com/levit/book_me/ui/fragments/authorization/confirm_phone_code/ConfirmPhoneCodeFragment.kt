package com.levit.book_me.ui.fragments.authorization.confirm_phone_code

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.levit.book_me.R
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.core.models.MobileTelephone
import com.levit.book_me.core.utill.TelephoneNumberFormatter
import com.levit.book_me.databinding.FragmentConfirmPhoneCodeBinding
import com.levit.book_me.ui.activities.creating_profile.CreatingProfileActivity
import com.levit.book_me.ui.base.BaseAuthorizationFragment
import com.levit.bookme.uikit.utills.ParcelableTextWatcher
import java.lang.Exception

class ConfirmPhoneCodeFragment: BaseAuthorizationFragment(R.layout.fragment_confirm_phone_code) {

    private val binding by viewBinding { FragmentConfirmPhoneCodeBinding.bind(it) }

    private val viewModel by viewModels<ConfirmPhoneCodeViewModel> { authorizationComponent.viewModelFactory() }

    private val firebaseAuth: FirebaseAuth
    get() = Firebase.auth

    private val args by navArgs<ConfirmPhoneCodeFragmentArgs>()

    private val verificationId: String by lazy { args.verificationId }

    private val phoneNumber: MobileTelephone by lazy { args.phoneNumber }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        authorizationComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAllReceivedData()
        setAllClickListeners()
        setAllObservers()
        setPinViewCodeChangeListener()
    }

    private fun setAllReceivedData() {
        val formattedNumber = TelephoneNumberFormatter
            .formatNumberWithBrackets(phoneNumber)

        binding.authorizationDescription.text =
            getString(R.string.your_code_was_send_to, formattedNumber)
    }

    private fun setAllClickListeners() {
        binding.backButton.setOnClickListener {
            popBackStack()
        }
    }

    private fun setAllObservers() {
        viewModel.sentCode.observe(viewLifecycleOwner, Observer { sentCode ->
            if (sentCode != null) {
                showLoading(true)
                hideKeyboard()
                tryToSignInWithCode(sentCode)
            }
        })
    }

    private fun setPinViewCodeChangeListener() {
        binding.pinView.addTextChangedListener(ParcelableTextWatcher().apply {
            onTextChangeListener = viewModel::onCodeChangeListener
        })
    }

    private fun tryToSignInWithCode(sentCode: String) {
        val credentional = PhoneAuthProvider.getCredential(verificationId, sentCode)
        tryToSignInWithPhoneAuthCredentional(credentional)
    }

    private fun tryToSignInWithPhoneAuthCredentional(credentional: PhoneAuthCredential) {
        firebaseAuth.signInWithCredential(credentional)
            .addOnCompleteListener(requireActivity(), this::phoneOnCompleteListener)
    }

    private fun phoneOnCompleteListener(task: Task<AuthResult>) {
        if (task.isSuccessful) {
            navigateToProfileScreen()
        }
        else {
            showLoading(false)
            val exception = task.exception
            handlePhoneAuthException(exception)
        }
    }

    private fun handlePhoneAuthException(exception: Exception?) {
        if (exception == null) {
            showError(R.string.something_went_wrong)
            return
        }
        if (exception is FirebaseAuthInvalidCredentialsException) {
            showError(R.string.entered_code_was_invalid)
        }
        else {
            val errorMessage = exception.message
            val defaultErrorMessage = getString(R.string.something_went_wrong)
            showError(errorMessage ?: defaultErrorMessage)
        }
    }

    private fun showLoading(show: Boolean) {
        binding.progressBar.isVisible = show
    }

    private fun popBackStack() {
        findNavController().popBackStack()
    }

    private fun hideKeyboard() {
        val windowToken = binding.pinView.windowToken
        val inputService = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputService.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun navigateToProfileScreen() {
        showLoading(false)
        showSuccessMessage("Every Thing is good")
        navigateToCreatingProfileScreen()
    }

    private fun navigateToCreatingProfileScreen() {
        val intent = Intent(requireActivity(), CreatingProfileActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }
}