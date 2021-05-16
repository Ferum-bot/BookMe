package com.levit.book_me.ui.fragments.creating_profile.creating_name_surname

import android.content.Context
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
import com.levit.book_me.ui.fragments.quotes.utill.ProfileQuoteStorage

class CreatingNameSurnameFragment:
    BaseCreatingProfileFragment<CreatingNameSurnameViewModel>(R.layout.fragment_creating_name_surname) {

    companion object {
        private const val FRAGMENT_POSITION = 1
    }

    override val viewModel by viewModels<CreatingNameSurnameViewModel> { creatingProfileComponent.viewModelFactory() }

    private val binding by viewBinding { FragmentCreatingNameSurnameBinding.bind(it) }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        creatingProfileComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showMainPageIndicator(true)
        configureLayout()
        setAllClickListeners()
        setTextWatchers()
        setAllObservers()
        updatePageIndicator()
    }

    override fun setAllObservers() {
        super.setAllObservers()

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

        ProfileQuoteStorage.quote.observe(viewLifecycleOwner, Observer { quote ->
            if (quote == null) {
                binding.quoteItem.hideAuthor(true)
                binding.quoteItem.setChosen(false)
                binding.quoteItem.setNotChosenText()
            }
            else {
                binding.quoteItem.hideAuthor(false)
                binding.quoteItem.setChosen(true)
                binding.quoteItem.setQuote(quote)
            }
        })
    }

    private fun configureLayout() {
        binding.quoteItem.hideAuthor(true)
    }

    private fun setAllClickListeners() {
        binding.nextButton.setOnClickListener {
            navigateToChooseProfilePhotoFragment()
        }

        binding.quoteItem.setOnClickListener {
            navigateToChooseQuoteScreens()
        }
    }

    private fun setTextWatchers() {
        binding.nameTextInput.addTextChangedListener(ParcelableTextWatcher().apply {
            onTextChangeListener = viewModel::onNameChanged
        })

        binding.surnameTextInput.addTextChangedListener(ParcelableTextWatcher().apply {
            onTextChangeListener = viewModel::onSurnameChanged
        })

        binding.wordsAboutYouInputEditText.addTextChangedListener(ParcelableTextWatcher().apply {
            onTextChangeListener = viewModel::onAboutYouChanged
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

    private fun navigateToChooseQuoteScreens() {
        val action = CreatingNameSurnameFragmentDirections
            .actionCreatingNameSurnameFragmentToChooseQuoteNavGraph()
        findNavController().navigate(action)
    }
}