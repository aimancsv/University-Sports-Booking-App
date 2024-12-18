import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mae.apsport.model.Courts

class CourtsViewModel(key: String, type: String? = null): ViewModel()
{
    val db = Firebase.firestore
    val response: MutableState<CourtsStates> = mutableStateOf(CourtsStates.Empty)

    init {
        if(type == "listCourt"){getSingleData(key)} else {getDataByGameKey(key)}
    }

    private fun getDataByGameKey(key: String) {
        val tempGamesList = mutableListOf<Courts>()
        response.value = CourtsStates.Loading

        val firestore = FirebaseFirestore.getInstance()
        val collectionRef = firestore.collection("Courts").whereEqualTo(FieldPath.documentId(), key)

        collectionRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document in task.result!!) {
                    val i = document.toObject(Courts::class.java)
                    val dataKey = document.id
                    i.setKeyValue(dataKey)
                    tempGamesList.add(i)
                }
                Log.d("dw", tempGamesList.toString())

                response.value = CourtsStates.Success(tempGamesList)
            } else {
                response.value = CourtsStates.Failure(task.exception?.message!!)
            }
        }
    }

    private fun getSingleData(key: String) {
        val tempGamesList = mutableListOf<Courts>()
        response.value = CourtsStates.Loading

        val firestore = FirebaseFirestore.getInstance()
        val collectionRef = firestore.collection("Courts").whereEqualTo("game_key", key)

        collectionRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document in task.result!!) {
                    val i = document.toObject(Courts::class.java)
                    val dataKey = document.id
                    i.setKeyValue(dataKey)
                    tempGamesList.add(i)
                }
                Log.d("dw", tempGamesList.toString())

                response.value = CourtsStates.Success(tempGamesList)
            } else {
                response.value = CourtsStates.Failure(task.exception?.message!!)
            }
        }
    }

}