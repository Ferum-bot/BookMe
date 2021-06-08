package com.levit.book_me.core.utill

import android.net.Uri
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.LifecycleOwner

class ProfileImagePicker(
    activityResultRegistry: ActivityResultRegistry,
    lifecycleOwner: LifecycleOwner,
    callback: (imageUri: Uri?) -> Unit,
) {

    companion object {
        private const val IMAGE_TYPE = "image/*"

        private const val REGISTRY_KEY = "ProfileImagePicker"
    }

    private val activityResultCallback = ActivityResultCallback<Uri?> { callback.invoke(it) }

    private val getContent: ActivityResultLauncher<String> =
        activityResultRegistry.register(REGISTRY_KEY, lifecycleOwner, contract, activityResultCallback)

    private val contract
    get() = ActivityResultContracts.GetContent()

    fun pickPicture() {
        getContent.launch(IMAGE_TYPE)
    }

}