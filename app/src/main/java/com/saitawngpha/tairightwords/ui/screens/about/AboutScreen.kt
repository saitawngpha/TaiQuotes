package com.saitawngpha.tairightwords.ui.screens.about

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.widget.ScrollView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.saitawngpha.tairightwords.BuildConfig
import com.saitawngpha.tairightwords.R
import com.saitawngpha.tairightwords.ui.theme.screenBackground
import com.saitawngpha.tairightwords.ui.theme.textColor
import com.saitawngpha.tairightwords.utils.Helper

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 09/03/2024.
 */

@Composable
fun AboutScreen() {

    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.screenBackground)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "About App",
                textAlign = TextAlign.Center,
                fontStyle = FontStyle.Normal,
                color = MaterialTheme.colorScheme.textColor,
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                fontFamily = MaterialTheme.typography.titleLarge.fontFamily
            )

            Spacer(modifier = Modifier.height(20.dp))

            Image(
                modifier = Modifier
                    .size(100.dp)
                    .shadow(2.dp, RoundedCornerShape(20.dp)),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo Image"
            )

            Spacer(modifier = Modifier.height(10.dp))

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
            )
            Text(text = stringResource(id = R.string.app_name))
            Text(text = "Version ${getVerstion(context)}")

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                modifier = Modifier
                    .padding(10.dp),
                text = stringResource(id = R.string.des_about_app)
            )

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
            )

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                horizontalArrangement = Arrangement.Start
            ){
                Text(
                    text = "Credit Json Data: ",
                    fontWeight = FontWeight.Bold
                    )
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = "Saing Hmaine Tun")
            }
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                horizontalArrangement = Arrangement.Start
            ){
                Text(
                    text = "Credit Voice: ",
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = "NLLB FB")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
            )

            Text(
                text = "Developer",
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.Start
            ){
                Text(
                    text = "Developer: ",
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = "PL Dev")
            }

            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.Start
            ){
                Text(
                    text = "Email: ",
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(20.dp))
                Text(text = "pldevapp@pm.me")
            }

            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, bottom = 10.dp)
            )

            Row (
                modifier = Modifier
                    .clickable {
                        Helper.openPlayStoreApp(
                            context = context,
                            context.packageName
                        )
                    }
                    .fillMaxWidth()
                    .padding(10.dp)
                    .background(Color.White.copy(alpha = 0.4f))
                    .clip(RoundedCornerShape(20.dp))
                    ,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Image(
                    modifier = Modifier
                        .size(50.dp)
                        .weight(2f),
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Play Icon"
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(3f)
                    ,
                    text = "More apps",
                    textAlign = TextAlign.Center
                )

                Image(
                    modifier = Modifier
                        .weight(1f),
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "Arrow Right"
                )
            }

        }
    }
}

private fun getVerstion(context: Context): String{
    var version = ""
    try {
        val pInfo: PackageInfo =
            context.packageManager.getPackageInfo(context.packageName, 0)
        version = pInfo.versionName
    } catch (e: PackageManager.NameNotFoundException) {
        e.printStackTrace()
    }

    return version
}

@Composable
@Preview
fun AboutPreview() {
    AboutScreen()
}