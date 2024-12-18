package com.mae.apsport.auth

import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.mae.apsport.model.Users

class RegistrationViewModel() : ViewModel() {

    fun addDataToFirebase(
        name: String,
        email: String,
        username: String,
        phone: String,

        context: android.content.Context
    ) {
        val uid = FirebaseAuth.getInstance().currentUser?.uid;

        val db: FirebaseFirestore = FirebaseFirestore.getInstance()

        val dbUsers: CollectionReference = db.collection("Users")

        val userDetails = Users(name,email,username, phone, uid.toString())

        if (uid != null) {
            dbUsers.document(uid).set(userDetails).addOnSuccessListener {

                Toast.makeText(
                    context,
                    "Your data has been added to Firebase Firestore",
                    Toast.LENGTH_SHORT
                ).show()

            }.addOnFailureListener { e ->
                Toast.makeText(context, "Fail to add data \n$e", Toast.LENGTH_SHORT).show()
            }
        }
    }

}