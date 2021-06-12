package com.levit.book_me.ui.activities.creating_profile

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.levit.book_me.R
import com.levit.book_me.core.ui.custom_view.CreatingProfilePageIndicator
import com.levit.book_me.databinding.ActivityCreatingProfileBinding
import com.levit.book_me.di.components.CreatingProfileComponent
import com.levit.book_me.ui.activities.main_screen.MainScreenActivity
import com.levit.book_me.ui.base.BaseActivity

class CreatingProfileActivity: BaseActivity(), TitleViewController {

    private lateinit var binding: ActivityCreatingProfileBinding

    private val viewModel: CreatingProfileActivityViewModel by viewModels {
        creatingProfileComponent.viewModelFactory()
    }

    lateinit var creatingProfileComponent: CreatingProfileComponent

    val pageIndicatorController = object: CreatingProfilePageIndicator.OnActivePrefixNumberChangeListener {

        override fun activePrefixChanged(activePrefixNumber: Int) {
            val pageIndicator: CreatingProfilePageIndicator = findViewById(R.id.page_indicator)
            pageIndicator.setActivePrefixNumber(activePrefixNumber)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        initComponent()

        super.onCreate(savedInstanceState)

        binding = ActivityCreatingProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun showTitle(show: Boolean) {
        binding.title.isVisible = show
        binding.pageIndicator.isVisible = show
    }

    private fun initComponent() {
        creatingProfileComponent = appComponent.creatingProfileComponent().build()
        creatingProfileComponent.inject(this)
    }
}