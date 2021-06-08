package com.levit.book_me.ui.fragments.authorization.email_sign_up

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.levit.book_me.R
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.core.ui.ParcelableTextWatcher
import com.levit.book_me.databinding.FragmentEmailSignUpBinding
import com.levit.book_me.ui.activities.creating_profile.CreatingProfileActivity
import com.levit.book_me.ui.base.BaseAuthorizationFragment

class EmailSignUpFragment: BaseAuthorizationFragment(R.layout.fragment_email_sign_up) {

    private val binding by viewBinding { FragmentEmailSignUpBinding.bind(it) }

    private val viewModel by viewModels<EmailSignUpViewModel> { authorizationComponent.viewModelFactory() }

    private val firebaseAuth: FirebaseAuth
    get() = Firebase.auth

    private val emailTextWatcher by lazy { ParcelableTextWatcher().apply {
        onTextChangeListener = viewModel::onEmailChanged
    } }

    private val passwordTextWatcher by lazy { ParcelableTextWatcher().apply {
        onTextChangeListener = viewModel::onPasswordChanged
    } }

    private val repeatPasswordTextWatcher by lazy { ParcelableTextWatcher().apply {
        onTextChangeListener = viewModel::onRepeatPasswordChanged
    } }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureSoftKeyBoard()
        setAllClickListeners()
        setAllTextListeners()
        setAllObservers()
    }

    private fun configureSoftKeyBoard() {
        val window = requireActivity().window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(true)
            binding.root.setOnApplyWindowInsetsListener(null)
        }
        else {
            val mode = WindowManager.LayoutParams.SOFT_INPUT_ADJUST_NOTHING
            window.setSoftInputMode(mode)
        }
    }

    private fun setAllClickListeners() {
        binding.backButton.setOnClickListener {
            popBackStack()
        }

        binding.signUpButton.setOnClickListener {
            if (allInputDataIsValid()) {
                showLoading()
                tryToSignUp()
            }
        }
    }

    private fun setAllTextListeners() {
        binding.emailEditText.addTextChangedListener(emailTextWatcher)
        binding.passwordEditText.addTextChangedListener(passwordTextWatcher)
        binding.repeatPasswordEditText.addTextChangedListener(repeatPasswordTextWatcher)
    }

    private fun setAllObservers() {
        viewModel.isEmailValid.observe(viewLifecycleOwner, Observer { emailValid ->
            if (emailValid) {
                binding.wrongEmailLabel.visibility = View.GONE
            }
        })

        viewModel.isPasswordValid.observe(viewLifecycleOwner, Observer { passwordValid ->
            if (passwordValid) {
                binding.wrongPasswordLabel.visibility = View.GONE
            }
        })

        viewModel.isPasswordsMatch.observe(viewLifecycleOwner, Observer { passwordsMatch ->
            if (passwordsMatch) {
                binding.passwordsNotMatchesLabel.visibility = View.GONE
            }
        })
    }

    private fun allInputDataIsValid(): Boolean {
        if (viewModel.email == null || viewModel.email!!.isBlank()) {
            binding.wrongEmailLabel.visibility = View.VISIBLE
            return false
        }
        if (viewModel.password == null || viewModel.password!!.isBlank()) {
            binding.wrongPasswordLabel.visibility = View.VISIBLE
            return false
        }
        if (viewModel.passwordMismatch()) {
            binding.passwordsNotMatchesLabel.visibility = View.VISIBLE
            return false
        }
        return true
    }

    private fun tryToSignUp() {
        val email = viewModel.email!!
        val password = viewModel.password!!

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(requireActivity()) { task ->
            if (task.isSuccessful) {
                navigateToCreatingProfileScreen()
            }
            else {
                showSignUpButton()
                showError(R.string.something_went_wrong)
            }
        }
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.signUpButton.visibility = View.GONE
    }

    private fun showSignUpButton() {
        binding.progressBar.visibility = View.GONE
        binding.signUpButton.visibility = View.VISIBLE
    }

    private fun navigateToProfileScreen() {
        showMessage("Everything is good")
    }

    private fun navigateToCreatingProfileScreen() {
        val intent = Intent(requireContext(), CreatingProfileActivity::class.java)
        startActivity(intent)
        requireActivity().finish()
    }

    private fun popBackStack() {
        findNavController().popBackStack()
    }
}