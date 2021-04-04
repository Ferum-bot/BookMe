package com.levit.book_me.ui.activities.onboarding

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.levit.book_me.R
import com.levit.book_me.core.utill.AssetsImageLoader
import com.levit.book_me.core.utill.ImageFormats
import com.levit.book_me.databinding.FragmentOnboardingBinding

class OnBoardingViewPagerAdapter: RecyclerView.Adapter<OnBoardingViewPagerAdapter.OnBoardingViewPagerVH>(){

    override fun getItemCount(): Int = ITEMS_COUNT

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewPagerVH {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FragmentOnboardingBinding.inflate(inflater, parent, false)
        return OnBoardingViewPagerVH(binding)
    }

    override fun onBindViewHolder(holder: OnBoardingViewPagerVH, position: Int)
        = holder.bind(position)

    companion object {
        private const val ITEMS_COUNT = 3
    }

    class OnBoardingViewPagerVH(
        private val binding: FragmentOnboardingBinding
    ): RecyclerView.ViewHolder(binding.root) {

        private val context: Context
            get() = binding.root.context

        private val imageLoader = AssetsImageLoader.Builder(binding.imageView)
            .apply {
                imageFormat = ImageFormats.PNG
                folderLink = ON_BOARDING_FOLDER_NAME
            }
            .build()

        fun bind(position: Int) {
            when(position) {
                FIRST -> bindFirstScreen()
                SECOND -> bindSecondScreen()
                THIRD -> bindThirdScreen()
            }
        }

        private fun bindFirstScreen() {
            imageLoader.loadImage(ON_BOARDING_FIRST_IMAGE_NAME)
            binding.label.text = getString(R.string.onboarding_first_label)
            binding.description.text = getString(R.string.onboarding_first_description)
        }

        private fun bindSecondScreen() {
            imageLoader.loadImage(ON_BOARDING_SECOND_IMAGE_NAME)
            binding.label.text = getString(R.string.onboarding_second_label)
            binding.description.text = getString(R.string.onboarding_second_description)
        }

        private fun bindThirdScreen() {
            imageLoader.loadImage(ON_BOARDING_THIRD_IMAGE_NAME)
            binding.label.text = getString(R.string.onboarding_third_label)
            binding.description.text = getString(R.string.onboarding_third_description)
        }

        private fun getString(@StringRes id: Int): String
                = context.getString(id)

        companion object {
            private const val FIRST = 0
            private const val SECOND = 1
            private const val THIRD = 2

            private const val ON_BOARDING_FOLDER_NAME = "onboarding"
            private const val ON_BOARDING_FIRST_IMAGE_NAME = "onboarding_first_screen_image"
            private const val ON_BOARDING_SECOND_IMAGE_NAME = "onboarding_second_screen_image"
            private const val ON_BOARDING_THIRD_IMAGE_NAME = "onboarding_third_screen_image"
        }
    }

}
