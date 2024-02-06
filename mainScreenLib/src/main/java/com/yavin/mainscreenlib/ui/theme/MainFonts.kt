package com.yavin.mainscreenlib.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
data class ExtendedTypography(
    val headline26: TextStyle,
    val title20Bold: TextStyle,
    val title20Medium: TextStyle,
    val body16Medium: TextStyle,
    val body16Regular: TextStyle,
    val body14Medium: TextStyle,
    val body14Regular: TextStyle,
    val caption12Medium: TextStyle,
    val caption12Regular: TextStyle,
    val caption11Medium: TextStyle,
    val caption11Regular: TextStyle,
    val caption11SemiBold: TextStyle,
    val data40Bold: TextStyle,
    val button: TextStyle
)

val LocalExtendedTypography = staticCompositionLocalOf {
    ExtendedTypography(
        headline26 = TextStyle.Default,
        title20Bold = TextStyle.Default,
        title20Medium = TextStyle.Default,
        body16Medium = TextStyle.Default,
        body16Regular = TextStyle.Default,
        body14Medium = TextStyle.Default,
        body14Regular = TextStyle.Default,
        caption12Medium = TextStyle.Default,
        caption12Regular = TextStyle.Default,
        caption11Medium = TextStyle.Default,
        caption11Regular = TextStyle.Default,
        caption11SemiBold = TextStyle.Default,
        data40Bold = TextStyle.Default,
        button = TextStyle.Default,
    )
}

@Composable
fun MainTheme(
    content: @Composable () -> Unit
) {
    val extendedTypography = ExtendedTypography(
        headline26 = TextStyle(
            fontWeight = FontWeight.W700,
            fontStyle = FontStyle.Normal,
            fontSize = 26.sp,
            lineHeight = 32.sp,
            letterSpacing = 0.4.sp,
            color = MainColors.SpaceCadet
        ),
        title20Bold = TextStyle(
            fontWeight = FontWeight.W700,
            fontStyle = FontStyle.Normal,
            fontSize = 20.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.4.sp,
            color = MainColors.SpaceCadet
        ),
        title20Medium = TextStyle(
            fontWeight = FontWeight.W500,
            fontStyle = FontStyle.Normal,
            fontSize = 20.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.4.sp,
            color = MainColors.SpaceCadet
        ),
        body16Medium = TextStyle(
            fontWeight = FontWeight.W500,
            fontStyle = FontStyle.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp,
            color = MainColors.SpaceCadet
        ),
        body16Regular = TextStyle(
            fontWeight = FontWeight.W400,
            fontStyle = FontStyle.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp,
            color = MainColors.SpaceCadet
        ),
        body14Medium = TextStyle(
            fontWeight = FontWeight.W500,
            fontStyle = FontStyle.Normal,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.25.sp,
            color = MainColors.SpaceCadet
        ),
        body14Regular = TextStyle(
            fontWeight = FontWeight.W400,
            fontStyle = FontStyle.Normal,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.25.sp,
            color = MainColors.SpaceCadet
        ),
        caption12Medium = TextStyle(
            fontWeight = FontWeight.W500,
            fontStyle = FontStyle.Normal,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.4.sp,
            color = MainColors.SpaceCadet
        ),
        caption12Regular = TextStyle(
            fontWeight = FontWeight.W400,
            fontStyle = FontStyle.Normal,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.4.sp,
            color = MainColors.SpaceCadet
        ),
        caption11Medium = TextStyle(
            fontWeight = FontWeight.W500,
            fontStyle = FontStyle.Normal,
            fontSize = 11.sp,
            lineHeight = 13.sp,
            letterSpacing = 0.4.sp,
            color = MainColors.SpaceCadet
        ),
        caption11Regular = TextStyle(
            fontWeight = FontWeight.W400,
            fontStyle = FontStyle.Normal,
            fontSize = 11.sp,
            lineHeight = 13.sp,
            letterSpacing = 0.4.sp,
            color = MainColors.SpaceCadet
        ),
        caption11SemiBold = TextStyle(
            fontWeight = FontWeight.W600,
            fontStyle = FontStyle.Normal,
            fontSize = 11.sp,
            lineHeight = 13.sp,
            letterSpacing = 0.7.sp,
            color = MainColors.SpaceCadet
        ),
        data40Bold = TextStyle(
            fontWeight = FontWeight.W600,
            fontStyle = FontStyle.Normal,
            fontSize = 40.sp,
            lineHeight = 13.sp,
            letterSpacing = 0.49.sp,
            color = MainColors.SpaceCadet
        ),
        button = TextStyle(
            fontWeight = FontWeight.W500,
            fontStyle = FontStyle.Normal,
            fontSize = 14.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.3.sp
        ),
    )
    CompositionLocalProvider(LocalExtendedTypography provides extendedTypography) {
        MaterialTheme(
            content = content
        )
    }
}

object MainTheme {
    val typography: ExtendedTypography
        @Composable
        get() = LocalExtendedTypography.current
}

//private val LightColors = lightColors(
//    primary = MainColors.CarolinaBlue,
//    error = MainColors.Red,
//)