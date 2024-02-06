package com.yavin.mainscreenlib.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.yavin.mainscreenlib.ui.theme.MainDimens.Companion.verySmallRound
import com.yavin.mainscreenlib.ui.util.scrollEnabled

@Composable
fun DynamicContentSkeleton(
    fullScreenItemWidth: Dp,
    halfScreenItemWidth: Dp,
    itemHeight: Dp
) {
    Column(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxWidth()
    ) {
        CompatSkeleton(halfScreenItemWidth, itemHeight)
        BannerSkeleton()
        FullWidthSkeleton(fullScreenItemWidth, itemHeight)
        PromoButtonsSkeleton()
    }
}

@Composable
private fun CompatSkeleton(
    itemWidth: Dp,
    itemHeight: Dp
) {
    Shimmer(
        Modifier
            .size(208.dp, 24.dp)
            .padding(start = 24.dp, bottom = 16.dp, top = 16.dp)
            .clip(RoundedCornerShape(verySmallRound))
    )

    LazyRow(
        contentPadding = PaddingValues(start = 24.dp, bottom = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.scrollEnabled(false)
    ) {
        repeat(2) {
            item {
                Shimmer(
                    Modifier
                        .size(itemWidth, itemHeight)
                        .clip(RoundedCornerShape(verySmallRound))
                )
            }
        }
    }
}

@Composable
private fun BannerSkeleton() {
    Shimmer(
        Modifier
            .size(135.dp, 24.dp)
            .padding(start = 24.dp, bottom = 16.dp, top = 16.dp)
            .clip(RoundedCornerShape(verySmallRound))
    )
    Shimmer(
        Modifier
            .size(LocalConfiguration.current.screenWidthDp.dp, 140.dp)
            .padding(start = 24.dp, bottom = 16.dp, end = 24.dp)
            .clip(RoundedCornerShape(verySmallRound))
    )
}

@Composable
private fun FullWidthSkeleton(
    itemWidth: Dp,
    itemHeight: Dp
) {
    Shimmer(
        Modifier
            .size(173.dp, 24.dp)
            .padding(start = 24.dp, bottom = 16.dp, top = 16.dp)
            .clip(RoundedCornerShape(verySmallRound))
    )
    Shimmer(
        Modifier
            .size(itemWidth, itemHeight)
            .padding(start = 24.dp, bottom = 16.dp, end = 24.dp)
            .clip(RoundedCornerShape(verySmallRound))
    )
}

@Composable
private fun PromoButtonsSkeleton() {
    Shimmer(
        Modifier
            .size(LocalConfiguration.current.screenWidthDp.dp, 44.dp)
            .padding(start = 24.dp, bottom = 8.dp, end = 24.dp)
            .clip(RoundedCornerShape(verySmallRound))
    )
    Shimmer(
        Modifier
            .size(LocalConfiguration.current.screenWidthDp.dp, 44.dp)
            .padding(start = 24.dp, bottom = 16.dp, end = 24.dp)
            .clip(RoundedCornerShape(verySmallRound))
    )
}