package com.example.speedotransfer.ui.elements

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.theme.BodyMedium16
import com.example.speedotransfer.ui.theme.G200
import com.example.speedotransfer.ui.theme.G40


@Composable
fun ArrowedSmallMenuItem(name: String, @DrawableRes icon: Int, modifier: Modifier = Modifier) {

    Column(modifier.background(Color.Transparent)) {

        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth()
        ) {


            Image(
                painter = painterResource(icon),
                contentDescription = "$name Icon", colorFilter = ColorFilter.tint(G200), modifier =
                modifier
                    .size(24.dp)
                // .align(Alignment.Center)
            )


            Text(
                text = name,
                style = BodyMedium16,
                color = G200,
                modifier = modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            )



            Image(
                painter = painterResource(R.drawable.chevron),
                contentDescription = "Arrow Icon", modifier =
                modifier
                    .size(24.dp)
                    .align(Alignment.CenterVertically)
            )


        }

        HorizontalDivider(
            color = G40,
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()

        )

    }

}


@Preview(showSystemUi = true)
@Composable
private fun ArrowedSmallMenuItemPreview() {
    ArrowedSmallMenuItem(name = "Settings", icon = R.drawable.setting)

}