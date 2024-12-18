package com.mae.apsport.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route:String,
    val title: String,
    val icon: ImageVector

) {
    object Home: BottomBarScreen("HomePage", "Home", Icons.Default.Home)
    object BookingHistory: BottomBarScreen("BookingHistoryPage", "History", Icons.Default.History)
    object Profile: BottomBarScreen("ProfilePage", "Profile", Icons.Default.Favorite)


}