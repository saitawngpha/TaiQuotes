package com.saitawngpha.tairightwords.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.saitawngpha.tairightwords.R

val ghkFont = FontFamily(
    Font(R.font.ghkkengtung)
)

val normalFont = FontFamily(
    Font(R.font.panglong_001)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
).defaultFontFamily(normalFont, ghkFont)


fun Typography.defaultFontFamily(normalFont: FontFamily, headingFont: FontFamily): Typography {
    return this.copy(
        displayLarge = this.displayLarge.copy(fontFamily = headingFont),
        displayMedium = this.displayMedium.copy(fontFamily = normalFont),
        displaySmall = this.displaySmall.copy(fontFamily = normalFont),
        headlineLarge = this.headlineLarge.copy(fontFamily = headingFont),
        headlineMedium = this.headlineMedium.copy(fontFamily = headingFont),
        headlineSmall = this.headlineSmall.copy(fontFamily = headingFont),
        titleLarge = this.titleLarge.copy(fontFamily = headingFont),
        titleMedium = this.titleMedium.copy(fontFamily = normalFont),
        titleSmall = this.titleSmall.copy(fontFamily = normalFont),
        bodyLarge = this.bodyLarge.copy(fontFamily = normalFont),
        bodyMedium = this.bodyMedium.copy(fontFamily = normalFont),
        bodySmall = this.bodySmall.copy(fontFamily = normalFont),
        labelLarge = this.labelLarge.copy(fontFamily = normalFont),
        labelMedium = this.labelMedium.copy(fontFamily = normalFont),
        labelSmall = this.labelSmall.copy(fontFamily = normalFont)
    )
}

