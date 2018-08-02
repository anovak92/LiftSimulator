import kotlin.properties.Delegates

open class Door(val speed:Long = 500){
    var state by Delegates.observable(DoorState.CLOSED){
        _, _, newValue ->
        println("The door is ${newValue.toString().toLowerCase()}")
    }
    
    fun open(){
        Thread.sleep(speed)
        if(state!=DoorState.OPENED)
        state = DoorState.OPENED
    }
    fun close() {
        Thread.sleep(speed)
        if(state!=DoorState.CLOSED)
        state = DoorState.CLOSED
    }
    
}
enum class DoorState{
    OPENED,CLOSED
}