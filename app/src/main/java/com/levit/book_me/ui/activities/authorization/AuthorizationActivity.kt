package com.levit.book_me.ui.activities.authorization

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.levit.book_me.R
import com.levit.book_me.application.BookMeApplication
import com.levit.book_me.di.components.AuthorizationComponent
import com.levit.book_me.ui.base.BaseActivity

class AuthorizationActivity : BaseActivity() {

    lateinit var authorizationComponent: AuthorizationComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        initComponent()
        setTheme(R.style.DefaultAppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)
    }


    private fun initComponent() {
        authorizationComponent = appComponent.authorizationComponent().build()
        authorizationComponent.inject(this)
    }
}
