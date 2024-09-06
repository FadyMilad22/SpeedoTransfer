package com.example.speedotransfer.ui.elements

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.theme.Body2

@Composable
fun NotificationBanner(message: String, onCloseClick: () -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(92.dp)
            .background(Color(0xFFE3E2E2), shape = RoundedCornerShape(4.5.dp))
            .padding(16.dp),
    ) {
        Icon(
            painter = painterResource(id = R.drawable.alert),
            contentDescription = "Warning",
            modifier = modifier.size(27.dp),
            tint = Color.Unspecified
        )
        Spacer(modifier = modifier.width(9.5.dp))
        Text(
            text = message,
            modifier = modifier
                .weight(1f)
                .wrapContentHeight(),
            style = Body2,
            textAlign = TextAlign.Center,
            color = Color(0xFF565552)
        )
        Spacer(modifier = modifier.width(9.5.dp))
        Icon(
            painter = painterResource(id = R.drawable.cancel),
            contentDescription = "Close",
            modifier = modifier
                .size(26.5.dp)
                .clickable { onCloseClick() },
            tint = Color.Unspecified
        )
    }
}