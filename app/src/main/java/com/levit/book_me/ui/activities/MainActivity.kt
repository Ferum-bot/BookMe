package com.levit.book_me.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.levit.book_me.R
import com.levit.book_me.ui.fragments.authorization.choose_type_authorization.ChooseTypeAuthorizationFragment
import com.levit.book_me.ui.fragments.authorization.email_phone_authorization.EmailPhoneAuthorizationContainerFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container,
                EmailPhoneAuthorizationContainerFragment()
            )
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }
}
