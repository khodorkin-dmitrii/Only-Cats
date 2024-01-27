package com.yavin.mainscreenlib.data

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UserCatsRepositoryImpl : UserCatsRepository {
    override suspend fun getCats(): Flow<List<UserCatDto>> {
        return flow {
            delay(500)
            emit(getMockCats())
        }
    }

    private fun getMockCats(): List<UserCatDto> {
        return listOf(
            UserCatDto(
                "0",
                "Cat0",
                "http://placekitten.com/200/200",
                true
            ),
            UserCatDto(
                "1",
                "Cat1",
                "http://placekitten.com/200/200",
                true
            )
        )
    }

}