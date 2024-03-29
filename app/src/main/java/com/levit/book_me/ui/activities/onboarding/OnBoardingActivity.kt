package com.levit.book_me.ui.activities.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.levit.book_me.R
import com.levit.book_me.databinding.ActivitySplashOnboardingBinding
import com.levit.book_me.core.enums.profile.CurrentUserStatus
import com.levit.book_me.di.components.OnBoardingComponent
import com.levit.book_me.ui.activities.authorization.AuthorizationActivity
import com.levit.book_me.ui.activities.creating_profile.CreatingProfileActivity
import com.levit.book_me.ui.activities.main_screen.MainScreenActivity
import com.levit.book_me.ui.base.BaseActivity

class OnBoardingActivity: BaseActivity() {

    companion object {
        private const val FIRST = 0
        private const val SECOND = 1
        private const val THIRD = 2

        const val LAUNCH_FROM_AUTHORIZATION_KEY = "launch_from_authorization"
    }

    private val viewModel by viewModels<OnBoardingViewModel> { onBoardingComponent.viewModelFactory() }

    private lateinit var binding: ActivitySplashOnboardingBinding

    private lateinit var onBoardingComponent: OnBoardingComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        initComponent()
        setTheme(R.style.DefaultAppTheme)

        super.onCreate(savedInstanceState)

        binding = ActivitySplashOnboardingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAllClickListeners()
    }

    override fun onStart() {
        super.onStart()

        if (isFirstLaunch()) {
            showOnBoarding()
        }
        else {
            handleAuthorization()
        }
    }

    private fun initComponent() {
        onBoardingComponent = appComponent.onBoardingComponent().build()
        onBoardingComponent.inject(this)
    }

    private fun setAllClickListeners() {
        binding.nextButton.setOnClickListener {
            val viewPager = binding.viewPager
            when(viewPager.currentItem) {
                FIRST -> {
                    viewPager.currentItem = SECOND
                }
                SECOND -> {
                    viewPager.currentItem = THIRD
                }
                THIRD -> {
                    navigateToAuthorization()
                    finish()
                }
            }
        }
    }

    private fun isFirstLaunch(): Boolean {
        if (intent.getBooleanExtra(LAUNCH_FROM_AUTHORIZATION_KEY, false)) {
            return true
        }
        return viewModel.isFirstLaunch()
    }

    private fun showOnBoarding() {
        configureViewPager()
        configurePageIndicator()
    }

    private fun configureViewPager() {
        val viewPager = binding.viewPager
        val adapter = OnBoardingViewPagerAdapter()
        viewPager.adapter = adapter
    }

    private fun configurePageIndicator() {
        val viewPager = binding.viewPager
        val pageIndicator = binding.pageIndicator
        pageIndicator.count = 3
        pageIndicator.setSelected(0)

        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                pageIndicator.setSelected(position)
            }
        })
    }

    private fun handleAuthorization() {
        val currentUserStatus = viewModel.getCurrentUserStatus()
        when(currentUserStatus) {
            CurrentUserStatus.NOT_AUTHORIZED ->
                navigateToAuthorization()
            CurrentUserStatus.AUTHORIZED_BUT_PROFILE_NOT_CREATED ->
                navigateToCreatingProfile()
            CurrentUserStatus.AUTHORIZED_PROFILE_CREATED ->
                navigateToMainScreen()
        }
        finish()
    }

    private fun navigateToAuthorization() {
        val intent = Intent(this, AuthorizationActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToCreatingProfile() {
        val intent = Intent(this, CreatingProfileActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToMainScreen() {
        val intent = Intent(this, MainScreenActivity::class.java)
        startActivity(intent)
    }
}