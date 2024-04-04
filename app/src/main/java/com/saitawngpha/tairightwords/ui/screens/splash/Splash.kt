package com.saitawngpha.tairightwords.ui.screens.splash

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.saitawngpha.tairightwords.R
import com.saitawngpha.tairightwords.ui.theme.LOGO_HEIGHT
import com.saitawngpha.tairightwords.ui.theme.screenBackground
import com.saitawngpha.tairightwords.utils.Constants.SPLASH_SCREEN_DELAY
import kotlinx.coroutines.delay

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 11/03/2024.
 */

@Composable
fun SplashScreen(
    navigateToHomeScreen: () -> Unit
) {

    var startAnimation by remember { mutableStateOf(false) }
    val offsetState by animateDpAsState(
        targetValue = if(startAnimation) 0.dp else 100.dp,
        animationSpec = tween(
            durationMillis = 1000
        )
    )

    val alphaState by animateFloatAsState(
        targetValue = if(startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1000
        )
    )

    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(SPLASH_SCREEN_DELAY)
        navigateToHomeScreen()
    }

    Splash(
        offsetState = offsetState,
        alphaState = alphaState
    )
}

@Composable
fun Splash(
    offsetState: Dp,
    alphaState: Float
) {

    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.screenBackground),
        contentAlignment = Alignment.Center
    ){
        Image(
            modifier = Modifier
                .size(LOGO_HEIGHT)
                .offset(y = offsetState)
                .alpha(alpha = alphaState),
            painter = painterResource(id = getLogo()),
            contentDescription = "Logo"
        )

    }
}

@Composable
fun getLogo(): Int {
    return if(isSystemInDarkTheme())
        R.drawable.logo
    else
        R.drawable.logo
}