package com.example.speedotransfer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.speedotransfer.ui.screens.CompleteSignUpScreen
import com.example.speedotransfer.ui.screens.SignUpScreen
import com.example.speedotransfer.ui.theme.SpeedoTransferTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
        )
        setContent {
            SpeedoTransferTheme {
                CompleteSignUpScreen()

            }
        }
    }
}

@Composable
fun Login(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                color = Color.Blue
            )
    ) {
        Text(text = "Hello")
    }
}



