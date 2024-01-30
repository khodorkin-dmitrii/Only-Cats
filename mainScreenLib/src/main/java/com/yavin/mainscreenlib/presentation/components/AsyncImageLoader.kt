package com.yavin.mainscreenlib.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.FilterQuality
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.yavin.mainscreenlib.R

@Composable
fun AsyncImageWithPreview(
    url: String?, modifier: Modifier = Modifier
) {
    val contentDescription: String = stringResource(R.string.content_description_beer_image)
    if (!LocalInspectionMode.current) { // not in preview move
        var loadingState by remember { mutableStateOf(true) }

        Box(modifier = modifier) {
            SubcomposeAsyncImage(
                model = url,
                onLoading = {
                    loadingState = true
                },
                onSuccess = {
                    loadingState = false
                },
                onError = {
                    loadingState = false
                },
                contentDescription = contentDescription,
                filterQuality = FilterQuality.Low,
                modifier = modifier
            )

            AnimatedVisibility(
                visible = loadingState, exit = fadeOut(animationSpec = tween(300))
            ) {
                Shimmer(
                    baseColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
        }
    } else { // for preview mode ONLY
        AsyncImage(
            model = url,
            placeholder = debugPlaceholder(R.drawable.img_preview),
            contentDescription = contentDescription,
            filterQuality = FilterQuality.Low,
            modifier = modifier
        )
    }
}