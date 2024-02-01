package com.yavin.mainscreenlib.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yavin.mainscreenlib.model.UserCat
import com.yavin.mainscreenlib.data.UserCatsRepository
import com.yavin.mainscreenlib.model.toCat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class UserCatsModelState(
    val userCasts: List<UserCat>,
    val state: UserCatsLoadState = UserCatsLoadState.LOADING
) {
    fun toUiState(): UserCatsModelState =
        UserCatsModelState(
            userCasts = userCasts,
            state = state
        )
}

enum class UserCatsLoadState {
    LOADING, LOADED, EMPTY, ERROR
}

@HiltViewModel
class UserCatsViewModel @Inject constructor(
    private val userCatsRepository: UserCatsRepository
) : ViewModel() {

    private var catsJob: Job? = null
    private val viewModeState = MutableStateFlow(
        UserCatsModelState(userCasts = emptyList())
    )

    val uiState = viewModeState
        .map { it.toUiState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModeState.value.toUiState()
        )

    init {
        getUserCastsFlow()
    }

    private fun updateCatsUI(uCats: List<UserCat>) {
        Log.d("updateCatsUI", "uc.size=${uCats.size}")
        viewModeState.update {
            it.copy(
                userCasts = uCats,
                state = if (uCats.isEmpty()) UserCatsLoadState.EMPTY else UserCatsLoadState.LOADED
            )
        }
    }

    private fun getUserCastsFlow() {
        catsJob?.cancel()
        catsJob = viewModelScope.launch(Dispatchers.IO) {
            Log.d(
                "FETCH CATS",
                "getCatsFlow" + Thread.currentThread().name + " launch"
            )
            try {
                userCatsRepository.getCats().collect { rawCats ->
                    updateCatsUI(rawCats.map { it.toCat() })
                }
            } catch (e: Error) {
                Log.d(
                    "FETCH CATS",
                    "getCatsFlow" + Thread.currentThread().name + " launch"
                )
                catsJob?.cancel()
                viewModeState.update {
                    it.copy(
                        state = UserCatsLoadState.ERROR
                    )
                }
            }
        }
    }
}