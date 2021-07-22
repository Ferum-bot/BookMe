package com.levit.book_me.work_managers

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.levit.book_me.core.models.profile.AuthInfo
import com.levit.book_me.core.utill.WorkManagerUtil
import com.levit.book_me.di.Injector
import com.levit.book_me.network.network_result_data.RetrofitResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UploadFCMTokenWorker(
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
        newToken ?: return Result.retry()
        return withContext(Dispatchers.IO) {
            val newAuthInfo = repository.updateRemotePushToken(newToken!!)
            return@withContext if (newAuthInfo.isTokenUpdated()) {
                Result.success()
            } else {
                Result.retry()
            }
        }
    }

    private fun RetrofitResult<AuthInfo>.isTokenUpdated(): Boolean {
        return when(this) {
            is RetrofitResult.Success -> true
            is RetrofitResult.Failure<*> -> false
        }
    }
}