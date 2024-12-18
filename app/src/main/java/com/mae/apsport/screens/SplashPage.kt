import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.mae.apsport.nav.Routes

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SplashPage(navController: NavHostController, auth: FirebaseAuth){
    checkUserState(navController, auth)
    Scaffold() {
        Surface(modifier = Modifier.fillMaxSize()) {
            SplashScreen(navController = navController)
        }
    }
}

@Composable
fun SplashScreen(navController: NavController){
    Column(
        Modifier
            .padding(17.dp),
        verticalArrangement = Arrangement.Top,
    ) {
        SectionTitle(label = "Loading")
    }
}

fun checkUserState(navController: NavHostController, auth: FirebaseAuth){
    if (auth.currentUser?.uid == null){
        navController.navigate(Routes.Login.route)
    }else{
        navController.navigate(Routes.HomePage.route)
    }
}