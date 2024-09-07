package com.example.speedotransfer.ui.screens.error

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.elements.SpeedoButton
import com.example.speedotransfer.ui.theme.BodyRegular16
import com.example.speedotransfer.ui.theme.G700
import com.example.speedotransfer.ui.theme.G900
import com.example.speedotransfer.ui.theme.Heading3

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    val errors = listOf(
        Error(
            title = "Server error...",
            description = "It seems like we’re haveing some diffculities , please don’t leave your aspirations, we are sending for help",
            imageRes = R.drawable.cuate
        ),
        Error(
            title = "Internet connection \n disabled...",
            imageRes = R.drawable.no_internet
        ),

        )
    Scaffold { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),

            ) {
// server
//            ErrorPageContent(error = errors[0])
//            Spacer(modifier = Modifier.height(32.dp))
//
//            SpeedoButton(text = "Call Us", enabled = true, isTransparent = false)
//            Spacer(modifier = Modifier.height(16.dp))
//
//            SpeedoButton(text = "Message Us", enabled = true, isTransparent = true)
//internet
            ErrorPageContent(error = errors[1], isInternetDisabled = true)
//            Spacer(modifier = Modifier.height(32.dp))
            SpeedoButton(text = "Update", enabled = true, isTransparent = false)


        }
    }
}


@Composable
fun ErrorPageContent(error: Error, modifier: Modifier = Modifier,isInternetDisabled :Boolean = false,) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .wrapContentHeight()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = error.imageRes),
            contentDescription = null,
            modifier = modifier
                .height(
                    if (isInternetDisabled)109.dp else 200.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(45.dp))
        Text(text = error.title, style = Heading3, color = G900, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = error.description,
            style = BodyRegular16,
            color = G700,
            textAlign = TextAlign.Center
        )
    }
}


data class Error(
    val title: String,
    val description: String = "",
    @DrawableRes val imageRes: Int
)