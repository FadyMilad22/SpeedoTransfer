package com.example.speedotransfer.ui.elements

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.speedotransfer.ui.theme.BodyMedium16
import com.example.speedotransfer.ui.theme.BodyRegular16
import com.example.speedotransfer.ui.theme.G100
import com.example.speedotransfer.ui.theme.G40
import com.example.speedotransfer.ui.theme.G900

@Composable
fun ProfileInfoItem(
    title: String,
    details: String,
    enableStroke: Boolean,
    modifier: Modifier = Modifier
) {
    Column (modifier = modifier.height(72.dp)){
        Text(text = title, style = BodyMedium16, color = G900, modifier = modifier.padding(bottom = 8.dp))
        Text(text = details, style = BodyRegular16, color = G100,modifier = modifier.padding(bottom = 16.dp))

        if (enableStroke) {
            HorizontalDivider(
                color = G40,
                thickness = 1.dp,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }

}