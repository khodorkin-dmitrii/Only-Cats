package com.yavin.mainscreenlib.presentation.screens

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.yavin.mainscreenlib.data.WidgetTapData
import com.yavin.mainscreenlib.model.CollectionWidgetType
import com.yavin.mainscreenlib.model.UiCollectionWithWidgetData
import com.yavin.mainscreenlib.model.UserCat
import com.yavin.mainscreenlib.presentation.FetchWidgetState
import com.yavin.mainscreenlib.presentation.MainUiViewModel
import com.yavin.mainscreenlib.presentation.UserCatsViewModel
import com.yavin.mainscreenlib.presentation.components.DynamicContentSkeleton
import com.yavin.mainscreenlib.presentation.widgets.CatsRow
import com.yavin.mainscreenlib.presentation.widgets.CompatBlock
import com.yavin.mainscreenlib.ui.theme.MainDimens.Companion.blockPaddingDp
import com.yavin.mainscreenlib.ui.theme.MainDimens.Companion.itemPaddingDp

@Composable
fun MainScreenContent() {

    val scrollState = rememberScrollState()
    val userCatsViewModel = hiltViewModel<UserCatsViewModel>()
    val userCastState = userCatsViewModel.uiState.collectAsState()

    val mainUiViewModel = hiltViewModel<MainUiViewModel>()

    // TODO map loading to skeleton end error case
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .animateContentSize()
    ) {
        CatsRowBlock(
            userCastState.value.userCasts,
            onCatTap = {}
        )

        WidgetsColumnBlock(
            mainUiViewModel,
            onWidgetTap = {}
        )
    }
}

@Composable
private fun CatsRowBlock(
    userCats: List<UserCat>,
    onCatTap: (String) -> Unit,
) {
    CatsRow(userCats = userCats, onCatTap = onCatTap)
}

@Composable
private fun WidgetsColumnBlock(
    mainUiViewModel: MainUiViewModel,
    onWidgetTap: (WidgetTapData) -> Unit
) {
    val widgetsModelState by mainUiViewModel.uiState.collectAsState()

    val fullScreenItemWidth = LocalConfiguration.current.screenWidthDp.dp - (blockPaddingDp * 2)
    val halfScreenItemWidth = (fullScreenItemWidth - itemPaddingDp) / 2
    val itemHeight = 180.dp

    when (widgetsModelState.state) {
        FetchWidgetState.LOADING ->
            DynamicContentSkeleton(fullScreenItemWidth, halfScreenItemWidth, itemHeight)

        FetchWidgetState.LOADED -> ShowLoadedWidgets(
            widgetsModelState.uiCollectionWithWidgetDataWidgets,
            fullScreenItemWidth,
            halfScreenItemWidth,
            itemHeight,
            onWidgetTap
        )

        FetchWidgetState.EMPTY -> ShowEmptyWidgets()
        FetchWidgetState.ERROR -> ShowWidgetsLoadError()
    }
}

@Composable
private fun ShowLoadedWidgets(
    uiWidgets: List<UiCollectionWithWidgetData>,
    fullScreenItemWidth: Dp,
    halfScreenItemWidth: Dp,
    itemHeight: Dp,
    onWidgetTap: (WidgetTapData) -> Unit
) {
    uiWidgets.forEach { item ->
        when (item.type) {
        CollectionWidgetType.COMPACT -> CompatBlock(item, halfScreenItemWidth, itemHeight, onWidgetTap)
//        CollectionWidgetType.COMPACT_SCROLL -> CompatScrollBlock(item, halfScreenItemWidth, itemHeight, onWidgetTap)
//        CollectionWidgetType.BANNER -> MoneyBannerBlock(item, fullScreenItemWidth, onWidgetTap)
//        CollectionWidgetType.FULL_WIDTH -> FullWidthBlock(item, fullScreenItemWidth, itemHeight, onWidgetTap)
//        CollectionWidgetType.PROMO_BUTTONS -> PromoButtonsBlock(item, onWidgetTap)
            else -> Log.w("UNKNOWN_UI","$item")
        }
    }
}

@Composable
private fun ShowEmptyWidgets() {

}

@Composable
private fun ShowWidgetsLoadError() {

}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    MainScreenContent()
}