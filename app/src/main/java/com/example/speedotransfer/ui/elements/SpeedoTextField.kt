package com.example.speedotransfer.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.speedotransfer.ui.theme.BodyRegular14
import com.example.speedotransfer.ui.theme.BodyRegular16
import com.example.speedotransfer.ui.theme.D300
import com.example.speedotransfer.ui.theme.G0
import com.example.speedotransfer.ui.theme.G70
import com.example.speedotransfer.ui.theme.G700

@Composable
fun SpeedoTextField(
    labelText: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    icon: Int,
    isError: Boolean = false,
    errorMessage: String? = null,
    isPasswordVisible: Boolean = false,
    onTrailingIconClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isEnabled: Boolean = true
) {
    Column {
        // Display the label of the TextField
        Text(
            text = labelText,
            style = BodyRegular16,
            color = if (isError) D300 else G700,
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
                            }
                    )
                }else {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                        modifier = modifier.size(24.dp),

                        )
                    }
            },
            colors = OutlinedTextFieldDefaults.colors(),
            shape = RoundedCornerShape(6.dp),
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            modifier = modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
                .background(G0),
            isError = isError,
            enabled = isEnabled
        )

        if (isError && !errorMessage.isNullOrEmpty()) {
            Text(
                text = errorMessage,
                color = D300,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            )
        }
    }
}
