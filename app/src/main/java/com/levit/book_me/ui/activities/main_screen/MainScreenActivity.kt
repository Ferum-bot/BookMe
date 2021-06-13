package com.levit.book_me.ui.activities.main_screen

import android.graphics.Color
import android.os.Bundle
import androidx.activity.viewModels
import com.levit.book_me.databinding.ActivityMainScreenBinding
import com.levit.book_me.di.components.MainScreenComponent
import com.levit.book_me.ui.base.BaseActivity

class MainScreenActivity: BaseActivity() {

    private lateinit var binding: ActivityMainScreenBinding

    private val viewModel: MainScreenActivityViewModel by viewModels {
        mainScreenComponent.viewModelFactory()
    }

    lateinit var mainScreenComponent: MainScreenComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        initComponent()

        super.onCreate(savedInstanceState)

        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        configureLayout()
    }

    private fun initComponent() {
        mainScreenComponent = appComponent.mainScreenComponent().build()
        mainScreenComponent.inject(this)
    }

    private fun configureLayout() {

    }
}