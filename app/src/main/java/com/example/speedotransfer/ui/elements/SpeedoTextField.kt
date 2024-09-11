package com.example.speedotransfer.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.speedotransfer.ui.theme.BodyRegular14
import com.example.speedotransfer.ui.theme.BodyRegular16
import com.example.speedotransfer.ui.theme.D300
import com.example.speedotransfer.ui.theme.G0
import com.example.speedotransfer.ui.theme.G70
import com.example.speedotransfer.ui.theme.G700
import java.util.regex.Pattern

@Composable
fun SpeedoTextField(
    labelText: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    icon: Int,
    isError: Boolean = false,
    showError: Boolean = false,  // New parameter to control error visibility
    errorMessage: String? = null,
    isPasswordVisible: Boolean = false,
    onTrailingIconClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isEnabled: Boolean = true
) {
    // TextField validation rules
    fun isValidPassword(password: String): Boolean {
        val passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#\$%^&*]).{6,}$"
        return Pattern.compile(passwordRegex).matcher(password).matches()
    }

    fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        return Pattern.compile(emailRegex).matcher(email).matches()
    }

    var isFocused by remember { mutableStateOf(false) } // Track the focus state

    Column {
        // Display the label of the TextField
        Text(
            text = labelText,
            style = BodyRegular16,
            color = G700,
            modifier = modifier
                .fillMaxWidth()
                .height(24.dp)
        )

        // OutlinedTextField for user input
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = BodyRegular14,
            placeholder = {
                Text(
                    text = placeholderText,
                    style = BodyRegular14,
                    color = G70
                )
            },
            trailingIcon = {
                if (onTrailingIconClick != null) {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                        modifier = modifier
                            .size(24.dp)
                            .clickable(enabled = isEnabled) {
                                onTrailingIconClick()
                            },
                        tint = when {
                            isError -> D300
                            isFocused -> G700 // Color changes when focused
                            isPasswordVisible -> G700
                            !isEnabled -> G70
                            else -> G70
                        }
                    )
                }else {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                        modifier = modifier.size(24.dp),
                        tint = when {
                            isError -> D300
                            isFocused -> G700 // Color changes when focused
                            !isEnabled -> G70
                            else -> G70
                        }
                        )
                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = if (isEnabled) G700 else G70,
                focusedBorderColor = if (isError) D300 else if (isEnabled) G700 else G70,
                unfocusedBorderColor = if (isError) D300 else G70,
                cursorColor = if (isEnabled) G700 else G70,
                disabledPlaceholderColor = G70,
                disabledTrailingIconColor = G70,
                disabledBorderColor = G70,
                errorBorderColor = D300,
                errorTrailingIconColor = D300
            ),
            shape = RoundedCornerShape(6.dp),
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            modifier = modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
                .background(G0).onFocusChanged { focusState ->
                    isFocused = focusState.isFocused // Update focus state when focus changes
                },

            isError = isError,
            enabled = isEnabled
        )

        // Show error only when `showError` is true, and errorMessage is provided
        if (isError && showError && !errorMessage.isNullOrEmpty()) {
            Text(
                text = errorMessage.take(50),  // Limit to 50 characters
                color = D300,
                style = BodyRegular14,
                textAlign = TextAlign.Start,
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
//                fontSize = 12.sp,  // Reduce font size
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 4.dp)  // Reduce padding
            )
        }
    }
}
