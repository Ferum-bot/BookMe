package com.levit.book_me.core.utill

import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.provider.DocumentsContract
import java.io.File
import java.util.*

object FileProvider {

    private const val EXTERNAL_STORAGE_DOCUMENT = "com.android.externalstorage.documents"
    private const val DOWNLOADS_DOCUMENT = "com.android.providers.downloads.documents"
    private const val MEDIA_DOCUMENT = "com.android.providers.media.documents"

    private const val CONTENT = "content"
    private const val FILE = "file"

//    fun getFileFromUri(context: Context, uri: Uri): File {
//        val path = if (isDocumentUri(context, uri)) {
//            handleDocumentUri(context, uri)
//        } else if (isMediaStore(uri)) {
//
//        }
//        else if
//    }

//    private fun handleDocumentUri(context: Context, uri: Uri): String {
//
//    }
//
//    private fun handleMediaStoreUri(context: Context, uri: Uri): String {
//
//    }
//
//    private fun handleFileUri(context: Context, uri: Uri): String {
//
//    }

    private fun getDataColumn(context: Context, uri: Uri, selection: String, selectionArgs: Array<String>): String? {
        var cursor: Cursor? = null
        val column = "_data"
        val projection = Array(1) { column }

        try {
            cursor = context.contentResolver.query(uri, projection, selection, selectionArgs, null)
            if (cursor != null && cursor.moveToFirst()) {
                val columnIndex = cursor.getColumnIndexOrThrow(column)
                return cursor.getString(columnIndex)
            }
        }
        finally {
            cursor?.close()
        }
        return null
    }

    private fun isDocumentUri(context: Context, uri: Uri) =
        DocumentsContract.isDocumentUri(context, uri)

    private fun isExternalStorageDocument(uri: Uri): Boolean =
        EXTERNAL_STORAGE_DOCUMENT == uri.authority

    private fun isDownLoadsDocument(uri: Uri): Boolean =
        DOWNLOADS_DOCUMENT == uri.authority

    private fun isMediaDocument(uri: Uri): Boolean =
        MEDIA_DOCUMENT == uri.authority

    private fun isMediaStore(uri: Uri): Boolean =
        CONTENT == uri.scheme?.toLowerCase(Locale.getDefault())

    private fun isFile(uri: Uri): Boolean =
        FILE == uri.scheme?.toLowerCase(Locale.getDefault())
}