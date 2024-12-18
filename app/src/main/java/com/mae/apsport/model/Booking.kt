package com.mae.apsport.model

import com.google.firebase.Timestamp

data class Booking(
    var key: String? = null,
    var court_id: String? = null,
    var user_id: String? = null,
    var date: Timestamp? = null,
    var payment_total: String? = null,
    var paid: Boolean? = null,

){
    fun setKeyValue(dataKey: String){
        this.key = dataKey
    }
}

data class NewBooking(
    var court_id: String? = null,
    var user_id: String? = null,
    var date: Timestamp? = null,
    var payment_total: String? = null,
    var paid: Boolean? = null,
)
