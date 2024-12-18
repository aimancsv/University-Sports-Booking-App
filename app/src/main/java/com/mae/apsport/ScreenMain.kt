package com.mae.apsport

import BookingPage
import CourtPage
import PaymentSuccessPage


import SplashPage
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.mae.apsport.nav.Routes
import com.mae.apsport.screens.BookingHistoryPage
import com.mae.apsport.screens.ProfilePage
import com.mae.apsport.viewmodel.ProfileViewModel


@Composable
fun ScreenMain(auth: FirebaseAuth) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Splash.route) {

        composable(Routes.Splash.route) {
            SplashPage(navController = navController, auth = auth)
        }

        composable(Routes.Login.route) {
            Login(navController = navController, auth = auth)
        }

        composable(Routes.Registration.route) {
            Registration(navController = navController,auth = auth)
        }

        composable(Routes.HomePage.route) {
            HomePage(navController = navController)
        }

        composable(Routes.BookingHistoryPage.route) {
            BookingHistoryPage(navController = navController)
        }

        composable(Routes.ProfilePage.route) {
            ProfilePage(navController = navController, viewModel= ProfileViewModel())
        }

        composable("${Routes.CourtPage.route}/{id}/{name}") {
            CourtPage(navController = navController)
        }

        composable("${Routes.BookingPage.route}/{court_id}/{court_name}/{court_rate}") {
            BookingPage(navController = navController)
        }

        composable("${Routes.PaymentSuccessPage.route}/{booking_id}/{court_name}/{court_rate}") {
            PaymentSuccessPage(navController = navController)
        }


    }
}
