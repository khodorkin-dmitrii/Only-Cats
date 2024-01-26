package com.yavin.mainscreenlib.presentation.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.yavin.mainscreenlib.presentation.UserCatsViewModel
import com.yavin.mainscreenlib.presentation.widgets.CatsRow

@Composable
fun MainScreenContent() {
    Text(text = "Main Screen Content")
    val scrollState = rememberScrollState()

    val userCatsViewModel = hiltViewModel<UserCatsViewModel>()
//    val userCast = userCatsViewModel.getUserCasts()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .animateContentSize()
    ) {
        CatsRowBlock(onCatTap = {})
    }
}

@Composable
private fun CatsRowBlock(
    onCatTap: (String) -> Unit,
) {
    CatsRow(onCatTap = onCatTap)
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    MainScreenContent()
}