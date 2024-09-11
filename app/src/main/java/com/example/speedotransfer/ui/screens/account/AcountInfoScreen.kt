package com.example.speedotransfer.ui.screens.profile

import androidx.compose.foundation.background
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
import com.example.speedotransfer.AppRoutes.Route.PROFILE
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.elements.CustomAppBarIcon
import com.example.speedotransfer.ui.elements.CutomAppBarTitle
import com.example.speedotransfer.ui.elements.ProfileInfoItem
import com.example.speedotransfer.ui.uiConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountInfoScreen(navController: NavController,
                      accountDescription: String,
                       accountName: String,
                       accountNumber: String,
                       accountType: String,
                       active: Boolean,
                       balance: Int,
                       currency: String,
                      modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    CutomAppBarTitle(
                        text = "Account information",

                        )
                },
                Modifier.background(
                    brush = uiConstants.APP_BACKGROUND_COLOR
                ),

                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack("$PROFILE/{accountId}/{name}/{email}/{birthDate}/{country}/{createdDate}/{token}", inclusive = false) // Navigate back to the profile screen

                    }) {
                        CustomAppBarIcon(
                            icon = R.drawable.drop_down
                        )
                    }
                },
            )
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
                    title = "Account Description",
                    details = accountDescription,
                    enableStroke = true
                )
                ProfileInfoItem(
                    title = "Account Name",
                    details = accountName,
                    enableStroke = true
                )
                ProfileInfoItem(
                    title = "Account Number",
                    details = accountNumber,
                    enableStroke = true
                )
                ProfileInfoItem(
                    title = "Account Type",
                    details = accountType,
                    enableStroke = true
                )
                ProfileInfoItem(
                    title = "Active",
                    details = active.toString(),
                    enableStroke = true
                )
                ProfileInfoItem(
                    title = "Balance",
                    details = balance.toString(),
                    enableStroke = true
                )
                ProfileInfoItem(
                    title = "Currency",
                    details = currency,
                    enableStroke = false
                )
                Spacer(modifier = modifier.height(80.dp))

            }

        })
}

