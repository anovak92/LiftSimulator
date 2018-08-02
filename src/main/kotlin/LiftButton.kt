class LiftButton(val floor:Int)
{
    var onPressedListener: OnPressedListener? = null

    fun press(){
        println("Button pressed on $floor floor")
        onPressedListener?.onPressed(this)
    }

}
interface OnPressedListener{
    fun onPressed(button: LiftButton)
}

