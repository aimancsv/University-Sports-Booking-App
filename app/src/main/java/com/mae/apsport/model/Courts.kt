package com.mae.apsport.model;

import com.google.firebase.Timestamp

data class Courts(
    var key: String? = null,
    var game_key: String? = null,
    var name: String? = null,
    var start_time: Timestamp? = null,
    var end_time: Timestamp? = null,
    var rate: Double? = null,
    var booked: Boolean? = null
){
    fun setKeyValue(dataKey: String){
        this.key = dataKey
    }
}