package com.example.speedotransfer.ui.elements

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.speedotransfer.ui.theme.BodyRegular16
import com.example.speedotransfer.ui.theme.G100
import com.example.speedotransfer.ui.theme.G40
import com.example.speedotransfer.ui.theme.G900
import com.example.speedotransfer.ui.theme.P300
import com.example.speedotransfer.ui.theme.P50

@Composable
fun ArrowedLargeMenuItem(
    name: String,
    descritipn: String,
    @DrawableRes icon: Int,
    enableStroke: Boolean,
    modifier: Modifier = Modifier
) {

    Column(modifier.background(Color.Transparent)) {

        Row(
            verticalAlignment = Alignment.CenterVertically, modifier = modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth()
        ) {

            Box(
                modifier = modifier
                    .size(48.dp)
                    //.align(Alignment.CenterVertically)
                    .background(color = P50, shape = RoundedCornerShape(8.dp))
                // .padding(16.dp)

            ) {
                Image(
                    painter = painterResource(icon),
                    contentDescription = "$name Icon",
                    colorFilter = ColorFilter.tint(P300),
                    modifier =
                    modifier
                        .size(20.dp)
                        .align(Alignment.Center)
                )
            }

            Column(
                modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            ) {
                Text(text = name, style = BodyMedium16, color = G900)
                Text(text = descritipn, style = BodyRegular16, color = G100)

            }

            Image(
                painter = painterResource(R.drawable.chevron),
                contentDescription = "Arrow Icon", modifier =
                modifier
                    .size(24.dp)
                    .align(Alignment.CenterVertically)
            )


        }
        if (enableStroke) {
            HorizontalDivider(
                color = G40,
                thickness = 1.dp,
                modifier = Modifier.fillMaxWidth()
            )
        }

    }

}


@Preview(showSystemUi = true)
@Composable
private fun ArrowedLargeMenuItemPreview() {
    ArrowedLargeMenuItem(
        name = "Settings",
        descritipn = "Change your settings",
        icon = R.drawable.setting,
        enableStroke = true
    )

}