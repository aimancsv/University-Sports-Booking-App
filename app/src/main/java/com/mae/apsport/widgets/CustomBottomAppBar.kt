package com.mae.apsport.widgets

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mae.apsport.nav.BottomBarScreen
import com.mae.apsport.ui.theme.Background
import com.mae.apsport.ui.theme.Secondary


@Composable
fun CustomBottomAppBar(
    navController: NavController,
) {
    val items = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.BookingHistory,
        BottomBarScreen.Profile,



    )
    BottomNavigation(

    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon((item.icon), contentDescription = item.title) },
                label = { Text(text = item.title,
                    fontSize = 9.sp) },
                selectedContentColor = Secondary,
                unselectedContentColor = Background.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id)
                        launchSingleTop = true

                    }
                }
            )
        }
    }
}

