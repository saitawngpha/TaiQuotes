package com.saitawngpha.tairightwords.model

import androidx.compose.ui.graphics.vector.ImageVector

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 09/03/2024.
 */

data class BottomNavigationItem(
    val title: String,
    val route: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
)