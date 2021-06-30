package com.levit.book_me.interactors.main_screen.impl

import com.levit.book_me.core.models.FriendProfileModel
import com.levit.book_me.core.utill.LocaleHelper
import com.levit.book_me.core_base.di.MainScreenScope
import com.levit.book_me.interactors.main_screen.CurrentFriendInteractor
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.repositories.profile.ProfileRepository
import com.levit.book_me.repositories.result_models.BaseRepositoryResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@MainScreenScope
class TestCurrentFriendInterator @Inject constructor(
    private val repository: ProfileRepository
): CurrentFriendInteractor {

    private val calendar = Calendar.getInstance(LocaleHelper.DEFAULT_LOCALE).also { calendar ->
        val currentHours = calendar.get(Calendar.HOUR_OF_DAY)
        val newHours = if (currentHours > 5) {
            2
        } else {
            22
        }
        calendar.set(Calendar.HOUR_OF_DAY, newHours)
    }

    private val dateFormat = "dd.MM.yyyy HH:mm:ss"
    private val dateFormatter = SimpleDateFormat(dateFormat, LocaleHelper.DEFAULT_LOCALE)

    private val launchTimeUTC0 = dateFormatter.format(calendar.time)

    override val friendModel: Flow<BaseRepositoryResult<FriendProfileModel>>
    = flow {
        repository.resultProfile.collect { repositoryResult ->
            if (repositoryResult !is BaseRepositoryResult.RemoteResult) {
                emit(BaseRepositoryResult.EmptySuccess)
                return@collect
            }
            val retrofitResult = repositoryResult.result
            if (retrofitResult !is RetrofitResult.Success) {
                emit(BaseRepositoryResult.EmptySuccess)
                return@collect
            }
            val profileModel = retrofitResult.data
            val friendModel = FriendProfileModel(
                id = 150,
                launchTimeUTC0 = launchTimeUTC0,
                model = profileModel,
            )
            val retrofitFriendResult = RetrofitResult.Success.Value(friendModel)
            val friendResult = BaseRepositoryResult.RemoteResult(retrofitFriendResult)
            emit(friendResult)
        }
    }

    override suspend fun getFriend(id: Long) {
        repository.getProfile()
    }
}