package com.yavin.mainscreenlib.data

import kotlinx.coroutines.flow.Flow

interface UserCatRepository {

    suspend fun getCats(): Flow<List<UserCatDto>>

}