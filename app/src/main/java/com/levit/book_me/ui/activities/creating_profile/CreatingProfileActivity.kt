package com.levit.book_me.ui.activities.creating_profile

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import com.levit.book_me.R
import com.levit.book_me.databinding.ActivityCreatingProfileBinding
import com.levit.book_me.di.components.CreatingProfileComponent
import com.levit.book_me.ui.base.BaseActivity
import com.levit.bookme.uikit.ui.PageIndicator

class CreatingProfileActivity: BaseActivity(), TitleViewController {

    private lateinit var binding: ActivityCreatingProfileBinding

    private val viewModel: CreatingProfileActivityViewModel by viewModels {
        creatingProfileComponent.viewModelFactory()
    }

    lateinit var creatingProfileComponent: CreatingProfileComponent

    val pageIndicatorController = object: PageIndicator.OnActivePrefixNumberChangeListener {

        override fun activePrefixChanged(activePrefixNumber: Int) {
            val pageIndicator: PageIndicator = findViewById(R.id.page_indicator)
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