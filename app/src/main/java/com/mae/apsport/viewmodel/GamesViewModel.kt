import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.mae.apsport.model.Games

class GamesViewModel(key: String? = null): ViewModel()
{
    val db = Firebase.firestore
    val response: MutableState<GameStates> = mutableStateOf(GameStates.Empty)

    init {
        if(key == null){getData()} else {getSingleData(key)}
    }

    private fun getData() {
        val tempGamesList = mutableListOf<Games>()
        response.value = GameStates.Loading

        val firestore = FirebaseFirestore.getInstance()
        val collectionRef = firestore.collection("Games")

        collectionRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document in task.result!!) {
                    val i = document.toObject(Games::class.java)
                    val dataKey = document.id
                    i.setKeyValue(dataKey)
                    tempGamesList.add(i)
                }
                response.value = GameStates.Success(tempGamesList)
            } else {
                response.value = GameStates.Failure(task.exception?.message!!)
            }
        }
    }

    private fun getSingleData(key: String) {
        val tempGamesList = mutableListOf<Games>()
        response.value = GameStates.Loading

        val firestore = FirebaseFirestore.getInstance()
        val collectionRef = firestore.collection("Games").whereEqualTo(FieldPath.documentId(), key)

        collectionRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                for (document in task.result!!) {
                    val i = document.toObject(Games::class.java)
                    val dataKey = document.id
                    i.setKeyValue(dataKey)
                    tempGamesList.add(i)
                }
                response.value = GameStates.Success(tempGamesList)
            } else {
                response.value = GameStates.Failure(task.exception?.message!!)
            }
        }
    }

}