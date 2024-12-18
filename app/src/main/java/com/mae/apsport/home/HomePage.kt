package com.mae.apsport

import GameCards
import GameStates
import GamesViewModel
//import GamesViewModel
import SectionTitle
import android.annotation.SuppressLint
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.mae.apsport.model.Games
import com.mae.apsport.nav.Routes
import com.mae.apsport.widgets.CustomBottomAppBar
import com.mae.apsport.widgets.CustomTopAppBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomePage(navController: NavHostController){
    Scaffold(
        topBar = {
            CustomTopAppBar(title = "APSport", navController = navController, showBackIcon = false)
        },
        bottomBar = {
            CustomBottomAppBar(navController = navController)
        }
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            HomeScreen(navController = navController)
        }
    }
}

@Composable
fun HomeScreen(navController: NavController){
    val screenScrollState = rememberScrollState()

    Column(
        Modifier
            .padding(17.dp)
            .verticalScroll(screenScrollState),
        verticalArrangement = Arrangement.spacedBy(17.dp),

        ) {
        
        SectionTitle(label = "Games")
        LoadGamesType(viewModel = viewModel() { GamesViewModel() },navController = navController)
    }
}

@Composable
fun LoadGamesType(viewModel: GamesViewModel = viewModel(), navController: NavController){
    when (val res = viewModel.response.value) {
        is GameStates.Loading -> {
            val scrollState = rememberScrollState()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(200.dp)
                    .padding(bottom = 60.dp)
                    .horizontalScroll(scrollState),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
//                ItemCardSkeleton()
//                ItemCardSkeleton()
//                ItemCardSkeleton()
            }
        }
        is GameStates.Success -> {
            ListOfGames(listOfPlaces = res.data, navController)
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
fun ListOfGames(listOfPlaces: MutableList<Games>, navController: NavController) {
    Column(modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        for(game in listOfPlaces){
            // game key = game id
            GameCards(game, onPress = { navController.navigate("${Routes.CourtPage.route}/${game.key}/${game.name}") })
        }
    }

}