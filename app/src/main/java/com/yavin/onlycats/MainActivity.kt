package com.yavin.onlycats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import com.yavin.mainscreenlib.ScreenData
import com.yavin.mainscreenlib.screens.MainScreenContent
import com.yavin.onlycats.ui.theme.OnlyCatsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val screenData = ScreenData("main")
        setContent {
            OnlyCatsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column {
                        Text(
                            text = "Only Cats! ${screenData.name}"
                        )
                        MainScreenContent()
                    }
                }
            }
        }
    }
}

