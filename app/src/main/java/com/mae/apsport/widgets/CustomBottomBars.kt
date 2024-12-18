import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun TicketBottomBar(
    navController: NavController,
    selectedDate: String = "",
    dateSelected: Boolean = false,
    onDatePicker: () -> Unit = {},
    onBook: () -> Unit = {}
) {
    BottomAppBar(
        modifier = Modifier
            .shadow(20.dp)
            .height(70.dp)
            .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
        ,
        cutoutShape = CircleShape,
        backgroundColor = MaterialTheme.colors.primaryVariant,
        elevation = 20.dp
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        Row(
            modifier = Modifier
                .padding(horizontal = 0.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ){
            DateSelectorWidget(text = selectedDate, onPress = onDatePicker)
//            BookButtonWidget(text = "Book Court", validInputs = dateSelected, onPress = onBook)
        }
    }
}

@Composable
fun BookButtonWidget(
    text: String,
    validInputs: Boolean,
    errorText: String = "Select a date!",
    onPress: () -> Unit = {}
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(25.dp, 25.dp, 25.dp, 25.dp))
            .background(
                MaterialTheme.colors.secondary
            )
            .clickable { if(validInputs) onPress.invoke() else Toast.makeText(context, errorText, Toast.LENGTH_SHORT).show() },
        contentAlignment = Alignment.Center
    ){
        Row(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 25.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(3.dp),
                text = text,
                color = MaterialTheme.colors.onSecondary,
            )
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = Icons.Filled.ArrowForwardIos,
                contentDescription = "arrow",
                tint = MaterialTheme.colors.onSecondary,
            )
        }
    }
}

@Composable
fun DateSelectorWidget(
    text: String,
    onPress: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(25.dp))
            .background(
                MaterialTheme.colors.secondary
            )
            .clickable { onPress.invoke() },
        contentAlignment = Alignment.Center
    ){
        Row(
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 25.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(20.dp),
                imageVector = Icons.Filled.DateRange,
                contentDescription = "arrow",
                tint = MaterialTheme.colors.onSecondary,
            )
            Text(
                modifier = Modifier.padding(3.dp),
                text = text,
                color = MaterialTheme.colors.onSecondary,
            )
        }
    }
}

@Composable
fun CheckoutBottomBar(
    navController: NavController,
    onBook: () -> Unit = {}
) {

    BottomAppBar(
        modifier = Modifier
            .shadow(20.dp)
            .height(70.dp)
            .clip(RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp))
        ,
        cutoutShape = CircleShape,
        backgroundColor = MaterialTheme.colors.primaryVariant,
        elevation = 20.dp
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        Row(
            modifier = Modifier
                .padding(horizontal = 0.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ){
            BookButtonWidget(text = "Pay", validInputs = true, onPress = onBook)
        }
    }
}