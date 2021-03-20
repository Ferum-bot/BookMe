package com.levit.book_me.ui.fragments.authorization.email_phone_authorization

import android.os.Bundle
import android.os.Parcelable
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.core.view.get
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayout
import com.google.android.material.textfield.TextInputEditText
import com.levit.book_me.R
import com.levit.book_me.application.BookMeApplication
import com.levit.book_me.core.di.components.AppComponent
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.core.utill.ParcelableTextWatcher
import com.levit.book_me.core_presentation.base.BaseFragment
import com.levit.book_me.databinding.FragmentEmailPhoneAuthorizationContainerBinding
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

class EmailPhoneAuthorizationContainerFragment: BaseFragment(R.layout.fragment_email_phone_authorization_container) {

    private val viewModel by viewModels<EmailPhoneAuthorizationViewModel> {
        appComponent.viewModelFactory()
    }

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

    private val emailTextChangeListener = ParcelableTextWatcher().apply {
        onTextChangeListener = viewModel::onEmailTextChanged
    }

    private val phoneTextChangeListener = ParcelableTextWatcher().apply {
        onTextChangeListener = viewModel::onPhoneTextChanged
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configureTabLayoutWithViewPager()
        setAllClickListeners()
        setAllObservers()
    }

    private fun configureTabLayoutWithViewPager() {
        configureViewPager()
        configureTabLayout()
    }

    private fun setAllClickListeners() {
        binding.nextButton.setOnClickListener {

        }
    }

    private fun setAllObservers() {

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
            }

        })
    }

    private fun configureViewPager() {
        val viewPager = binding.viewPager
        viewPager.adapter = viewPagerAdapter
        viewPager.isUserInputEnabled = false
    }
}