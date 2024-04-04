package com.saitawngpha.tairightwords.navigation.destinations

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.saitawngpha.tairightwords.ui.screens.about.AboutScreen
import com.saitawngpha.tairightwords.utils.Constants.ABOUT_SCREEN

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 09/03/2024.
 */

fun NavGraphBuilder.aboutComposable() {
    composable(
        route = ABOUT_SCREEN,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = {fullWidth -> -fullWidth },
                animationSpec = tween(
                    durationMillis = 300
                )
            )
        }
    ){
        AboutScreen()
    }
}