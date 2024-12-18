package com.mae.apsport.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun StyledTitle(modifier: Modifier = Modifier,
                label: String) {

    Surface(modifier = modifier.padding(start = 5.dp, top = 10.dp, bottom = 0.dp)) {
        Column {
            Text(
                text = label,
                fontSize = 24.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.secondary
            )
        }

    }
}

@Composable
fun DescriptiveText(modifier: Modifier = Modifier,
                    label: String) {

    Surface(modifier = modifier.padding(start = 5.dp, top = 10.dp, bottom = 25.dp)) {
        Column {
            Text(
                text = label,
                fontSize = 17.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colors.secondary
            )
        }

    }
}

@Composable
fun NormalInput(
    text: MutableState<String>,
    placeholderText: String,
    leadingIcon: ImageVector,
    onSearchClicked: () -> Unit,
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(30.dp, 30.dp, 30.dp, 30.dp)),
        value = text.value,
        onValueChange = {
            text.value = it
        },
        placeholder = {
            Text(
                modifier = Modifier
                    .alpha(ContentAlpha.medium),
                text = placeholderText,
                color = Color(0xFF83828A)
            )
        },
        textStyle = TextStyle(
            fontSize = MaterialTheme.typography.subtitle1.fontSize
        ),
        singleLine = true,
        leadingIcon = {
            Icon(
                imageVector = leadingIcon,
                contentDescription = placeholderText,
                tint = Color(0xFF83828A)
            )
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Go
        ),
        keyboardActions = KeyboardActions(
            onGo = {

            }
        ),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color(0xFFF7F7F8),
            cursorColor = MaterialTheme.colors.secondary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ))
}

@Composable
fun NormalButton(
    text: String,
    loading: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(25.dp, 25.dp, 25.dp, 25.dp))
            .background(MaterialTheme.colors.secondary)
            .clickable { onClick.invoke() },
        contentAlignment = Alignment.Center
    ){
        if(loading) CircularProgressIndicator(
            modifier = Modifier
                .padding(vertical = 13.dp, horizontal = 25.dp)
        )
        else Text(
            modifier = Modifier.padding(vertical = 15.dp, horizontal = 25.dp),
            text = text,
            color = MaterialTheme.colors.primary,
            fontWeight = FontWeight.Normal
        )
    }
}
//@Composable
//fun TopBar(
//    title: String,
//    navController: NavController,
//    isPopup: Boolean = false,
//    afterPayment: Boolean = false,
//    hideTrailingIcons: Boolean = false
//) {
//    TopAppBar(
//        modifier = Modifier
//            .background(MaterialTheme.colors.surface)
//            .padding(vertical = 10.dp),
//
//        title = {
//            Row(
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                TitleHeader(label = title)
//            }
//        },
//        navigationIcon = if (!isPopup) null else {
//            {
//                IconButton(onClick = {if(!afterPayment) navController.popBackStack() else navController.navigate(Screen.Profile.route)}) {
//                    Icon(Icons.Filled.ArrowBack, "backIcon")
//                }
//            }
//        },
//        actions = {
//            if (!hideTrailingIcons)
//                IconButton(
//                    onClick = { }) { Icon(imageVector = Icons.Filled.Notifications, contentDescription = "Notifications", tint = MaterialTheme.colors.secondary ) }
//            if (!hideTrailingIcons)
//                IconButton(
//                    onClick = { }) { Icon(imageVector = Icons.Filled.MoreHoriz, contentDescription = "More", tint = MaterialTheme.colors.secondary ) }
//        },
//        backgroundColor = MaterialTheme.colors.surface,
//        elevation = 0.dp
//    )
//
//}
