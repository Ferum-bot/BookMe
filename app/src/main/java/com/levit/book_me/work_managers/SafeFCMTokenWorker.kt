package com.levit.book_me.work_managers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.levit.book_me.core.utill.WorkManagerUtil
import com.levit.book_me.di.Injector
import com.levit.book_me.repositories.profile.AuthRepository
import kotlinx.coroutines.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext

class SafeFCMTokenWorker(
    appContext: Context,
    workParams: WorkerParameters,
): CoroutineWorker(appContext, workParams) {

    private val repository by lazy {
        Injector.appComponent.authRepository
    }

    private val newToken by lazy {
        inputData.getString(WorkManagerUtil.NEW_PUSH_TOKEN_NAME)
    }

    override suspend fun doWork(): Result {
        newToken ?: return Result.failure()
        return withContext(Dispatchers.IO) {
            repository.updateLocalPushToken(newToken!!)
            return@withContext Result.success()
        }
    }

}