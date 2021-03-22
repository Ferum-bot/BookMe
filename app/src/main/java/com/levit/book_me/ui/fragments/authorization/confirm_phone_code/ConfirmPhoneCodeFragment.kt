package com.levit.book_me.ui.fragments.authorization.confirm_phone_code

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.levit.book_me.R
import com.levit.book_me.application.BookMeApplication
import com.levit.book_me.core.di.components.AppComponent
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.core_presentation.base.BaseFragment
import com.levit.book_me.databinding.FragmentConfirmPhoneCodeBinding

class ConfirmPhoneCodeFragment: BaseFragment(R.layout.fragment_confirm_phone_code) {

    private val binding by viewBinding { FragmentConfirmPhoneCodeBinding.bind(it) }

    private val viewModel by viewModels<ConfirmPhoneCodeViewModel> { appComponent.viewModelFactory() }

    private val appComponent: AppComponent
    get() {
        val application = requireActivity().application as BookMeApplication
        return application.appComponent
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAllClickListeners()
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