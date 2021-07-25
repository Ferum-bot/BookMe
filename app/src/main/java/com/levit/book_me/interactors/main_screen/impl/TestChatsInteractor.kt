package com.levit.book_me.interactors.main_screen.impl

import com.levit.book_me.core.models.chat_kit.UserChat
import com.levit.book_me.core_base.di.MainScreenScope
import com.levit.book_me.interactors.main_screen.ChatsInteractor
import com.levit.book_me.network.network_result_data.RetrofitResult
import com.levit.book_me.repositories.result_models.BaseRepositoryResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

@MainScreenScope
class TestChatsInteractor: ChatsInteractor {

    private val imagesUrls = listOf(
        "https://images.unsplash.com/photo-1623413487006-54669f337144?crop=entropy\\u0026cs=srgb\\u0026fm=jpg\\u0026ixid=MnwyMTkyMTJ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjYwMzE4MTI\\u0026ixlib=rb-1.2.1\\u0026q=85",
        "https://images.unsplash.com/photo-1623512335584-a7d391d8bf82?crop=entropy\\u0026cs=srgb\\u0026fm=jpg\\u0026ixid=MnwyMTkyMTJ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjYwMzE4MTI\\u0026ixlib=rb-1.2.1\\u0026q=85",
        "https://images.unsplash.com/photo-1623597649935-b276f85e7723?crop=entropy\\u0026cs=srgb\\u0026fm=jpg\\u0026ixid=MnwyMTkyMTJ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjYwMzE4MTI\\u0026ixlib=rb-1.2.1\\u0026q=85",
        "https://images.unsplash.com/photo-1625353914937-ac01c98048fd?crop=entropy\\u0026cs=srgb\\u0026fm=jpg\\u0026ixid=MnwyMTkyMTJ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjYwMzE4MTI\\u0026ixlib=rb-1.2.1\\u0026q=85",
        "https://images.unsplash.com/photo-1625838144804-300f3907c110?crop=entropy\\u0026cs=srgb\\u0026fm=jpg\\u0026ixid=MnwyMTkyMTJ8MHwxfHJhbmRvbXx8fHx8fHx8fDE2MjYwMzE4MTI\\u0026ixlib=rb-1.2.1\\u0026q=85",

    )

    override val userChats: Flow<BaseRepositoryResult<List<UserChat>>>
    = flow {
        delay(3000L)
        val resultList = listOf(
            UserChat(0, 0, "Matvey Popov", imagesUrls[0], "Hello!", 1626032030284L, true, 6),
            UserChat(1, 1, "Bob Martin", imagesUrls[1], "WHO ARE YOU??", 1626022030284L, false, 0),
            UserChat(2, 2, "Bill Gates", imagesUrls[2], "It is really you?", 1626021030284L, true, 0),
            UserChat(3, 3, "Robert Junior", imagesUrls[3], "good bye", 1626021000284L, false, 0),
            UserChat(4, 4, "Alice Bush", imagesUrls[4], "thanks", 1626020030284L, false, 0),
        )
        val retrofitResult = RetrofitResult.Success.Value(resultList)
        val repositoryResult = BaseRepositoryResult.RemoteResult(retrofitResult)
        emit(repositoryResult)
    }

    override suspend fun getAllChats() {

    }
}