package com.levit.book_me.work_managers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class UploadFCMTokenWorker(
    appContext: Context,
    workParams: WorkerParameters
): Worker(appContext, workParams) {

    override fun doWork(): Result {
        TODO("Not yet implemented")
    }

}