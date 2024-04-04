package com.saitawngpha.tairightwords.navigation.destinations

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.saitawngpha.tairightwords.ui.screens.home.HomeScreen
import com.saitawngpha.tairightwords.ui.screens.RightWordViewModel
import com.saitawngpha.tairightwords.utils.Constants.HOME_SCREEN
import kotlinx.coroutines.delay
import kotlin.random.Random

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 28/02/2024.
 */

fun NavGraphBuilder.homeComposable(
    navController: NavHostController,
    viewModel: RightWordViewModel
) {
    composable(
        route = HOME_SCREEN,
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = {fullWidth -> -fullWidth },
                animationSpec = tween(
                    durationMillis = 300
                )
            )
        }
    ) {

        val jsonKeys  by viewModel.jsonDataKey.collectAsState()
        val randomText by viewModel.randomText.collectAsState()

        LaunchedEffect(key1 = randomText) {
                delay(1000L)
                viewModel.loadRandomProverb()
        }

        HomeScreen(
            navController = navController,
            jsonKeys = jsonKeys,
            randomText = randomText
        )
    }
}