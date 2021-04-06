package com.levit.book_me.ui.activities.creating_profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.levit.book_me.R
import com.levit.book_me.application.BookMeApplication
import com.levit.book_me.core.di.components.CreatingProfileComponent

class CreatingProfileActivity: AppCompatActivity() {

    private val appComponent by lazy {
        val application = application as BookMeApplication
        return@lazy application.appComponent
    }

    lateinit var creatingProfileComponent: CreatingProfileComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        initComponent()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_creating_profile)
    }

    private fun initComponent() {
        creatingProfileComponent = appComponent.creatingProfileComponent().build()
        creatingProfileComponent.inject(this)
    }
}