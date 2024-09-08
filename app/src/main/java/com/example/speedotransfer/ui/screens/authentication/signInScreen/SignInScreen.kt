package com.example.speedotransfer.ui.screens.authentication.signInScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.elements.CutomAppBarTitle
import com.example.speedotransfer.ui.elements.NotificationBanner
import com.example.speedotransfer.ui.elements.SignTrailingText
import com.example.speedotransfer.ui.elements.SpeedoButton
import com.example.speedotransfer.ui.elements.SpeedoTextField
import com.example.speedotransfer.ui.theme.BodyRegular14
import com.example.speedotransfer.ui.theme.D300
import com.example.speedotransfer.ui.theme.TitleSemiBold
import com.example.speedotransfer.ui.UIConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(modifier: Modifier = Modifier) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    CutomAppBarTitle(text = "Sign In")
                },
                Modifier.background(
                    brush = UIConstants.BRUSH2
                ),
//                navigationIcon = {
//                    IconButton(onClick = {}) {
//                        Icon(
//                            painter = painterResource(id = R.drawable.drop_down),
//                            contentDescription = "Back",
//                            modifier = modifier.size(24.dp)
//                        )
//                    }
//                },
            )
        },
        bottomBar = {

        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = modifier.height(32.dp))
                var showNotification by remember { mutableStateOf(true) }
                if (showNotification) {
                    NotificationBanner(
                        message = "We Logged you out because you were\n inactive for 2 minutes - it’s to help your\n account secure ",
                        onCloseClick = { showNotification = false }
                    )
                }
                Spacer(modifier = Modifier.height(32.dp))
//                Spacer(modifier = Modifier.height(64.dp))

                Text(
                    text = "Speedo Transfer",
                    style = TitleSemiBold,
                    textAlign = TextAlign.Center,
                    modifier = modifier
                        .fillMaxWidth()
                        .height(29.dp)
                )
                Spacer(modifier = Modifier.height(64.dp))


                var email by remember { mutableStateOf("") }
                var password by remember { mutableStateOf("") }
                var showPassword by remember { mutableStateOf(false) }
                var isPasswordValid by remember { mutableStateOf(true) }

                val isFormValid by derivedStateOf {
                    email.isNotBlank() && password.isNotBlank()
                            && isPasswordValid
                }
                SpeedoTextField(

                    labelText = "Email",
                    value = email,
                    onValueChange = { email = it },
                    placeholderText = "Enter your email address",
                    icon = R.drawable.email,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    visualTransformation = VisualTransformation.None
                )

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


                Spacer(modifier = Modifier.height(24.dp))

                SpeedoButton(
                    text = stringResource(R.string.sign_in_a),
                    enabled = isFormValid,
                    isTransparent = false
                )

                Spacer(modifier = Modifier.height(16.dp))

                SignTrailingText(
                    question = R.string.already_have_an_account_q,
                    answer = R.string.sign_up
                )
            }
        })
}