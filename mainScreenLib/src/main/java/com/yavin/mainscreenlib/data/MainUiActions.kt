package com.yavin.mainscreenlib.data

import com.yavin.mainscreenlib.model.CollectionWidgetType
import com.yavin.mainscreenlib.model.WidgetActionType

interface MainUiActions {
    fun onWidgetTap(widgetData: WidgetTapData)
}

data class WidgetTapData(
    val widgetId: Int,
    val widgetType: CollectionWidgetType,
    val actionType: WidgetActionType,
    val uri: String?
)