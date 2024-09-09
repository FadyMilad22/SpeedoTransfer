import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.speedotransfer.AppRoutes.Route
import com.example.speedotransfer.AppRoutes.Route.HOME
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.elements.BeneficiaryCard
import com.example.speedotransfer.ui.elements.CustomAppBarIcon
import com.example.speedotransfer.ui.elements.CutomAppBarTitle
import com.example.speedotransfer.ui.elements.SpeedoButton
import com.example.speedotransfer.ui.elements.StepsRow
import com.example.speedotransfer.ui.theme.BodyRegular16
import com.example.speedotransfer.ui.theme.G40
import com.example.speedotransfer.ui.theme.G500
import com.example.speedotransfer.ui.theme.G900
import com.example.speedotransfer.ui.theme.TitleSemiBold
import com.example.speedotransfer.ui.uiConstants

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferConfirmedDesign(
    navController: NavController,
    senderName: String,
    receiverName: String,
    senderAccountNumberSuffix: String,
    receiverAccountNumberSuffix: String,
    transferAmount : Int,
    currency :String,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    CutomAppBarTitle(
                        text = "Transfer",

                        )
                },
                Modifier.background(
                    brush = uiConstants.BRUSH
                ),

                navigationIcon = {
                    IconButton(onClick = {}) {
                        CustomAppBarIcon(
                            icon = R.drawable.drop_down
                        )
                    }
                },
            )
        },
        bottomBar = {

        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(start = 16.dp, end = 16.dp, top = 20.dp)
                    .verticalScroll(scrollState)
            ) {


                StepsRow(currentStep = 3)
                TransferDoneSuccesfullyArea()
                AccountsDetailsArea(
                    senderName = senderName,
                    receiverName = receiverName,
                    senderAccountNumberSuffix = senderAccountNumberSuffix.toString(),
                    receiverAccountNumberSuffix = receiverAccountNumberSuffix.toString()
                )



                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Transfer amount",
                        style = BodyRegular16,
                        color = G500,
                    )

                    Text(
                        text ="$transferAmount $currency",
                        style = BodyRegular16,
                        color = G500,
                    )
                }

                HorizontalDivider(thickness = 1.dp , color = G40 , modifier = modifier.padding(bottom = 32.dp))




                SpeedoButton(text = "Back to Home", enabled = true, isTransparent = false ,Modifier.clickable {
                    val currentEntry = navController.currentBackStackEntry
                    val previousEntry = navController.previousBackStackEntry

                    Log.d("NavBackStack", "Current destination: ${currentEntry?.destination?.route}")
                    if (previousEntry != null) {
                        Log.d("NavBackStack", "Previous destination: ${previousEntry.destination.route}")
                    } else {
                        Log.d("NavBackStack", "No previous destination")
                    }
                    navController.popBackStack("$HOME/{accountId}/{startDate}/{endDate}/{balance}/{name}/{currency}", inclusive = false)
                })
                Spacer(modifier = Modifier.padding(bottom = 16.dp))
                SpeedoButton(text = "Add to Favourite", enabled = true, isTransparent = true)
                Spacer(modifier = Modifier.padding(bottom = 16.dp))
            }


        }
    )
}

@Composable
fun AccountsDetailsArea(
    senderName: String,
    receiverName: String,
    senderAccountNumberSuffix: String,
    receiverAccountNumberSuffix: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.padding(bottom = 16.dp)) {
        Column {
            BeneficiaryCard(
                direction = R.string.from,
                clientName = senderName,
                accountNumberSuffix = senderAccountNumberSuffix
            )
            Spacer(modifier = Modifier.padding(bottom = 12.dp))
            BeneficiaryCard(
                direction = R.string.to,
                clientName = receiverName,
                accountNumberSuffix = receiverAccountNumberSuffix
            )
        }
        Image(
            painter = painterResource(id = R.drawable.right_arrow_transactions),
            contentDescription = "transfer arrows",
            modifier = Modifier
                .size(44.dp)
                .align(Alignment.Center)
        )
    }

}


@Composable
fun TransferDoneSuccesfullyArea(
    modifier: Modifier = Modifier
) {

    Column(
        Modifier
            .padding(top = 24.dp, bottom = 16.dp)
            .fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.two_d_right_sign),
            contentDescription = "Right mark",
            Modifier.size(86.dp)
        )
        Text(
            text = "Your transfer was successful",
            style = TitleSemiBold, fontSize = 20.sp,
            color = G900, modifier = modifier.padding(top = 16.dp)
        )

    }
}

@Preview(showSystemUi = true)
@Composable
private fun TransferConfirmedPreview() {
    TransferConfirmedDesign(
        rememberNavController(),
        currency = "EGP",
        transferAmount = 11213,
        senderName = "Asmaa Dosuky",
        receiverName = "Jonathon Smith",
        senderAccountNumberSuffix = "7890",
        receiverAccountNumberSuffix = "7890"
    )

}