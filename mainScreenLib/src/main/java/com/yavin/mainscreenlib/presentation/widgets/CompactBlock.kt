package com.yavin.mainscreenlib.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.yavin.mainscreenlib.data.UserCatsRepositoryImpl
import com.yavin.mainscreenlib.data.WidgetTapData
import com.yavin.mainscreenlib.model.CollectionWidgetType
import com.yavin.mainscreenlib.model.TranslatableField
import com.yavin.mainscreenlib.model.UiCollectionWithWidgetData
import com.yavin.mainscreenlib.model.UiWidget
import com.yavin.mainscreenlib.model.WidgetActionType
import com.yavin.mainscreenlib.presentation.components.AsyncImageWithPreview
import com.yavin.mainscreenlib.ui.theme.MainColors
import com.yavin.mainscreenlib.ui.theme.MainDimens.Companion.bigRound
import com.yavin.mainscreenlib.ui.theme.MainDimens.Companion.blockPaddingDp
import com.yavin.mainscreenlib.ui.theme.MainDimens.Companion.itemPaddingDp
import com.yavin.mainscreenlib.ui.theme.MainTheme
import com.yavin.mainscreenlib.ui.util.parseColor
import com.yavin.mainscreenlib.ui.util.scrollEnabled
import com.yavin.mainscreenlib.ui.util.textStyleWithColor

@Composable
fun CompactBlock(
    collection: UiCollectionWithWidgetData,
    itemWidth: Dp,
    itemHeight: Dp,
    onWidgetTap: (WidgetTapData) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(start = blockPaddingDp, top = 16.dp, end = 0.dp, bottom = 12.dp)
    ) {
        collection.translatableFields[0].title?.let {
            Text(
                text = it,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MainTheme.typography.title20Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        Row(
            modifier = Modifier.scrollEnabled(false)
        ) {
            collection.widgets.forEach {
                ItemContent(
                    it,
                    itemWidth,
                    itemHeight,
                    onWidgetTap
                )
            }
        }
    }
}

@Composable
private fun ItemContent(
    data: UiWidget,
    itemWidth: Dp,
    itemHeight: Dp,
    onWidgetTap: (WidgetTapData) -> Unit
) {
    Box(
        modifier = Modifier
            .padding(end = itemPaddingDp)
            .width(itemWidth)
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
            AsyncImageWithPreview(it, Modifier.size(itemWidth, itemHeight))
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White.copy(0.5f))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp, 16.dp, 16.dp, 8.dp)
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
                        textAlign = TextAlign.Start,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                }
                data.translatable_fields[0].subtitle?.let {
                    Text(
                        text = it,
                        style = textStyleWithColor(
                            MainTheme.typography.body16Medium,
                            data.text_color,
                            MainColors.SpaceCadet
                        ),
                        textAlign = TextAlign.Start
                    )
                }
            }

        }
        data.translatable_fields[0].stateBadge?.let {
            StateBadge(text = it)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ItemContentPreview() {
    ItemContent(data = UiWidget(
        8,
        null,
        1,
        1,
        WidgetActionType.DEEPLINK,
        "deeplink to another screen", // TODO
        null,
        "#d3d3d3",
        UserCatsRepositoryImpl.CAT_IMG_URL,
        "#31304a",
        "#31304a",
        CollectionWidgetType.COMPACT,
        listOf(
            TranslatableField(8, "Рецепты для питомцев", "100 500+ рецептов", "badge")
        )
    ), itemWidth = 250.dp, itemHeight = 250.dp, onWidgetTap = {})
}

