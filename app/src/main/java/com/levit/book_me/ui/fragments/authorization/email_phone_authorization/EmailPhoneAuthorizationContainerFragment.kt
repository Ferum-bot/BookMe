package com.levit.book_me.ui.fragments.authorization.email_phone_authorization

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.levit.book_me.R
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.core.ui.ParcelableClickableSpan
import com.levit.book_me.core.ui.ParcelableTextWatcher
import com.levit.book_me.databinding.FragmentEmailPhoneAuthorizationContainerBinding
import com.levit.book_me.ui.activities.creating_profile.CreatingProfileActivity
import com.levit.book_me.ui.base.BaseAuthorizationFragment
import com.levit.book_me.ui.fragments.authorization.email_phone_authorization.EmailPhoneViewPagerAdapter.Companion.FIRST_POSITION
import com.levit.book_me.ui.fragments.authorization.email_phone_authorization.EmailPhoneViewPagerAdapter.Companion.SECOND_POSITION
import java.util.concurrent.TimeUnit

class EmailPhoneAuthorizationContainerFragment: BaseAuthorizationFragment(R.layout.fragment_email_phone_authorization_container) {

    companion object {
        private const val PHONE_REQUEST_TIMEOUT = 60L
    }

    enum class AuthorizationType(val position: Int) {
        PHONE(FIRST_POSITION), EMAIL(SECOND_POSITION)
    }

    private val viewModel by viewModels<EmailPhoneAuthorizationViewModel> { authorizationComponent.viewModelFactory() }

    private val binding by viewBinding {
        FragmentEmailPhoneAuthorizationContainerBinding.bind(it)
    }

    private val viewPagerAdapter by lazy {
        EmailPhoneViewPagerAdapter(
            this,
            emailTextChangeListener,
            phoneTextChangeListener,
            passwordTextChangeListener,
            emailSignUpClickableSpan,
        )
    }

    private val emailTextChangeListener by lazy { ParcelableTextWatcher().apply {
        onTextChangeListener = viewModel::onEmailTextChanged
    } }

    private val passwordTextChangeListener by lazy { ParcelableTextWatcher().apply {
        onTextChangeListener = viewModel::onPasswordTextChanged
    } }

    private val phoneTextChangeListener by lazy {ParcelableTextWatcher().apply {
        onTextChangeListener = viewModel::onPhoneTextChanged
    }}

    private val emailSignUpClickableSpan by lazy { ParcelableClickableSpan().apply {
        onTextClicked = this@EmailPhoneAuthorizationContainerFragment::navigateToEmailSignUpScreen
    } }

    private var currentAuthorizationType = AuthorizationType.PHONE

    private val firebaseAuth: FirebaseAuth = Firebase.auth

    private val phoneAuthOptions: PhoneAuthOptions
    get() {
        val phoneNumber: String = viewModel.phoneNumber!!.toStringWithOutSeparator()
        return PhoneAuthOptions.newBuilder(firebaseAuth)
            .setPhoneNumber(phoneNumber)
            .setTimeout(PHONE_REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .setActivity(requireActivity())
            .setCallbacks(phoneAuthCallback)
            .build()
    }

    private val phoneAuthCallback = object: PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onCodeSent(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
            super.onCodeSent(verificationId, token)

            showProgressBar()
            navigateToConfirmPhoneCodeFragment(verificationId, token)
        }

        override fun onVerificationCompleted(credentional: PhoneAuthCredential) {
            showProgressBar()
            signInWithCredentional(credentional)
        }

        override fun onVerificationFailed(ex: FirebaseException) {
            showNextButton()
            handlePhoneAuthorizationError(ex)
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureSoftKeyBoard()
        configureTabLayoutWithViewPager()
        setAllClickListeners()
        setAllObservers()
        addPhoneNumberObserver()
    }

    private fun configureSoftKeyBoard() {
        val window = requireActivity().window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            binding.root.setOnApplyWindowInsetsListener { view: View, windowInsets: WindowInsets ->
                val ime = windowInsets.getInsets(WindowInsets.Type.ime())
                windowInsets.inset(ime)
                return@setOnApplyWindowInsetsListener windowInsets
            }
        }
        else {
            val mode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE
            window.setSoftInputMode(mode)
        }
    }

    private fun configureTabLayoutWithViewPager() {
        configureViewPager()
        configureTabLayout()
    }

    private fun setAllClickListeners() {
        binding.nextButton.setOnClickListener {
            if (networkNotAvailableAndShowError()) {
                return@setOnClickListener
            }

            hideKeyboard()
            showProgressBar()

            if (currentAuthorizationType == AuthorizationType.PHONE) {
                sendCodeToPhoneNumber()
            }
            else {
                handleEmailSignIn()
            }
        }

        binding.backButton.setOnClickListener {
            popBackStack()
        }
    }

    private fun setAllObservers() {
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { message ->
            if (message != null) {
                showError(message)
                viewModel.errorMessageHasShown()
            }
        })
    }

    private fun configureTabLayout() {
        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager

        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {

            override fun onTabReselected(tab: TabLayout.Tab) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
                currentAuthorizationType = if (tab.position == FIRST_POSITION) {
                    removeEmailAddressObserver()
                    addPhoneNumberObserver()
                    AuthorizationType.PHONE
                } else {
                    removePhoneNumberObserver()
                    addEmailAddressObserver()
                    AuthorizationType.EMAIL
                }
            }

        })
    }

    private fun sendCodeToPhoneNumber() {
        PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions)
    }

    private fun handleEmailSignIn() {
        if (viewModel.emailPassword == null) {
            showNextButton()
            showError(R.string.enter_password)
            return
        }
        signInWithEmail()
    }

    private fun addPhoneNumberObserver() {
        viewModel.isPhoneValid.observe(viewLifecycleOwner, Observer { isPhoneValid ->
            binding.nextButton.isEnabled = isPhoneValid
        })
    }

    private fun removePhoneNumberObserver() {
        binding.nextButton.isEnabled = false
        viewModel.isPhoneValid.removeObservers(viewLifecycleOwner)
    }

    private fun addEmailAddressObserver() {
        viewModel.isEmailValid.observe(viewLifecycleOwner, Observer { isEmailValid ->
            binding.nextButton.isEnabled = isEmailValid
        })
    }

    private fun removeEmailAddressObserver() {
        binding.nextButton.isEnabled = false
        viewModel.isEmailValid.removeObservers(viewLifecycleOwner)
    }

    private fun configureViewPager() {
        val viewPager = binding.viewPager
        viewPager.adapter = viewPagerAdapter
        viewPager.isUserInputEnabled = false
    }

    private fun popBackStack() {
        findNavController().popBackStack()
    }

    private fun hideKeyboard() {
        val currentEditText = getCurrentEditText()
        val windowToken = currentEditText.windowToken
        val inputService = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputService.hideSoftInputFromWindow(windowToken, 0)
    }

    private fun getCurrentEditText(): TextInputEditText =
        if (currentAuthorizationType == AuthorizationType.PHONE) {
            requireActivity().findViewById<TextInputEditText>(R.id.phone_edit_text)
        }
        else {
            requireActivity().findViewById(R.id.email_edit_text)
        }

    private fun showProgressBar() {
        binding.nextButton.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun showNextButton() {
        binding.nextButton.visibility = View.VISIBLE
        binding.progressBar.visibility = View.GONE
    }

    private fun navigateToConfirmPhoneCodeFragment(verificationId: String, token: PhoneAuthProvider.ForceResendingToken) {
        val telephoneNumber = viewModel.phoneNumber!!
        val action = EmailPhoneAuthorizationContainerFragmentDirections
            .actionEmailPhoneAuthorizationContainerFragmentToConfirmPhoneCodeFragment(
                telephoneNumber,
                verificationId,
                token
            )
        findNavController().navigate(action)
    }


    private fun handlePhoneAuthorizationError(exception: FirebaseException?) =
        when(exception) {
            is FirebaseAuthInvalidCredentialsException -> {
                showError(R.string.invalid_request)
            }
            is FirebaseTooManyRequestsException -> {
                showError(R.string.too_many_request)
            }
            else -> {
                val defaultErrorMessage = getString(R.string.something_went_wrong)
                showError(exception?.message ?: defaultErrorMessage)
            }
        }

    private fun signInWithCredentional(credentional: PhoneAuthCredential) {
        firebaseAuth.signInWithCredential(credentional)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    navigateToCreatingProfileScreen()
                }
                else {
                    showNextButton()
                    val exception = task.exception as FirebaseException
                    handlePhoneAuthorizationError(exception)
                }
            }
    }

    private fun signInWithEmail() {
        val email = viewModel.emailAddress
        val password = viewModel.emailPassword
        if (email == null || password == null) {
            handlePhoneAuthorizationError(null)
            return
        }

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(requireActivity()) { task ->
            if (task.isSuccessful) {
                navigateToCreatingProfileScreen()
            }
            else {
                showError(R.string.wrong_email_or_password)
                showNextButton()
            }
        }
    }

    private fun navigateToProfileScreen() {
        showMessage("Everything is good!")
    }

    private fun navigateToCreatingProfileScreen() {
        val intent = Intent(requireContext(), CreatingProfileActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }
    private fun navigateToEmailSignUpScreen(widget: View) {
        val action = EmailPhoneAuthorizationContainerFragmentDirections
            .actionEmailPhoneAuthorizationContainerFragmentToEmailSignUpFragment()
        findNavController().navigate(action)
    }
}