package com.levit.book_me.ui.fragments.creating_profile.creating_name_surname

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.levit.book_me.R
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.core.ui.ParcelableTextWatcher
import com.levit.book_me.databinding.FragmentCreatingNameSurnameBinding
import com.levit.book_me.ui.activities.creating_profile.CreatingProfileActivity
import com.levit.book_me.ui.base.BaseCreatingProfileFragment
import com.levit.book_me.ui.fragments.creating_profile.utills.CreatingProfileConstants
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
            binding.invalidNameLabel.isVisible = !nameIsCorrect
        })

        viewModel.isSurnameCorrect.observe(viewLifecycleOwner, Observer { surnameIsCorrect ->
            binding.invalidSurnameLabel.isVisible = !surnameIsCorrect
        })

        viewModel.isWordsAboutYouCorrect.observe(viewLifecycleOwner) { isWordsAboutYouCorrect ->
            binding.invalidWordsAboutYouLabel.isVisible = !isWordsAboutYouCorrect
        }

        viewModel.isQuoteChosen.observe(viewLifecycleOwner) { isQuoteChosen ->
            binding.quoteNotChosenLabel.isVisible = !isQuoteChosen
        }

        viewModel.isWordsAboutYouErrorStringId.observe(viewLifecycleOwner) { stringId ->
            val errorString = when (stringId) {
                R.string.minimum_length_is -> {
                    getString(stringId, CreatingProfileConstants.WORDS_ABOUT_YOU_MIN_LENGTH)
                }
                R.string.maximum_length_is -> {
                    getString(stringId, CreatingProfileConstants.WORDS_ABOUT_YOU_MAX_LENGTH)
                }
                R.string.write_some_words_about_you -> {
                    getString(stringId)
                }
                else -> {
                    getString(R.string.something_went_wrong)
                }
            }

            binding.invalidWordsAboutYouLabel.text = errorString
        }

        ProfileQuoteStorage.quote.observe(viewLifecycleOwner, Observer { quote ->
            viewModel.chosenQuote = quote
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
            val name = binding.nameTextInput.text.toString()
            val surname = binding.surnameTextInput.text.toString()
            val wordsAboutYou = binding.wordsAboutYouInputEditText.text.toString()
            //if (viewModel.checkIsEverythingIsValid(name, surname, wordsAboutYou)) {
                navigateToChooseProfilePhotoFragment()
            //}
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