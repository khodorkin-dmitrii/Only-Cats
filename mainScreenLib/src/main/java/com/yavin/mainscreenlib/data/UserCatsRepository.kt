package com.yavin.mainscreenlib.data

import kotlinx.coroutines.flow.Flow

interface UserCatsRepository {

    suspend fun getCats(): Flow<List<UserCatDto>>

}