package com.example.speedotransfer.ui.elements

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.theme.BodyMedium14
import com.example.speedotransfer.ui.theme.G0
import com.example.speedotransfer.ui.theme.G100
import com.example.speedotransfer.ui.theme.G900
import com.example.speedotransfer.ui.theme.P300
import com.example.speedotransfer.ui.theme.P50


@Composable
fun HelpBtmSheetCard(@DrawableRes icon :Int,title :String,modifier: Modifier = Modifier) {

    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        //modifier = modifier.background(G0),
        colors = CardDefaults.cardColors(containerColor = G0),
        shape = RoundedCornerShape(size = 8.dp)
    ) {

        Column(modifier = modifier.padding( start =  20.dp ,end=20.dp, top =16.dp , bottom = 32.dp)) {


            Box(
                modifier = modifier
                    .size(55.dp)
                    .align(Alignment.CenterHorizontally)
                    .background(color = P50, shape = RoundedCornerShape(8.dp))
                // .padding(16.dp)

            ) {
                Image(
                    painter = painterResource(icon),
                    contentDescription = "$title Icon", colorFilter = ColorFilter.tint(P300),modifier=
                    modifier
                        .size(24.dp)
                        .align(Alignment.Center)
                ) }

Text(text= title , style = BodyMedium14  , color=G900 , modifier = modifier.padding(top = 16.dp))

        }

    }

}


@Preview(showSystemUi = true)
@Composable
private fun HelpBtmSheetCardPreview() {

    HelpBtmSheetCard(R.drawable.email,"Send Email")

}