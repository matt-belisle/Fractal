package data.distances.GraphObjects

data class Circle(val centre: Point,val radius: Double){
    override fun toString(): String {
        return "Radius: $radius, Centre Point: $centre"
    }

}