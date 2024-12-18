package com.mae.apsport.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.firebase.auth.FirebaseAuth
import com.mae.apsport.model.BookingHistory
import com.mae.apsport.states.BookingHistoryStates
import com.mae.apsport.viewmodel.BookingHistoryViewModel
import com.mae.apsport.widgets.*

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BookingHistoryPage(navController: NavHostController){
//    val backStackEntry by navController.currentBackStackEntryAsState()


    Scaffold(
        topBar = {
            CustomTopAppBar(title = "Your Bookings", navController = navController, showBackIcon = false)
        },
        bottomBar = {
            CustomBottomAppBar(navController = navController)

        }
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            MyOrdersContent(navController)
        }
    }
}


@Composable
fun MyOrdersContent(navController: NavController){
    val backStackEntry by navController.currentBackStackEntryAsState()
    val userId = FirebaseAuth.getInstance().currentUser?.uid
    Column(
        Modifier
            .padding(17.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
    ) {
        if (userId != null) BookingCardWidget(viewModel = viewModel() { BookingHistoryViewModel(userId) },navController = navController)
        Spacer(modifier = Modifier.height(100.dp)) // important !!
    }
}


@Composable
fun BookingCardWidget(viewModel: BookingHistoryViewModel = viewModel(), navController: NavController){
    when (val res = viewModel.response.value) {
        is BookingHistoryStates.Loading -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
        is BookingHistoryStates.Success -> {
            BookingCard(listOfOrder = res.data, navController)
        }
        is BookingHistoryStates.Failure -> {
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
fun BookingCard(listOfOrder: MutableList<BookingHistory>, navController: NavController) {
    Column(modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        StyledTitle(label = "Your Bookings")
//        DescriptiveText(label = "🎟 Hope you had fun with those trips!")
        for(order in listOfOrder){
            HistoryCards(order)
        }
    }
}