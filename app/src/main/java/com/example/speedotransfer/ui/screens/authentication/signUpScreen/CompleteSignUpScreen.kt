package com.example.speedotransfer.ui.screens.authentication.signUpScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.speedotransfer.AppRoutes.Route
import com.example.speedotransfer.R
import com.example.speedotransfer.data.network.APIClient
import com.example.speedotransfer.data.repository.singUp.SignUpRepoImpl
import com.example.speedotransfer.ui.elements.CountryRow
import com.example.speedotransfer.ui.elements.CustomAppBarIcon
import com.example.speedotransfer.ui.elements.CutomAppBarTitle
import com.example.speedotransfer.ui.elements.SignTrailingText
import com.example.speedotransfer.ui.elements.SpeedoButton
import com.example.speedotransfer.ui.elements.SpeedoTextField
import com.example.speedotransfer.ui.theme.BodyRegular16
import com.example.speedotransfer.ui.theme.Heading3
import com.example.speedotransfer.ui.theme.TitleSemiBold
import com.example.speedotransfer.ui.uiConstants
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompleteSignUpScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {

    val signUpViewModel: SignUpViewModel =
        viewModel(
            factory = SignUpViewModelFactory(
                SignUpRepoImpl(
                    APIClient
                )
            )
        )
    var country by remember { mutableStateOf("") }
    var showBottomSheet by remember { mutableStateOf(false) } // Track Bottom Sheet visibility
    var isDatePickerShown by remember { mutableStateOf(false) } // Track Date Picker visibility
    val sheetState = rememberModalBottomSheetState()
    var selectedCountry by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()

    // Fix: Only show Bottom Sheet if Date Picker is not shown
    if (showBottomSheet && !isDatePickerShown) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState,
            scrimColor = Color.Black.copy(alpha = 0.8f),
            dragHandle = null
        ) {
            LazyColumn(
                modifier = modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .padding(16.dp)
            ) {
                items(5) {
                    CountryRow(
                        countryName = "United States",
                        isSelected = selectedCountry == "United States",
                        onCountrySelected = {
                            selectedCountry = it
                            scope.launch {
                                showBottomSheet = false
                                sheetState.hide()
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { CutomAppBarTitle(text = "Sign Up") },
                Modifier.background(brush = uiConstants.BRUSH2),
                navigationIcon = {
                    IconButton(onClick = {}) {
                        CustomAppBarIcon(icon = R.drawable.drop_down)
                    }
                },
            )
        },
        bottomBar = {},
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(modifier = Modifier.height(56.dp))

                Text(
                    text = "Speedo Transfer",
                    style = TitleSemiBold,
                    textAlign = TextAlign.Center,
                    modifier = modifier.fillMaxWidth().height(29.dp)
                )

                Text(
                    text = "Welcome to Banque Misr!",
                    style = Heading3,
                    textAlign = TextAlign.Center,
                    modifier = modifier.fillMaxWidth().padding(top = 66.dp)
                )

                Text(
                    text = "Let's Complete your Profile",
                    style = BodyRegular16,
                    textAlign = TextAlign.Center,
                    modifier = modifier.fillMaxWidth().padding(top = 16.dp, bottom = 32.dp)
                )

                // Fix: Don't show DatePicker if Bottom Sheet is active
                SpeedoTextField(
                    labelText = "Country",
                    value = selectedCountry,
                    onValueChange = { selectedCountry = it },
                    placeholderText = "Select your country",
                    icon = R.drawable.chevron_down,
                    keyboardOptions = KeyboardOptions.Default,
                    visualTransformation = VisualTransformation.None,
                    modifier = modifier.clickable {
                        if (!isDatePickerShown) {  // Only show Bottom Sheet if DatePicker is not shown
                            showBottomSheet = true
                            scope.launch { sheetState.show() }
                        }
                    }
                )

                var dateOfBirth by remember { mutableStateOf("") }
                var dateMillis by remember { mutableLongStateOf(0) }

                // Show Date Picker
                if (isDatePickerShown) {
                    DatePickerChooser(onConfirm = {
                        val dateFormatted = SimpleDateFormat("dd/MM/yyyy", Locale.US)
                        dateMillis = it.selectedDateMillis!!
                        dateOfBirth = dateFormatted.format(dateMillis)
                        isDatePickerShown = false
                    }, onDismiss = {
                        isDatePickerShown = false
                    })
                }

                // Fix: Don't show Bottom Sheet if DatePicker is active
                SpeedoTextField(
                    labelText = "Date Of Birth",
                    value = dateOfBirth,
                    onValueChange = { dateOfBirth = it },
                    placeholderText = "DD/MM/YYYY",
                    icon = R.drawable.date,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    visualTransformation = VisualTransformation.None,
                    modifier = modifier.clickable {
                        if (!showBottomSheet) {  // Only show DatePicker if Bottom Sheet is not shown
                            isDatePickerShown = true
                        }
                    }
                )

                Spacer(modifier = Modifier.height(24.dp))

                val isFormValid by derivedStateOf {
                    selectedCountry.isNotBlank() && dateOfBirth.isNotBlank()
                }

                SpeedoButton(
                    text = "Continue",
                    enabled = isFormValid,
                    isTransparent = false,
                    onClick = {
                        signUpViewModel.onCountryChange(selectedCountry)
                        signUpViewModel.onBirthDateChange(dateOfBirth)
                        signUpViewModel.registerCustomer()

                        navController.navigate(Route.SIGN_IN) {
                            popUpTo(Route.COMPLETE_SIGN_UP) { inclusive = true }
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                SignTrailingText(
                    question = R.string.already_have_an_account_q,
                    answer = R.string.sign_in_a,
                    distination = Route.SIGN_IN,
                    navController = navController
                )
            }
        }
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerChooser(
    onConfirm: (DatePickerState) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val datePickerState = rememberDatePickerState()
    DatePickerDialog(onDismissRequest = onDismiss,  // Allow dismiss action
        confirmButton = {
            TextButton(onClick = { onConfirm(datePickerState) }) {
                Text(text = "Ok")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(text = "Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

//@Preview(showBackground = true)
//@Composable
//fun SignUpScreenPreview() {
//    SignUpScreen()
//}