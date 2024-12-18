import com.mae.apsport.model.Games


sealed class GameStates {
    class Success(val data: MutableList<Games>) : GameStates()
    class Failure(val message: String) : GameStates()
    object Loading : GameStates()
    object Empty : GameStates()
}