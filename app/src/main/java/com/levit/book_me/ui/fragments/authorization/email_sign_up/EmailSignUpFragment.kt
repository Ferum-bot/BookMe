package com.levit.book_me.ui.fragments.authorization.email_sign_up

import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.levit.book_me.R
import com.levit.book_me.application.BookMeApplication
import com.levit.book_me.core.di.components.AppComponent
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.core_presentation.base.BaseFragment
import com.levit.book_me.databinding.FragmentEmailSignUpBinding

class EmailSignUpFragment: BaseFragment(R.layout.fragment_email_sign_up) {

    private val binding by viewBinding { FragmentEmailSignUpBinding.bind(it) }

    private val viewModel by viewModels<EmailSignUpViewModel> { appComponent.viewModelFactory() }

    private val appComponent: AppComponent
    get() {
        val application = requireActivity().application as BookMeApplication
        return application.appComponent
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureSoftKeyBoard()
        setAllClickListeners()
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
    }

    private fun popBackStack() {
        findNavController().popBackStack()
    }
}