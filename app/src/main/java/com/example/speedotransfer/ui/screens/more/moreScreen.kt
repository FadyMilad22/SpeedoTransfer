package com.example.speedotransfer.ui.screens.more

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.elements.ArrowedSmallMenuItem
import com.example.speedotransfer.ui.elements.HelpBtmSheetCard

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
    .fillMaxWidth()) {
    
    HelpBtmSheetCard(icon = R.drawable.email, title = "Send Email")
Spacer(modifier = modifier.width(32.dp))
    HelpBtmSheetCard(icon = R.drawable.call, title = "Call us\n 000000")


}


        }}

}


@Preview(device = "spec:parent=pixel_5", showSystemUi = true)
@Composable
private fun MoreScreenPreview() {
    MoreScreenDesign()

}