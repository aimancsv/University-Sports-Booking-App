import com.mae.apsport.model.Courts

sealed class CourtsStates {
    class Success(val data: MutableList<Courts>) : CourtsStates()
    class Failure(val message: String) : CourtsStates()
    object Loading : CourtsStates()
    object Empty : CourtsStates()
}