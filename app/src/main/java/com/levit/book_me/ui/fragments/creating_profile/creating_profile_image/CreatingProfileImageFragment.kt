package com.levit.book_me.ui.fragments.creating_profile.creating_profile_image

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.levit.book_me.R
import com.levit.book_me.core.extensions.viewBinding
import com.levit.book_me.core.utill.AssetsImageLoader
import com.levit.book_me.core.enums.util.ImageFormats
import com.levit.book_me.core.utill.ProfileImagePicker
import com.levit.book_me.databinding.FragmentCreatingProfileImageBinding
import com.levit.book_me.ui.activities.creating_profile.CreatingProfileActivity
import com.levit.book_me.ui.base.BaseCreatingProfileFragment

class CreatingProfileImageFragment:
    BaseCreatingProfileFragment<CreatingProfileImageViewModel>(R.layout.fragment_creating_profile_image) {

    companion object {
        private const val PROFILE_PLACEHOLDER_IMAGE_FOLDER_LINK = "creating_profile"
        private const val PROFILE_PLACEHOLDER_IMAGE_NAME = "upload_your_photo_image"

        private const val FRAGMENT_POSITION = 2
    }

    override val viewModel by viewModels<CreatingProfileImageViewModel> { creatingProfileComponent.viewModelFactory() }

    private val binding by viewBinding { FragmentCreatingProfileImageBinding.bind(it) }

    private val profileImagePicker by lazy {
        ProfileImagePicker(activityResultRegistry, this, this::loadProfileImage)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        creatingProfileComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadPlaceHolderToProfilePhoto()
        initProfileImagePicker()
        setAllClickListeners()
        setAllObservers()
        updatePageIndicator()
    }

    private fun initProfileImagePicker() {
        profileImagePicker
    }

    private fun setAllClickListeners() {
        binding.nextButton.setOnClickListener {
            showLoading(true)
            viewModel.upLoadProfilePhoto(this::requireContext)
        }

        binding.profilePhoto.setOnClickListener {
            profileImagePicker.pickPicture()
        }
    }

    override fun setAllObservers() {

        viewModel.isPhotoChosen.observe(viewLifecycleOwner) { photoIsChosen ->
            binding.nextButton.isEnabled = true
            if (photoIsChosen) {
                binding.photoHint.text = getString(R.string.press_next_or_choose_another_photo)
            } else {
                binding.photoHint.text = getString(R.string.take_a_photo_or_choose_from_your_library)
            }
        }

        viewModel.imageUri.observe(viewLifecycleOwner) { imageUri ->
            if (imageUri != null) {
                binding.profilePhoto.setImageURI(imageUri)
                sharedViewModel.safeProfileImageUrl(imageUri.toString())
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { message ->
            if (message != null) {
                showError(message)
                viewModel.errorMessageHasShown()
                showLoading(false)
            }
        }

        viewModel.errorMessageId.observe(viewLifecycleOwner) { messageId ->
            if (messageId != null) {
                showError(messageId)
                viewModel.errorMessageHasShown()
                showLoading(false)
            }
        }

        viewModel.isPhotoUpLoaded.observe(viewLifecycleOwner) { photoIsUpLoaded ->
            if (photoIsUpLoaded) {
                showLoading(false)
                showSuccessMessage(R.string.photo_uploaded)
                navigateToCreatingFavouriteGenresFragment()
                viewModel.photoUploadHasShown()
            }
        }
    }

    private fun updatePageIndicator() {
        val activity = requireActivity() as CreatingProfileActivity
        activity.pageIndicatorController.activePrefixChanged(FRAGMENT_POSITION)
    }

    private fun loadPlaceHolderToProfilePhoto() {
        val assetsLoader = AssetsImageLoader.Builder(binding.profilePhoto).apply {
            folderLink = PROFILE_PLACEHOLDER_IMAGE_FOLDER_LINK
            imageFormat = ImageFormats.PNG
        }.build()

        assetsLoader.loadImage(PROFILE_PLACEHOLDER_IMAGE_NAME)
    }

    private fun loadProfileImage(imageUri: Uri?) {
        imageUri ?: return
        viewModel.photoIsChosen(imageUri)
    }

    private fun showLoading(show: Boolean) {
        binding.progressBar.isVisible = show
        binding.nextButton.isVisible = !show
    }

    private fun navigateToCreatingFavouriteGenresFragment() {
        val action = CreatingProfileImageFragmentDirections
            .actionCreatingProfileImageFragmentToCreatingFavouriteGenresFragment()
        findNavController().navigate(action)
    }
}