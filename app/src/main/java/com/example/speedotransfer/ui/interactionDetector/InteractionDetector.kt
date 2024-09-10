package com.example.speedotransfer.ui.interactionDetector

import android.os.Handler
import android.os.Looper
import android.util.Log
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
    token:String, // Function to call logout logic
    content: @Composable () -> Unit // Your main UI content
) {

  val   logoutViewModel: LogoutViewModel = viewModel(
    factory = LogoutViewModelFactory(
        LogoutRepoImpl(APIClient)
    )

    )

    val logoutState by logoutViewModel.logoutState.collectAsState()

//    if (logoutState?.httpStatus  == "OK") {
//        // Show the success toast and navigate back to the profile screen
//        Log.d("logout" , "${logoutState?.httpStatus}")
//        Toast.makeText(LocalContext.current, "Successfully", Toast.LENGTH_SHORT).show()
//        navController.popBackStack(SIGN_IN, inclusive = false)
//    }
// FIX: Check if the logout was successful and only navigate once.
    if (logoutState?.httpStatus == "OK") {
        // FIX: Prevent multiple navigation actions by checking the current route.
        if (navController.currentDestination?.route != SIGN_IN) {
            Toast.makeText(LocalContext.current, "Successfully logged out", Toast.LENGTH_SHORT).show()
            navController.navigate(SIGN_IN) {
                popUpTo(0) { inclusive = true }
            // Clear the backstack and navigate to sign in

            }
        logoutViewModel.setStateBacktoNull()
        }
    }


    var lastInteractionTime by remember { mutableLongStateOf(System.currentTimeMillis()) }

    // Create the handler outside of the Runnable to avoid referencing the same object
    val handler = remember { Handler(Looper.getMainLooper()) }

    DisposableEffect(Unit) {
        // Function that checks inactivity
        val checkInactivityRunnable = object : Runnable {
            override fun run() {
                val currentTime = System.currentTimeMillis()
                if (currentTime - lastInteractionTime >= INACTIVITY_TIMEOUT) {
                    // User is inactive for more than 2 minutes, log out
                    if (logoutState == null) {
                    logoutViewModel.logout(token)}
                    navController.navigate(Route.SIGN_IN) {
                        popUpTo(0) { inclusive = true } // Clear the backstack
                    }
                } else {
                    // Re-schedule the check after INACTIVITY_TIMEOUT
                    handler.postDelayed(this, INACTIVITY_TIMEOUT)
                }
            }
        }

        // Schedule the first check
        handler.postDelayed(checkInactivityRunnable, INACTIVITY_TIMEOUT)

        // Cleanup the handler when the composable is disposed
        onDispose {
            handler.removeCallbacks(checkInactivityRunnable)
        }
    }

    // Wrapper for your app's content, which listens for user interactions
    InteractionListener(onInteraction = {
        lastInteractionTime = System.currentTimeMillis() // Reset the timer on interaction
    }) {
        content()
    }
}

// Custom composable that listens for touch interactions and resets the timer
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
