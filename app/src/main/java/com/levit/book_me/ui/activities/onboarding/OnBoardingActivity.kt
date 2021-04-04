package com.levit.book_me.ui.activities.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.levit.book_me.R
import com.levit.book_me.databinding.ActivitySplashOnboardingBinding
import com.levit.book_me.application.BookMeApplication
import com.levit.book_me.core.di.components.OnBoardingComponent
import com.levit.book_me.ui.activities.authorization.AuthorizationActivity

class OnBoardingActivity: AppCompatActivity() {

    private val viewModel by viewModels<OnBoardingViewModel> { onBoardingComponent.viewModelFactory() }

    private lateinit var binding: ActivitySplashOnboardingBinding

    private val appComponent by lazy {
        val application = application as BookMeApplication
        return@lazy application.appComponent
    }

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
        if (isUserSignIn()) {
            navigateToMainScreen()
        }
        else {
            navigateToAuthorization()
        }
        finish()
    }

    private fun isUserSignIn(): Boolean =
        Firebase.auth.currentUser != null

    private fun navigateToAuthorization() {
        val intent = Intent(this, AuthorizationActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToMainScreen() {
        navigateToAuthorization()
    }

    companion object {
        private const val FIRST = 0
        private const val SECOND = 1
        private const val THIRD = 2

        const val LAUNCH_FROM_AUTHORIZATION_KEY = "launch_from_authorization"
    }
}