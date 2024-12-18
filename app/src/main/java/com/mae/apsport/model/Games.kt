package com.mae.apsport.model;

data class Games(
    var key: String? = null,
    var name: String? = null,
    var description: String? = null,
    var image_url: String? = null
){
    fun setKeyValue(dataKey: String){
        this.key = dataKey
    }
}

//data class SingleGameData(
//    var name: String? = null,
//    var description: String? = null,
//    var image_url: String? = null
//)