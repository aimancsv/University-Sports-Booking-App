package com.mae.apsport.widgets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mae.apsport.model.BookingHistory
import getTimeString


@Composable
fun HistoryCards(
//    historyy:MutableList<BookingHistory>,
    history: BookingHistory,
    onPress: () -> Unit = {}
) {
    Card(
        shape = RoundedCornerShape(1.dp),
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp)),
        backgroundColor = MaterialTheme.colors.secondary,
        elevation = 20.dp
    ) {
        Column() {
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier.padding(3.dp),
                        text = "${history.name}",
                        color = MaterialTheme.colors.onSecondary,
                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        modifier = Modifier.padding(3.dp),
                        text = "RM ${history.rate}",
                        color = MaterialTheme.colors.onSecondary,
                        fontWeight = FontWeight.Bold
                    )


                }
                Text(
                    modifier = Modifier.padding(2.dp),
                    text = "From ${getTimeString(history.start_time!!)}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    modifier = Modifier.padding( 2.dp),
                    text = "To ${getTimeString(history.end_time!!)}",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,

                    overflow = TextOverflow.Ellipsis,
                )

//                Text(
//                    modifier = Modifier
//                        .padding(top = 8.dp),
//                    text = "Tour Date : ${history.tourDate}",
//                    fontSize = 13.sp,
//                    maxLines = 3,
//                    overflow = TextOverflow.Ellipsis,
//                )
            }
        }
    }
}