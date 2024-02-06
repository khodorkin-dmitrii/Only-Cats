package com.yavin.mainscreenlib.ui.util

import androidx.annotation.ColorInt
import androidx.annotation.Size
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.TextStyle

fun Modifier.scrollEnabled(
    enabled: Boolean,
) = nestedScroll(
    connection = object : NestedScrollConnection {
        override fun onPreScroll(
            available: Offset,
            source: NestedScrollSource
        ): Offset = if (enabled) Offset.Zero else available
    }
)

//fun Color.fromStringHex(hex: String): Color {
//    return Color(android.graphics.Color.parseColor(hex))
//}

@ColorInt
fun parseColor(@Size(min = 1) colorString: String?, defaultColor: Color = Color.Black): Int {
    if (colorString == null) return defaultColor.toArgb()


    if (colorString[0] == '#') { // Use a long to avoid rollovers on #ffXXXXXX
        var color = colorString.substring(1).toLong(16)
        if (colorString.length == 7) { // Set the alpha value
            color = color or -0x1000000
        } else require(colorString.length == 9) { "Unknown color" }
        return color.toInt()
    }
    throw IllegalArgumentException("Unknown color")
}

fun textStyleWithColor(
    style: TextStyle,
    contentColor: String?,
    defaultColor: Color = Color.Black
): TextStyle {

    val targetColor = if (contentColor != null)
        Color(parseColor(contentColor)) else defaultColor

    return if (style.color != targetColor) {
        style.copy(color = targetColor)
    } else {
        style
    }
}

fun textStyleWithColor(
    style: TextStyle,
    contentColor: Color?,
    defaultColor: Color = Color.Black
): TextStyle {

    val targetColor = contentColor ?: defaultColor

    return if (style.color != targetColor) {
        style.copy(color = targetColor)
    } else {
        style
    }
}