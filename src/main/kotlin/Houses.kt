abstract class House(val maxFloor: Int){

    val minFloor:Int = 1
    val floorButtons = generateSequence(seed = LiftButton(minFloor),
            nextFunction = {LiftButton(it.floor + 1) }).take(maxFloor - minFloor + 1).toList()

    fun callForLift(floor:Int){
        if(floor > maxFloor || floor < minFloor)
            throw IllegalArgumentException("Wrong floor: $floor!")

        floorButtons[floor-1].press()
    }

}

class OneLiftHouse(maxFloor:Int):House(maxFloor) {

    val lift = Lift(maxFloor = maxFloor)
    val liftController = SingleLiftController(lift)

    init {
        floorButtons.onEach {
            it.onPressedListener = liftController
        }
    }
}

class TwoLiftHouse(maxFloor: Int):House(maxFloor){

    val smallLift = Lift(name = "Small lift",maxFloor = maxFloor)
    val mediumLift = Lift(name = "Medium lift",capacity = 6,
                            speed = 1200,maxFloor = maxFloor)

    val liftController = MultipleLiftController()
    init {
        liftController.addLift(smallLift)
        liftController.addLift(mediumLift)
        floorButtons.onEach {
            it.onPressedListener = liftController
        }
    }
}