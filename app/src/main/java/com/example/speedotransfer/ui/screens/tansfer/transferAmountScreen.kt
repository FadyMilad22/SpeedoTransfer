import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.speedotransfer.AppRoutes.Route
import com.example.speedotransfer.R
import com.example.speedotransfer.ui.elements.CustomAppBarIcon
import com.example.speedotransfer.ui.elements.CutomAppBarTitle
import com.example.speedotransfer.ui.elements.FavListBeneficiaryCard
import com.example.speedotransfer.ui.elements.SpeedoButton
import com.example.speedotransfer.ui.elements.SpeedoTextField
import com.example.speedotransfer.ui.elements.StepsRow
import com.example.speedotransfer.ui.theme.*
import com.example.speedotransfer.ui.screens.more.favourite.FavViewModelFactory
import com.example.speedotransfer.data.network.APIClient
import com.example.speedotransfer.data.repository.favourite.FavRepoImpl
import com.example.speedotransfer.model.FavouriteResponse
import com.example.speedotransfer.ui.uiConstants
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransferAmountDesign(
    navController: NavController,
    senderName: String,
    senderAccountNumberSuffix: String,
    currency: String,
    token :String,
    favViewModel: FavViewModel = viewModel(
        factory = FavViewModelFactory(
            FavRepoImpl(APIClient)
        )
    ),  // ViewModel is passed here
    modifier: Modifier = Modifier
) {

    val scrollState = rememberScrollState()
    var amount by rememberSaveable { mutableStateOf("") }

    // Collecting the list of favourites from the ViewModel (NEW)
    val favourites by favViewModel.favourites.collectAsState()

    // Fetch all favourites when the screen loads (NEW)
    LaunchedEffect(Unit) {
        favViewModel.getAllFav("token")  // Pass the appropriate authToken here
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
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(horizontal = 16.dp)
                    .verticalScroll(scrollState)
            ) {
                StepsRow(currentStep = 1)
                AmountArea(amount = amount, onAmountChange = { amount = it })

                // Updated to use the dynamic favourites list from the ViewModel (NEW)
                RecipientInformationArea(
                    navController,
                    senderName,
                    senderAccountNumberSuffix,
                    amount,
                    currency,
                    favourites,
                    token,// Using the collected list of favourites
                    modifier
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecipientInformationArea(
    navController: NavController,
    senderName: String,
    senderAccountNumberSuffix: String,
    amount: String,
    currency: String,
    favouritesList: List<FavouriteResponse>,
    token :String,// Accept the dynamic list from ViewModel
    modifier: Modifier = Modifier
) {
    var recipientName by rememberSaveable { mutableStateOf("") }
    var recipientAccountNumber by rememberSaveable { mutableStateOf("") }

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Text(text = "Recipient Information", style = BodyRegular16, color = G700)
        Row(
            modifier = Modifier.padding(horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.favorite),
                contentDescription = "Favourites Icon",
                tint = P300,  // Assuming P300 is a Color
                modifier = modifier.size(20.dp)
            )
            Text(
                text = "Favourites",
                style = BodyMedium14,
                color = P300,
                modifier = modifier.clickable { showBottomSheet = true }
            )
        }
    }

    SpeedoTextField(
        labelText = "Recipient Name",
        value = recipientName,
        onValueChange = { recipientName = it },
        placeholderText = "Enter Recipient Name",
        icon = R.drawable.transparent_image,
        keyboardOptions = KeyboardOptions.Default
    )

    SpeedoTextField(
        labelText = "Recipient Account",
        value = recipientAccountNumber,
        onValueChange = { recipientAccountNumber = it },
        placeholderText = "Enter Recipient Account Number",
        icon = R.drawable.transparent_image,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
    )

    Spacer(modifier = modifier.padding(bottom = 32.dp))

    SpeedoButton(text = "Continue",
        enabled = amount.isNotBlank() && recipientAccountNumber.isNotBlank()&&recipientName.isNotBlank(),
        isTransparent = false) {
        navController.navigate(
            route = "${Route.CONFIRM_TRANSACTION}/${amount}/${currency}/$senderName/${recipientName}/$senderAccountNumberSuffix/$recipientAccountNumber/$token"
        )
    }

    Spacer(modifier = Modifier.padding(bottom = 16.dp))

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            sheetState = sheetState,
            scrimColor = Color.Black.copy(alpha = 0.8f),
            dragHandle = {}
        ) {
            // Sheet content for displaying the favourites list
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.padding(top = 32.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier.padding(bottom = 16.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.favorite),
                        contentDescription = "Favourite Icon",
                        tint = P300,
                        modifier = modifier.size(24.dp)
                    )
                    Text(
                        text = "Favourite List",
                        style = BodyRegular16,
                        fontSize = 20.sp,
                        color = P300,
                        modifier = modifier.padding(start = 8.dp)
                    )
                }

                LazyColumn {
                    items(favouritesList) { favourite ->
                        FavListBeneficiaryCard(
                            clientName = favourite.recipientName,
                            accountNumberSuffix = favourite.accountNumber,
                            modifier = Modifier.clickable {
                                // Set the recipient name and account number when a favourite is selected
                                recipientName = favourite.recipientName
                                recipientAccountNumber = favourite.accountNumber

                                // Dismiss the bottom sheet
                                scope.launch {
                                    sheetState.hide()
                                    showBottomSheet = false
                                }
                            }
                        )
                        Spacer(modifier = modifier.height(16.dp))
                    }
                }
            }
        }
    }
}

@Composable
fun AmountArea(
    amount: String,
    onAmountChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        text = "How much are you sending?",
        style = TitleSemiBold,
        color = G900,
        modifier = modifier.padding(vertical = 24.dp)
    )

    Card(
        colors = CardDefaults.cardColors(G0),
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 24.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(modifier.padding(top = 16.dp, bottom = 32.dp, start = 8.dp, end = 8.dp)) {
            SpeedoTextField(
                labelText = "Amount",
                value = amount,
                onValueChange = onAmountChange,
                placeholderText = "Enter amount",
                icon = R.drawable.transparent_image,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
            )
        }
    }
}

@Preview(showSystemUi = true, device = "spec:parent=pixel_5")
@Composable
private fun TransferAmountDesignPreview() {
//    TransferAmountDesign(
//        navController = rememberNavController(),
//        senderName = "Fady",
//        senderAccountNumberSuffix = "4893",
//        currency = "EGP"
//    )
}
