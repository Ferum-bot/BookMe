package com.levit.book_me.ui.fragments.authorization.email_phone_authorization

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.levit.book_me.R
import com.levit.book_me.application.BookMeApplication
import com.levit.book_me.core.di.components.AppComponent
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.core_presentation.base.BaseFragment
import com.levit.book_me.databinding.FragmentEmailPhoneAuthorizationContainerBinding
import com.levit.book_me.ui.fragments.authorization.email_phone_authorization.EmailPhoneViewPagerAdapter.Companion.FIRST_POSITION
import com.levit.book_me.ui.fragments.authorization.email_phone_authorization.EmailPhoneViewPagerAdapter.Companion.SECOND_POSITION

class EmailPhoneAuthorizationContainerFragment: BaseFragment(R.layout.fragment_email_phone_authorization_container) {

    private val viewModel by viewModels<EmailPhoneAuthorizationViewModel> {
        appComponent.viewModelFactory()
    }

    private val binding by viewBinding {
        FragmentEmailPhoneAuthorizationContainerBinding.bind(it)
    }

    private val viewPagerAdapter by lazy {
        EmailPhoneViewPagerAdapter(this)
    }

    private val appComponent: AppComponent
    get() {
        val application = requireActivity().application as BookMeApplication
        return application.appComponent
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureTabLayoutWithViewPager()
        setAllClickListeners()
        setAllOnservers()
    }

    private fun configureTabLayoutWithViewPager() {
        configureViewPager()

        val tabLayout = binding.tabLayout
        val viewPager = binding.viewPager

        TabLayoutMediator(tabLayout, viewPager) {tab, position ->
            when(position) {
                FIRST_POSITION -> {
                    tab.text = getString(R.string.phone)
                }
                SECOND_POSITION -> {
                    tab.text = getString(R.string.email)
                }
            }
        }.attach()
    }

    private fun setAllClickListeners() {
        binding.nextButton.setOnClickListener {

        }
    }

    private fun setAllOnservers() {

    }

    private fun configureViewPager() {
        val viewPager = binding.viewPager
        viewPager.adapter = viewPagerAdapter
        viewPager.isUserInputEnabled = false
    }
}