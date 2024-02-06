package com.yavin.mainscreenlib.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import com.yavin.mainscreenlib.ui.theme.MainColors
import com.yavin.mainscreenlib.ui.theme.MainDimens.Companion.smallRound
import com.yavin.mainscreenlib.ui.theme.MainTheme
import com.yavin.mainscreenlib.ui.util.textStyleWithColor

@Composable
fun StateBadge(
    text: String,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = modifier
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(smallRound, 0.dp, 0.dp, smallRound))
                .background(color = MainColors.SpaceCadet)
        ) {
            Text(
                text = text,
                style = textStyleWithColor(
                    MainTheme.typography.caption11SemiBold,
                    Color.White
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(8.dp, 4.dp, 12.dp, 4.dp)
            )
        }
    }
}