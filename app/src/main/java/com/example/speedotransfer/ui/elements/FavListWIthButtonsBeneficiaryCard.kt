package com.example.speedotransfer.ui.elements

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.theme.BodyMedium16
import com.example.speedotransfer.ui.theme.BodyRegular16
import com.example.speedotransfer.ui.theme.D300
import com.example.speedotransfer.ui.theme.G100
import com.example.speedotransfer.ui.theme.G40
import com.example.speedotransfer.ui.theme.G700
import com.example.speedotransfer.ui.theme.G900
import com.example.speedotransfer.ui.theme.P300
import com.example.speedotransfer.ui.theme.P50
import com.example.speedotransfer.ui.theme.P75
import com.example.speedotransfer.ui.theme.TitleSemiBold


@Composable
fun FavListWithButtonsBeneficiaryCard(
    clientName: String,
    accountNumberSuffix: String,
    modifier: Modifier = Modifier, onEditClick: ()->Unit={}, onDeleteClick:  ()->Unit={}){

    var edit by remember { mutableStateOf(false) }
    var delete by remember { mutableStateOf(false) }


    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(P50, RoundedCornerShape(8.dp))
    ) {
        Row(modifier = modifier.padding(16.dp)) {


            Box(
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.CenterVertically)
                    .background(color = G40, shape = CircleShape)

            ) {
                Image(
                    painter = painterResource(id = R.drawable.bank),
                    contentDescription = "bankIcon", colorFilter = ColorFilter.tint(G700),
                    modifier =
                    modifier
                        .size(32.dp)
                        .align(Alignment.Center)
                )
            }
            Column(
                modifier = modifier.padding(start = 16.dp),

                ) {
                Column(
                    horizontalAlignment = Alignment.Start
                ) {


                    Text(
                        text = clientName,
                        style = BodyMedium16,
                        color = G900,
                        modifier = modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = buildAnnotatedString {
                            append(
                                String.format(
                                    stringResource(id = R.string.account_number),
                                    accountNumberSuffix
                                )
                            )
                        },
                        style = BodyRegular16,
                        color = G100
                    )
                }
            }


        }


        Row(
            modifier = modifier.padding(16.dp).weight(1f).fillMaxWidth(), // Optional padding around the row
            horizontalArrangement = Arrangement.End,// Space between the icons
        ) {
            IconButton(
                onClick = {
                    // Handle edit action here
                    onEditClick()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.edit),
                    contentDescription = "Edit", modifier = modifier.size(24.dp)
                ,tint= G100)
            }
            IconButton(
                onClick = {
                   onDeleteClick()
                    // Handle delete action here
                }

            ) {
                Icon(
                    painter = painterResource(id = R.drawable.delete),
                    contentDescription = "Delete", modifier = modifier.size(24.dp), tint = D300
                )
            }
        }


    }



}




@Preview(showSystemUi = true)
@Composable
private fun FavListWithButtonsBeneficiaryCardPreview() {

    FavListWithButtonsBeneficiaryCard("Fady Milad", "1234")


}