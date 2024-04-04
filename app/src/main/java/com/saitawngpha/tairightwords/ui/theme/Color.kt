package com.saitawngpha.tairightwords.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val backgroundColor = Color(0xFF89CFF3)

val ColorScheme.screenBackground: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color.Black else backgroundColor

val ColorScheme.textColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color.White else Color.Black

val ColorScheme.textFieldColor: Color
    @Composable
    get() = if (isSystemInDarkTheme()) Color.Black.copy(alpha = 0.7f) else Color.Black.copy(alpha = 0.7f)