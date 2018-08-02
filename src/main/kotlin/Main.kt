fun main(args:Array<String>){

//    val house = OneLiftHouse(5)
//    house.callForLift(5)
//    house.callForLift(3)

    val twoLiftHouse = TwoLiftHouse(10)
    twoLiftHouse.callForLift(4)
    twoLiftHouse.callForLift(7)
    Thread.sleep(6000)
    twoLiftHouse.callForLift(2)
    twoLiftHouse.callForLift(10)

    twoLiftHouse.shutdownLifts()

}