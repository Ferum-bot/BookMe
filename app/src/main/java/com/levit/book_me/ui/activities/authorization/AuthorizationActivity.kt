package com.levit.book_me.ui.activities.authorization

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.levit.book_me.R

class AuthorizationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.DefaultAppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)
    }

}
