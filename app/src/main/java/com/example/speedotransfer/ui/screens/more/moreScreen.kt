package com.example.speedotransfer.ui.screens.more

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.elements.ArrowedSmallMenuItem
import com.example.speedotransfer.ui.theme.BodyMedium14
import com.example.speedotransfer.ui.theme.BodyRegular14
import com.example.speedotransfer.ui.theme.G0
import com.example.speedotransfer.ui.theme.G900
import com.example.speedotransfer.ui.theme.P300
import com.example.speedotransfer.ui.theme.P50

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoreScreenDesign(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    Column(modifier= modifier
        .padding(top = 24.dp, start = 16.dp, end = 16.dp)
        .verticalScroll(scrollState)) {




        ArrowedSmallMenuItem(name = "Transfer From Website", icon = R.drawable.website )
        ArrowedSmallMenuItem(name = "Favourites", icon = R.drawable.favorite )
        ArrowedSmallMenuItem(name = "Profile", icon = R.drawable.user)
        ArrowedSmallMenuItem(name = "Help", icon = R.drawable.compliance,modifier=modifier.clickable { showBottomSheet =true } )
        ArrowedSmallMenuItem(name = "logout", icon = R.drawable.logout )
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState,
            scrimColor = Color.Black.copy(alpha = 0.8f), dragHandle = {}
        ) {
            // Sheet content

Row(verticalAlignment = Alignment.CenterVertically , modifier = modifier
    .padding(54.dp)
    .fillMaxWidth().height(140.dp)) {
    
    HelpBtmSheetCardEmail(icon = R.drawable.email, title = "Send Email")
Spacer(modifier = modifier.width(32.dp))
    HelpBtmSheetCardCall(icon = R.drawable.call, title = "Call us" , "000000")


}



        }}

}



@Composable
fun HelpBtmSheetCardEmail(@DrawableRes icon :Int, title :String, modifier: Modifier = Modifier) {

    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = modifier.width(120.dp),
        colors = CardDefaults.cardColors(containerColor = G0),
        shape = RoundedCornerShape(size = 8.dp)
    ) {

        Column(modifier = modifier.padding( start =  20.dp ,end=20.dp, top =16.dp , bottom = 12.dp).fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {


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

            Text(text= title , style = BodyMedium14  , color= G900 , modifier = modifier.padding(top = 16.dp))

        }

    }

}

@Composable
fun HelpBtmSheetCardCall(@DrawableRes icon :Int, title :String,number: String, modifier: Modifier = Modifier) {

    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = modifier.width(120.dp),
        colors = CardDefaults.cardColors(containerColor = G0),
        shape = RoundedCornerShape(size = 8.dp)
        ,
    ) {

        Column(modifier = modifier.padding( start =  20.dp ,end=20.dp, top =16.dp , bottom = 12.dp).fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {


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

            Text(text= title , style = BodyMedium14  , color= G900 , modifier = modifier.padding(top = 16.dp))
            Text(text= number , style = BodyRegular14  , color= P300 )
        }

    }

}



@Preview(device = "spec:parent=pixel_5", showSystemUi = true)
@Composable
private fun MoreScreenPreview() {
    MoreScreenDesign()
//HelpBtmSheetCardCall(icon = R.drawable.call, title = "Call Us" , "000000")
}