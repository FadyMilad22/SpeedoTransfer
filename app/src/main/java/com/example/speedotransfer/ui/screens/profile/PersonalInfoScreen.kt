package com.example.speedotransfer.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.elements.ProfileInfoItem
import com.example.speedotransfer.ui.theme.G900
import com.example.speedotransfer.ui.theme.TitleMedium

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileInfoScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(

                title = {
                    Text(
                        text = "Profile information",
                        style = TitleMedium,
                        color = G900,

                        )
                },
                Modifier.background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color(
                                0xFFFFF8E7
                            ), Color(0xFFFFEAEE)
                        )
                    )
                ),
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            painter = painterResource(id = R.drawable.drop_down),
                            contentDescription = "Back",
                            modifier = modifier.size(24.dp)
                        )
                    }
                },
            )
        },
        bottomBar = {

        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
            ) {
                Spacer(modifier = modifier.padding(top = 32.dp))
                ProfileInfoItem(
                    title = "Full Name",
                    details = "Asmaa Dosuky",
                    enableStroke = true
                )
                ProfileInfoItem(
                    title = "Email",
                    details = "Asmaa@gmail.com",
                    enableStroke = true
                )
                ProfileInfoItem(
                    title = "Date Of Brith",
                    details = "12/01/2000",
                    enableStroke = true
                )
                ProfileInfoItem(
                    title = "Country",
                    details = "Egypt",
                    enableStroke = true
                )
                ProfileInfoItem(
                    title = "Bank Account",
                    details = "1234xxxx",
                    enableStroke = false
                )
            }

        })
}

