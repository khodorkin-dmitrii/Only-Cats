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
                "Stephan",
                "https://picsum.photos/id/40/200/200.jpg",
                true
            ),
            UserCatDto(
                "1",
                "Shteffie",
                "https://picsum.photos/id/219/200/200.jpg",
                true
            )
        )
    }

}