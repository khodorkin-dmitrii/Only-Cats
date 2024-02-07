package com.yavin.mainscreenlib.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
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
fun BannerBlock(
    collection: UiCollectionWithWidgetData,
    itemWidth: Dp,
    onWidgetTap: (WidgetTapData) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(top = 16.dp, bottom = 2.dp)
    ) {
        collection.translatableFields[0].title?.let {
            Text(
                text = it,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MainTheme.typography.title20Bold,
                modifier = Modifier.padding(
                    start = MainDimens.blockPaddingDp,
                    end = MainDimens.blockPaddingDp
                )
            )
        }

        if (collection.widgets.size == 1) {
            BannerItem(
                collection.widgets[0],
                itemWidth,
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
                        BannerItem(it, itemWidth, onWidgetTap)
                    }
                }
            }
        }
    }
}

@Composable
private fun BannerItem(
    data: UiWidget,
    itemWidth: Dp,
    onWidgetTap: (WidgetTapData) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.width(itemWidth)
    ) {

        Card(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            ),
            shape = RoundedCornerShape(bigRound),
            modifier = Modifier
                .padding(top = 20.dp, bottom = 10.dp)

        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(radius = 300.dp)
                    ) {
//                        onWidgetTap.invoke(WidgetTapData(data))
                    }
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(92.dp)
                        .clip(RoundedCornerShape(bigRound))
                        .background(
                            color = Color(
                                parseColor(
                                    data.background_color,
                                    Color.White
                                )
                            )
                        )
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
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(start = 16.dp, top = 16.dp)
                                .align(Alignment.TopStart)
                        )
                    }

                    data.translatable_fields[0].stateBadge?.let {
                        Text(
                            text = it,// IT coin value0000
                            style = textStyleWithColor(
                                MainTheme.typography.data40Bold,
                                data.text_color,
                                MainColors.SpaceCadet
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(start = 16.dp, bottom = 12.dp)
                                .align(Alignment.BottomStart)
                        )
                    }
                }

                data.translatable_fields[0].subtitle?.let {
                    Text(
                        text = it,
                        style = textStyleWithColor(
                            MainTheme.typography.body16Regular,
                            data.text_color,
                            MainColors.SpaceCadet
                        ),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(start = 16.dp, top = 14.dp)
                    )
                }
            }
        }

        data.image_id?.let {
            AsyncImageWithPreview(
                url = it,
                Modifier
                    .size(112.dp)
                    .align(Alignment.TopEnd)
                    .padding(end = 16.dp)
            )
        }
    }
}