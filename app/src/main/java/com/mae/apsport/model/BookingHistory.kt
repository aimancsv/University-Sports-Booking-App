package com.mae.apsport.model

import com.google.firebase.Timestamp

data class BookingHistory(
    var key: String? = null,
    var court_id: String? = null,
    var user_id: String? = null,
    var date: Timestamp? = null,
    var payment_total: String? = null,
    var paid: Boolean? = null,

    //courtdata
    var name: String? = null,
    var start_time: Timestamp? = null,
    var end_time: Timestamp? = null,
    var rate: String?  = null

){
    fun setKeyValue(dataKey: String){
        this.key = dataKey
    }

    fun setCourtName(dataKey: String){
        this.name = dataKey
    }

    fun setStartTime(dataKey: Timestamp){
        this.start_time = dataKey
    }


    fun setEndTime(dataKey: Timestamp){
        this.end_time = dataKey
    }


    fun setRatee(dataKey: Double){
        this.rate = dataKey.toString()
    }













}
