package com.example.speedotransfer.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.theme.BodyRegular14
import com.example.speedotransfer.ui.theme.BodyRegular16
import com.example.speedotransfer.ui.theme.D300
import com.example.speedotransfer.ui.theme.G0
import com.example.speedotransfer.ui.theme.G70
import com.example.speedotransfer.ui.theme.G700

//@Composable
//fun SpeedoTextField(
//    labelText: String,
//    value: String,
//    onValueChange: (String) -> Unit,
//    placeholderText: String,
//    icon: Int,
//    modifier: Modifier = Modifier,
//    isError: Boolean = false,
//    errorMessage: String? = null,
//    isPasswordVisible: Boolean = false,
////    isFocused: Boolean = false,
//
//    onTrailingIconClick: (() -> Unit)? = null,
//    keyboardOptions: KeyboardOptions,
//    visualTransformation: VisualTransformation = VisualTransformation.None,
//    isEnabled: Boolean = true
//) {
//    Column {
//        Text(
//            text = labelText,
//            style = BodyRegular16,
//            color = G700,
//            modifier = modifier
//                .fillMaxWidth()
//                .height(24.dp)
//        )
//        OutlinedTextField(
//            value = value,
//            onValueChange = onValueChange,
//            textStyle = BodyRegular14,
//            placeholder = {
//                Text(
//                    text = placeholderText,
//                    style = BodyRegular14,
//                    color = G70
//                )
//            },
//            trailingIcon = {
//                if (onTrailingIconClick != null) {
//                    Icon(
//                        painter = painterResource(id = icon),
//                        contentDescription = null,
//                        modifier = modifier
//                            .size(24.dp)
//                            .clickable(enabled = isEnabled, onClick = onTrailingIconClick),
//                        tint = when {
//                            isError -> D300
//                            isPasswordVisible -> G700
////                            isFocused -> G700
//                            !isEnabled -> G70
//                            else -> G70
//                        }
//                    )
//                } else {
//                    Icon(
//                        painter = painterResource(id = icon),
//                        contentDescription = null,
//                        modifier = modifier.size(24.dp),
//                        tint = when {
//                            isError -> D300
////                            !isFocused -> G700
//                            !isEnabled -> G70
//                            else -> G70
//                        }
//                    )
//                }
//            },
//            colors = OutlinedTextFieldDefaults.colors(
//                focusedTextColor = if (isEnabled) G700 else G70,
//                focusedBorderColor = if (isError) D300 else if (isEnabled) G700 else G70,
//                unfocusedBorderColor = if (isError) D300 else G70,
//                cursorColor = if (isEnabled) G700 else G70,
//                disabledPlaceholderColor = G70,
//                disabledTrailingIconColor = G70,
//                focusedTrailingIconColor = G700,
//                disabledBorderColor = G70,
//                errorBorderColor = D300,
//                errorTrailingIconColor = D300
//            ),
//            shape = RoundedCornerShape(6.dp),
//            keyboardOptions = keyboardOptions,
//            visualTransformation = visualTransformation,
//            modifier = modifier
//                .padding(vertical = 8.dp)
//                .fillMaxWidth()
//                .background(G0),
//            isError = isError,
//            enabled = isEnabled
//        )
//
//        if (isError && !errorMessage.isNullOrEmpty()) {
//            Text(
//                text = errorMessage!!,
//                style = BodyRegular14,
//                color = D300,
//                modifier = modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 8.dp)
//                    .height(21.dp)
//            )
//        }
//    }
//}

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
        Text(
            text = labelText,
            style = BodyRegular16,
            color = if (isError) D300 else G700,
            modifier = modifier
                .fillMaxWidth()
                .height(24.dp)
        )
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
                            isPasswordVisible -> G700
                            !isEnabled -> G70
                            else -> G70
                        }
                    )
                } else {
                    Icon(
                        painter = painterResource(id = icon),
                        contentDescription = null,
                        modifier = modifier.size(24.dp),
                        tint = when {
                            isError -> D300
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
                .background(G0),
            isError = isError,
            enabled = isEnabled
        )

        if (isError && !errorMessage.isNullOrEmpty()) {
            Text(
                text = errorMessage!!,
                style = BodyRegular14,
                color = D300,
                modifier = modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .height(21.dp)
            )
        }
    }
}

