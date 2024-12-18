package com.mae.apsport.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.mae.apsport.nav.Routes
import com.mae.apsport.viewmodel.ProfileViewModel
import com.mae.apsport.widgets.CustomBottomAppBar
import com.mae.apsport.widgets.CustomTopAppBar
import com.mae.apsport.widgets.UserProfileWidget



@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
fun ProfilePage(navController: NavHostController, viewModel: ProfileViewModel) {


        Scaffold(
            topBar = {
                CustomTopAppBar(title = "My Profile", navController = navController, showBackIcon = false)
            },
            bottomBar = {
                CustomBottomAppBar(navController = navController)
            }
        ) {
            Surface(modifier = Modifier.fillMaxSize()) {

                EditProfile(navController = navController,viewModel= ProfileViewModel())
            }
        }
    }

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EditProfile(navController: NavController, viewModel: ProfileViewModel){


    val getUserData = viewModel.state.value



    val context = LocalContext.current

    Scaffold(
        bottomBar = {

        },
        backgroundColor = Color(0xFFFAFAFA)
    ){
        UserProfileWidget(onLogoutPress = {
            FirebaseAuth.getInstance().signOut().run { navController.navigate(Routes.Login.route) }
        })
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Row(
                Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "About Me", style = MaterialTheme.typography.h6, fontWeight = FontWeight.Bold, color = Color(0xFFFBC02D))
            }
            Spacer(Modifier.height(10.dp))


            Spacer(modifier = Modifier.height(25.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(62.dp),
                value = getUserData.name,
                onValueChange = {
                    viewModel.updateName(it)
                },
                label = {
                    Text(
                        text = "Name",
                        color = Color.Gray,
                        fontSize = 14.sp,

                    )
                },
                textStyle = TextStyle(
                    fontSize = 14.sp,

                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.AccountBox,
                        contentDescription = "",
                        tint = Color(0xFF373073)
                    )
                },
                trailingIcon = {
                    if(getUserData.name.isNotBlank())
                        IconButton(
                            onClick = {
                                getUserData.name = ""
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Clear,
                                contentDescription = "",
                                tint = Color(0xFF373073)

                            )
                        }
                },
            )

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(62.dp),
                value = getUserData.email,
                onValueChange = {
                    viewModel.updateEmail(it)
                },
                label = {
                    Text(
                        text = "Email",
                        color = Color.Gray,
                        fontSize = 14.sp,

                    )
                },
                textStyle = TextStyle(
                    fontSize = 14.sp,

                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Done
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = "",
                        tint = Color(0xFF373073)
                    )
                },
                trailingIcon = {
                    if(getUserData.email.isNotBlank())
                        IconButton(
                            onClick = {
                                getUserData.email = ""
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Clear,
                                contentDescription = "",
                                tint = Color(0xFF373073)

                            )
                        }
                },

            )

            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(62.dp),
                value = getUserData.phone,
                onValueChange = {
                    viewModel.updatePhone(it)
                },
                label = {
                    Text(
                        text = "Contact Number",
                        color = Color.Gray,
                        fontSize = 14.sp,

                    )
                },
                textStyle = TextStyle(
                    fontSize = 14.sp,

                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Phone,
                        contentDescription = "",
                        tint = Color(0xFF373073)
                    )
                },
                trailingIcon = {
                    if(getUserData.phone.isNotBlank())
                        IconButton(
                            onClick = {
                                getUserData.phone = ""
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Clear,
                                contentDescription = "",
                                tint = Color(0xFF373073)

                            )
                        }
                },

                )


            Spacer(modifier = Modifier.height(15.dp))

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeight(62.dp),
                value = getUserData.username,
                onValueChange = {
                    viewModel.updateUsername(it)
                },
                label = {
                    Text(
                        text = "Username",
                        color = Color.Gray,
                        fontSize = 14.sp,

                    )
                },
                textStyle = TextStyle(
                    fontSize = 14.sp,

                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = "",

                    )
                },
                trailingIcon = {
                    if(getUserData.username.isNotBlank())
                        IconButton(
                            onClick = {
                                getUserData.username = ""
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Clear,
                                contentDescription = "",

                            )
                        }
                },
                colors = TextFieldDefaults.textFieldColors(
                    cursorColor = Color(0xFF373073),
                    textColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                )
            )

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = {
                    viewModel.updateUserDetails(context)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),

            ) {
                Text(
                    text = "Update Profile",
                    color = Color.White,
                    fontSize = 14.sp,

                )
            }
        }
    }

}

























//    val screenScrollState = rememberScrollState()
//    val context = LocalContext.current
//    val savingState = rememberSaveable { mutableStateOf(false) }
//
//    val currentUser = FirebaseAuth.getInstance().currentUser?
//    val db: FirebaseFirestore = FirebaseFirestore.getInstance()
//
//    Log.d("CURRENT USER", (currentUser.toString()))
//
//    val dbUsers: CollectionReference = db.collection("Users")
//
//    val email = remember { mutableStateOf("") }
//    val username = remember { mutableStateOf("") }


