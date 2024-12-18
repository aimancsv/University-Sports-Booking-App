package com.mae.apsport.widgets

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.mae.apsport.nav.Routes


@Composable
fun CustomTopAppBar(navController: NavHostController, title: String, showBackIcon : Boolean, redirectHome : Boolean = false) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = if (showBackIcon && navController.previousBackStackEntry != null) {
            {
                IconButton(onClick = { if (redirectHome) navController.navigate(Routes.HomePage.route) else navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        } else {
            null
        }
    )
}