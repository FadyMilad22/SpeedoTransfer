package com.example.speedotransfer.ui.screens.authentication.signUpScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.speedotransfer.AppRoutes.Route
import com.example.speedotransfer.R
import com.example.speedotransfer.data.network.APIClient
import com.example.speedotransfer.data.repository.SignUpRepoImpl
import com.example.speedotransfer.ui.elements.CutomAppBarTitle
import com.example.speedotransfer.ui.elements.SignTrailingText
import com.example.speedotransfer.ui.elements.SpeedoButton
import com.example.speedotransfer.ui.elements.SpeedoTextField
import com.example.speedotransfer.ui.theme.BodyRegular14
import com.example.speedotransfer.ui.theme.BodyRegular16
import com.example.speedotransfer.ui.theme.D300
import com.example.speedotransfer.ui.theme.G100
import com.example.speedotransfer.ui.theme.LinkMediumTextStyle
import com.example.speedotransfer.ui.theme.P300
import com.example.speedotransfer.ui.theme.TitleSemiBold
import com.example.speedotransfer.ui.uiConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    signUpViewModel: SignUpViewModel =
        viewModel(factory = SignUpViewModelFactory(SignUpRepoImpl(
        APIClient
    )))
) {

    // Observing StateFlow from ViewModel
    val fullName by signUpViewModel.fullName.collectAsState()
    val email by signUpViewModel.email.collectAsState()
    val password by signUpViewModel.password.collectAsState()
    val confirmPassword by signUpViewModel.confirmPassword.collectAsState()
    val isPasswordValid by signUpViewModel.isPasswordValid.collectAsState()
    val isConfirmPasswordValid by signUpViewModel.isConfirmPasswordValid.collectAsState()

    // Local state for toggling visibility of the password fields
    var showPassword by remember { mutableStateOf(false) }
    var showConfirmPassword by remember { mutableStateOf(false) }  // Added showConfirmPassword
//    val signUpState by signUpViewModel.signUpState.collectAsState()
//
//    if (signUpState is SignUpState.Success) {
//        // Navigate to SignInScreen after successful sign-up
//        LaunchedEffect(Unit) {
//            navController.navigate(Routes.SIGN_IN)
//        }
//    }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { CutomAppBarTitle(text = "Sign Up") },
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
                Spacer(modifier = Modifier.height(56.dp))

                Text(
                    text = "Speedo Transfer",
                    style = TitleSemiBold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(29.dp)
                )
                Spacer(modifier = Modifier.height(64.dp))

                // Full Name TextField
                SpeedoTextField(
                    labelText = "Full Name",
                    value = fullName,
                    onValueChange = { signUpViewModel.onFullNameChange(it) },
                    placeholderText = "Enter your Full Name",
                    icon = R.drawable.user,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    visualTransformation = VisualTransformation.None
                )

                // Email TextField
                SpeedoTextField(
                    labelText = "Email",
                    value = email,
                    onValueChange = { signUpViewModel.onEmailChange(it) },
                    placeholderText = "Enter your email address",
                    icon = R.drawable.email,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    visualTransformation = VisualTransformation.None
                )

                // Password TextField
                SpeedoTextField(
                    labelText = "Password",
                    value = password,
                    onValueChange = { signUpViewModel.onPasswordChange(it) },
                    placeholderText = "Enter your password",
                    icon = if (showPassword) R.drawable.eye_comp2 else R.drawable.eye_comp,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    isPasswordVisible = showPassword,
                    visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    onTrailingIconClick = { showPassword = !showPassword }
                )

                // Error message if password is invalid
                if (!isPasswordValid) {
                    Text(
                        text = "Password must be at least 6 characters, include one uppercase letter, one lowercase letter, and one special character.",
                        color = D300,
                        style = BodyRegular14,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                    )
                }

                // Confirm Password TextField
                SpeedoTextField(
                    labelText = "Confirm password",
                    value = confirmPassword,
                    onValueChange = { signUpViewModel.onConfirmPasswordChange(it) },
                    placeholderText = "Confirm your password",
                    icon = if (showConfirmPassword) R.drawable.eye_comp2 else R.drawable.eye_comp,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    isPasswordVisible = showConfirmPassword,
                    visualTransformation = if (showConfirmPassword) VisualTransformation.None else PasswordVisualTransformation(),
                    onTrailingIconClick = { showConfirmPassword = !showConfirmPassword }
                )

                // Error message if confirm password doesn't match
                if (!isConfirmPasswordValid) {
                    Text(
                        text = "Passwords do not match.",
                        color = D300,
                        style = BodyRegular14,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Sign Up Button
                SpeedoButton(
                    text = "Sign Up",
                    enabled =  signUpViewModel.isFormValid() && confirmPassword.isNotBlank() && isConfirmPasswordValid && confirmPassword==password,
                    isTransparent = false,
                    onClick = {

                        navController.navigate(Route.COMPLETE_SIGN_UP)}  // Calling registerCustomer on click
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row() {
                    Text(text = "${stringResource(id = R.string.already_have_an_account_q)} "
                        , style = BodyRegular16, color = G100,)
                        Text(text = stringResource(id = R.string.sign_in_a),
                        style = LinkMediumTextStyle,
                        color = P300,
                        textDecoration = TextDecoration.Underline,
                        modifier=modifier.clickable {
                            navController.popBackStack(Route.SIGN_IN,false)
                        }
                    )

                }

            }
        }
    )
}
