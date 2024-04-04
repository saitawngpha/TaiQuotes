package com.saitawngpha.tairightwords.navigation.destinations

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.saitawngpha.tairightwords.ui.screens.splash.SplashScreen
import com.saitawngpha.tairightwords.utils.Constants.SPLASH_SCREEN

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 11/03/2024.
 */

fun NavGraphBuilder.splashComposable(
    navigateToHomeScreen: () -> Unit
) {
    composable(
        route = SPLASH_SCREEN,
        exitTransition = {
            slideOutVertically(
                targetOffsetY = { fullHeight -> -fullHeight },
                animationSpec = tween(1000)
            )
        }
    ) {
        SplashScreen(
            navigateToHomeScreen = navigateToHomeScreen
        )
    }
}