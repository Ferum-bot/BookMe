package com.levit.book_me.core.utill

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.widget.ImageView
import com.levit.book_me.core.enums.ImageFormats
import timber.log.Timber
import java.io.IOException
import java.io.InputStream

class AssetsImageLoader private constructor(
        private val folderLink: String,
        private val imageFormat: ImageFormats,
        private val imageViewToLoad: ImageView,
){

    private val context: Context
    get() = imageViewToLoad.context

    private val assets: AssetManager
    get() = context.assets

    /**
     * Name of the asset image with out expansion
     */
    fun loadImage(name: String) {
        try {
            tryToLoadImageToImageView(name)
        }
        catch (ex: IOException) {
            Timber.e(ex)
        }
        catch (ex: NullPointerException) {
            Timber.e(ex)
        }
    }

    private fun tryToLoadImageToImageView(name: String) {
        val fileLink = getFileLink(name)
        val inputStream = assets.open(fileLink)

        val imageBitmap = getBitmap(inputStream)
        loadBitmapToImage(imageBitmap)
        inputStream.close()
    }

    private fun getFileLink(name: String): String {
        return if (folderLink.isBlank()) {
            getFullFileName(name)
        }
        else {
            folderLink + SLASH + getFullFileName(name)
        }
    }

    private fun getFullFileName(name: String): String
        = name + DOT + imageFormat.toString()

    private fun getBitmap(inputStream: InputStream): Bitmap {
        val options = BitmapFactory.Options().apply {
            inPreferredConfig = Bitmap.Config.ALPHA_8
        }
        return BitmapFactory.decodeStream(inputStream, null, options)!!
    }

    private fun loadBitmapToImage(bitmap: Bitmap) {
        imageViewToLoad.setImageBitmap(bitmap)
    }

    companion object {
        private const val DOT = "."
        private const val SLASH = "/"
    }

    class Builder(
        var imageViewToLoad: ImageView
    ) {

        var folderLink: String = ""
        var imageFormat: ImageFormats = ImageFormats.PNG

        fun build(): AssetsImageLoader =
            AssetsImageLoader(
                folderLink,
                imageFormat,
                imageViewToLoad
            )
    }
}