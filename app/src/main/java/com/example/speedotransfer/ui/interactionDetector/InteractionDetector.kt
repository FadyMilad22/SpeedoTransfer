package com.example.speedotransfer.ui.interactionDetector

import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.speedotransfer.AppRoutes.Route
import com.example.speedotransfer.AppRoutes.Route.SIGN_IN
import com.example.speedotransfer.data.network.APIClient
import com.example.speedotransfer.data.repository.logout.LogoutRepoImpl

private const val INACTIVITY_TIMEOUT = 2 * 60 * 1000L // 2 minutes

@Composable
fun InactivityHandler(
    navController: NavController,
    token: String, // Token passed for logout
    content: @Composable () -> Unit // Your main UI content
) {

    // Initialize ViewModel
    val logoutViewModel: LogoutViewModel = viewModel(
        factory = LogoutViewModelFactory(
            LogoutRepoImpl(APIClient)
        )
    )

    val logoutState by logoutViewModel.logoutState.collectAsState()

    // Check if logout was successful and navigate to Sign-In screen
    if (logoutState?.httpStatus == "OK") {
        if (navController.currentDestination?.route != SIGN_IN) {
            Toast.makeText(LocalContext.current, "Successfully logged out", Toast.LENGTH_SHORT).show()
            navController.navigate(SIGN_IN) {
                popUpTo(0) { inclusive = true } // Clear the backstack and navigate to sign in
            }
            // Reset the state back to null to ensure the inactivity handler works on subsequent logins.
            logoutViewModel.setStateBacktoNull()
        }
    }

    // Track last interaction time
    var lastInteractionTime by remember { mutableLongStateOf(System.currentTimeMillis()) }

    // Handler for inactivity detection
    val handler = remember { Handler(Looper.getMainLooper()) }

    // FIX: Declare the Runnable outside, so it can reference itself properly
    lateinit var checkInactivityRunnable: Runnable

    checkInactivityRunnable = Runnable {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastInteractionTime >= INACTIVITY_TIMEOUT) {
            // If the user is inactive for more than the timeout period, log out
            if (logoutState == null) { // Ensure logout happens only once per session
                logoutViewModel.logout(token)
            }
        } else {
            handler.postDelayed(checkInactivityRunnable, INACTIVITY_TIMEOUT) // FIX: Use the declared variable for the Runnable
        }
    }

    DisposableEffect(Unit) {
        // Schedule the first check
        handler.postDelayed(checkInactivityRunnable, INACTIVITY_TIMEOUT)

        // Cleanup handler when the composable is disposed
        onDispose {
            handler.removeCallbacks(checkInactivityRunnable)
        }
    }

    // Wrapper for detecting user interaction and resetting the timer
    InteractionListener(onInteraction = {
        lastInteractionTime = System.currentTimeMillis() // Reset the timer on interaction
    }) {
        content()
    }
}

// Custom composable that listens for touch interactions and resets the inactivity timer
@Composable
fun InteractionListener(onInteraction: () -> Unit, content: @Composable () -> Unit) {
    Box(
        modifier = Modifier.pointerInput(Unit) {
            detectTapGestures {
                onInteraction() // Reset timer on user interaction
            }
        }
    ) {
        content()
    }
}
