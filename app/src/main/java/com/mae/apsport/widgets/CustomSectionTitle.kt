import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SectionTitle(
    modifier: Modifier = Modifier,
    label: String
) {

    Surface() {
        Column {
            Text(
                text = label,
                fontSize = 20.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.secondary
            )
        }

    }
}

@Composable
fun NormalText(modifier: Modifier = Modifier,
                    label: String) {
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