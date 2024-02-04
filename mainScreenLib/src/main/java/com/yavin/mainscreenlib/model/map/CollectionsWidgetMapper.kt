package com.yavin.mainscreenlib.model.map

import com.yavin.mainscreenlib.model.CollectionWidgetType
import com.yavin.mainscreenlib.model.UiCollection
import com.yavin.mainscreenlib.model.UiCollectionWithWidgetData
import com.yavin.mainscreenlib.model.UiWidget

object CollectionsWidgetMapper :
    Mapper21<List<UiCollection>, List<UiWidget>, List<UiCollectionWithWidgetData>> {

    override fun map(
        src1: List<UiCollection>,
        src2: List<UiWidget>
    ): List<UiCollectionWithWidgetData> {

        val widgetCollection = mutableListOf<UiCollectionWithWidgetData>()

        src1.forEach {
            val itWidgets = src2.filter { it2 -> it2.collection_id == it.id }

            var collectionType = it.type
            if (collectionType == CollectionWidgetType.COMPACT && itWidgets.size > 2) {
                collectionType = CollectionWidgetType.COMPACT_SCROLL
            }

            widgetCollection.add(
                UiCollectionWithWidgetData(
                    id = it.id,
                    orderId = it.orderId,
                    pageId = it.pageId,
                    type = collectionType,
                    translatableFields = it.translatableFields,
                    widgets = itWidgets
                )
            )
        }

        return widgetCollection
    }
}