package com.example.speedotransfer.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.speedotransfer.AppRoutes.Route
import com.example.speedotransfer.AppRoutes.Route.EDIT_PROFILE
import com.example.speedotransfer.AppRoutes.Route.HOME
import com.example.speedotransfer.AppRoutes.Route.PROFILE
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.elements.ArrowedLargeMenuItem
import com.example.speedotransfer.ui.elements.CustomAppBarIcon
import com.example.speedotransfer.ui.elements.CutomAppBarTitle
import com.example.speedotransfer.ui.uiConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController,
                   accountId: Long,    // Passed from the previous screen
                   name: String,  // Passed from the previous screen
                   email: String,    // Passed from the previous screen
                   birthDate: String,    // Passed from the previous screen
                   country: String,       // Passed from the previous screen
    modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    CutomAppBarTitle(
                        text = "Setting",

                        )
                },
                Modifier.background(
                    brush = uiConstants.BRUSH
                ),

                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack("$PROFILE/{accountId}/{name}/{email}/{birthDate}/{country}/{createdDate}", inclusive = false)

                    }) {
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
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
            ) {

                ArrowedLargeMenuItem(
                    name = "Change password",
                    descritipn = "Change password",
                    icon = R.drawable.password_outline,
                    enableStroke = false,
                            modifier = modifier.clickable {
                        navController.navigate(Route.CHANGE_PASSWORD)
                    }
                )
                Spacer(modifier = modifier.height(16.dp)) // check it again
                ArrowedLargeMenuItem(
                    name = "Edit Profile",
                    descritipn = "Change your information",
                    icon = R.drawable.edit,
                    enableStroke = true,
                    modifier = modifier.clickable {
                        navController.navigate("$EDIT_PROFILE/${accountId}/${name}/${email}/${birthDate}/${country}")
                    }
                )
            }
        })

}