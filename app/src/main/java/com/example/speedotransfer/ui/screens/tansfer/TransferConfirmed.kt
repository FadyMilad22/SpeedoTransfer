import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.speedotransfer.AppRoutes.Route.HOME
import com.example.speedotransfer.R
import com.example.speedotransfer.data.network.APIClient
import com.example.speedotransfer.data.repository.favourite.FavRepoImpl
import com.example.speedotransfer.model.FavouriteRequest
import com.example.speedotransfer.ui.elements.BeneficiaryCard
import com.example.speedotransfer.ui.elements.CustomAppBarIcon
import com.example.speedotransfer.ui.elements.CutomAppBarTitle
import com.example.speedotransfer.ui.elements.SpeedoButton
import com.example.speedotransfer.ui.elements.StepsRow
import com.example.speedotransfer.ui.screens.more.favourite.FavViewModelFactory
import com.example.speedotransfer.ui.theme.BodyRegular16
import com.example.speedotransfer.ui.theme.G40
import com.example.speedotransfer.ui.theme.G500
import com.example.speedotransfer.ui.theme.G900
import com.example.speedotransfer.ui.theme.TitleSemiBold
import com.example.speedotransfer.ui.uiConstants
import kotlinx.coroutines.launch

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
    favViewModel: FavViewModel = viewModel(
        factory = FavViewModelFactory(
            FavRepoImpl(APIClient)
        )
    ),  // ViewModel is passed here
    token: String,  // Authentication token is passed here
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    val scope = rememberCoroutineScope()  // Define the coroutine scope here

    // Collect the addFavResponse from the ViewModel's state
    val addFavResponse by favViewModel.addFavResponse.collectAsState()
    LaunchedEffect(addFavResponse) {
        if (addFavResponse != null) {
            Log.d("Favourite Added", "Added at: ${addFavResponse?.addedAt}")
        } else {
            Log.d("Favourite Response", "addFavResponse is null")
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    CutomAppBarTitle(
                        text = "Transfer",

                        )
                },
                Modifier.background(
                    brush = uiConstants.APP_BACKGROUND_COLOR
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




                SpeedoButton(text = "Back to Home", enabled = true, isTransparent = false ,) {
                    val currentEntry = navController.currentBackStackEntry
                    val previousEntry = navController.previousBackStackEntry

                    Log.d("NavBackStack", "Current destination: ${currentEntry?.destination?.route}")
                    if (previousEntry != null) {
                        Log.d("NavBackStack", "Previous destination: ${previousEntry.destination.route}")
                    } else {
                        Log.d("NavBackStack", "No previous destination")
                    }
                    navController.popBackStack("$HOME/{accountId}/{startDate}/{endDate}/{balance}/{name}/{currency}", inclusive = false)
                }
                Spacer(modifier = Modifier.padding(bottom = 16.dp))
                // Button to add the recipient to Favourites
                SpeedoButton(
                    text = "Add to Favourite",
                    enabled = true,
                    isTransparent = true,
                    onClick =  {
                        // Launch a coroutine to add the customer to favourites
                        scope.launch {
                            try {
                                Log.d("Favourite", "Attempting to add favourite")
                                val favouriteRequest = FavouriteRequest(
                                    accountNumber = receiverAccountNumberSuffix,
                                    recipientName = receiverName
                                )

                                // Call ViewModel to add the favourite
                                favViewModel.addCustomerToFavourite(token, favouriteRequest)

                                // Log when the request has been made
                                Log.d("Favourite", "Request sent to add favourite")

                            } catch (e: Exception) {
                                // Handle and log any errors that occur
                                Log.e("Favourite Error", "Error during addCustomerToFavourite: ${e.localizedMessage}")
                            }
                        }
                    }
                )

                Spacer(modifier = Modifier.padding(bottom = 80.dp))
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
//    TransferConfirmedDesign(
//        rememberNavController(),
//        currency = "EGP",
//        transferAmount = 11213,
//        senderName = "Asmaa Dosuky",
//        receiverName = "Jonathon Smith",
//        senderAccountNumberSuffix = "7890",
//        receiverAccountNumberSuffix = "7890"
//    )

}