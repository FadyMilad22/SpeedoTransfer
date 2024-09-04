package com.example.speedotransfer.ui.screens.more

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.elements.ArrowedSmallMenuItem

@Composable
fun MoreScreenDesign(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()

    Column(modifier=modifier.padding(top= 24.dp, start = 16.dp,end=16.dp).verticalScroll(scrollState)) {



        ArrowedSmallMenuItem(name = "Transfer From Website", icon = R.drawable.website )
        ArrowedSmallMenuItem(name = "Favourites", icon = R.drawable.favorite )
        ArrowedSmallMenuItem(name = "Profile", icon = R.drawable.user )
        ArrowedSmallMenuItem(name = "Help", icon = R.drawable.compliance )
        ArrowedSmallMenuItem(name = "logout", icon = R.drawable.logout )
    }
}


@Preview(showSystemUi = true, device = "spec:parent=pixel_5,orientation=landscape")
@Composable
private fun MoreScreenPreview() {
    MoreScreenDesign()

}