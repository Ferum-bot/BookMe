package com.levit.book_me.work_managers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.levit.book_me.di.modules.SharedPreferenceModule
import com.levit.book_me.repositories.profile.AuthRepository
import com.levit.book_me.repositories.profile.impl.SharedPrefAuthRepository
import javax.inject.Inject

class UploadFCMTokenWorker(
    appContext: Context,
    workParams: WorkerParameters,
): Worker(appContext, workParams) {

    override fun doWork(): Result {
        TODO("Not yet implemented")
    }

}