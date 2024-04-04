package com.saitawngpha.tairightwords.ui.screens.home

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavHostController
import com.robohorse.gpversionchecker.GPVersionChecker
import com.saitawngpha.tairightwords.R
import com.saitawngpha.tairightwords.navigation.Screens
import com.saitawngpha.tairightwords.ui.theme.screenBackground
import com.saitawngpha.tairightwords.ui.theme.textColor
import com.saitawngpha.tairightwords.ui.theme.textFieldColor
import dev.jianastrero.compose_permissions.composePermission

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 28/02/2024.
 */

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    jsonKeys: List<String>,
    navController: NavHostController,
    randomText: String
) {
    var searchText by remember { mutableStateOf("") }
    var isPermission by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val WINDOW_PERMISSION = 123
    val caPermission = composePermission(android.Manifest.permission.SYSTEM_ALERT_WINDOW)

    LaunchedEffect(isPermission) {
        if(!caPermission.isGranted){
                if (!Settings.canDrawOverlays(context)) {
                    // You don't have permission
                    val intent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:${context.packageName}"))
                    } else {
                        null
                    }
                    (context as? Activity)?.startActivityForResult(intent, WINDOW_PERMISSION)
                } else {
                    // Do as per your logic
                }
            isPermission = false
        }else{
            isPermission = true
        }
    }

    LaunchedEffect(Unit ){
        //check for new version
        GPVersionChecker.Builder(context as Activity).create()
    }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.screenBackground)
        ) {
            Column {

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    text = "ၵႂၢမ်းၵပ်းထုၵ်ႇတႆး",
                    textAlign = TextAlign.Center,
                    fontStyle = FontStyle.Normal,
                    color = MaterialTheme.colorScheme.textColor,
                    fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 20.dp)
                        .clip(RoundedCornerShape(27.dp))
                        .background(Color.White.copy(alpha = 0.7f)),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    TextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(7f),
                        textStyle = TextStyle(MaterialTheme.colorScheme.textFieldColor),
                        value = searchText,
                        onValueChange = {searchText = it},
                        placeholder = {
                            Text(
                                text = "ၶူၼ်ႉႁႃႈတီႈၼႆႈၶႃႈ…",
                                color = MaterialTheme.colorScheme.textFieldColor
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            cursorColor = MaterialTheme.colorScheme.textFieldColor,
                            focusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            containerColor = Color.Transparent
                        ),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Search
                        )
                    )

                    Icon(
                        modifier = Modifier
                            .clickable {
                                if (searchText.isNotEmpty())
                                    searchText = ""
                            }
                            .alpha(if (searchText.isNotEmpty()) 1f else 0f)
                            .size(15.dp)
                            .weight(1f),
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear Text"
                    )
                }

                Spacer(modifier = Modifier
                    .height(16.dp))

                if (searchText.isEmpty()) {
                    Column {
                        HomeBanner(
                            randomText = randomText
                        )
                    }
                } else {
                    LazyColumn {
                        itemsIndexed(jsonKeys){ index, keyString ->

                                HomeItem(taiRWModel = keyString, onClicked = {
                                    //navigateToDetails(it)
                                    Screens(navController = navController).details(it)
                                })

                        }
                    }
                }
            }
        }
    }

@Composable
fun HomeItem(
    taiRWModel: String,
    onClicked: (String) -> Unit,
) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 10.dp, end = 10.dp, bottom = 10.dp, top = 5.dp)
        .clip(RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.CenterStart
    ){

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(start = 40.dp)
                .background(Color.White.copy(alpha = 0.6f))
                .clickable { onClicked(taiRWModel) },
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                modifier = Modifier
                    .padding(start = 15.dp)
                    .weight(7f),
                text = "ၵႂၢမ်းၵပ်းထုၵ်ႇတႆး",
                maxLines = 2,
            )

            Icon(
                modifier = Modifier
                    .size(20.dp)
                    .weight(1f),
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Arrow Right"
            )
        }

        Canvas(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .shadow(1.dp)
        ){
            drawCircle(
                color = Color.White
            )
        }

        Text(
            modifier = Modifier
                .size(50.dp)
                .offset(y = 9.dp),
            text = taiRWModel,
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            fontFamily = MaterialTheme.typography.titleLarge.fontFamily
        )
    }

}

@Composable
fun HomeBanner(
    randomText: String
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = if (isSystemInDarkTheme()) painterResource(id = R.drawable.intro_board_dark)
            else
                painterResource(id = R.drawable.intro_board),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(297.dp)
                .clip(MaterialTheme.shapes.medium)
        )

        Text(
            modifier = Modifier
                .padding(all = 20.dp),
            text = randomText,
            textAlign = TextAlign.Center,
            fontStyle = FontStyle.Normal,
            fontSize = MaterialTheme.typography.headlineLarge.fontSize,
            fontFamily = MaterialTheme.typography.titleLarge.fontFamily,
            color = Color.Black,
            maxLines = 3,
            lineHeight = 48.sp
        )
    }
}

@Preview
@Composable
fun HomeItemsPreview() {
    //HomeBanner()
}