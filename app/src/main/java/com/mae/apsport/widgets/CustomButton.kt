package com.mae.apsport.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.mae.apsport.ui.theme.Primary

@Composable
fun CustomButton(
    btnText: String,
    onClickFun: () -> Unit,
    btnColor: Color
) {
    Button(
        onClick = onClickFun,
        shape = RoundedCornerShape(60.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = btnColor)
    ) {
        Text(
            text = btnText,
            color = Primary
        )
    }
}

@Composable
fun CustomTextButton(
    btnText: String,
    onClickFun: () -> Unit,
) {
    TextButton(
        onClick = onClickFun,
    ) {
        Text(
            text = btnText,
            color = Primary
        )
    }
}

@Composable
fun IconButton(
    onClickFun: () -> Unit,
    icon: ImageVector,
) {
    Box(modifier = Modifier.padding(horizontal = 10.dp)) {
        Button(
            onClick =  onClickFun ,
            modifier = Modifier.size(80.dp),
            contentPadding = PaddingValues(1.dp)
        ) {
            // Inner content including an icon and a text label
            Icon(
                imageVector = icon,
                contentDescription = "",
                modifier = Modifier.size(50.dp)
            )
        }

    }
}

/*@Composable
fun CustomIconButton(
    btnText: String,
    onClickFun: () -> Unit,
    btnColor: Color,
) {
    Button( onClick = {onClickFun},
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 8.dp,
            disabledElevation = 0.dp
        ),
        shape = RoundedCornerShape(28.dp),
        modifier = Modifier
            .padding(5.dp),
        contentPadding = PaddingValues(15.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        Column(
            // on below line we are specifying modifier
            // and setting max height and max width
            // for our column
            modifier = Modifier
                // on below line we are
                // adding padding for our column
                .padding(5.dp),
            // on the below line we are specifying horizontal
            // and vertical alignment for our column
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(imageVector = btnIcon, contentDescription = "Icon")
            // adding spacer on below line.
            Spacer(Modifier.height(10.dp))

            // adding text on below line.
            Text(
                // specifying text as android
                "Android",

                // on below line adding style
                style = TextStyle(fontWeight = FontWeight.Bold),

                // adding text align on below line.
                textAlign = TextAlign.Center,

                // adding font size on below line.
                fontSize = 20.sp
            )

        }*/
