import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.mae.apsport.widgets.CustomTopAppBar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PaymentSuccessPage(navController: NavHostController){
    Scaffold(
        topBar = {
            CustomTopAppBar(title = "APSport", navController = navController, showBackIcon = true, redirectHome = true)
        },
    ) {
        Surface(modifier = Modifier.fillMaxSize()) {
            PaymentSuccessScreen(navController = navController)
        }
    }
}

@Composable
fun PaymentSuccessScreen(navController: NavController){
    val backStackEntry by navController.currentBackStackEntryAsState()
    val bookingKey = backStackEntry?.arguments?.getString("booking_id")
    val courtName = backStackEntry?.arguments?.getString("court_name")
    val courtRate = backStackEntry?.arguments?.getString("court_rate")
    Column(
        Modifier
            .padding(17.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        SectionTitle(label = "Payment Success")
        Card(
            shape = RoundedCornerShape(7.dp),
            border = BorderStroke(2.dp, MaterialTheme.colors.secondary),
        ) {
            Column() {
                Column(
                    modifier = Modifier
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Booking ID",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            modifier = Modifier.padding(3.dp),
                            text = "$bookingKey",
                            fontWeight = FontWeight.Normal
                        )
                    }
                    Divider (
                        color = MaterialTheme.colors.secondary,
                        modifier = Modifier
                            .height(1.dp)
                            .fillMaxWidth()
                    )

                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Court",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            modifier = Modifier.padding(3.dp),
                            text = "$courtName",
                            fontWeight = FontWeight.Normal
                        )

                    }

                    Divider (
                        color = MaterialTheme.colors.secondary,
                        modifier = Modifier
                            .height(1.dp)
                            .fillMaxWidth()
                    )

                    Row(
                        Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Amount Paid",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            modifier = Modifier.padding(3.dp),
                            text = "RM $courtRate",
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
            }
        }
    }
}