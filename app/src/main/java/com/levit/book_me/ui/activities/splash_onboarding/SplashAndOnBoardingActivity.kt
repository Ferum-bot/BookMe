package com.levit.book_me.ui.activities.splash_onboarding

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.levit.book_me.R
import com.levit.book_me.databinding.ActivitySplashOnboardingBinding
import com.levit.book_me.application.BookMeApplication
import com.levit.book_me.ui.activities.authorization.AuthorizationActivity

class SplashAndOnBoardingActivity: AppCompatActivity() {

    private val viewModel by viewModels<SplashAndOnBoardingViewModel> { appComponent.viewModelFactory() }

    private lateinit var binding: ActivitySplashOnboardingBinding

    private val appComponent by lazy {
        val application = application as BookMeApplication
        return@lazy application.appComponent
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.DefaultAppTheme)
        super.onCreate(savedInstanceState)
        binding = ActivitySplashOnboardingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onStart() {
        super.onStart()

        if (viewModel.isFirstLaunch()) {
            showOnBoarding()
        }
        else {
            handleAuthorization()
        }
    }

    private fun showOnBoarding() {
        binding.bookMeIcon.visibility = View.GONE
        binding.nextButton.visibility = View.VISIBLE
    }

    private fun handleAuthorization() {
        if (isUserSignIn()) {
            navigateToMainScreen()
        }
        else {
            navigateToAuthorization()
            finish()
        }
    }

    private fun isUserSignIn(): Boolean =
        Firebase.auth.currentUser != null

    private fun navigateToAuthorization() {
        val intent = Intent(this, AuthorizationActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToMainScreen() {
        navigateToAuthorization()
        finish()
    }
}