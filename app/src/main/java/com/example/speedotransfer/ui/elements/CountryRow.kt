package com.example.speedotransfer.ui.elements

import androidx.annotation.DrawableRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.theme.BodyMedium16

@Composable
fun CountryRow(
    countryName: String,
    isSelected: Boolean,
    onCountrySelected: (String) -> Unit,
    @DrawableRes icon : Int,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .clickable { onCountrySelected(countryName) }
            .padding(vertical = 8.dp)
    ) {
        Box (

        ){
            Icon(

                painter = painterResource(id = icon),
                contentDescription = countryName,
                modifier = Modifier
                    .size(28.dp)
                    .padding(end = 8.dp),
                        tint = Color.Unspecified

            )
        }

        Text(
            text = countryName,
            style = BodyMedium16,
            color = Color(0xFF706E6C),
            modifier = Modifier.weight(1f)
        )

        if (isSelected) {
            Icon(
                painter = painterResource(id = R.drawable.check),
                contentDescription = "Selected",
            )
        }
    }
}