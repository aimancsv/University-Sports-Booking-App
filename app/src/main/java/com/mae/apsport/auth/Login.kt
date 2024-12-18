package com.mae.apsport

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mae.apsport.nav.Routes
import com.mae.apsport.ui.theme.Primary
import com.mae.apsport.ui.theme.PrimaryVariant
import com.mae.apsport.widgets.CustomButton
import com.mae.apsport.widgets.CustomDialogClose
import com.mae.apsport.widgets.CustomOutlinedTextField
import kotlinx.coroutines.launch
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.mae.apsport.auth.LoginViewModel

@Composable
fun Login(navController: NavHostController, auth: FirebaseAuth, vm: LoginViewModel = viewModel()) {

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val scope = rememberCoroutineScope()


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize()
    ) {
        //RoadHelpLogo
        Image(
            painter = painterResource(id = R.drawable.vicky),
            contentDescription = stringResource(id = R.string.logo_desc),
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(20.dp))
        )


        CustomOutlinedTextField(
            value = vm.email,
            onValueChangeFun = {vm.email = it},
            labelText = stringResource(R.string.email_hint),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            leadingIconImageVector = Icons.Default.Email
        )

        // Password
        var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

        CustomOutlinedTextField(
            value = vm.password,
            onValueChangeFun = { vm.password = it },
            labelText = stringResource(R.string.password_hint),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isPasswordField = true,
            isPasswordVisible = isPasswordVisible,
            onVisibilityChange = { isPasswordVisible = it},
            leadingIconImageVector = Icons.Default.Lock
        )

        // LoginButton
        var showLoginError by remember { mutableStateOf(false) }
        var isLoading by remember { mutableStateOf(false) }

        CustomButton(
            btnText = stringResource(R.string.login_button),
            btnColor = PrimaryVariant,
            onClickFun = {
                scope.launch {
                    isLoading = true
                    val data = vm.logInWithEmail()
                    if(data!= null) {
                        navController.navigate(Routes.HomePage.route)
                    } else {
                        showLoginError = true
                    }
                    isLoading = false
                }
            }

        )

        // RegisterLink

        ClickableText(
            text = AnnotatedString("Don't have an account?. Sign Up"),
            modifier = Modifier.padding(20.dp),
            onClick = { navController.navigate(Routes.Registration.route) },
            style = TextStyle(
                fontSize = 14.sp,
                textDecoration = TextDecoration.Underline,
                color = Primary
            )
        )

        if (showLoginError) {
            CustomDialogClose(
                alertTitle = stringResource(id = R.string.login_error_header),
                alertBody = stringResource(id = R.string.login_error_desc),
                onDismissFun = { showLoginError = false },
                btnCloseClick = { showLoginError = false }
            )
        }
    }


}