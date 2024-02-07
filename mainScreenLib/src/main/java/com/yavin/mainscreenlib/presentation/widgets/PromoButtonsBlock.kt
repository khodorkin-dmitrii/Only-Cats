package com.yavin.mainscreenlib.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.yavin.mainscreenlib.data.WidgetTapData
import com.yavin.mainscreenlib.model.UiCollectionWithWidgetData
import com.yavin.mainscreenlib.ui.theme.MainDimens.Companion.smallRound
import com.yavin.mainscreenlib.ui.theme.MainTheme
import com.yavin.mainscreenlib.ui.util.parseColor
import com.yavin.mainscreenlib.ui.util.textStyleWithColor

@Composable
fun PromoButtonsBlock(
    collection: UiCollectionWithWidgetData,
    onWidgetTap: (WidgetTapData) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(start = 24.dp, top = 16.dp, end = 24.dp, bottom = 12.dp)
    ) {
        collection.widgets.forEach { data ->

            Box(
                contentAlignment = Alignment.CenterStart,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
                    .height(44.dp)
                    .clip(RoundedCornerShape(smallRound))
                    .background(color = Color(parseColor(data.background_color, Color.Black)))
                    .clickable {
//                        onWidgetTap.invoke(WidgetTapData(data))
                    }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 18.dp)
                ) {
                    data.image_id?.let {
                        // TODO add image support
//                        SelfDownloadImage(
//                            it, 22.dp, 22.dp,
//                            colorFilter = ColorFilter.tint(
//                                Color(parseColor(data.text_color))
//                            )
//                        )
                    }

                    data.translatable_fields[0].title?.let {
                        Text(
                            text = it,
                            style = textStyleWithColor(
                                MainTheme.typography.body16Medium,
                                Color(parseColor(data.text_color))
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(start = 18.dp),
                        )
                    }
                }
            }
        }
    }
}