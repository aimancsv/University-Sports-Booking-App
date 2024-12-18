package com.mae.apsport.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Logout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun UserProfileWidget(
    onLogoutPress: () -> Unit = {},
    hideLogout: Boolean = false
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        if (!hideLogout)
            androidx.compose.material.IconButton(
                onClick = { onLogoutPress.invoke() }, modifier = Modifier.width(500.dp)) { Icon(imageVector = Icons.Filled.Logout, contentDescription = "Logout", tint = MaterialTheme.colors.secondary ) }
    }
}