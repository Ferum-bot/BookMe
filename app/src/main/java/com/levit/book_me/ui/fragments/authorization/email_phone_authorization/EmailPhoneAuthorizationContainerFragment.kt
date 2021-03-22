package com.levit.book_me.ui.fragments.authorization.email_phone_authorization

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayout
import com.levit.book_me.R
import com.levit.book_me.application.BookMeApplication
import com.levit.book_me.core.di.components.AppComponent
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.core.ui.ParcelableTextWatcher
import com.levit.book_me.core_presentation.base.BaseFragment
import com.levit.book_me.databinding.FragmentEmailPhoneAuthorizationContainerBinding
import com.levit.book_me.ui.fragments.authorization.email_phone_authorization.EmailPhoneViewPagerAdapter.Companion.FIRST_POSITION
import com.levit.book_me.ui.fragments.authorization.email_phone_authorization.EmailPhoneViewPagerAdapter.Companion.SECOND_POSITION

class EmailPhoneAuthorizationContainerFragment: BaseFragment(R.layout.fragment_email_phone_authorization_container) {

    private val viewModel by viewModels<EmailPhoneAuthorizationViewModel> { appComponent.viewModelFactory() }

    private val binding by viewBinding {
        FragmentEmailPhoneAuthorizationContainerBinding.bind(it)
    }

    private val viewPagerAdapter by lazy {
        EmailPhoneViewPagerAdapter(this, emailTextChangeListener, phoneTextChangeListener)
    }

    private val appComponent: AppComponent
    get() {
        val application = requireActivity().application as BookMeApplication
        return application.appComponent
    }

    private val emailTextChangeListener by lazy { ParcelableTextWatcher().apply {
        onTextChangeListener = viewModel::onEmailTextChanged
    } }

    private val phoneTextChangeListener by lazy {ParcelableTextWatcher().apply {
        onTextChangeListener = viewModel::onPhoneTextChanged
    }}

    private var currentAuthorizationType = AuthorizationType.PHONE

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureTabLayoutWithViewPager()
        setAllClickListeners()
        setAllObservers()
        addPhoneNumberObserver()
    }

    private fun configureTabLayoutWithViewPager() {
        configureViewPager()
        configureTabLayout()
    }

    private fun setAllClickListeners() {
        binding.nextButton.setOnClickListener {

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

    enum class AuthorizationType(val position: Int) {
        PHONE(FIRST_POSITION), EMAIL(SECOND_POSITION)
    }
}