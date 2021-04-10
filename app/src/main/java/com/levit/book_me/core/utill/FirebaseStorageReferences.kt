package com.levit.book_me.core.utill

import android.net.Uri
import androidx.core.net.toFile
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.ktx.storage
import java.io.File

object FirebaseStorageReferences {

    private const val STORAGE_USER_PROFILE_PHOTOS_PATH = "UsersProfilePhotos/"
    private const val PROFILE_IMAGE_NAME = "ProfilePhoto"
    private const val DOT = "."
    private const val PHOTO = "photo"


    private val userId
    get() = (Firebase.auth.currentUser?.uid ?: "UnAuthoredUser") + "/"

    private val storage = Firebase.storage
    private val startStoragePath = storage.reference
    private val imageFolderRef = startStoragePath.child(STORAGE_USER_PROFILE_PHOTOS_PATH)

    fun getStorageProfileImageReference(imageFile: File): StorageReference {
        val extension = imageFile.extension
        val fileName = PROFILE_IMAGE_NAME + DOT + extension

        return imageFolderRef
                .child(userId)
                .child(fileName)
    }

    fun getStorageProfileImageReference(imageUri: Uri): StorageReference {
        val fileName = PROFILE_IMAGE_NAME + DOT + ImageFormats.JPG

        return imageFolderRef
                .child(userId)
                .child(PHOTO)
    }

}