package com.levit.bookme.chatkit.models.utills

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.levit.book_me.chat_kit.R

class RemoteImageLoader(
    private val imageView: ImageView,
    private val options: RequestOptions,
) {

    companion object {
        private const val CROSS_FADE_DURATION = 10
        private const val DEFAULT_CACHE_SIZE = 100
    }

    private val defaultListener: RequestListener<Drawable>
    get() = object: RequestListener<Drawable> {

        override fun onLoadFailed(
            e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean
        ): Boolean {
            imageView.setBackgroundResource(R.drawable.ic_default_error_placeholder)
            return false
        }

        override fun onResourceReady(
            resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean
        ): Boolean {
            imageView.setImageDrawable(resource)
            return false
        }
    }

    fun load(url: String?) {
        Glide.with(imageView)
            .applyDefaultRequestOptions(options)
            .load(url)
            .addListener(defaultListener)
            .transition(getDefaultCrossFade())
            .into(imageView)
    }

    private fun getDefaultCrossFade(): DrawableTransitionOptions
            = withCrossFade().crossFade(CROSS_FADE_DURATION)
}