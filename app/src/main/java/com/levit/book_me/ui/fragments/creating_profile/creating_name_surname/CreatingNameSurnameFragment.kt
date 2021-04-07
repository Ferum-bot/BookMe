package com.levit.book_me.ui.fragments.creating_profile.creating_name_surname

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.levit.book_me.R
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.core.ui.ParcelableTextWatcher
import com.levit.book_me.databinding.FragmentCreatingNameSurnameBinding
import com.levit.book_me.ui.activities.creating_profile.CreatingProfileActivity
import com.levit.book_me.ui.base.BaseCreatingProfileFragment

class CreatingNameSurnameFragment: BaseCreatingProfileFragment(R.layout.fragment_creating_name_surname) {

    companion object {
        private const val FRAGMENT_POSITION = 1
    }

    private val binding by viewBinding { FragmentCreatingNameSurnameBinding.bind(it) }

    private val viewModel by viewModels<CreatingNameSurnameViewModel> { creatingProfileComponent.viewModelFactory() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAllClickListeners()
        setTextWatchers()
        setAllObservers()
        updatePageIndicator()
    }

    private fun setAllClickListeners() {
        binding.nextButton.setOnClickListener {
            navigateToChooseProfilePhotoFragment()
        }
    }

    private fun setTextWatchers() {
        binding.nameTextInput.addTextChangedListener(ParcelableTextWatcher().apply {
            onTextChangeListener = viewModel::onNameChanged
        })

        binding.surnameTextInput.addTextChangedListener(ParcelableTextWatcher().apply {
            onTextChangeListener = viewModel::onSurnameChanged
        })
    }

    private fun setAllObservers() {
        viewModel.isNameCorrect.observe(viewLifecycleOwner, Observer { nameIsCorrect ->
            if (nameIsCorrect) {
                binding.invalidNameLabel.visibility = View.GONE
            }
        })

        viewModel.isSurnameCorrect.observe(viewLifecycleOwner, Observer { surnameIsCorrect ->
            if (surnameIsCorrect) {
                binding.invalidSurnameLabel.visibility = View.GONE
            }
        })
    }

    private fun updatePageIndicator() {
        val activity = requireActivity() as CreatingProfileActivity
        activity.pageIndicatorController.activePrefixChanged(FRAGMENT_POSITION)
    }

    private fun navigateToChooseProfilePhotoFragment() {
        val action = CreatingNameSurnameFragmentDirections
            .actionCreatingNameSurnameFragmentToCreatingProfileImageFragment()
        findNavController().navigate(action)
    }

}