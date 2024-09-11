package com.example.speedotransfer.ui.elements

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ProfileImage(imageResource: Int?, name: String, modifier: Modifier = Modifier) {
    if (imageResource != null) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "Profile Picture",
            modifier = modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color.Gray)
        )
    } else {
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .size(48.dp)
                .clip(CircleShape)
                .background(Color.Gray)
        ) {
            Text(
                text = name.first().uppercaseChar().toString(),
//                text = "AD",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}
