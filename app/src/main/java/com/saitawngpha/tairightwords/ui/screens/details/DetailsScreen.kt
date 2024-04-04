package com.saitawngpha.tairightwords.ui.screens.details

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.navigation.NavHostController
import com.saitawngpha.tairightwords.R
import com.saitawngpha.tairightwords.model.TaiRWModel
import com.saitawngpha.tairightwords.ui.screens.RightWordViewModel
import com.saitawngpha.tairightwords.ui.theme.screenBackground
import com.saitawngpha.tairightwords.ui.theme.textColor
import com.saitawngpha.tairightwords.ui.theme.textFieldColor
import com.saitawngpha.tairightwords.utils.Helper.addVowelsToConsonants

/**
 * @Author: ၸၢႆးတွင်ႉၾႃႉ
 * @Date: 05/03/2024.
 */

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun DetailsScreen(
    navController: NavHostController,
    keyText: String,
    viewModel: RightWordViewModel
) {
    val isLoading by viewModel.isLoading.collectAsState()
    var isSpeakClicked by remember { mutableStateOf(false) }
    var audio by remember { mutableStateOf("") }
    val context = LocalContext.current

    LaunchedEffect(isLoading) {
        viewModel.nameValue.collect() { audioUrl ->
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back Arrow",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                title = {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = "ၵႂၢမ်းၵပ်းထုၵ်ႇတွၼ်ႈ \"$keyText\"",
                    textAlign = TextAlign.Center,
                    fontStyle = FontStyle.Normal,
                    color = MaterialTheme.colorScheme.textColor,
                    fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                    fontFamily = MaterialTheme.typography.titleLarge.fontFamily
                )
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MaterialTheme.colorScheme.screenBackground,
                titleContentColor = MaterialTheme.colorScheme.onPrimary,
                navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                actionIconContentColor = MaterialTheme.colorScheme.onSecondary
            )
            )
        }

    ) { paddingValues ->
        Box(modifier = Modifier
            .padding(paddingValues = paddingValues)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.screenBackground)
        ){

            val textSearch by viewModel.searchText.collectAsState()
            val jsonSearchList by viewModel.jsonSearchList.collectAsState()

            Column {
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
                        value = textSearch,
                        onValueChange = viewModel::onSearchTextChange,
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
                                if (textSearch.isNotEmpty())
                                    viewModel.clearSearchText()
                            }
                            .alpha(if (textSearch.isNotEmpty()) 1f else 0f)
                            .size(15.dp)
                            .weight(1f),
                        imageVector = Icons.Default.Clear,
                        contentDescription = "Clear Text"
                    )
                }

                Spacer(modifier = Modifier
                    .height(16.dp))

                LazyColumn {
                    itemsIndexed(jsonSearchList) {index, item ->
                        DetailListItem(
                            searchText = keyText,
                            jsonProverb = item,
                            onSpeakClicked = {
                                isSpeakClicked = true
                            }
                        )
                    }
                }
            }

        }

        if(isLoading) {
            Box (
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.4f)),
                contentAlignment = Alignment.Center,
            ){
                Column {
                    CircularProgressIndicator()
                    Text(text = "Loading...", color = Color.White)
                }
            }
        }

        if (isSpeakClicked){

        }
    }

}

@Composable
fun DetailListItem(
    searchText: String,
    jsonProverb: TaiRWModel.AllProverb.Proverb,
    onSpeakClicked: (String) -> Unit
) {

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 10.dp, end = 10.dp, bottom = 10.dp, top = 5.dp)
        .clip(RoundedCornerShape(10.dp)),
        contentAlignment = Alignment.CenterStart
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(start = 40.dp)
                .background(Color.White.copy(alpha = 0.6f)),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                modifier = Modifier
                    .padding(start = 15.dp)
                    .weight(7f),
                text = jsonProverb.proverb,
                maxLines = 2,
            )

            Icon(
                modifier = Modifier
                    .clickable {
                        onSpeakClicked(jsonProverb.proverb)
                    }
                    .size(20.dp)
                    .weight(1f),
                painter = painterResource(id = R.drawable.ic_volume_up_24),
                contentDescription = "Arrow Right"
            )
        }

        Canvas(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .shadow(1.dp)
        ) {
            drawCircle(
                color = Color.White
            )
        }

        Text(
            modifier = Modifier
                .size(50.dp)
                .offset(y = 10.dp),
            text = searchText,
            color = Color.Black,
            textAlign = TextAlign.Center,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            fontFamily = MaterialTheme.typography.titleLarge.fontFamily
        )
    }
}

@Composable
@Preview
fun DetailsPreview() {
    //DetailsScreen(navController = rememberNavController())
}