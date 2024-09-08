package com.example.speedotransfer.ui.screens.authentication

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.elements.CutomAppBarTitle
import com.example.speedotransfer.ui.elements.SignTrailingText
import com.example.speedotransfer.ui.elements.SpeedoButton
import com.example.speedotransfer.ui.elements.SpeedoTextField
import com.example.speedotransfer.ui.theme.TitleSemiBold
import com.example.speedotransfer.ui.UIConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(modifier: Modifier = Modifier) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    var showConfirmPassword by remember { mutableStateOf(false) }
    var isPasswordValid by remember { mutableStateOf(true) }
    var isConfirmPasswordValid by remember { mutableStateOf(true) }
    val  isValid = fullName.isNotBlank() && email.isNotBlank() && password.isNotBlank() &&
            confirmPassword.isNotBlank() && isPasswordValid && isConfirmPasswordValid
    val isFormValid by remember {
        mutableStateOf(isValid)

    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    CutomAppBarTitle(text = "Sign Up")
                },
                Modifier.background(
                    brush = UIConstants.BRUSH2
                    
                ),
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

                Spacer(modifier = modifier.height(56.dp))

                Text(
                    text = "Speedo Transfer",
                    style = TitleSemiBold,
                    textAlign = TextAlign.Center,
                    modifier = modifier
                        .fillMaxWidth()
                        .height(29.dp)
                )
                Spacer(modifier = Modifier.height(64.dp))

                SpeedoTextField(
//isError = true,
                    labelText = "Full Name",
                    value = fullName,
                    onValueChange = { fullName = it },
                    placeholderText = "Enter your Full Name",
                    icon = R.drawable.user,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    visualTransformation = VisualTransformation.None
                )

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
errorMessage = "aaa",
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

//                if (!isPasswordValid) {
//                    Text(
//                        text = "This is an error message.",
//                        color = D300,
//                        style = BodyRegular14,
//                        textAlign = TextAlign.Start,
//                        modifier = modifier
//                            .fillMaxWidth()
//                            .padding(vertical = 8.dp)
//                    )
//
//
//                }

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

//                if (!isConfirmPasswordValid) {
//                    Text(
//                        text = "This is an error message.",
//                        color = D300,
//                        style = BodyRegular14,
//                        textAlign = TextAlign.Start,
//                        modifier = modifier
//                            .fillMaxWidth()
//                            .padding(vertical = 8.dp)
//                    )
//                }

                Spacer(modifier = Modifier.height(24.dp))

                SpeedoButton(
                    text = "Sign up",
                    enabled = isFormValid,
                    isTransparent = false
                )

                Spacer(modifier = Modifier.height(16.dp))

                SignTrailingText(
                    question = R.string.already_have_an_account_q,
                    answer = R.string.sign_in_a
                )
            }})
}

@Preview(showBackground = true)
@Composable
fun SignUpScreenPreview() {
    SignUpScreen()
}
