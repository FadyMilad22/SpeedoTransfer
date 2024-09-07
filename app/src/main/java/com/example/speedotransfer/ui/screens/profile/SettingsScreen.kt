package com.example.speedotransfer.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.elements.ArrowedLargeMenuItem
import com.example.speedotransfer.ui.theme.G100
import com.example.speedotransfer.ui.theme.G40
import com.example.speedotransfer.ui.theme.G900
import com.example.speedotransfer.ui.theme.TitleMedium
import com.example.speedotransfer.ui.theme.TitleSemiBold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(

                title = {
                    Text(
                        text = "Setting",
                        style = TitleMedium,
                        color = G900,
                        modifier = modifier.height(30.dp)

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

                ArrowedLargeMenuItem(
                    name = "Change password",
                    descritipn = "Change password",
                    icon = R.drawable.password_outline,
                    enableStroke = false
                )
                Spacer(modifier = modifier.height(16.dp)) // check it again
                ArrowedLargeMenuItem(
                    name = "Edit Profile",
                    descritipn = "Change your information",
                    icon = R.drawable.edit,
                    enableStroke = true
                )
            }
        })

}