import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mae.apsport.model.Booking

class BookingViewModel(key: String? = null): ViewModel()
{
    val db = Firebase.firestore
    val response: MutableState<BookingStates> = mutableStateOf(BookingStates.Empty)

    init {
        if(key == null){getData()} else {getSingleData(key)}
    }

    private fun getData() {
        val tempList = mutableListOf<Booking>()
        response.value = BookingStates.Loading

        val firestore = FirebaseFirestore.getInstance()
        val collectionRef = firestore.collection("Booking")

        collectionRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document in task.result!!) {
                    val i = document.toObject(Booking::class.java)
                    val dataKey = document.id
                    i.setKeyValue(dataKey)
                    tempList.add(i)
                }
                response.value = BookingStates.Success(tempList)
                // Log.d("dw", tempGamesList.toString())
            } else {
                response.value = BookingStates.Failure(task.exception?.message!!)
            }
        }
    }

    private fun getSingleData(key: String) {
        val tempBookingList = mutableListOf<Booking>()
        response.value = BookingStates.Loading

        val firestore = FirebaseFirestore.getInstance()
        val collectionRef = firestore.collection("Booking").whereEqualTo("game_key", key)

        collectionRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document in task.result!!) {
                    val i = document.toObject(Booking::class.java)
                    val dataKey = document.id
                    i.setKeyValue(dataKey)
                    tempBookingList.add(i)
                }
                Log.d("dw", tempBookingList.toString())

                response.value = BookingStates.Success(tempBookingList)
            } else {
                response.value = BookingStates.Failure(task.exception?.message!!)
            }
        }
    }

}