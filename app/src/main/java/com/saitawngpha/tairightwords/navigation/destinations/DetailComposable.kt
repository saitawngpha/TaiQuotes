package com.saitawngpha.tairightwords.navigation.destinations

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.saitawngpha.tairightwords.ui.screens.RightWordViewModel
import com.saitawngpha.tairightwords.ui.screens.details.DetailsScreen
import com.saitawngpha.tairightwords.utils.Constants.DETAILS_ARGUMENT_KEY
import com.saitawngpha.tairightwords.utils.Constants.DETAILS_SCREEN

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 28/02/2024.
 */

fun NavGraphBuilder.detailsComposable(
    navController: NavHostController,
    viewModel: RightWordViewModel
){

    composable(
        route = DETAILS_SCREEN,
        arguments = listOf(navArgument(name = DETAILS_ARGUMENT_KEY){
            type = NavType.StringType
        }),
        enterTransition = {
            slideInHorizontally(
                initialOffsetX = {fullWidth -> -fullWidth },
                animationSpec = tween(
                    durationMillis = 300
                )
            )
        }
    ) { navBackStackEntry ->

        val searchKey = navBackStackEntry.arguments!!.getString(DETAILS_ARGUMENT_KEY)

        LaunchedEffect(key1 = searchKey) {
            searchKey?.let { viewModel.loadProverbs(searchText = it) }
        }
       // val jsonProverbs = viewModel.jsonProverbs.collectAsState()

        DetailsScreen(
            navController = navController,
            keyText = searchKey!!,
            viewModel = viewModel
        )
    }

}