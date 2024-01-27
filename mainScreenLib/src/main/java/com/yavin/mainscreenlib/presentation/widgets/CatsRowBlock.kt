package com.yavin.mainscreenlib.presentation.widgets

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.yavin.mainscreenlib.data.UserCat

val itemSize = 80.dp
val textBlockHeight = 64.dp

@Composable
fun CatsRow(
    userCats: List<UserCat>,
    onCatTap: (String) -> Unit,
) {
    Row {
        userCats.forEach { uCat ->
            Text(text = uCat.name)
        }
    }
}

@Preview
@Composable
fun CatsRowPreview() {
    CatsRow(userCats = mockCats, onCatTap = {})
}

private val mockCats = listOf(
    UserCat(
        "0",
        "Cat0",
        "http://placekitten.com/200/200",
        true
    ),
    UserCat(
        "1",
        "Cat1",
        "http://placekitten.com/200/200",
        true
    )
)