class LiftController(val lift: Lift): LiftObserver, OnPressedListener {

    init{
        lift.observer = this
    }

    override fun onPressed(button: LiftButton) {
        lift.move(button.floor)
    }

    override fun floorChanged(newFloor: Int) {
        println("Lift arrived on $newFloor")
    }

}