package com.example.speedotransfer.ui.screens.authentication.signInScreen

import SignInViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.speedotransfer.AppRoutes.Route
import com.example.speedotransfer.data.network.APIClient
import com.example.speedotransfer.data.repository.SignInRepoImpl
import com.example.speedotransfer.ui.uiConstants
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    signInViewModel: SignInViewModel = viewModel(factory = SignInViewModelFactory(SignInRepoImpl(APIClient)))
) {

    // Observing StateFlow from ViewModel
    val signInState by signInViewModel.signInState.collectAsState()
    val email by signInViewModel.email.collectAsState()
    val password by signInViewModel.password.collectAsState()
    val isPasswordValid by signInViewModel.isPasswordValid.collectAsState()  // Observing password validation
    var showPassword by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { CutomAppBarTitle(text = "Sign In") },
                Modifier.background(brush = uiConstants.BRUSH2)
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
                        LaunchedEffect(Unit) {
                            navController.navigate("home")
                        }
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

                val isFormValid by derivedStateOf {
                    email.isNotBlank() && password.isNotBlank() && isPasswordValid
                }

                // Email TextField
                SpeedoTextField(
                    labelText = "Email",
                    value = email,
                    onValueChange = { signInViewModel.onEmailChange(it) },
                    placeholderText = "Enter your email address",
                    icon = R.drawable.email,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    visualTransformation = VisualTransformation.None
                )

                // Password TextField
                SpeedoTextField(
                    labelText = "Password",
                    value = password,
                    onValueChange = { signInViewModel.onPasswordChange(it) },
                    placeholderText = "Enter your password",
                    icon = if (showPassword) R.drawable.eye_comp2 else R.drawable.eye_comp,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    isPasswordVisible = showPassword,
                    visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    onTrailingIconClick = { showPassword = !showPassword }
                )

                if (!isPasswordValid) {
                    Text(
                        text = "Password must be at least 6 characters, include one uppercase letter, one lowercase letter, and one special character.",
                        color = D300,
                        style = BodyRegular14,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Sign In Button
                SpeedoButton(
                    text = stringResource(R.string.sign_in_a),
                    enabled = isFormValid,
                    isTransparent = false,
                    onClick = { signInViewModel.logIn() }
                )

                Spacer(modifier = Modifier.height(16.dp))

                SignTrailingText(
                    question = R.string.already_have_an_account_q,
                    answer = R.string.sign_up,
                    navController = navController,
                    distination = Route.SIGNUP)

            }
        }
    )
}
