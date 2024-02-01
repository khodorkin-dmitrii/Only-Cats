package com.yavin.mainscreenlib.data

import com.yavin.mainscreenlib.model.UserCatDto
import kotlinx.coroutines.flow.Flow

interface UserCatsRepository {

    suspend fun getCats(): Flow<List<UserCatDto>>

}