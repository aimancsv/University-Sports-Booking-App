import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.mae.apsport.model.Games

@Composable
fun GameCards(
    games: Games = Games("some random id","Badminton","Badminton ",  image_url = "https://upload.wikimedia.org/wikipedia/commons/3/3f/Placeholder_view_vector.svg"),
    onPress: () -> Unit = {}
) {
    Card(
        shape = RoundedCornerShape(1.dp),
        modifier = Modifier
            .height(205.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onPress.invoke() },
        backgroundColor = MaterialTheme.colors.secondary,
        elevation = 20.dp
    ) {
        Column() {
            Image(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Crop,
                painter = rememberAsyncImagePainter(model = if(games.image_url == "") R.drawable.placeholder_view_vector else games.image_url),
                contentDescription = games.name!!,
            )
            Column(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                Text(
                    modifier = Modifier.width(100.dp),
                    text = games.name!!,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}