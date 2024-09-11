package com.example.speedotransfer.ui.screens.authentication.signInScreen

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import com.example.speedotransfer.ui.theme.TitleSemiBold
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.speedotransfer.AppRoutes.Route
import com.example.speedotransfer.data.network.APIClient
import com.example.speedotransfer.data.repository.signIn.SignInRepoImpl
import com.example.speedotransfer.ui.uiConstants
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.regex.Pattern
import kotlin.math.sin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {
    // Validation functions
    fun isValidPassword(password: String): Boolean {
        val passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#\$%^&*]).{6,}$"
        return Pattern.compile(passwordRegex).matcher(password).matches()
    }

    fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        return Pattern.compile(emailRegex).matcher(email).matches()
    }

    val context = LocalContext.current
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("customerPrefs", Context.MODE_PRIVATE)
    val signInViewModel: SignInViewModel = viewModel(factory = SignInViewModelFactory(
        SignInRepoImpl(APIClient), sharedPreferences))

    var text by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }

    // Observing StateFlow from ViewModel
    val signInState by signInViewModel.signInState.collectAsState()
    val email by signInViewModel.email.collectAsState()
    val password by signInViewModel.password.collectAsState()
    val isPasswordValid by signInViewModel.isPasswordValid.collectAsState()
    val customerState by signInViewModel.customerState.collectAsState() // Observing customer details
    var showPassword by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var emailError by remember { mutableStateOf(false) }
// Check if both email and password meet validation criteria
    val isFormValid by derivedStateOf {
        password.length > 5 && email.isNotBlank() && isValidPassword(password) && isValidEmail(email)
    }
    val isPasswordLengthValid by derivedStateOf { password.length > 5 }


    val response by signInViewModel.response.collectAsState()  // Observe response state

    LaunchedEffect(response) {
        response?.let {
            if (it.status == "ACCEPTED") {
                // Show success toast
                Log.d("API Test SignIN",response!!.token)
                signInViewModel.fetchCustomerByEmail(response!!.token,email)


                // Navigate to the Sign-In screen
            } else {
                Toast.makeText(context, "Server Error: Try Again Later", Toast.LENGTH_SHORT).show()


            }
        }
    }

    LaunchedEffect(customerState) {
        customerState?.let {
            if (it.id != -1 ) {
                // Show success toast
              signInViewModel.saveCustomerDataToPreferences(it)
                Log.d("API Test SignIN", customerState.toString())

                navController.navigate(
                    "${Route.HOME}/${it.accounts[0].id.toLong()}/${it.accounts[0].createdAt}/${getCurrentDate()}/${it.accounts[0].balance.toFloat()}/${it.name}/${it.accounts[0].currency}"
                )

                // Navigate to the Sign-In screen
               // navController.popBackStack(Route.SIGN_IN, inclusive = false)
            } else {
                Toast.makeText(context, "Server Error: Try Again Later", Toast.LENGTH_SHORT).show()


            }
        }
    }


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { CutomAppBarTitle(text = "Sign In") },
                Modifier.background(brush = uiConstants.AUTH_BACKGROUND_COLOR)
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

                // Handle sign-in state (Loading, Success, Error)
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
                    modifier = Modifier.fillMaxWidth().height(29.dp)
                )
                Spacer(modifier = Modifier.height(64.dp))



                // Email TextField
                SpeedoTextField(
                    labelText = "Email",
                    value = email,
                    onValueChange = { signInViewModel.onEmailChange(it)
                        showError = false  // Reset error on change
                    },
                    isError = showError && !isValidEmail(email),
                    showError = showError,

                    errorMessage = "Enter a valid email, e.g., example@domain.com.",
                    placeholderText = "Enter your email address",
                    icon = R.drawable.email,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    visualTransformation = VisualTransformation.None
                )

                // Password TextField
                SpeedoTextField(
                    labelText = "Password",
                    value = password,
                    onValueChange = { signInViewModel.onPasswordChange(it)
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

                // Sign In Button
                SpeedoButton(
                    text = stringResource(R.string.sign_in_a),
                    enabled = isPasswordLengthValid,
                    isTransparent = false,
                    // Call login when clicked

                    onClick = {
                        // Check email and password validation on click
                        if (!isValidPassword(password) || !isValidEmail(email)) {
                            showError = true
                            passwordError = !isValidPassword(password)
                            emailError = !isValidEmail(email)
                        } else {
                            // If valid, proceed with sign-in
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