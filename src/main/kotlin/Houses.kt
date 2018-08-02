
abstract class House(val maxFloor: Int){

    val minFloor:Int = 1

    abstract fun callForLift(floor:Int)

}

class OneLiftHouse(maxFloor:Int):House(maxFloor) {

    val lift = Lift(maxFloor = maxFloor)
    val liftController = LiftController(lift)
    val floorButtons = generateSequence(seed = LiftButton(minFloor),
            nextFunction = {LiftButton(it.floor + 1) }).take(maxFloor - minFloor + 1).toList()

    init {
        floorButtons.onEach {
            it.onPressedListener = liftController
        }
        println(floorButtons.count())
    }

    override fun callForLift(floor:Int){
        if(floor > maxFloor || floor < minFloor)
            throw IllegalArgumentException("Wrong floor: $floor!")

        floorButtons[floor-1].press()
    }
}