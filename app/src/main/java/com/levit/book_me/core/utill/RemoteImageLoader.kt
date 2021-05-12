package com.levit.book_me.core.utill

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.levit.book_me.network.models.google_books.ImageLinks

class RemoteImageLoader(
    private val imageView: ImageView,
    private val options: RequestOptions,
) {

    companion object {
        private const val CROSS_FADE_DURATION = 1000
        private const val DEFAULT_CACHE_SIZE = 500
    }

    fun load(url: String?) {
        Glide.with(imageView)
            .applyDefaultRequestOptions(options)
            .load(url)
            .override(DEFAULT_CACHE_SIZE, DEFAULT_CACHE_SIZE)
            .transform(CenterCrop())
            .transition(getDefaultCrossFade())
            .into(imageView)
    }

    fun load(imageLinks: ImageLinks?) {
        val link = imageLinks?.getBiggestAvailableLink()

        load(link)
    }

    private fun getDefaultCrossFade(): DrawableTransitionOptions
        = withCrossFade().crossFade(CROSS_FADE_DURATION)
}