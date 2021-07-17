package com.levit.book_me.work_managers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.levit.book_me.di.Injector
import com.levit.book_me.repositories.profile.AuthRepository
import javax.inject.Inject

class SafeFCMTokenWorker(
    appContext: Context,
    workParams: WorkerParameters,
): Worker(appContext, workParams) {

    private val repository by lazy {
        Injector.appComponent.authRepository
    }

    override fun doWork(): Result {
        TODO("Not yet implemented")
    }

}