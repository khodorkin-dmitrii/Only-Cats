package com.yavin.mainscreenlib.presentation

import androidx.lifecycle.ViewModel
import com.yavin.mainscreenlib.data.UserCat
import com.yavin.mainscreenlib.data.UserCatRepository
import com.yavin.mainscreenlib.data.toCat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class UserCatsViewModel @Inject constructor(
    private val userCatRepository: UserCatRepository
) : ViewModel() {

    suspend fun getUserCasts(): Flow<List<UserCat>> {
        return userCatRepository.getCats()
            .map { userCatDtoList ->
                userCatDtoList.map { it.toCat() }
            }
    }
}