import java.util.concurrent.Executors
import kotlin.properties.Delegates

class Lift(val name:String = "Lift",
           val capacity:Int = 4,
           val speed:Long = 1000,
           val maxFloor:Int){

    val liftDoor = Door()

    val minFloor:Int = 1

    val executor = Executors.newSingleThreadExecutor()

    var observer: LiftObserver? = null

    var currentFloor:Int by Delegates.observable(minFloor) {
                _, _, newValue -> observer?.floorChanged(newValue)
            }

    fun move(floor:Int){
        if(floor > maxFloor)
            throw IllegalArgumentException("Max floor = $maxFloor, asked to move $floor")
        if(floor == currentFloor)
            return

        executor.execute {
            liftDoor.close()
            lateinit var floorRange:IntProgression
            if(currentFloor - floor > 0){
                floorRange = (currentFloor - 1) downTo floor
            }else{
                floorRange = (currentFloor + 1)..floor
            }

            for (i in floorRange) {
                Thread.sleep(speed)
                currentFloor = i
            }
            liftDoor.open()
            executor.shutdown()
        }
    }

}
interface LiftObserver{
    fun floorChanged(newFloor:Int)
}