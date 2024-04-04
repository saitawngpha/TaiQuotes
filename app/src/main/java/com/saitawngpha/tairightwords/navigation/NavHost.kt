package com.saitawngpha.tairightwords.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.saitawngpha.tairightwords.navigation.destinations.aboutComposable
import com.saitawngpha.tairightwords.navigation.destinations.detailsComposable
import com.saitawngpha.tairightwords.navigation.destinations.homeComposable
import com.saitawngpha.tairightwords.navigation.destinations.splashComposable
import com.saitawngpha.tairightwords.ui.screens.RightWordViewModel
import com.saitawngpha.tairightwords.utils.Constants.SPLASH_SCREEN

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 28/02/2024.
 */

@Composable
fun SetUpNavController(
    viewModel: RightWordViewModel,
    navController: NavHostController
) {

    val screen = remember(navController) {
        Screens(navController = navController)
    }

   NavHost(
       navController = navController,
       startDestination = SPLASH_SCREEN
   ) {

       splashComposable (
           navigateToHomeScreen = screen.splash
       )

       homeComposable(
           navController = navController,
           viewModel = viewModel
       )

       detailsComposable(
           navController = navController,
           viewModel = viewModel
       )

       aboutComposable()
   }
}