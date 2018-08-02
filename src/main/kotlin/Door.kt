import kotlin.properties.Delegates

open class Door(val speed:Int = 500){
    var state by Delegates.observable(DoorState.CLOSED){
        _, _, newValue ->
        println("The door is ${newValue.toString().toLowerCase()}")
    }
    
    fun open(){
        if(state!=DoorState.OPENED)
        state = DoorState.OPENED
    }
    fun close() {
        if(state!=DoorState.CLOSED)
        state = DoorState.CLOSED
    }
    
}
enum class DoorState{
    OPENED,CLOSED
}