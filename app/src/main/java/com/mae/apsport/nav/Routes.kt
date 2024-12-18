package com.mae.apsport.nav

sealed class Routes(val route: String) {

    object Splash : Routes("SplashPage")
    object Login : Routes("Login")
    object Registration : Routes("Registration")
    object HomePage : Routes("HomePage")
    object CourtPage : Routes("CourtPage")
    object BookingPage : Routes("BookingPage")
    object PaymentSuccessPage : Routes("PaymentSuccessPage")
    object BookingHistoryPage : Routes("BookingHistoryPage")
    object ProfilePage : Routes("ProfilePage")
}