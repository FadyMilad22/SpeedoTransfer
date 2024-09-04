package com.example.speedotransfer.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.elements.SignTrailingText
import com.example.speedotransfer.ui.elements.SpeedoButton
import com.example.speedotransfer.ui.elements.SpeedoTextField
import com.example.speedotransfer.ui.theme.BodyRegular14
import com.example.speedotransfer.ui.theme.D300
import com.example.speedotransfer.ui.theme.G0
import com.example.speedotransfer.ui.theme.G700
import com.example.speedotransfer.ui.theme.P50
import com.example.speedotransfer.ui.theme.TitleMedium
import com.example.speedotransfer.ui.theme.TitleSemiBold

@Composable
fun SignInScreen(modifier: Modifier = Modifier) {
    var email by remember { mutableStateOf("agamal@gmail.com") }
    var password by remember { mutableStateOf("123456") }
    var showPassword by remember { mutableStateOf(false) }
    var isPasswordValid by remember { mutableStateOf(true) }

    val isFormValid by derivedStateOf {
         email.isNotBlank() && password.isNotBlank()
                 && isPasswordValid
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(colors = listOf(G0, P50)))
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Sign In",
            style = TitleMedium,
            textAlign = TextAlign.Center,
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        )
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
}