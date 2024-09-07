package com.example.speedotransfer.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.elements.SpeedoButton
import com.example.speedotransfer.ui.elements.SpeedoTextField
import com.example.speedotransfer.ui.theme.BodyRegular14
import com.example.speedotransfer.ui.theme.D300
import com.example.speedotransfer.ui.theme.G900
import com.example.speedotransfer.ui.theme.TitleMedium

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePasswordScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Edit Profile",
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
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                ) {


                var password by remember { mutableStateOf("123456") }
                var confirmPassword by remember { mutableStateOf("123456") }
                var showPassword by remember { mutableStateOf(false) }
                var showConfirmPassword by remember { mutableStateOf(false) }
                var isPasswordValid by remember { mutableStateOf(true) }
                var isConfirmPasswordValid by remember { mutableStateOf(true) }

                val isFormValid by derivedStateOf {
                    password.isNotBlank() &&
                            confirmPassword.isNotBlank() && isPasswordValid && isConfirmPasswordValid
                }

                Spacer(modifier = Modifier.height(32.dp))

                SpeedoTextField(

                    labelText = "Password",
                    value = password,
                    onValueChange = {
                        password = it
                        isPasswordValid = password.length >= 8
                    },
                    placeholderText = "Enter your password",
                    icon = if (showPassword) R.drawable.eye_comp2 else R.drawable.eye_comp,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    isPasswordVisible = showPassword,

                    visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    onTrailingIconClick = {
                        showPassword = !showPassword
                    },
                )

                if (!isPasswordValid) {
                    Text(
                        text = "This is an error message.",
                        color = D300,
                        style = BodyRegular14,
                        textAlign = TextAlign.Start,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )


                }

                SpeedoTextField(
                    labelText = "Confirm password",
                    value = confirmPassword,
                    onValueChange = {
                        confirmPassword = it
                        isConfirmPasswordValid = confirmPassword == password
                    },
                    placeholderText = "Enter your password",
                    icon = if (showConfirmPassword) R.drawable.eye_comp2 else R.drawable.eye_comp,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    isPasswordVisible = showConfirmPassword,

                    onTrailingIconClick = {
                        showConfirmPassword = !showConfirmPassword
                    },
                    visualTransformation = if (showConfirmPassword) VisualTransformation.None else PasswordVisualTransformation()
                )

                if (!isConfirmPasswordValid) {
                    Text(
                        text = "This is an error message.",
                        color = D300,
                        style = BodyRegular14,
                        textAlign = TextAlign.Start,
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )
                }
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