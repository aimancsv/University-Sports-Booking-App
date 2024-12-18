import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.firebase.Timestamp
import java.util.*

@Composable
fun SlotPicker(
    courtName: String,
    date: Timestamp,
    startTime: Timestamp,
    endTime: Timestamp,
    onPress: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .background(
                MaterialTheme.colors.secondary
            )
            .clickable { onPress.invoke() },
        contentAlignment = Alignment.Center
    ){
        Column(
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 25.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(3.dp),
                text = courtName,
                color = MaterialTheme.colors.onSecondary,
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.padding(3.dp),
                text = (getDateString(startTime)),
                color = MaterialTheme.colors.onSecondary,
            )
            Text(
                modifier = Modifier.padding(3.dp),
                text = ("${getTimeString(startTime)} - ${getTimeString(endTime)}"),
                color = MaterialTheme.colors.onSecondary,
            )
        }
    }
}

fun getDateString(timestamp: Timestamp) : String = SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH).format(Date(timestamp.seconds * 1000 + timestamp.nanoseconds / 1000000))
fun getTimeString(timestamp: Timestamp) : String = SimpleDateFormat("hh:mm a", Locale.ENGLISH).format(Date(timestamp.seconds * 1000 + timestamp.nanoseconds / 1000000))