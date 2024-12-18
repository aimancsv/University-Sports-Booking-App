package com.mae.apsport.viewmodel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.mae.apsport.model.UserProfile
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class ProfileViewModel: ViewModel() {

    val state = mutableStateOf(UserProfile())

    val uid = FirebaseAuth.getInstance().currentUser?.uid

    val db = FirebaseFirestore.getInstance()

    init {
        getData()
    }
    private fun getData(){
        viewModelScope.launch {
            state.value = getUserDataFromFireStore()

        }
    }
    private suspend fun getUserDataFromFireStore(): UserProfile {

        var data = UserProfile()

        try {
            val documentSnapshot = db.collection("Users").document(uid.toString()).get().await()
            if (documentSnapshot.exists()) {
                val result = documentSnapshot.toObject(UserProfile::class.java)
                result?.let { userProfile ->
                    data = userProfile
                }
            }
        } catch (e: FirebaseFirestoreException) {
            Log.d("error", "getDataFromFireStore: $e")
        }

        return data
    }

    fun updateName(newName: String) {
        state.value = state.value.copy(name = newName)
    }

    fun updateEmail(newEmail: String) {
        state.value = state.value.copy(email = newEmail)
    }

    fun updateUsername(newUsername: String) {
        state.value = state.value.copy(username = newUsername)
    }

    fun updatePhone(newPhone: String) {
        state.value = state.value.copy(phone = newPhone)
    }




    fun updateUserDetails(context: Context) {
        uid?.let {
            db.collection("Users").document(it).set(state.value)
                .addOnSuccessListener {
                    //Log.d("ProfileViewModel", "User details updated successfully in Firestore.")
                    Toast.makeText(
                        context,
                        "Profile updated successfully!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

        }
    }
}