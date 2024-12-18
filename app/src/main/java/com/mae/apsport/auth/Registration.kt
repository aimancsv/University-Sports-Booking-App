package com.mae.apsport

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.mae.apsport.auth.RegistrationViewModel
import com.mae.apsport.nav.Routes
import com.mae.apsport.ui.theme.Primary
import com.mae.apsport.ui.theme.PrimaryVariant
import com.mae.apsport.widgets.CustomButton
import com.mae.apsport.widgets.CustomOutlinedTextField
import com.mae.apsport.widgets.CustomTopAppBar


@Composable
fun Registration (navController: NavHostController, auth: FirebaseAuth, vm: RegistrationViewModel = viewModel()){


    val context = LocalContext.current
    val scrollState = rememberScrollState()


    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var username by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable{ mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var confirmPassword by rememberSaveable { mutableStateOf("") }

    var validateName by rememberSaveable { mutableStateOf(true) }
    var validateEmail by rememberSaveable { mutableStateOf(true) }
    var validateUsername by rememberSaveable { mutableStateOf(true) }
    var validatePhone by rememberSaveable { mutableStateOf(true) }
    var validatePassword by rememberSaveable { mutableStateOf(true) }
    var validateConfirmPassword by rememberSaveable { mutableStateOf(true) }
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    val validateNameError = "Plaase input a valid name"
    val validateEmailError = "Please input a valid email"
    val validateUsernameError = "Please input a valid username"
    val validatePhoneError = "Please enter a valid Malaysia phone number"
    val validatePasswordError = "Please mix uppercase and lowercase letters and a number with a minimum length of 8 "
    val validateConfirmPasswordError = "Password and confirm password must be same !!! "


    fun validateData(name: String, email:String, username:String, phone: String, password: String, confirmPassword: String): Boolean {
        val passwordRegex = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}".toRegex()
        //val passwordRegex = "(^\\d+\$).([a-zA-z]*).{8,}".toRegex()


        validateName = name.isNotBlank()
        validateEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        validateUsername = username.isNotBlank()
        validatePhone = Patterns.PHONE.matcher(phone).matches()
        validatePassword = passwordRegex.matches(password)
        validateConfirmPassword = password == confirmPassword

        return validateName && validateEmail && validateUsername && validatePhone && validatePassword && validateConfirmPassword
    }

    fun register (
        name: String,
        email: String,
        username: String,
        phone: String,
        password: String,
        confirmpassword: String
    ) {
        if (validateData(name, email, username,phone, password , confirmpassword)) {

            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener {
                    if(it.isSuccessful){
                        vm.addDataToFirebase(
                            name = name,
                            email = email,
                            username = username,
                            phone = phone,
                            context = context)
                        navController.navigate(Routes.Login.route) // testing

                    } else {
                        Toast.makeText(context, "Please review the fields", Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }

    Scaffold (
        topBar = {
            CustomTopAppBar(
                navController = navController,
                title = "User Registration",
                showBackIcon = true
            )
        }, content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                Text(
                    text = "Set Up Profile",
                    modifier = Modifier.padding(vertical = 20.dp),
                    color = Primary,
                    fontSize = 30.sp
                )

                CustomOutlinedTextField(
                    value = name,
                    onValueChangeFun = {name = it},
                    showError = !validateName,
                    labelText = stringResource(R.string.name_hint),
                    errorMessage = validateNameError,
                    leadingIconImageVector = Icons.Default.Person,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )

                CustomOutlinedTextField(
                    value = email,
                    onValueChangeFun = {email = it},
                    showError = !validateEmail,
                    labelText = stringResource(R.string.email_hint),
                    errorMessage = validateEmailError,
                    leadingIconImageVector = Icons.Default.Email,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    )
                )

                CustomOutlinedTextField(
                    value = username,
                    onValueChangeFun = {username = it},
                    showError = !validateUsername,
                    labelText = stringResource(R.string.username_hint),
                    errorMessage = validateUsernameError,
                    leadingIconImageVector = Icons.Default.Email,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    )
                )

                CustomOutlinedTextField(
                    value = phone,
                    onValueChangeFun = {phone = it},
                    showError = !validatePhone,
                    labelText = stringResource(R.string.phone_hint),
                    errorMessage = validatePhoneError,
                    leadingIconImageVector = Icons.Default.Phone,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Phone,
                        imeAction = ImeAction.Next
                    )
                )

                CustomOutlinedTextField(
                    value = password,
                    onValueChangeFun = {password = it},
                    showError = !validatePassword,
                    labelText = stringResource(id = R.string.password_hint),
                    errorMessage = validatePasswordError,
                    isPasswordField = true,
                    isPasswordVisible = isPasswordVisible,
                    onVisibilityChange = { isPasswordVisible = it},
                    leadingIconImageVector = Icons.Default.Lock,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    )
                )

                CustomOutlinedTextField(
                    value = confirmPassword,
                    onValueChangeFun = {confirmPassword = it},
                    showError = !validateConfirmPassword,
                    labelText = stringResource(id = R.string.confirmpassword_hint),
                    errorMessage = validateConfirmPasswordError,
                    isPasswordField = true,
                    isPasswordVisible = isPasswordVisible,
                    onVisibilityChange = { isPasswordVisible = it},
                    leadingIconImageVector = Icons.Default.Lock,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Next
                    )
                )

                CustomButton(
                    btnText = stringResource(id = R.string.register_button) ,
                    onClickFun = { register(name, email, username, phone, password, confirmPassword) },
                    btnColor = PrimaryVariant
                )

            }
        }
    )
}
