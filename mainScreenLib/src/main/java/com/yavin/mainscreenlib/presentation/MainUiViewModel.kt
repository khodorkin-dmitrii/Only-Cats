package com.yavin.mainscreenlib.presentation

import androidx.lifecycle.ViewModel
import com.yavin.mainscreenlib.data.MainUIDataRepository
import com.yavin.mainscreenlib.data.MainUiActions
import com.yavin.mainscreenlib.model.UiCollectionWithWidgetData

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

class MainUiViewModel(
    private val mainUIRepo: MainUIDataRepository,
    val mainUiActions: MainUiActions
    // TODO add val logger: ModuleLogger
) : ViewModel() {
}