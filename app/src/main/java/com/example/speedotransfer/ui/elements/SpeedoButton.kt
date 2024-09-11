package com.example.speedotransfer.ui.elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.speedotransfer.ui.theme.ButtonTextStyle
import com.example.speedotransfer.ui.theme.G0
import com.example.speedotransfer.ui.theme.G100
import com.example.speedotransfer.ui.theme.P300

@Composable
fun SpeedoButton(
    text: String,
    enabled: Boolean,
    isTransparent: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp),
        border = BorderStroke(
            1.5.dp,
            color = if (enabled) P300 else G100
        ),
        shape = RoundedCornerShape(6.dp),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isTransparent) G0 else P300,
            contentColor = if (isTransparent) P300 else G0,
            disabledContainerColor = G100,
            disabledContentColor = G0
        )
    ) {
        Text(
            text = text,
            style = ButtonTextStyle,
            modifier = Modifier.height(21.dp)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SpeedoButtonPreview() {
    SpeedoButton(text = "Login", enabled = true, isTransparent = true)
}
