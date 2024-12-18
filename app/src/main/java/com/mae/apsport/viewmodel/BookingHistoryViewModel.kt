package com.mae.apsport.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.mae.apsport.model.BookingHistory
import com.mae.apsport.model.Courts
import com.mae.apsport.states.BookingHistoryStates


class BookingHistoryViewModel(key: String) : ViewModel() {

    val response: MutableState<BookingHistoryStates> = mutableStateOf(BookingHistoryStates.Empty)

    private val db = FirebaseFirestore.getInstance()

    init {
        getData(key)
    }

    private fun getData(key: String) {
        val tempOrderData = mutableListOf<BookingHistory>()

        response.value = BookingHistoryStates.Loading

        db.collection("Bookings")
            .whereEqualTo("user_id", key)
            .get()
            .addOnSuccessListener { snapshot ->
                for (document in snapshot) {
                    val i = document.toObject(BookingHistory::class.java)
                    val dataKey = document.id
                    if (dataKey != null) {
                        i?.setKeyValue(dataKey)
                    }

                    //TODO:: START -- add PUT for places here -- DONE
                    //TODO:: add ifelse for nullcheck (place_id) -- NOT YET (opt but breakable if unchanged (if no ticket id))
                    db.collection("Courts")
                        .document(i?.court_id!!)
                        .get()
                        .addOnSuccessListener { ticketDoc ->
                            val e = ticketDoc.toObject(Courts::class.java)
                            if (e != null) {
                                i?.setCourtName(e.name!!)
                                i?.setStartTime(e.start_time!!)
                                i?.setEndTime(e.end_time!!)
                                i?.setRatee(e.rate!!)
                            }
                            if (i != null )tempOrderData.add(i)
                        }
                        .addOnFailureListener { error ->
                            response.value = error.message?.let { BookingHistoryStates.Failure(it) }!!
                        }
                    //TODO:: END -- add PUT for places here
                    // if (i != null) tempOrderData.add(i)
                }
                // this gets called at the end of FL iteration
                response.value = BookingHistoryStates.Success(tempOrderData)
            }
            .addOnFailureListener { error ->
                response.value = error.message?.let { BookingHistoryStates.Failure(it) }!!
            }
    }
}