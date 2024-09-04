package com.example.speedotransfer.ui.screens

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.elements.SignTrailingText
import com.example.speedotransfer.ui.elements.SpeedoButton
import com.example.speedotransfer.ui.elements.SpeedoTextField
import com.example.speedotransfer.ui.theme.BodyMedium16
import com.example.speedotransfer.ui.theme.BodyRegular16
import com.example.speedotransfer.ui.theme.G0
import com.example.speedotransfer.ui.theme.Heading3
import com.example.speedotransfer.ui.theme.P50
import com.example.speedotransfer.ui.theme.TitleSemiBold
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompleteSignUpScreen(modifier: Modifier = Modifier) {
    var country by remember { mutableStateOf("") }
    var dateOfBirth by remember { mutableStateOf("") }
    val isFormValid by derivedStateOf {
        country.isNotBlank() && dateOfBirth.isNotBlank()
    }
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.linearGradient(colors = listOf(G0, P50)))
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.drop_down), contentDescription = "",
            modifier = modifier
                .size(50.dp)
                .align(Alignment.Start)
                .fillMaxWidth()
                .padding(top = 24.dp)
                .clickable { }
        )
        Spacer(modifier = Modifier.height(56.dp))

        Text(
            text = "Speedo Transfer",
            style = TitleSemiBold,
            textAlign = TextAlign.Center,
            modifier = modifier
                .fillMaxWidth()
                .height(29.dp)
        )

        Text(
            text = "Welcome to Banque Misr!",
            style = Heading3,
            textAlign = TextAlign.Center,
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 66.dp)
        )

        Text(
            text = "Let's Complete your Profile",
            style = BodyRegular16,
            textAlign = TextAlign.Center,
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 32.dp)
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
            text = "Continue",
            enabled = isFormValid,
            isTransparent = false
        )

        Spacer(modifier = Modifier.height(16.dp))

        SignTrailingText(
            question = R.string.already_have_an_account_q,
            answer = R.string.sign_in_a
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun SignUpScreenPreview() {
//    SignUpScreen()
//}