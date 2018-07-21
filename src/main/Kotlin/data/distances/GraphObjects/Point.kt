package data.distances.GraphObjects


data class Point(val x: Double, val y: Double){

    override fun toString(): String {
        return "($x , $y)"
    }

    override fun equals(other: Any?): Boolean {
        if(other is Point){
            return other.x == x && other.y == y
        }
        return false
    }
}