package com.yavin.mainscreenlib.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yavin.mainscreenlib.data.MainUIDataRepository
import com.yavin.mainscreenlib.model.CollectionWidgetType
import com.yavin.mainscreenlib.model.UiCollectionWithWidgetData
import com.yavin.mainscreenlib.model.map.CollectionsWidgetMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class WidgetsModelState(
    val uiCollectionWithWidgetDataWidgets: List<UiCollectionWithWidgetData>,
    val state: FetchWidgetState = FetchWidgetState.LOADING
) {

    fun toUiState(): WidgetsModelState =
        WidgetsModelState(
            uiCollectionWithWidgetDataWidgets = uiCollectionWithWidgetDataWidgets,
            state = state
        )
}

enum class FetchWidgetState {
    LOADING, LOADED, EMPTY, ERROR
}

@HiltViewModel
class MainUiViewModel @Inject constructor(
    private val mainUIRepo: MainUIDataRepository,
//    val mainUiActions: MainUiActions
    // TODO add val logger: ModuleLogger
) : ViewModel() {

    private var widgetsJob: Job? = null
    private val viewModelState = MutableStateFlow(
        WidgetsModelState(uiCollectionWithWidgetDataWidgets = emptyList())
    )

    val uiState = viewModelState
        .map { it.toUiState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    init {
        getWidgets()
    }

    private fun getWidgets() {
        widgetsJob?.cancel()
        widgetsJob = viewModelScope.launch {
            try {

                val uiCollections = mainUIRepo.getCollections()
                val widgetIds = uiCollections.map { it.id }
                val widgets = mainUIRepo.getWidgets(widgetIds)
                val collectedWidgets = CollectionsWidgetMapper.map(uiCollections, widgets)

                widgets.forEach {
                    if (it.type == CollectionWidgetType.CAT_MONEY) {
                        val coins = mainUIRepo.getCoins()
                        it.translatable_fields[0].stateBadge = "$coins"
                    }
                }

                viewModelState.update {
                    Log.d("MAIN_UI", collectedWidgets.toString())
                    it.copy(
                        uiCollectionWithWidgetDataWidgets = collectedWidgets,
                        state = if (collectedWidgets.isEmpty())
                            FetchWidgetState.EMPTY else FetchWidgetState.LOADED
                    )
                }

            } catch (e: Exception) {
                Log.d("FetchWidgetState.ERROR", e.toString())
                widgetsJob?.cancel()
                viewModelState.update {
                    it.copy(
                        state = FetchWidgetState.ERROR
                    )
                }
            }
        }
    }
}