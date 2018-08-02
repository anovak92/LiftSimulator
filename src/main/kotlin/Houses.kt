abstract class House(val maxFloor: Int){

    val minFloor:Int = 1

    abstract fun callForLift(floor:Int)

}

class OneLiftHouse(maxFloor:Int):House(maxFloor) {

    val lift = Lift(maxFloor = maxFloor)
    val liftController = SingleLiftController(lift)
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

class TwoLiftHouse(maxFloor: Int):House(maxFloor){

    val smallLift = Lift(name = "Small lift",maxFloor = maxFloor)
    val mediumLift = Lift(name = "Medium lift",capacity = 6,
                            speed = 1200,maxFloor = maxFloor)


    override fun callForLift(floor: Int) {
        if(floor > maxFloor || floor < minFloor)
            throw IllegalArgumentException("Wrong floor: $floor!")

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}