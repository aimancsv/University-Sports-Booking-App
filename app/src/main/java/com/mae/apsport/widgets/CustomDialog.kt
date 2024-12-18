package com.mae.apsport.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight


@Composable
fun CustomDialogClose(
    alertTitle: String,
    alertBody: String,
    onDismissFun: () -> Unit,
    btnCloseClick: () -> Unit,
    modifier: Modifier = Modifier,
    btnCloseText: String = "Close",
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismissFun,
        title = { Text(alertTitle, fontWeight = FontWeight.ExtraBold) },
        text = { Text(text = alertBody) },
        buttons = {
            Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                CustomTextButton(
                    btnText = btnCloseText,
                    onClickFun = btnCloseClick
                )
            }
        }
    )
}