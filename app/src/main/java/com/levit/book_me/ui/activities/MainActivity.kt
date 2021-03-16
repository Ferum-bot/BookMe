package com.levit.book_me.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.levit.book_me.R
import com.levit.book_me.ui.fragments.authorization.ChooseTypeAuthorizationFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, ChooseTypeAuthorizationFragment())
            .commitAllowingStateLoss()
    }
}
