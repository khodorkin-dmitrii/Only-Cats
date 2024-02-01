package com.yavin.mainscreenlib.presentation.widgets

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.yavin.mainscreenlib.R
import com.yavin.mainscreenlib.model.UserCat
import com.yavin.mainscreenlib.presentation.components.AsyncImageWithPreview

val itemSize = 80.dp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CatsRow(
    userCats: List<UserCat>,
    onCatTap: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .background(color = Color.White)
            .fillMaxWidth()
    ) {
        //region User Devices Title
        Box(
            modifier = Modifier
                .height(56.dp)
                .padding(horizontal = 24.dp)
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(radius = 300.dp)
                ) {
//                    onUserCatsTap.invoke()
                }) {
            Text(
                text = stringResource(R.string.main_my_cats, userCats.count()),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .align(Alignment.CenterStart)
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_chevron_right_24dp),
                tint = Color.DarkGray,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
            )

        }
        //endregion
        //region User Cats Row
        LazyRow(
            contentPadding = PaddingValues(start = 24.dp, end = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(userCats, key = { it.id }) {
                UserCatSmallItem(
                    data = it,
                    onUserCatTap = onCatTap,
                    Modifier.animateItemPlacement(
                        animationSpec = spring(
                            stiffness = Spring.StiffnessMediumLow,
                            visibilityThreshold = IntOffset(100, 0)
                        )
                    )
                )
            }
        }
        //endregion
    }
}

@Composable
private fun UserCatSmallItem(
    data: UserCat,
    onUserCatTap: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(100.dp)
    ) {
        Box(
            modifier = Modifier
                .size(itemSize)
                .clip(CircleShape)
                .background(color = Color.LightGray)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(radius = 300.dp)
                ) {
                    onUserCatTap.invoke(data.id)
                }) {
            AsyncImageWithPreview(data.imageUrl,
                Modifier
                    .size(102.dp)
                    .clip(CircleShape))
        }
        Column(
            modifier = modifier
                .fillMaxWidth()
        ) {
            Text(
                text = data.name,
                maxLines = 1,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
            )
        }
    }
}

@Preview(widthDp = 300)
@Composable
fun CatsRowPreview() {
    CatsRow(userCats = mockCats, onCatTap = {})
}

private val mockCats = listOf(
    UserCat(
        "0",
        "Stephan",
        "url"
    ),
    UserCat(
        "1",
        "Shteffie",
        "url"
    )
)