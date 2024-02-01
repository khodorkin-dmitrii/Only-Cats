package com.yavin.mainscreenlib.model

data class UiCollection(
    val id: Int,
    val orderId: Int,
    val pageId: String,
    val type: CollectionWidgetType,
    val translatableFields: List<TranslatableField>
)