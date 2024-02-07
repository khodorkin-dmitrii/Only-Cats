package com.yavin.mainscreenlib.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.yavin.mainscreenlib.data.WidgetTapData
import com.yavin.mainscreenlib.model.UiCollectionWithWidgetData
import com.yavin.mainscreenlib.model.UiWidget
import com.yavin.mainscreenlib.presentation.components.AsyncImageWithPreview
import com.yavin.mainscreenlib.ui.theme.MainColors
import com.yavin.mainscreenlib.ui.theme.MainDimens
import com.yavin.mainscreenlib.ui.theme.MainDimens.Companion.bigRound
import com.yavin.mainscreenlib.ui.theme.MainTheme
import com.yavin.mainscreenlib.ui.util.parseColor
import com.yavin.mainscreenlib.ui.util.textStyleWithColor

@Composable
fun FullWidthBlock(
    collection: UiCollectionWithWidgetData,
    itemWidth: Dp,
    itemHeight: Dp,
    onWidgetTap: (WidgetTapData) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(top = 16.dp, bottom = 12.dp)
    ) {
        collection.translatableFields[0].title?.let {
            Text(
                text = it,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MainTheme.typography.title20Bold,
                modifier = Modifier.padding(
                    start = MainDimens.blockPaddingDp,
                    end = MainDimens.blockPaddingDp,
                    bottom = 16.dp
                )
            )
        }

//        val data = collection.widgets[0]

        if (collection.widgets.size == 1) {
            FullWidthItemWidget(
                collection.widgets[0],
                itemWidth,
                itemHeight,
                onWidgetTap,
                Modifier.padding(
                    start = MainDimens.blockPaddingDp,
                    end = MainDimens.blockPaddingDp
                )
            )
        }

        if (collection.widgets.size > 1) {
            LazyRow(
                contentPadding = PaddingValues(
                    start = MainDimens.blockPaddingDp, end = MainDimens.blockPaddingDp
                ),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                collection.widgets.forEach {
                    item {
                        FullWidthItemWidget(it, itemWidth, itemHeight, onWidgetTap)
                    }
                }
            }
        }
    }
}

@Composable
private fun FullWidthItemWidget(
    data: UiWidget,
    itemWidth: Dp,
    itemHeight: Dp,
    onWidgetTap: (WidgetTapData) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(itemHeight)
            .clip(RoundedCornerShape(bigRound))
            .background(color = Color(parseColor(data.background_color, Color.White)))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(radius = 300.dp)
            ) {
//                onWidgetTap.invoke(WidgetTapData(data))
            }) {

        data.background_image_id?.let {
            AsyncImageWithPreview(
                it,
                Modifier.size(itemWidth, itemHeight)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(start = 16.dp, top = 16.dp, end = 16.dp)
        ) {
            data.translatable_fields[0].title?.let {
                Text(
                    text = it,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = textStyleWithColor(
                        MainTheme.typography.caption12Regular,
                        data.text_color,
                        MainColors.SpaceCadet
                    ),
                    modifier = Modifier
                        .padding(bottom = 4.dp)
                )
            }
            data.translatable_fields[0].subtitle?.let {
                Text(
                    text = it,
                    style = textStyleWithColor(
                        MainTheme.typography.body16Medium,
                        data.text_color,
                        MainColors.SpaceCadet
                    )
                )
            }
        }
        data.translatable_fields[0].stateBadge?.let {
            StateBadge(
                text = it, modifier = Modifier
                    .width(itemWidth)
                    .height(itemHeight)
            )
        }
    }
}