import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mae.apsport.widgets.CustomTopAppBar
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mae.apsport.model.Courts
import com.mae.apsport.model.NewBooking
import com.mae.apsport.nav.Routes

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BookingPage(navController: NavHostController){
    val backStackEntry by navController.currentBackStackEntryAsState()
    val courtKey = backStackEntry?.arguments?.getString("court_id")
    val courtName = backStackEntry?.arguments?.getString("court_name")
    val courtRate = backStackEntry?.arguments?.getString("court_rate")

    Scaffold(
        topBar = {
            CustomTopAppBar(title = "APSport", navController = navController, showBackIcon = false)
        },
        bottomBar = {
            CheckoutBottomBar(
                navController,
                onBook = { confirmPayment(courtRate!!, courtKey!!, courtName!!, courtRate, navController) }
            )
        }
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            BookingScreen(navController = navController)
        }
    }
}

@Composable
fun BookingScreen(navController: NavController){
    val backStackEntry by navController.currentBackStackEntryAsState()
    val courtKey = backStackEntry?.arguments?.getString("court_id")
    val courtName = backStackEntry?.arguments?.getString("court_name")

    Column(modifier = Modifier
        .padding(17.dp)
        .verticalScroll(rememberScrollState())
    ) {
        SectionTitle(label = "Booking for $courtName")
        if (courtKey != null) LoadCourtDetails(viewModel = viewModel() {CourtsViewModel(courtKey)}, navController = navController)

    }
}

@Composable
fun LoadCourtDetails(viewModel: CourtsViewModel = viewModel(), navController: NavController){
    when (val res = viewModel.response.value) {
        is CourtsStates.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        is CourtsStates.Success -> {
            CourtDetailsSection(courtData = res.data, navController)
        }
        is CourtsStates.Failure -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = res.message,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                )
            }
        }
        else -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Error fetching fata",
                    fontSize = MaterialTheme.typography.h5.fontSize,
                )
            }
        }
    }
}

@Composable
fun CourtDetailsSection(courtData: MutableList<Courts>, navController: NavController){
    var creditCardInput by remember { mutableStateOf("") }
    var expiryInput by remember { mutableStateOf("") }
    var cvvInput by remember { mutableStateOf("") }
    Column(modifier = Modifier
        .padding(17.dp)
        .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        SectionTitle(label = "Court Booking Time")
        Box(modifier = Modifier.padding(start = 10.dp)) {
            NormalText(label = "From ${getTimeString(courtData[0].start_time!!)}")
        }
        Box(modifier = Modifier.padding(start = 10.dp)) {
            NormalText(label = "To ${getTimeString(courtData[0].end_time!!)}")
        }
        SectionTitle(label = "Court Price")
        Box(modifier = Modifier.padding(start = 10.dp)) {
            NormalText(label = "RM ${courtData[0].rate}")
        }
        Spacer(modifier = Modifier.height(10.dp))
        SectionTitle(label = "Payment Details")
        Card(
            shape = RoundedCornerShape(7.dp),
            border = BorderStroke(2.dp, MaterialTheme.colors.secondary),
        ) {
            Column() {
                Column(
                    modifier = Modifier
                        .padding(5.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier.padding(start = 5.dp),
                            text = "Credit/Debit Card",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Transparent),
                            shape = CircleShape,
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                cursorColor = MaterialTheme.colors.primary,
                                textColor = MaterialTheme.colors.primary
                            ),
                            singleLine = true,
                            value = creditCardInput,
                            onValueChange = { text ->
                                creditCardInput = text
                            },
                            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End)
                        )
                    }
                    Divider (
                        color = MaterialTheme.colors.secondary,
                        modifier = Modifier
                            .height(1.dp)
                            .fillMaxWidth()
                    )

                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier.padding(start = 5.dp),
                            text = "Expiry Date",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Transparent),
                            shape = CircleShape,
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                cursorColor = MaterialTheme.colors.primary,
                                textColor = MaterialTheme.colors.primary
                            ),
                            singleLine = true,
                            value = expiryInput,
                            onValueChange = { text ->
                                expiryInput = text
                            },
                            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End)
                        )

                    }

                    Divider (
                        color = MaterialTheme.colors.secondary,
                        modifier = Modifier
                            .height(1.dp)
                            .fillMaxWidth()
                    )

                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            modifier = Modifier.padding(start = 5.dp),
                            text = "CVV",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Transparent),
                            shape = CircleShape,
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.Transparent,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                cursorColor = MaterialTheme.colors.primary,
                                textColor = MaterialTheme.colors.primary
                            ),
                            singleLine = true,
                            value = cvvInput,
                            onValueChange = { text ->
                                cvvInput = text
                            },
                            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.End)
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(70.dp))
    }
}

fun confirmPayment(
    payableAmount: String = "",
    court_key: String = "",
    courtName: String = "",
    courtRate: String = "",
    navController: NavController
){
    val user_id = FirebaseAuth.getInstance().currentUser?.uid
    val bookingDetails = NewBooking(court_key, user_id, Timestamp.now(), payableAmount, true);

    val db = Firebase.firestore

    // update court booked status
    db.collection("Courts")
        .document(court_key)
        .update("booked",true)

    // add booking
    db.collection("Bookings")
        .add(bookingDetails)
        .addOnSuccessListener { docRef -> navController.navigate("${Routes.PaymentSuccessPage.route}/${docRef.id}/${courtName}/${courtRate}") }
}