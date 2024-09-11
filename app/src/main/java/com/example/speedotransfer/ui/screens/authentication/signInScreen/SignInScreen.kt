package com.example.speedotransfer.ui.screens.authentication.signInScreen

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.speedotransfer.AppRoutes.Route
import com.example.speedotransfer.R
import com.example.speedotransfer.data.network.APIClient
import com.example.speedotransfer.data.repository.signIn.SignInRepoImpl
import com.example.speedotransfer.ui.elements.CutomAppBarTitle
import com.example.speedotransfer.ui.elements.NotificationBanner
import com.example.speedotransfer.ui.elements.SignTrailingText
import com.example.speedotransfer.ui.elements.SpeedoButton
import com.example.speedotransfer.ui.elements.SpeedoTextField
import com.example.speedotransfer.ui.theme.TitleSemiBold
import com.example.speedotransfer.ui.uiConstants
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.regex.Pattern

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    fun isValidPassword(password: String): Boolean {
        val passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#\$%^&*]).{6,}$"
        return Pattern.compile(passwordRegex).matcher(password).matches()
    }

    fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        return Pattern.compile(emailRegex).matcher(email).matches()
    }

    val context = LocalContext.current
    val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("customerPrefs", Context.MODE_PRIVATE)
    val signInViewModel: SignInViewModel = viewModel(
        factory = SignInViewModelFactory(
            SignInRepoImpl(APIClient), sharedPreferences
        )
    )

    var text by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }

    val signInState by signInViewModel.signInState.collectAsState()
    val email by signInViewModel.email.collectAsState()
    val password by signInViewModel.password.collectAsState()
    val isPasswordValid by signInViewModel.isPasswordValid.collectAsState()
    val customerState by signInViewModel.customerState.collectAsState()
    var showPassword by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
    val isFormValid by derivedStateOf {
        password.length > 5 && email.isNotBlank() && isValidPassword(password) && isValidEmail(email)
    }
    val isPasswordLengthValid by derivedStateOf { password.length > 5 }


    val response by signInViewModel.response.collectAsState()

    LaunchedEffect(response) {
        response?.let {
            if (it.status == "ACCEPTED") {
                // Show success toast
                Log.d("API Test SignIN", response!!.token)
                signInViewModel.fetchCustomerByEmail(response!!.token, email)


            } else {
                Toast.makeText(context, "Server Error: Try Again Later", Toast.LENGTH_SHORT).show()


            }
        }
    }

    LaunchedEffect(customerState) {
        customerState?.let {
            if (it.id != -1) {
                signInViewModel.saveCustomerDataToPreferences(it)
                Log.d("API Test SignIN", customerState.toString())

                navController.navigate(
                    "${Route.HOME}/${it.accounts[0].id.toLong()}/${it.accounts[0].createdAt}/${getCurrentDate()}/${it.accounts[0].balance.toFloat()}/${it.name}/${it.accounts[0].currency}"
                )


            } else {
                Toast.makeText(context, "Server Error: Try Again Later", Toast.LENGTH_SHORT).show()


            }
        }
    }


    Scaffold(
        modifier = Modifier.background(brush = uiConstants.AUTH_BACKGROUND_COLOR)
,
                topBar = {
            CenterAlignedTopAppBar(
                title = { CutomAppBarTitle(text = "Sign In") },
            )
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
                Spacer(modifier = Modifier.height(32.dp))

                when (signInState) {
                    is SignInState.Loading -> CircularProgressIndicator()
                    is SignInState.Error -> NotificationBanner(
                        message = (signInState as SignInState.Error).message,
                        onCloseClick = { }
                    )

                    is SignInState.Success -> {
                        NotificationBanner(
                            message = (signInState as SignInState.Success).message,
                            onCloseClick = { }
                        )
                    }

                    else -> Unit
                }



                Spacer(modifier = Modifier.height(32.dp))

                Text(
                    text = "Speedo Transfer",
                    style = TitleSemiBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(29.dp)
                )
                Spacer(modifier = Modifier.height(64.dp))



                SpeedoTextField(
                    labelText = "Email",
                    value = email,
                    onValueChange = {
                        signInViewModel.onEmailChange(it)
                        showError = false
                    },
                    isError = showError && !isValidEmail(email),
                    showError = showError,

                    errorMessage = "Enter a valid email, e.g., example@domain.com.",
                    placeholderText = "Enter your email address",
                    icon = R.drawable.email,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    visualTransformation = VisualTransformation.None
                )

                SpeedoTextField(
                    labelText = "Password",
                    value = password,
                    onValueChange = {
                        signInViewModel.onPasswordChange(it)
                        showError = false

                    },
                    isError = showError && !isValidPassword(password),
                    showError = showError,
                    errorMessage = "Password: 6+ chars,uppercase,lowercase,special character.",
                    placeholderText = "Enter your password",
                    icon = if (showPassword) R.drawable.eye_comp2 else R.drawable.eye_comp,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    isPasswordVisible = showPassword,
                    visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    onTrailingIconClick = { showPassword = !showPassword }
                )


                Spacer(modifier = Modifier.height(24.dp))

                SpeedoButton(
                    text = stringResource(R.string.sign_in_a),
                    enabled = isPasswordLengthValid,
                    isTransparent = false,

                    onClick = {
                        if (!isValidPassword(password) || !isValidEmail(email)) {
                            showError = true
                            passwordError = !isValidPassword(password)
                            emailError = !isValidEmail(email)
                        } else {
                            showError = false
                            passwordError = false
                            emailError = false
                            signInViewModel.logIn()
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                SignTrailingText(
                    question = R.string.already_have_an_account_q,
                    answer = R.string.sign_up,
                    navController = navController,
                    distination = Route.SIGN_UP
                )

                Spacer(modifier = Modifier.height(32.dp))

            }
        }
    )
}

fun getCurrentDate(): String {
    val currentDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return currentDate.format(formatter)
}