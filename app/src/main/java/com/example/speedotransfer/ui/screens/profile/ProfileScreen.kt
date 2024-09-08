package com.example.speedotransfer.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.elements.ArrowedLargeMenuItem
import com.example.speedotransfer.ui.elements.CustomAppBarIcon
import com.example.speedotransfer.ui.elements.CutomAppBarTitle
import com.example.speedotransfer.ui.theme.G100
import com.example.speedotransfer.ui.theme.G40
import com.example.speedotransfer.ui.theme.G900
import com.example.speedotransfer.ui.theme.TitleSemiBold
import com.example.speedotransfer.ui.UIConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    CutomAppBarTitle(
                        text = "Profile",

                        )
                },
                Modifier.background(
                    brush = UIConstants.BRUSH
                ),

                navigationIcon = {
                    IconButton(onClick = {}) {
                        CustomAppBarIcon(
                            icon = R.drawable.drop_down
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
                    .verticalScroll(
                        rememberScrollState()
                    )
                    .fillMaxSize()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier.fillMaxWidth()
                ) {
//                    ProfileImage(
//                        imageResource = null,
//                        name = "Asmaa Dosuky"
//                    )
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(G40)
                    ) {
                        Text(
                            text = "AD",
                            style = TitleSemiBold,
                            color = G100
                        )
                    }
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Asmaa Dosuky",
                        style = TitleSemiBold,
                        color = G900
                    )
                }

                ArrowedLargeMenuItem(
                    name = "Personal information",
                    descritipn = "Your information",
                    icon = R.drawable.user,
                    enableStroke = true
                )
                ArrowedLargeMenuItem(
                    name = "Setting",
                    descritipn = "Change your settings",
                    icon = R.drawable.setting,
                    enableStroke = true
                )
                ArrowedLargeMenuItem(
                    name = "Payment history",
                    descritipn = "View your transactions",
                    icon = R.drawable.history1,
                    enableStroke = true
                )
                ArrowedLargeMenuItem(
                    name = "My Favourite list",
                    descritipn = "View your favourites",
                    icon = R.drawable.favorite,
                    enableStroke = false
                )
            }
        }
    )
}
