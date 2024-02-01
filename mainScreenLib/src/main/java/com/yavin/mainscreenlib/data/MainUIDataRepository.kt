package com.yavin.mainscreenlib.data

import com.yavin.mainscreenlib.model.UiCollection
import com.yavin.mainscreenlib.model.UiWidget

interface MainUIDataRepository {

    suspend fun getCollections(): List<UiCollection>

    suspend fun getWidgets(ids: List<Int>): List<UiWidget>

    suspend fun getCoins(): Int

}