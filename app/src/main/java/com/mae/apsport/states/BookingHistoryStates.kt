package com.mae.apsport.states

import com.mae.apsport.model.BookingHistory

sealed class BookingHistoryStates{
    class Success(val data: MutableList<BookingHistory>) : BookingHistoryStates()
    class Failure(val message: String) : BookingHistoryStates()
    object Loading : BookingHistoryStates()
    object Empty : BookingHistoryStates()
}