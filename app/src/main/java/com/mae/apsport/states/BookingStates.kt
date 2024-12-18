import com.mae.apsport.model.Booking

sealed class BookingStates {
    class Success(val data: MutableList<Booking>) : BookingStates()
    class Failure(val message: String) : BookingStates()
    object Loading : BookingStates()
    object Empty : BookingStates()
}