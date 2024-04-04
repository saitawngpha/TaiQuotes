package com.saitawngpha.tairightwords

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.saitawngpha.tairightwords.model.BottomNavigationItem
import com.saitawngpha.tairightwords.navigation.SetUpNavController
import com.saitawngpha.tairightwords.ui.screens.RightWordViewModel
import com.saitawngpha.tairightwords.ui.theme.TaiRightWordsTheme
import com.saitawngpha.tairightwords.utils.Constants.ABOUT_SCREEN
import com.saitawngpha.tairightwords.utils.Constants.HOME_SCREEN
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: RightWordViewModel by viewModels ()
    //private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaiRightWordsTheme {
                val rootNavController = rememberNavController()
                val navBackStackEntry by rootNavController.currentBackStackEntryAsState()
                Scaffold(
                    bottomBar = {
                        NavigationBar(
                            modifier = Modifier
                                .background(Color.White.copy(alpha = 0.5f))
                        ) {
                            items.forEach { item ->
                                val isSelected = item.route ==
                                        navBackStackEntry?.destination?.route
                                NavigationBarItem(
                                    selected = isSelected,
                                    label = { Text(text = item.title)},
                                    onClick = {
                                        rootNavController.navigate(route = item.route) {
                                            popUpTo(rootNavController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                                            contentDescription = item.title
                                        )
                                    },
                                )
                            }
                        }
                    }
                ) { paddingValue ->
                    SetUpNavController(viewModel = viewModel, navController = rootNavController)
                }
            }
        }
    }
}

val items = listOf(
    BottomNavigationItem(
        title = "Home",
        route = HOME_SCREEN,
        unselectedIcon = Icons.Filled.Home,
        selectedIcon = Icons.Outlined.Home
    ),

    BottomNavigationItem(
        title = "Info",
        route = ABOUT_SCREEN,
        unselectedIcon = Icons.Filled.Info,
        selectedIcon = Icons.Outlined.Info
    )
)