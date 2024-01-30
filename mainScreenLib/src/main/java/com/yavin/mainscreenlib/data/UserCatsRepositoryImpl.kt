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
                CAT_IMG_URL
            ),
            UserCatDto(
                "1",
                "Shteffie",
                CAT_IMG_URL
            )
        )
    }

    companion object {
        const val CAT_IMG_URL = "https://cataas.com/cat?type=square&width=100"
    }

}