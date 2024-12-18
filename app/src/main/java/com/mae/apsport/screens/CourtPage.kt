import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.icu.text.SimpleDateFormat
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.Timestamp
import com.mae.apsport.model.Games
import com.mae.apsport.widgets.CustomTopAppBar
import com.mae.apsport.R
import com.mae.apsport.model.Courts
import com.mae.apsport.nav.Routes
import java.util.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CourtPage(navController: NavHostController){
    val backStackEntry by navController.currentBackStackEntryAsState()
    val gameKey = backStackEntry?.arguments?.getString("id")

    // calender logic
    val mContext = LocalContext.current
    val calender = Calendar.getInstance()
    // get current date
    val year = calender.get(Calendar.YEAR)
    val month = calender.get(Calendar.MONTH)
    val day = calender.get(Calendar.DAY_OF_MONTH)

//    val selectedDate = remember { mutableStateOf("$day/${month+1}/$year") }
    val selectedDate = remember { mutableStateOf("Select Date") }
    val dateSelected = remember { mutableStateOf(false) }

    calender.time = Date()

    val datePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            selectedDate.value = "$mDayOfMonth-${mMonth+1}-$mYear"
            if (!dateSelected.value) dateSelected.value = !dateSelected.value // changes after date chosen
        }, year, month, day
    )

    Scaffold(
        topBar = {
            CustomTopAppBar(title = "APSport", navController = navController, showBackIcon = false)
        },
        bottomBar = {
            TicketBottomBar(
                navController,
                selectedDate = selectedDate.value,
                dateSelected = dateSelected.value,
                onDatePicker = {
                    datePickerDialog.show()
                },
//                onBook = { if(selectedDate.value != "Select Date") navController.navigate("${Screen.Ticket.route}/${placeId}/${placeTitle}/${selectedDate.value}") }
            )
        }
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column() {
                if (gameKey != null) LoadCourtDetailsSection(viewModel = viewModel() {GamesViewModel(gameKey)}, navController = navController)
                if (dateSelected.value) LoadCourtTimingSection(viewModel = viewModel() {CourtsViewModel(gameKey!!, "listCourt")}, navController = navController, selectedDate.value)
            }

        }
    }
}

@Composable
fun LoadCourtDetailsSection(viewModel: GamesViewModel = viewModel(), navController: NavController){
    when (val res = viewModel.response.value) {
        is GameStates.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        is GameStates.Success -> {
            CourtDetailsSection(gameData = res.data, navController)
        }
        is GameStates.Failure -> {
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
fun CourtDetailsSection(gameData: MutableList<Games>, navController: NavController){
    Column(modifier = Modifier
        .verticalScroll(rememberScrollState())) {
        Box(Modifier.height(250.dp)) {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                ,
                contentScale = ContentScale.Crop,
                painter = rememberAsyncImagePainter(model = if (gameData[0].image_url == "") R.drawable.placeholder_view_vector else gameData[0].image_url),
                contentDescription = "image of game",
            )
        }
        Column(modifier = Modifier
            .padding(17.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            SectionTitle(label = gameData[0].name!!)
            Text(text = gameData[0].description!!)

        }
    }
}

@Composable
fun LoadCourtTimingSection(viewModel: CourtsViewModel = viewModel(), navController: NavController, selectedDate: String){
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
             CourtTimingSection(courtData = res.data, navController, selectedDate)
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
fun CourtTimingSection(courtData: MutableList<Courts>, navController: NavController, selectedDate: String){
    Column(modifier = Modifier
        .padding(17.dp)
        .fillMaxWidth()
        .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SectionTitle(label = "Courts for $selectedDate")

        for(court in courtData){
            if ((getDate(court.start_time!!) == selectedDate) && court.booked != true) SlotPicker(courtName = court.name!!, date = court.start_time!!, startTime = court.start_time!!, endTime = court.end_time!!, onPress = { navController.navigate("${Routes.BookingPage.route}/${court.key}/${court.name}/${court.rate}") })
        }

        Spacer(modifier = Modifier.height(70.dp)) // push content up
    }
}

private fun getDate(timestamp: Timestamp) : String = SimpleDateFormat("d-M-yyyy", Locale.ENGLISH).format(Date(timestamp.seconds * 1000 + timestamp.nanoseconds / 1000000))
