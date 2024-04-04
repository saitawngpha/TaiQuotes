package com.saitawngpha.tairightwords.navigation

import androidx.navigation.NavHostController
import com.saitawngpha.tairightwords.utils.Constants.HOME_SCREEN
import com.saitawngpha.tairightwords.utils.Constants.SPLASH_SCREEN

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 07/03/2024.
 */
class Screens(
    navController: NavHostController
) {

    val splash: () -> Unit =  {
        navController.navigate(route = HOME_SCREEN) {
            popUpTo(route = SPLASH_SCREEN) {
                inclusive = true
            }
        }
    }

    val details: (String) -> Unit = {taskId ->
        navController.navigate(route = "details_screen/$taskId")
    }
}