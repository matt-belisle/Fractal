package data.distances.GraphObjects

import tornadofx.*

class Circle(centre: Point, radius: Double){
    var radius by property(radius)
    fun radiusProperty() = getProperty(Circle::radius)

    var centre by property(centre)
    fun centreProperty() = getProperty(Circle::centre)

    override fun toString(): String {
        return "Radius: $radius, Centre Point: $centre"
    }

}