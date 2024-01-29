package com.yavin.mainscreenlib.presentation.screens

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.yavin.mainscreenlib.data.UserCat
import com.yavin.mainscreenlib.presentation.UserCatsViewModel
import com.yavin.mainscreenlib.presentation.widgets.CatsRow

@Composable
fun MainScreenContent(
//    state: UserCatsModelState
) {
    Text(text = "Main Screen Content")
    val scrollState = rememberScrollState()

    val userCatsViewModel = hiltViewModel<UserCatsViewModel>()
    val userCastState = userCatsViewModel.uiState.collectAsState()
    Log.d("CATS_STATE", userCastState.value.state.name)
    // TODO map loading to skeleton end error case

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .animateContentSize()
    ) {
        CatsRowBlock(
            userCastState.value.userCasts,
            onCatTap = {}
        )
    }
}

@Composable
private fun CatsRowBlock(
    userCats: List<UserCat>,
    onCatTap: (String) -> Unit,
) {
    CatsRow(userCats = userCats, onCatTap = onCatTap)
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    MainScreenContent()
}