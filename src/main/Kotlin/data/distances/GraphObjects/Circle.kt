package data.distances.GraphObjects

import tornadofx.*

class Circle(centre: Point, radius: Double, id:Int=0){
    var radius by property(radius)
    fun radiusProperty() = getProperty(Circle::radius)

    var centre by property(centre)
    fun centreProperty() = getProperty(Circle::centre)

    var id by property(id)
    fun idProperty() = getProperty(Point::id)
}