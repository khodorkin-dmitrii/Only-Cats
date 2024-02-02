package com.yavin.mainscreenlib.model

data class UiWidget(
    val id: Int,
    val image_id: String?,
    val order_id: Int,
    val collection_id: Int,
    val action_type: WidgetActionType,
    val action_uri: String?,
    val video_uri: String?,
    val background_color: String?,
    val background_image_id: String?,
    val icon_color: String?,
    val text_color: String?,
    val type: CollectionWidgetType,
    val translatable_fields: List<TranslatableField>
)

enum class WidgetActionType(val type: String) {
    EXTERNAL_BROWSER("external_browser"),
    INTERNAL_BROWSER("internal_browser"),
    DEEPLINK("deeplink"),
    UNKNOWN("unknown")
}

data class UiCollectionWithWidgetData(
    val id: Int,
    val orderId: Int,
    val pageId: String,
    val type: CollectionWidgetType,
    val translatableFields: List<TranslatableField>,
    val widgets: List<UiWidget>
)