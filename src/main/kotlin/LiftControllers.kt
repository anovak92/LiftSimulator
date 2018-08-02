import java.util.*

abstract class LiftController:LiftObserver,OnPressedListener{
    override fun floorChanged(lift: Lift, newFloor: Int) {
        println("${lift.name} arrived at $newFloor")
    }
}

class SingleLiftController(val lift: Lift): LiftController() {

    init{
        lift.observer = this
    }

    override fun onPressed(button: LiftButton) {
        lift.move(button.floor)
    }

}

class MultipleLiftController(): LiftController(){

    val liftsArray = ArrayList<Lift>()

    fun addLift(lift: Lift){
        liftsArray.add(lift)
        lift.observer = this
    }



    override fun onPressed(button: LiftButton) {
       if(!moveFree(button.floor))
           moveRandrom(button.floor)
    }

    fun moveFree(floor:Int):Boolean{
        for(lift in liftsArray){
            if (!lift.isMooving){
                lift.move(floor)
                return true
            }
        }
        return false
    }
    fun moveRandrom(floor:Int){
        val randomLiftIndex = Random(System.currentTimeMillis())
                .nextInt(liftsArray.size)
        liftsArray[randomLiftIndex].move(floor)
    }

}