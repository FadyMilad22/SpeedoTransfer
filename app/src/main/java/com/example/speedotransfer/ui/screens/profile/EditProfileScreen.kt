package com.example.speedotransfer.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.elements.CustomAppBarIcon
import com.example.speedotransfer.ui.elements.CutomAppBarTitle
import com.example.speedotransfer.ui.elements.SpeedoButton
import com.example.speedotransfer.ui.elements.SpeedoTextField
import com.example.speedotransfer.ui.theme.BodyMedium16
import com.example.speedotransfer.ui.UIConstants
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    CutomAppBarTitle(
                        text = "Edit Profile",

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
                    .verticalScroll(rememberScrollState())

                    .fillMaxSize()
            ) {


                var fullName by remember { mutableStateOf("") }
                var email by remember { mutableStateOf("") }
                var country by remember { mutableStateOf("") }
                var dateOfBirth by remember { mutableStateOf("") }
                val sheetState = rememberModalBottomSheetState()
                val scope = rememberCoroutineScope()
                var showBottomSheet by remember { mutableStateOf(false) }
                var edit by remember { mutableStateOf<Boolean>(false) }


                if (showBottomSheet) {
                    ModalBottomSheet(
                        onDismissRequest = {
                            showBottomSheet = false
                        },
                        sheetState = sheetState,
                        scrimColor = Color.Black.copy(alpha = 0.8f), dragHandle = {}
                    ) {
                        LazyColumn(
                            modifier = modifier
                                .fillMaxWidth()
                                .height(680.dp)
                                .padding(16.dp)
                        ) {
                            item {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = modifier
                                        .fillMaxWidth()
                                        .height(56.dp)
                                        .padding(vertical = 16.dp)
                                        .clickable {
                                            country = "United States"
                                            scope.launch {
                                                showBottomSheet = false
                                                sheetState.hide()
                                            }
                                        }
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.united_states),
                                        contentDescription = "United States",
                                        modifier = Modifier
                                            .size(24.dp)
                                            .padding(end = 8.dp)
                                            .align(Alignment.CenterVertically)

                                    )
//                        Spacer(modifier = Modifier.width(16.dp))
                                    Text(
                                        text = "United States",
                                        style = BodyMedium16,
                                        modifier = Modifier
                                            .height(24.dp)
                                            .align(Alignment.CenterVertically)
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                SpeedoTextField(

                    labelText = "Full Name",
                    value = fullName,
                    onValueChange = { fullName = it },
                    placeholderText = "Enter Cardholder Name",
                    icon = R.drawable.user,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    visualTransformation = VisualTransformation.None
                )
                SpeedoTextField(

                    labelText = "Email",
                    value = email,
                    onValueChange = { email = it },
                    placeholderText = "Enter Cardholder Email",
                    icon = R.drawable.email,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    visualTransformation = VisualTransformation.None
                )

                SpeedoTextField(
                    labelText = "Country",
                    value = country,
                    onValueChange = { country = it },
                    placeholderText = "Select your country",
                    icon = R.drawable.chevron_down,
                    keyboardOptions = KeyboardOptions.Default,
                    visualTransformation = VisualTransformation.None,
                    modifier = modifier.clickable {
                        showBottomSheet = true
                        scope.launch {
                            sheetState.show()
                        }
                    }
                )

                SpeedoTextField(
                    labelText = "Date Of Birth",
                    value = dateOfBirth,
                    onValueChange = { dateOfBirth = it },
                    placeholderText = "DD/MM/YYYY",
                    icon = R.drawable.date,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    visualTransformation = VisualTransformation.None
                )

                Spacer(modifier = Modifier.height(24.dp))

                SpeedoButton(
                    text = "Save",
                    enabled = true,
                    isTransparent = false
                )
            }
        }
    )
}